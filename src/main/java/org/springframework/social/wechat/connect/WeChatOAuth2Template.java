package org.springframework.social.wechat.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author huangjinzhou
 * @date 2019/5/29 21:51
 */
@Slf4j
public class WeChatOAuth2Template extends OAuth2Template {
    /**
     * wechat application id
     */
    private String appId;

    /**
     * wechat application secret
     */
    private String appSecrect;

    /**
     * url to exchange access token with WeChat using code
     */
    private String accessTokenUrl;

    /**
     * url to refresh access token with WeChat
     */
    private String refreshTokenUrl;

    public WeChatOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl, String refreshTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        this.appId = clientId;
        this.appSecrect = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
        this.refreshTokenUrl = refreshTokenUrl;
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        StringBuilder accessTokenUrl = new StringBuilder(this.accessTokenUrl);

        accessTokenUrl.append("?appid=").append(this.appId);
        accessTokenUrl.append("&secret=").append(this.appSecrect);
        accessTokenUrl.append("&code=").append(authorizationCode);
        accessTokenUrl.append("&grant_type=authorization_code");

        return getAccessToken(accessTokenUrl);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        StringBuilder refreshTokenUrl = new StringBuilder(this.refreshTokenUrl);

        refreshTokenUrl.append("?appid=").append(this.appId);
        refreshTokenUrl.append("&grant_type=refresh_token");
        refreshTokenUrl.append("&refresh_token=").append(refreshToken);

        return getAccessToken(refreshTokenUrl);
    }


    /**
     * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
     */
    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        url = url + "&appid=" + this.appId + "&scope=snsapi_login";
        return url;
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthenticateUrl(parameters);
    }

    /**
     * 微信返回的contentType是html/text，添加相应的HttpMessageConverter来处理。
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @SuppressWarnings("unchecked")
    private AccessGrant getAccessToken(StringBuilder accessTokenRequestUrl) {

        log.info("获取access_token, 请求URL: {}", accessTokenRequestUrl.toString());
        String response = getRestTemplate().getForObject(accessTokenRequestUrl.toString(), String.class);

        log.info("获取access_token, 响应内容: " + response);

        Map<String, Object> result = null;
        try {
            result = new ObjectMapper().readValue(response, Map.class);
        } catch (Exception e) {
            log.error("获取access token 失败", e);
        }

        //返回错误码时直接返回空
        if (StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))) {
            String errCode = MapUtils.getString(result, "errcode");
            String errMsg = MapUtils.getString(result, "errmsg");
            throw new RuntimeException("获取access token失败, errCode:" + errCode + ", errMsg:" + errMsg);
        }

        return new WeChatAccessGrant(
                MapUtils.getString(result, "access_token"),
                MapUtils.getString(result, "scope"),
                MapUtils.getString(result, "refresh_token"),
                MapUtils.getLong(result, "expires_in"),
                MapUtils.getString(result, "openid"),
                MapUtils.getString(result, "unionid"));
    }
}

package org.springframework.social.wechat.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.social.wechat.api.WeChat;
import org.springframework.social.wechat.api.impl.WeChatTemplate;

/**
 * @author huangjinzhou
 * @date 2019/5/29 21:04
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

    /**
     * Create a new {@link OAuth2ServiceProvider}.
     *
     * @param appId     appId
     * @param appSecret appSecret
     */
    public WeChatServiceProvider(String appId, String appSecret) {
        super(new WeChatOAuth2Template(appId, appSecret, "https://open.weixin.qq.com/connect/qrconnect",
                "https://api.weixin.qq.com/sns/oauth2/access_token",
                "https://api.weixin.qq.com/sns/oauth2/refresh_token"));
    }

    @Override
    public WeChat getApi(String accessToken) {
        return new WeChatTemplate(accessToken);
    }
}

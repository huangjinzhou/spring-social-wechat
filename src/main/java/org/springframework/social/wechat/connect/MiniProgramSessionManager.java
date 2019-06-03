package org.springframework.social.wechat.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.wechat.connect.json.MiniProgramModule;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 小程序登陆管理
 *
 * @author huangjinzhou
 * @date 2019-06-03 10:45
 */
public class MiniProgramSessionManager {

    private String code2SessionUrl;

    /**
     * 小程序appid
     */
    private final String appId;

    /**
     * 小程序appSecret
     */
    private final String secret;

    private RestTemplate restTemplate;

    public MiniProgramSessionManager(String appId, String secret, String code2SessionUrl) {
        this.appId = appId;
        this.secret = secret;
        this.code2SessionUrl = code2SessionUrl;
        createTemplate();
    }

    /**
     * 小程序登陆后获得临时登陆凭证code，通过此code完成登陆流程，获得session相关信息
     *
     * @param code 临时登陆凭证code
     * @return {@link MiniProgramSession}
     */
    public MiniProgramSession code2Session(String code) {
        Map<String, String> variables = new HashMap<>(4);
        variables.put("appid", appId);
        variables.put("secret", secret);
        variables.put("js_code", code);
        variables.put("grant_type", "authorization_code");
        return this.restTemplate.getForObject(code2SessionUrl, MiniProgramSession.class, variables);
    }

    private void createTemplate() {
        this.restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new MiniProgramModule());
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_XML, new MediaType("application", "*+json")));
        this.restTemplate.setMessageConverters(Collections.singletonList(converter));
    }
}

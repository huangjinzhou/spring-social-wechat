package org.springframework.social.wechat.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.wechat.api.UserOperations;
import org.springframework.social.wechat.api.WeChat;
import org.springframework.social.wechat.api.impl.json.WeChatModule;

/**
 * <p>
 * The central class for interacting with WeChat.
 * </p>
 *
 * @author huangjinzhou
 * @date 2019-05-29 16:36
 */
public class WeChatTemplate extends AbstractOAuth2ApiBinding implements WeChat {

    private UserOperations userOperations;

    public WeChatTemplate(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        initSubApis();
    }

    @Override
    public UserOperations userOperations() {
        return this.userOperations;
    }

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new WeChatModule());
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    private void initSubApis() {
        this.userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
    }
}

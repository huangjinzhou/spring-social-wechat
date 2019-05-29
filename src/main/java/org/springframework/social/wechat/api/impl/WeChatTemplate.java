package org.springframework.social.wechat.api.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.wechat.api.UserOperations;
import org.springframework.social.wechat.api.WeChat;

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
        super(accessToken);
        initSubApis();
    }

    @Override
    public UserOperations userOperations() {
        return this.userOperations;
    }

    private void initSubApis() {
        this.userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
    }
}

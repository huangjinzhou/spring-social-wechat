package org.springframework.social.wechat.api.impl;

import org.springframework.social.MissingAuthorizationException;

/**
 * Based on <code>AbstractGitHubOperations</code>, by Willie Wheeler (willie.wheeler@gmail.com).
 *
 * @author huangjinzhou
 * @date 2019-05-29 16:31
 */
class AbstractWeChatOperations {

    private final boolean isAuthorized;

    public AbstractWeChatOperations(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    protected void requireAuthorization() {
        if (!isAuthorized) {
            throw new MissingAuthorizationException("WeChat");
        }
    }

    protected String buildUri(String path) {
        return API_URL_BASE + path;
    }

    /**
     * WeChat API base
     */
    private static final String API_URL_BASE = "https://api.weixin.qq.com";
}
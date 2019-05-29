package org.springframework.social.wechat.api;

/**
 * Interface defining the operations for working with WeChat users.
 *
 * @author huangjinzhou
 * @date 2019-05-29 14:52
 */
public interface UserOperations {

    /**
     * Retrieves the user's WeChat profile details.
     *
     * @param openId 普通用户的标识
     * @return the user's WeChat profile
     */
    WeChatUserProfile getUserProfile(String openId);
}

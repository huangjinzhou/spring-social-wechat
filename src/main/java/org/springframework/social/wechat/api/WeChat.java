package org.springframework.social.wechat.api;

import org.springframework.social.ApiBinding;

/**
 * Interface specifying a basic set of operations for interacting with WeChat.
 *
 * @author huangjinzhou
 * @date 2019-05-29 14:48
 */
public interface WeChat extends ApiBinding {

    /**
     * Returns the portion of the WeChat API containing the user operations.
     *
     * @return user operations
     */
    UserOperations userOperations();
}

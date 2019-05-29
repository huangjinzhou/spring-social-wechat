package org.springframework.social.wechat.api.impl;

import org.springframework.social.wechat.api.UserOperations;
import org.springframework.social.wechat.api.WeChatUserProfile;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * <p>
 * User template implementation.
 * </p>
 *
 * @author huangjinzhou
 * @date 2019-05-29 16:35
 */
public class UserTemplate extends AbstractWeChatOperations implements UserOperations {

    private final RestTemplate restTemplate;

    public UserTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.restTemplate = restTemplate;
    }

    @Override
    public WeChatUserProfile getUserProfile(String openId) {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("/sns/userinfo"),
                WeChatUserProfile.class, Collections.singletonMap("openid", openId));
    }


}

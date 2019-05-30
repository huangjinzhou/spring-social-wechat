package org.springframework.social.wechat.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.wechat.api.WeChat;
import org.springframework.social.wechat.api.WeChatUserProfile;

/**
 * WeChat ApiAdapter implementation
 *
 * @author huangjinzhou
 * @date 2019-05-30 10:13
 */
public class WeChatApiAdapter implements ApiAdapter<WeChat> {

    private final String openId;

    public WeChatApiAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeChat api) {
        try {
            api.userOperations().getUserProfile(openId);
            return true;
        } catch (Exception ignored) {
        }

        return false;
    }

    @Override
    public void setConnectionValues(WeChat api, ConnectionValues values) {
        WeChatUserProfile userProfile = api.userOperations().getUserProfile(openId);
        if (userProfile == null) {
            return;
        }

        values.setDisplayName(userProfile.getNickname());
        values.setProviderUserId(userProfile.getOpenId());
        values.setImageUrl(userProfile.getHeadImageUrl());
        values.setProfileUrl(userProfile.getHeadImageUrl());
    }

    @Override
    public UserProfile fetchUserProfile(WeChat api) {
        WeChatUserProfile userProfile = api.userOperations().getUserProfile(openId);

        return new UserProfileBuilder()
                .setName(userProfile.getNickname())
                .setId(userProfile.getOpenId())
                .setUsername(userProfile.getNickname())
                .setFirstName(userProfile.getNickname())
                .build();
    }

    @Override
    public void updateStatus(WeChat api, String message) {
    }
}

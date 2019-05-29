package org.springframework.social.wechat.api.impl;

import org.springframework.http.MediaType;
import org.springframework.social.wechat.api.WeChatUserProfile;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class UserTemplateTest extends AbstractWeChatApiTest {

    @org.junit.Test
    public void getUserProfile() {
        mockServer.expect(requestTo("https://api.weixin.qq.com/sns/userinfo")).andExpect(method(GET))
                .andExpect(header("Authorization", "Bearer ACCESS_TOKEN"))
                .andRespond(withSuccess(jsonResource("profile"), MediaType.APPLICATION_JSON));
        WeChatUserProfile profile = weChat.userOperations().getUserProfile("OPENID");
        assertEquals("OPENID", profile.getOpenid());
        assertEquals("NICKNAME", profile.getNickname());
        assertEquals("PROVINCE", profile.getProvince());
        assertEquals("COUNTRY", profile.getCountry());
        assertEquals("CITY", profile.getCity());
    }
}
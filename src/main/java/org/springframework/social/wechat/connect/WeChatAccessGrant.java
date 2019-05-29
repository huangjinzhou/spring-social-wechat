package org.springframework.social.wechat.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author huangjinzhou
 * @date 2019/5/29 22:24
 */
public class WeChatAccessGrant extends AccessGrant {

    private String openId;

    private String unionId;

    public WeChatAccessGrant(String accessToken, String openId, String unionId) {
        this(accessToken, null, null, null, openId, unionId);
    }

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, String openId, String unionId) {
        super(accessToken, scope, refreshToken, expiresIn);
        this.openId = openId;
        this.unionId = unionId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}

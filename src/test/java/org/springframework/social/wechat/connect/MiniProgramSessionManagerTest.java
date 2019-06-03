package org.springframework.social.wechat.connect;

import org.junit.Test;

public class MiniProgramSessionManagerTest {

    @Test
    public void code2Session() {
        String appid = "appid";
        String secret = "appSerect";
        MiniProgramSessionManager sessionManager = new MiniProgramSessionManager(appid, secret,
                "https://api.weixin.qq.com/sns/jscode2session");
        MiniProgramSession session = sessionManager.code2Session("code");
        System.out.println(session.getErrCode());
        System.out.println(session.getErrMsg());
    }
}
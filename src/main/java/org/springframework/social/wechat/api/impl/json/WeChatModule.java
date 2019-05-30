package org.springframework.social.wechat.api.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.wechat.api.WeChatUserProfile;

/**
 * Jackson module for setting up mixin annotations on WeChat model types.
 *
 * @author huangjinzhou
 * @date 2019-05-30 11:41
 */
public class WeChatModule extends SimpleModule {
    private static final long serialVersionUID = 582209124795568854L;

    public WeChatModule() {
        super("WeChatModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(WeChatUserProfile.class, WeChatUserProfileMixin.class);
    }
}

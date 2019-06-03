package org.springframework.social.wechat.connect.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.wechat.connect.MiniProgramSession;

/**
 * Jackson module for setting up mixin annotations on WeChat Mini Program model types.
 *
 * @author huangjinzhou
 * @date 2019-06-03 10:36
 */
public class MiniProgramModule extends SimpleModule {
    public MiniProgramModule() {
        super("MiniProgramModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(MiniProgramSession.class, MiniProgramSessionMixin.class);
    }
}

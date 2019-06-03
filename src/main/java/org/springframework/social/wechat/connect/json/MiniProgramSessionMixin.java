package org.springframework.social.wechat.connect.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.wechat.connect.MiniProgramSession;

/**
 * Annotated mixin to add annotations to {@link  MiniProgramSession}
 *
 * @author huangjinzhou
 * @date 2019-06-03 10:46
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class MiniProgramSessionMixin {
    MiniProgramSessionMixin(@JsonProperty("openid") String openId,
                            @JsonProperty("session_key") String sessionKey,
                            @JsonProperty("unionid") String unionId,
                            @JsonProperty("errcode") Integer errCode,
                            @JsonProperty("errmsg") String errMsg) {

    }
}

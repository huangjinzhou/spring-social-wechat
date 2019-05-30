package org.springframework.social.wechat.api.impl.json;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.wechat.api.WeChatUserProfile;

import java.util.List;

/**
 * Annotated mixin to add annotations to {@link WeChatUserProfile}
 *
 * @author huangjinzhou
 * @date 2019-05-30 11:31
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class WeChatUserProfileMixin {
    WeChatUserProfileMixin(@JsonProperty("openid") String openId,
                           @JsonProperty("nickname") String nickname,
                           @JsonProperty("sex") Integer sex,
                           @JsonProperty("province") String province,
                           @JsonProperty("city") String city,
                           @JsonProperty("country") String country,
                           @JsonProperty("headimgurl") String headImageUrl,
                           @JsonProperty("privilege") List<String> privilege,
                           @JsonProperty("unionid") String unionId) {
    }
}

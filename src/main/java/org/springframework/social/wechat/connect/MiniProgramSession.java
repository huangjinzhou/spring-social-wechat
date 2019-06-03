package org.springframework.social.wechat.connect;

/**
 * 小程序登陆后的session
 *
 * @author huangjinzhou
 * @date 2019-06-03 10:08
 */
public class MiniProgramSession {
    /**
     * 用户唯一标识(openid)
     */
    private String openId;

    /**
     * 会话密钥(session_key)
     */
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。(unionid)
     */
    private String unionId;

    /**
     * 错误码(errcode)
     */
    private Integer errCode;

    /**
     * 错误信息(errmsg)
     */
    private String errMsg;

    public MiniProgramSession(String openId, String sessionKey, String unionId, Integer errCode, String errMsg) {
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.unionId = unionId;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public enum MiniProgramSessionExchangeErrorCodes {
        /**
         * 系统繁忙，此时请开发者稍候再试
         */
        SYSTEM_BUSY(-1, "系统繁忙"),

        /**
         * 请求成功
         */
        SUCCESS(0, "请求成功"),

        /**
         * code 无效
         */
        CODE_INVALID(40029, "code 无效"),

        /**
         * 频率限制，每个用户每分钟100次
         */
        REQUEST_LIMIT(45011, "频率限制，每个用户每分钟100次"),
        ;

        private final int code;

        private final String msg;

        MiniProgramSessionExchangeErrorCodes(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}

package org.springframework.social.wechat.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.social.wechat.api.WeChat;

/**
 * WeChat ConnectionFactory Implementation
 *
 * @author huangjinzhou
 * @date 2019-05-30 10:48
 */
public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param appId     the application id in WeChat of the application.
     * @param appSecret the application secret in WeChat of the application.
     */
    public WeChatConnectionFactory(String appId, String appSecret) {
        super("wechat", new WeChatServiceProvider(appId, appSecret), null);
    }

    /**
     * Create a OAuth2-based {@link Connection} from the {@link AccessGrant} returned after {@link #getOAuthOperations() completing the OAuth2 flow}.
     *
     * @param accessGrant the access grant
     * @return the new service provider connection
     * @see OAuth2Operations#exchangeForAccess(String, String, org.springframework.util.MultiValueMap)
     */
    @Override
    public Connection<WeChat> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<>(getProviderId(),
                extractProviderUserId(accessGrant),
                accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(),
                accessGrant.getExpireTime(),
                (OAuth2ServiceProvider<WeChat>) getServiceProvider(),
                new WeChatApiAdapter(((WeChatAccessGrant) accessGrant).getOpenId()));
    }

    /**
     * Create a OAuth2-based {@link Connection} from the connection data.
     *
     * @param data connection data from which to create the connection
     */
    @Override
    public Connection<WeChat> createConnection(ConnectionData data) {
        return new OAuth2Connection<>(data, (OAuth2ServiceProvider<WeChat>) getServiceProvider(),
                new WeChatApiAdapter(data.getProviderUserId()));
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        return ((WeChatAccessGrant) accessGrant).getOpenId();
    }
}

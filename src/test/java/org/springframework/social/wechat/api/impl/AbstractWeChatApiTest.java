package org.springframework.social.wechat.api.impl;

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 * <p>
 * Based on <code>AbstractGitHubApiTest</code>.
 * </p>
 *
 * @author huangjinzhou
 */
public abstract class AbstractWeChatApiTest {
    protected WeChatTemplate weChat;
    protected MockRestServiceServer mockServer;
    protected HttpHeaders responseHeaders;

    @Before
    public void setup() {
        this.weChat = new WeChatTemplate("ACCESS_TOKEN");
        this.mockServer = MockRestServiceServer.createServer(weChat.getRestTemplate());

        this.responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    protected Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json", getClass());
    }
}
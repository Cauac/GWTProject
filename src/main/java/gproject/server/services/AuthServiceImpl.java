package gproject.server.services;

import gproject.client.service.AuthService;
import gproject.server.controller.TwitterAuthController;
import gproject.shared.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Service;

@Service("authService")
@Scope("singleton")
public class AuthServiceImpl implements AuthService {

    @Override
    public UserInfo getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(auth.getName());
        userInfo.setRole(auth.getAuthorities().iterator().next().getAuthority());
        return userInfo;
    }

    @Autowired
    private TwitterConnectionFactory connectionFactory;

    @Autowired
    private TwitterAuthController controller;

    private static OAuthToken requestToken;

    @Override
    public String getTwitterAuthUrl() {
        OAuth1Operations oAuthOperations = connectionFactory.getOAuthOperations();
        requestToken = oAuthOperations.fetchRequestToken("http://localhost:8080/servlet/tw/getAccessToken", null);
        return oAuthOperations.buildAuthorizeUrl(requestToken.getValue(), OAuth1Parameters.NONE);
    }

    public OAuthToken getRequestToken() {
        return requestToken;
    }
}

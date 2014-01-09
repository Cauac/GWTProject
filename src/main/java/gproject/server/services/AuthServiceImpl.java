package gproject.server.services;

import gproject.client.service.AuthService;
import gproject.shared.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {
    @Override
    public UserInfo getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(auth.getName());
        userInfo.setRole(auth.getAuthorities().iterator().next().getAuthority());
        return userInfo;
    }
}

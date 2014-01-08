package gproject.server.services.gwt;

import gproject.client.service.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service("authService")
public class AuthServerImpl implements AuthService {

    @Override
    public boolean isUser() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority().equals("ROLE_USER");
    }
}

package gproject.server.auth;

import com.mongodb.DBObject;
import gproject.client.service.AuthService;
import gproject.server.dao.MongoUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("authService")
public class AuthServerImpl implements AuthService {

    @Autowired
    MongoUserDAO userDAO;

    @Override
    public String login(String login, String password) {
        DBObject user = userDAO.read(login);
        if (user == null) {
            throw new BadCredentialsException("user not found");
        }
        if (user.get("password").equals(password)) {
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            User secureUser=new User(login,password,true,true,true,true,authorities);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(secureUser, null);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return "1";
    }

    @Override
    public String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }
}

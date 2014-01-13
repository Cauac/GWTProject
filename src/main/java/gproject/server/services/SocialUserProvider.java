package gproject.server.services;


import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class SocialUserProvider {

    @Autowired
    UserService userService;

    public Authentication getUserByServiceProfileId(String serviceName, String id) throws AuthenticationException {
        DBObject dbUser = userService.getUserByServiceProfileId(serviceName, id);

        if (dbUser == null) {
            throw new UsernameNotFoundException("user with service profile id  " + id + " not found!");
        }

        Collection<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
        if (dbUser.containsField("isAdmin")) {
            userAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            userAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        User user = new User(dbUser.get("_id").toString(), dbUser.get("password").toString(), true, true, true, true, userAuthorities);
        return new UsernamePasswordAuthenticationToken(user, dbUser.get("password"), userAuthorities);
    }
}

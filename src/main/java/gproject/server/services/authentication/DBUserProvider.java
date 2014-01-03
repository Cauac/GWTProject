package gproject.server.services.authentication;

import com.mongodb.DBObject;
import gproject.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class DBUserProvider implements UserDetailsService {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    private ThreadLocal<User> currentUser = new ThreadLocal<User>();

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws AuthenticationException {
        Collection<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
        userAuthorities.add(new SimpleGrantedAuthority(ROLE_USER));

        DBObject dbUser = userService.getUser(userName);

        if (dbUser == null) {
            throw new UsernameNotFoundException("Username " + userName + " not found!");
        }
        if (dbUser.containsField("isAdmin")) {
            userAuthorities.clear();
            userAuthorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
        }
        User user = new User(dbUser.get("_id").toString(), dbUser.get("password").toString(), true, true, true, true, userAuthorities);
        currentUser.set(user);
        return user;
    }
}

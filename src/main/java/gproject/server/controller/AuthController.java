package gproject.server.controller;

import com.mongodb.BasicDBObject;
import gproject.server.dao.MongoUserDAO;
import gproject.server.services.FlickrService;
import gproject.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    FlickrService flickrService;

    @Autowired
    MongoUserDAO userDAO;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getFlickrAuthUrl", method = RequestMethod.GET)
    public
    @ResponseBody
    String getFlickAuthUrl() {
        flickrService.init();
        return flickrService.getAuthorizationUrl();
    }

    @RequestMapping(value = "/getFlickrAccessToken", method = RequestMethod.GET)
    public ModelAndView getFlickrAccessToken(String oauth_token, String oauth_verifier, String user) {
        Map<String, String> token = flickrService.getAccessToken(oauth_verifier);
        userService.saveToken(user, token, "flickr");
        return new ModelAndView("redirect:/#account");
    }

    @RequestMapping(value = "/getFlickrTokenForCurrentUser", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getFlickrTokenForCurrentUser(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map userTokensInfo = userService.getUserTokensInfo(auth.getName());
        if (userTokensInfo == null) {
            return new BasicDBObject();
        }
        return userTokensInfo;
    }
}
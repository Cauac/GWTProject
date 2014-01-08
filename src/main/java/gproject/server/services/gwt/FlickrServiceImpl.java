package gproject.server.services.gwt;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import gproject.client.service.FlickrService;
import gproject.server.dao.MongoFlickrDAO;
import gproject.server.services.UserService;
import gproject.shared.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("flickrService")
public class FlickrServiceImpl implements FlickrService {

    @Autowired
    UserService userService;
    @Autowired
    MongoFlickrDAO flickrDAO;

    @Override
    public Photo[] getPhotos() {
        Photo[] result = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String flickrUserId = userService.getFlickrUserId(auth.getName());
        if (flickrUserId != null) {
            BasicDBList photosList = flickrDAO.readPhotosFromActivity(flickrUserId, 10, 1);
            result = new Photo[photosList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = Photo.parse(((DBObject) photosList.get(i)).toMap());
            }
        }

        return result;
    }
}

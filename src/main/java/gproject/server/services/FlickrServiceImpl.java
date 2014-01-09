package gproject.server.services;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import gproject.client.service.FlickrService;
import gproject.server.dao.MongoFlickrDAO;
import gproject.shared.Comment;
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
    public int getPhotoCount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String flickrUserId = userService.getFlickrUserId(auth.getName());
        if (flickrUserId != null) {
            return (int) flickrDAO.getUserPhotoCount(flickrUserId);
        }
        return 0;
    }

    @Override
    public Photo[] getPhotos(int start, int length) {
        Photo[] result = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String flickrUserId = userService.getFlickrUserId(auth.getName());
        if (flickrUserId != null) {
            BasicDBList photosList = flickrDAO.readPhotosFromActivity(start, length, flickrUserId);
            result = new Photo[photosList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = Photo.parse(((DBObject) photosList.get(i)).toMap());
            }
        }
        return result;
    }

    @Override
    public int getCommentCount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String flickrUserId = userService.getFlickrUserId(auth.getName());
        if (flickrUserId != null) {
            return (int) flickrDAO.getUserCommentsCount(flickrUserId);
        }
        return 0;
    }

    @Override
    public Comment[] getComments(int start, int length) {
        Comment[] result = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String flickrUserId = userService.getFlickrUserId(auth.getName());
        if (flickrUserId != null) {
            BasicDBList commentsList = flickrDAO.readCommentsFromActivity(start, length, flickrUserId);
            flickrDAO.insertCommentDependencies(commentsList);
            result = new Comment[commentsList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = Comment.parseMap(((DBObject) commentsList.get(i)).toMap());
            }
        }
        return result;
    }
}

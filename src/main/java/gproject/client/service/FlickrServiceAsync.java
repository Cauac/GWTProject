package gproject.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import gproject.shared.Comment;
import gproject.shared.Photo;

public interface FlickrServiceAsync {

    void getAuthUrl(AsyncCallback<String> callback);

    void hasFlickrToken(AsyncCallback<Boolean> callback);

    void getPhotoCount(AsyncCallback<Integer> callback);

    void getPhotos(int start, int length, AsyncCallback<Photo[]> callback);

    void getCommentCount(AsyncCallback<Integer> callback);

    void getComments(int start, int length, AsyncCallback<Comment[]> callback);

    void getCommentsByPhoto(String photoId, AsyncCallback<String> callback);
}
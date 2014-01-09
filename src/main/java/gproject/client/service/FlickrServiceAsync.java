package gproject.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import gproject.shared.Photo;

public interface FlickrServiceAsync {

    void getPhotoCount(AsyncCallback<Integer> callback);

    void getPhotos(int start, int length, AsyncCallback<Photo[]> callback);
}
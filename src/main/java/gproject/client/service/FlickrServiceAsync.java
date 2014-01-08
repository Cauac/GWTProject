package gproject.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import gproject.shared.Photo;

public interface FlickrServiceAsync {

    void getPhotos(AsyncCallback<Photo[]> callback);
}

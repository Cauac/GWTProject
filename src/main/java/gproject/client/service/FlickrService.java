package gproject.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import gproject.shared.Photo;

@RemoteServiceRelativePath("springGwtServices/flickrService")
public interface FlickrService extends RemoteService {

    public Photo[] getPhotos();
}

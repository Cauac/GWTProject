package gproject.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import gproject.client.service.FlickrService;
import gproject.client.service.FlickrServiceAsync;
import gproject.client.view.Display;
import gproject.client.view.PhotoView;
import gproject.shared.Photo;

public class PhotoPresenter implements Presenter {

    private FlickrServiceAsync flickrService = GWT.create(FlickrService.class);
    private final HandlerManager eventBus;
    private final PhotoView display;

    public PhotoPresenter(HandlerManager eventBus, PhotoView view) {
        this.eventBus = eventBus;
        this.display = view;
    }

    public void bind() {

    }

    @Override
    public void go(HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
        fetchPhoto();
    }

    public void fetchPhoto() {
        flickrService.getPhotos(new AsyncCallback<Photo[]>() {
            @Override
            public void onFailure(Throwable throwable) {
                return;
            }

            @Override
            public void onSuccess(Photo[] photos) {
                display.showPhoto(photos);
            }
        });
    }
}

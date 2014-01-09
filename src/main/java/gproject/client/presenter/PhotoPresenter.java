package gproject.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import gproject.client.AppContext;
import gproject.client.event.ShowPhotoDialog;
import gproject.client.event.ShowPhotoDialogEventHandler;
import gproject.client.service.FlickrService;
import gproject.client.service.FlickrServiceAsync;
import gproject.client.view.DialogView;
import gproject.client.view.PhotoView;
import gproject.shared.Photo;

public class PhotoPresenter implements Presenter {

    private FlickrServiceAsync flickrService = GWT.create(FlickrService.class);
    private final PhotoView display;

    public PhotoPresenter(PhotoView view) {
        this.display = view;
    }

    public void bind() {
        HandlerManager eventBus = AppContext.getInstance().getEventBus();
        eventBus.addHandler(ShowPhotoDialog.TYPE, new ShowPhotoDialogEventHandler() {
            @Override
            public void onShow(ShowPhotoDialog event) {
                DialogView.getInstance().showPhoto(event.getPhoto());
            }
        });
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

package gproject.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.Range;
import gproject.client.AppContext;
import gproject.client.event.ShowPhotoDialog;
import gproject.client.event.ShowPhotoDialogEventHandler;
import gproject.client.event.ShowPhotoRange;
import gproject.client.event.ShowPhotoRangeHandler;
import gproject.client.service.FlickrService;
import gproject.client.service.FlickrServiceAsync;
import gproject.client.view.PhotoDialogView;
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
            public void onShow(final ShowPhotoDialog event) {
                flickrService.getCommentsByPhoto(event.getPhoto().getId(), new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        return;
                    }

                    @Override
                    public void onSuccess(String result) {
                        PhotoDialogView.getInstance().showPhoto(event.getPhoto(), result);
                    }
                });
            }
        });
        eventBus.addHandler(ShowPhotoRange.TYPE, new ShowPhotoRangeHandler() {
            @Override
            public void show(ShowPhotoRange event) {
                fetchPhoto(event.getRange());
            }
        });
    }

    @Override
    public void go(HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
        fetchPhotoCount();
    }

    public void fetchPhotoCount() {
        flickrService.getPhotoCount(new AsyncCallback<Integer>() {
            @Override
            public void onFailure(Throwable throwable) {
                return;
            }

            @Override
            public void onSuccess(Integer photoCount) {
                display.setPhotoCount(photoCount);
            }
        });
    }

    public void fetchPhoto(final Range range) {
        flickrService.getPhotos(range.getStart(), range.getLength(), new AsyncCallback<Photo[]>() {
            @Override
            public void onFailure(Throwable caught) {
                return;
            }

            @Override
            public void onSuccess(Photo[] result) {
                display.setPhotoRange(range.getStart(), result);
            }
        });
    }
}

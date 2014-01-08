package gproject.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import gproject.client.service.FlickrService;
import gproject.client.service.FlickrServiceAsync;
import gproject.shared.Photo;

public class PhotoPage extends Composite {

    interface PhotoPageUiBinder extends UiBinder<Widget, PhotoPage> {
    }

    static private PhotoPage _instance = null;

    private static PhotoPageUiBinder uiBinder = GWT.create(PhotoPageUiBinder.class);
    private FlickrServiceAsync flickrService = GWT.create(FlickrService.class);

    public static PhotoPage getInstance() {
        if (null == _instance) {
            _instance = new PhotoPage();
        }
        return _instance;
    }

    @UiField
    FlexTable table;

    public PhotoPage() {
        initWidget(uiBinder.createAndBindUi(this));
        initPhotoTable();
    }

    void initPhotoTable() {
        flickrService.getPhotos(new AsyncCallback<Photo[]>() {
            @Override
            public void onFailure(Throwable throwable) {
                return;
            }

            @Override
            public void onSuccess(Photo[] photos) {
                String[] header = {"Photo", "Title", "Ownername", "Views", "Date Upload", "Date Update"};
                for (int i = 0; i < header.length; i++) {
                    table.setWidget(0, i, new Label(header[i]));
                }

                for (int i = 0; i < photos.length; i++) {
                    table.setWidget(i + 1, 0, getImage(photos[i]));
                    table.setWidget(i + 1, 1, new Label(photos[i].getTitle()));
                    table.setWidget(i + 1, 2, new Label(photos[i].getOwnerName()));
                    table.setWidget(i + 1, 3, new Label(photos[i].getViews()));
                    DateLabel dateUpload = new DateLabel(DateTimeFormat.getFormat("dd-MM-yyyy"));
                    dateUpload.setValue(photos[i].getDateupload());
                    DateLabel dateUpdate = new DateLabel(DateTimeFormat.getFormat("dd-MM-yyyy"));
                    dateUpdate.setValue(photos[i].getLastupdate());
                    table.setWidget(i + 1, 4, dateUpload);
                    table.setWidget(i + 1, 5, dateUpdate);
                }
            }
        });
    }


    private Image getImage(Photo photo) {
        Image image = new Image(photo.getUrl());
        image.addClickHandler(new PhotoClickHandler(photo));
        return image;
    }

    class PhotoClickHandler implements ClickHandler {

        private String id;
        private String url;

        PhotoClickHandler(Photo photo) {
            id = photo.getId();
            url = photo.getLargeSizeUrl();
        }

        @Override
        public void onClick(ClickEvent event) {
            PhotoDialogBox dialogBox = new PhotoDialogBox(url);
            dialogBox.center();
            dialogBox.show();
        }
    }
}

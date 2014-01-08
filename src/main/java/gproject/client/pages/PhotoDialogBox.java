package gproject.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import gproject.client.service.FlickrService;
import gproject.client.service.FlickrServiceAsync;

public class PhotoDialogBox extends DialogBox {

    private FlickrServiceAsync flickrService = GWT.create(FlickrService.class);
    private PhotoDialogBox instance = this;

    public PhotoDialogBox(String url) {
        setGlassEnabled(true);
        setAnimationEnabled(true);
        setText("Photo");
        VerticalPanel content = new VerticalPanel();
        add(content);
        content.add(new Image(url));
        content.add(new Button("Close", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                instance.hide();
            }
        }));
    }
}

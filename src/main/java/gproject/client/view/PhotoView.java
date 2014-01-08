package gproject.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PhotoView extends Composite implements Display {

    @UiTemplate("template/PhotoView.ui.xml")
    interface PhotoViewUiBinder extends UiBinder<Widget, PhotoView> {}

    private static PhotoViewUiBinder uiBinder = GWT.create(PhotoViewUiBinder.class);

    static private PhotoView instance = null;

    public static PhotoView getInstance() {
        if (null == instance) {
            instance = new PhotoView();
        }
        return instance;
    }

    public PhotoView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}

package gproject.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PhotoPage extends Composite {

    interface PhotoPageUiBinder extends UiBinder<Widget, PhotoPage> {
    }

    static private PhotoPage _instance = null;

    private static PhotoPageUiBinder uiBinder = GWT.create(PhotoPageUiBinder.class);

    @UiField
    Button addButton;

    public static PhotoPage getInstance() {
        if (null == _instance) {
            _instance = new PhotoPage();
        }
        return _instance;
    }

    public PhotoPage() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("addButton")
    public void handleClick(ClickEvent clickEvent) {
        History.newItem("login");
    }
}

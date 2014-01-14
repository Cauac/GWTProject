package gproject.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;
import gproject.client.service.FlickrService;
import gproject.client.service.FlickrServiceAsync;

public class AccountView extends Composite implements Display {

    @UiTemplate("template/AccountView.ui.xml")
    interface AccountViewUiBinder extends UiBinder<Widget, AccountView> {
    }

    private FlickrServiceAsync flickrService = GWT.create(FlickrService.class);

    private static AccountViewUiBinder uiBinder = GWT.create(AccountViewUiBinder.class);

    static private AccountView instance = null;


    public static AccountView getInstance() {
        if (null == instance) {
            instance = new AccountView();
        }
        return instance;
    }

    public AccountView() {
        initWidget(uiBinder.createAndBindUi(this));
        flickrService.hasFlickrToken(new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                return;
            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    flickrBtn.setText("Allowed");
                    flickrBtn.setEnabled(false);
                } else {
                    flickrBtn.setText("Allow access");
                    flickrBtn.setEnabled(true);
                }
            }
        });
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @UiField
    Button flickrBtn;

    @UiHandler("flickrBtn")
    public void handle(ClickEvent event) {
        flickrService.getAuthUrl(new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                return;
            }

            @Override
            public void onSuccess(String result) {
                redirect(result);
            }
        });
    }

    public static native String redirect(String url) /*-{
        return $wnd.location.href = url;
    }-*/;
}

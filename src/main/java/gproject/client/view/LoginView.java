package gproject.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gproject.client.service.AuthService;
import gproject.client.service.AuthServiceAsync;

public class LoginView extends Composite implements Display {

    @UiTemplate("template/LoginView.ui.xml")
    interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
    }

    private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);
    private AuthServiceAsync authService = GWT.create(AuthService.class);

    static private LoginView instance = null;

    public static LoginView getInstance() {
        if (null == instance) {
            instance = new LoginView();
        }
        return instance;
    }

    public LoginView() {
        initWidget(uiBinder.createAndBindUi(this));
        twitter.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                authService.getTwitterAuthUrl(new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        return;
                    }

                    @Override
                    public void onSuccess(String s) {
                        Window.Location.replace(s);
                    }
                });
            }
        });
    }

    @UiField
    Button twitter;

    @Override
    public Widget asWidget() {
        return this;
    }
}

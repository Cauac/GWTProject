package gproject.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gproject.client.service.AuthService;
import gproject.client.service.AuthServiceAsync;

public class LoginPage extends Composite {

    static private LoginPage _instance = null;

    private AuthServiceAsync authService = GWT.create(AuthService.class);

    private static LoginPageUiBinder uiBinder = GWT.create(LoginPageUiBinder.class);

    interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
    }

    public static LoginPage getInstance() {
        if (null == _instance) {
            _instance = new LoginPage();
        }
        return _instance;
    }

    public LoginPage() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}

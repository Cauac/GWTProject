package gproject.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LoginPage extends Composite {

    static private LoginPage _instance = null;

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

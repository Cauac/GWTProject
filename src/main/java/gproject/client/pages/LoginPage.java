package gproject.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Button;

public class LoginPage extends Composite {

    interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
    }

    static private LoginPage _instance = null;

    private static LoginPageUiBinder uiBinder = GWT.create(LoginPageUiBinder.class);

    @UiField
    Button addButton;
    @UiField
    TextBox login;
    @UiField
    TextBox password;

    public static LoginPage getInstance() {
        if (null == _instance) {
            _instance = new LoginPage();
        }
        return _instance;
    }

    public LoginPage() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("addButton")
    public void handleClick(ClickEvent clickEvent) {
        History.newItem("photo");
    }
}

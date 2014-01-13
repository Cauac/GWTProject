package gproject.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
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
        addFaceBookButton();
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

    private void addFaceBookButton() {
        exportMyFunction();
        panel.setHTML(panel.getHTML() + "<fb:login-button show-faces=\"false\" width=\"400\" max-rows=\"1\" size=\"large\"></fb:login-button>");
        ScriptElement script = Document.get().createScriptElement();
        script.setSrc("http://localhost:8080/resources/facebook.js");
        Element span = Document.get().getElementsByTagName("div").getItem(0);
        span.appendChild(script);
    }

    public static void fbLogin(String profileId) throws RequestException {
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, "http://localhost:8080/j_spring_service_security_check");
        builder.setHeader("Content-type", "application/x-www-form-urlencoded");
        builder.sendRequest("profile_id=" + profileId + "&service_name=facebook", new RequestCallback() {
            @Override
            public void onResponseReceived(Request request, Response response) {
                if (response.getStatusCode() < 400) {
                    History.newItem("photo");
                }
            }

            @Override
            public void onError(Request request, Throwable exception) {
                return;
            }
        });
    }

    public static native void exportMyFunction() /*-{
        $wnd.fbLogin =
            $entry(@gproject.client.view.LoginView::fbLogin(Ljava/lang/String;));
    }-*/;

    @UiField
    Button twitter;

    @UiField
    HTML panel;

    @Override
    public Widget asWidget() {
        return this;
    }
}

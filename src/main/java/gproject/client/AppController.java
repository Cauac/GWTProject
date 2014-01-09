package gproject.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import gproject.client.presenter.LoginPresenter;
import gproject.client.presenter.PhotoPresenter;
import gproject.client.presenter.Presenter;
import gproject.client.service.AuthService;
import gproject.client.service.AuthServiceAsync;
import gproject.client.view.LoginView;
import gproject.client.view.MenuView;
import gproject.client.view.PhotoView;
import gproject.shared.UserInfo;

public class AppController implements ValueChangeHandler<String> {

    private AuthServiceAsync authService = GWT.create(AuthService.class);
    private Panel header;
    private Panel content;

    public AppController() {
        header = RootPanel.get("header");
        content = RootPanel.get("content");
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);
    }

    public void go() {
        if ("".equals(History.getToken())) {
            openDefaultPage();
        } else {
            History.fireCurrentHistoryState();
        }
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
            Presenter presenter = null;

            if ("photo".equals(token)) {
                header.add(MenuView.getInstance().asWidget());
                presenter = new PhotoPresenter(PhotoView.getInstance());
            } else if ("login".equals(token)) {
                header.clear();
                presenter = new LoginPresenter(LoginView.getInstance());
            }

            if (presenter != null) {
                presenter.go(content);
            }
        }
    }

    private void openDefaultPage() {
        authService.getUserInfo(new AsyncCallback<UserInfo>() {
            @Override
            public void onFailure(Throwable throwable) {
                return;
            }

            @Override
            public void onSuccess(UserInfo userInfo) {
                if ("ROLE_ANONYMOUS".equals(userInfo.getRole())) {
                    History.newItem("login");
                } else {
                    History.newItem("photo");
                }
            }
        });
    }
}

package gproject.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import gproject.client.pages.LoginPage;
import gproject.client.pages.Menu;
import gproject.client.pages.PhotoPage;
import gproject.client.service.AuthService;
import gproject.client.service.AuthServiceAsync;

public class NavigationListener implements ValueChangeHandler<String> {

    RootPanel panel;

    private AuthServiceAsync authService = GWT.create(AuthService.class);

    public NavigationListener(RootPanel panel) {
        this.panel = panel;
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        if (event.getValue().equals("login")) {
            openLoginPage();
        }

        if (event.getValue().equals("photo")) {
            openPhotoPage();
        }
    }

    private void openLoginPage() {
        panel.get().clear();
        panel.get().add(LoginPage.getInstance());
    }

    private void openPhotoPage() {
        authService.isUser(new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable throwable) {
                openLoginPage();
            }

            @Override
            public void onSuccess(Boolean s) {
                if (s) {
                    Menu.getInstance().setVisible(true);
                    panel.get().clear();
                    panel.get("content").add(PhotoPage.getInstance());
                }
            }
        });

    }
}

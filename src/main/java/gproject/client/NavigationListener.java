package gproject.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import gproject.client.pages.LoginPage;
import gproject.client.pages.PhotoPage;

public class NavigationListener implements ValueChangeHandler<String> {

    RootPanel panel;

    public NavigationListener(RootPanel panel) {
        this.panel = panel;
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        if (event.getValue().equals("login")) {
            RootPanel.get().clear();
            RootPanel.get().add(LoginPage.getInstance());
        }

        if (event.getValue().equals("photo")) {
            RootPanel.get().clear();
            RootPanel.get().add(PhotoPage.getInstance());
        }
    }
}

package gproject.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import gproject.client.presenter.LoginPresenter;
import gproject.client.presenter.PhotoPresenter;
import gproject.client.presenter.Presenter;
import gproject.client.view.LoginView;
import gproject.client.view.MenuView;
import gproject.client.view.PhotoView;

public class AppController implements ValueChangeHandler<String> {

    private final HandlerManager eventBus;
    private Panel header;
    private Panel content;

    public AppController(HandlerManager eventBus) {
        this.eventBus = eventBus;
        header = RootPanel.get("header");
        content = RootPanel.get("content");
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);
    }

    public void go() {
        if ("".equals(History.getToken())) {
            History.newItem("login");
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
                presenter = new PhotoPresenter(eventBus, PhotoView.getInstance());
            } else if ("login".equals(token)) {
                header.clear();
                presenter = new LoginPresenter(LoginView.getInstance());
            }

            if (presenter != null) {
                presenter.go(content);
            }
        }
    }
}

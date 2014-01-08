package gproject.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import gproject.client.view.Display;


public class LoginPresenter implements Presenter {

    private final Display display;

    public LoginPresenter(Display display) {
        this.display = display;
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
}

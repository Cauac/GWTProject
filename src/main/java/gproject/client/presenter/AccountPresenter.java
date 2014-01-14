package gproject.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import gproject.client.view.AccountView;

public class AccountPresenter implements Presenter {

    private AccountView display = AccountView.getInstance();

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
}

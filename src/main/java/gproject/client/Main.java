package gproject.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import gproject.client.pages.Menu;


public class Main implements EntryPoint {
    @Override
    public void onModuleLoad() {
        RootPanel.get("header").add(Menu.getInstance());
        String initToken = History.getToken();

        if (initToken.length() == 0) {
            History.newItem("login");
        }

        History.addValueChangeHandler(new NavigationListener(RootPanel.get("content")));
        History.fireCurrentHistoryState();
    }

}

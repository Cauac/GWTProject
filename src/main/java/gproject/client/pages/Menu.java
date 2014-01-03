package gproject.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class Menu extends Composite {

    interface MenuUiBinder extends UiBinder<Widget, Menu> {
    }

    static private Menu _instance = null;

    private static MenuUiBinder uiBinder = GWT.create(MenuUiBinder.class);

    public static Menu getInstance() {
        if (null == _instance) {
            _instance = new Menu();
        }
        return _instance;
    }

    public Menu() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setVisible(boolean value) {
        menu.setVisible(true);
    }

    @UiField
    HTML menu;

    class MenuCommand implements Command {

        private String page;

        MenuCommand(String page) {
            this.page = page;
        }

        @Override
        public void execute() {
            History.newItem(page);
        }
    }
}

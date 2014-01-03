package gproject.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

public class Menu extends Composite {

    interface MenuUiBinder extends UiBinder<Widget, Menu> {
    }

    static private LoginPage _instance = null;

    private static MenuUiBinder uiBinder = GWT.create(MenuUiBinder.class);

    public Menu() {
        initWidget(uiBinder.createAndBindUi(this));
        initMenu();
    }

    @UiField
    MenuItem photo;
    @UiField
    MenuItem comment;
    @UiField
    MenuItem account;
    @UiField
    MenuItem friend;
    @UiField
    MenuItem findFriend;
    @UiField
    MenuItem statistic;

    void initMenu() {
        photo.setScheduledCommand(new MenuCommand("photo"));
        comment.setScheduledCommand(new MenuCommand("comment"));
        account.setScheduledCommand(new MenuCommand("account"));
        friend.setScheduledCommand(new MenuCommand("friend"));
        findFriend.setScheduledCommand(new MenuCommand("findFriend"));
        statistic.setScheduledCommand(new MenuCommand("statistic"));
    }

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

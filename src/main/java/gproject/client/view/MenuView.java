package gproject.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class MenuView extends Composite {

    @UiTemplate("template/MenuView.ui.xml")
    interface MenuUiBinder extends UiBinder<Widget, MenuView> {
    }

    private static MenuUiBinder uiBinder = GWT.create(MenuUiBinder.class);
    static private MenuView instance = null;

    public static MenuView getInstance() {
        if (null == instance) {
            instance = new MenuView();
        }
        return instance;
    }

    public MenuView() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}

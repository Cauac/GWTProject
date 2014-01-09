package gproject.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

public class DialogView {

    private static DialogView instance;
    private DialogBox dialogBox;
    private VerticalPanel content;

    public static DialogView getInstance() {
        if (null == instance) {
            instance = new DialogView();
        }
        return instance;
    }

    public DialogView() {
        dialogBox = new DialogBox();
        content = new VerticalPanel();
        VerticalPanel dialogPanel = new VerticalPanel();
        dialogPanel.setSpacing(4);
        dialogPanel.add(content);
        dialogPanel.add(new Button("Close", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        }));
        dialogBox.setWidget(dialogPanel);
    }

    public void show(String caption, Widget widget) {
        dialogBox.setText(caption);
        content.clear();
        content.add(widget);
        dialogBox.center();
        dialogBox.show();
    }
}

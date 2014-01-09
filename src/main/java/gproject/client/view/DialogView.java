package gproject.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import gproject.shared.Photo;

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
        dialogPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        dialogPanel.setSpacing(4);
        dialogPanel.add(content);
        Button button = new Button("Close", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });
        button.setStyleName("btn btn-primary");
        dialogPanel.add(button);
        dialogBox.setWidget(dialogPanel);
    }

    public void showPhoto(Photo photo) {
        dialogBox.setText(photo.getTitle());
        content.clear();
        content.add(new Image(photo.getLargeSizeUrl()));
        dialogBox.center();
        dialogBox.show();
    }
}

package gproject.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import gproject.client.view.Display;
import gproject.client.view.PhotoView;

public class PhotoPresenter implements Presenter {

    private final HandlerManager eventBus;
    private final Display display;

    public PhotoPresenter(HandlerManager eventBus, PhotoView view) {
        this.eventBus = eventBus;
        this.display = view;
    }

    public void bind() {

    }

    @Override
    public void go(HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
    }
}

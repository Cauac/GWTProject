package gproject.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;


public class Main implements EntryPoint {
    @Override
    public void onModuleLoad() {
        HandlerManager eventBus = new HandlerManager(null);
        AppController appViewer = new AppController(eventBus);
        appViewer.go();
    }

}

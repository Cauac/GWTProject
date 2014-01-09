package gproject.client;

import com.google.gwt.event.shared.HandlerManager;

public class AppContext {

    private static AppContext instance;
    private HandlerManager eventBus;

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }

        return instance;
    }

    private AppContext() {
        eventBus = new HandlerManager(null);
    }

    public HandlerManager getEventBus() {
        return eventBus;
    }
}

package gproject.client;

import com.google.gwt.core.client.EntryPoint;


public class Main implements EntryPoint {
    @Override
    public void onModuleLoad() {
        AppController appViewer = new AppController();
        appViewer.go();
    }

}

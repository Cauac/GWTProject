package gproject.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthServiceAsync {

    void isUser(AsyncCallback<Boolean> callback);
}

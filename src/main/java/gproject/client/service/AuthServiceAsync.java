package gproject.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthServiceAsync {

    void login(String login, String password, AsyncCallback<String> callback);

    void getLogin(AsyncCallback<String> callback);
}

package gproject.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import gproject.shared.UserInfo;

public interface AuthServiceAsync {

    void getUserInfo(AsyncCallback<UserInfo> callback);
}
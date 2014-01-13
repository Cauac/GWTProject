package gproject.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import gproject.shared.UserInfo;

@RemoteServiceRelativePath("springGwtServices/authService")
public interface AuthService extends RemoteService {

    UserInfo getUserInfo();

    String getTwitterAuthUrl();
}

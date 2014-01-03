package gproject.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/authService")
public interface AuthService extends RemoteService {

    String login(String login, String password);

    String getLogin();
}

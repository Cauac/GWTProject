package gproject.shared;

import java.io.Serializable;
import java.util.Map;

public class UserInfo implements Serializable {

    private String username;
    private String role;

    public UserInfo() {
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

package Utilities;

import java.util.HashMap;

/**
 *
 * @author kaimo
 */
public class CorrectUser {
    private HashMap data;
    private String passw;

    public CorrectUser() {
        this.data = new HashMap();
    }

    public HashMap getData() {
        return data;
    }

    public void setData(String user, String email) {
        this.data.put(user, email);
    }

    public String getPassword() {
        return passw;
    }

    public void setPassword(String password) {
        this.passw = password;
    }
    
    
}

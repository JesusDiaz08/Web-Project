package OurServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static Utilities.OurXML.*;
import Utilities.User;

public class ServletEditUser extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        String path = request.getServletContext().getRealPath("\\xml_code\\storage.xml");
        path = path.replace("\\build\\", "\\");
        LoginValidator validator = new LoginValidator(path);
        String user = request.getParameter(ATTR_USER_NAME);
        String name = request.getParameter(NAME);
        String lastname = request.getParameter(LAST_NAME);
        String email = request.getParameter(ATTR_EMAIL);
        String password = request.getParameter(PASSWORD);
        String type = request.getParameter(ATTR_TYPE_USER);
        
        System.out.println("New data user name = [" + user + "] name = [" + name + "] "
                + "last name = [" + lastname +"] email = [" + email + "] "
                        + "password = [" + password + "] type = [" + type + "]");
        
        User obj_user = new User(name, lastname, email, user, password, type);
        System.out.println("User object created: " + obj_user);
        validator.updateUser(user, obj_user);
        System.out.println("User " + user + " updated");
    }

}

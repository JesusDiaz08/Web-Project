package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static Utilities.OurXML.*;
/**
 *
 * @author Erick
 */
public class ServletDropUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        System.out.println("OurServlets.ServletDropUser.doGet()");
        String path = request.getServletContext().getRealPath("\\xml_code\\storage.xml");
        path = path.replace("\\build\\", "\\");
        LoginValidator validator = new LoginValidator(path);
        String email = request.getParameter(ATTR_EMAIL);
        System.out.println("I've try to drop user " + email);
        validator.dropUser(email);
        System.out.println("Probably the user has been droped");
    }
}

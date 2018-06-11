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
public class ServletDropDiagrams extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        System.out.println("OurServlets.ServletDropDiagrams.doGet()");
        String path = request.getServletContext().getRealPath("\\xml_code\\storage.xml");
        path = path.replace("\\build\\", "\\");
        String name_project = request.getParameter(ATTR_NAME_PROJECT);
        String user_name = request.getParameter("name_user");
        System.out.println("name_project = " + name_project);
        System.out.println("user_name = " + user_name);
        LoginValidator validator = new LoginValidator(path);
        validator.dropProject(user_name, name_project);   
    }

}

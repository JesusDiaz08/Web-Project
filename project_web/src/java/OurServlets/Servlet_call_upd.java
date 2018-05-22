package OurServlets;

import Utilities.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author kaimo
 */
public class Servlet_call_upd extends HttpServlet {
       
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter write = response.getWriter();
        String path = request.getRealPath("\\xml_code");
        path += "\\storage.xml";
        //System.out.println(" ->-> "+path);
        
        File file = new File(path);
        SAXBuilder saxBuilder = new SAXBuilder();
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String user_name = request.getParameter("user");
        String password = request.getParameter("password");       
        String type_user = request.getParameter("type_user");
        User usuario = new User(name, last_name, email, user_name, password, type_user);
        /** BEGIN XML treat**/
        LoginValidator valid = new LoginValidator(path);
        valid.updateUser(email, usuario);
    }

}

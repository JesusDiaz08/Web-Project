package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet_session: this class is a Servlet that we use to 
 * redirect a user to another servlet depending of the user type
 * also if the user exists or not
 **/
public class Servlet_login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        String path = request.getRealPath("\\xml_code");
        path += "\\storage.xml";
        //Instance of a class that we use to search a user
        LoginValidator loginValidator = new LoginValidator(path);
                
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("usuario");
        String password = request.getParameter("clave");
        //The user exists
        if(loginValidator.validateUser(user, password)){
            //We redirect the user to another servlet
            //response.sendRedirect("user");
            System.out.println("Si existe cuenta");
        } 
        //The user doesn't exists
        else {
            //We redirect the user to another servlet
            //response.sendRedirect("");
            System.out.println("No existe cuenta");
        }
    }

}

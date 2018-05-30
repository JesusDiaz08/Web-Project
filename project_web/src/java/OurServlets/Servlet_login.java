package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static Utilities.OurXML.*;
import javax.servlet.http.HttpSession;
import org.jdom.Element;

/**
 * Servlet_session: this class is a Servlet that we use to redirect a user to
 * another servlet depending of the user type also if the user exists or not
 *
 */
public class Servlet_login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("si entra al servlet-login");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter write = response.getWriter();
        String path = request.getRealPath("\\xml_code");
        path += "\\storage.xml";

        //Instance of a class that we use to search a user
        LoginValidator login = new LoginValidator(path);
        String user = request.getParameter("usuario");
        String password = request.getParameter("clave");
        
        /*Recuperamos los atributos para la sesión a abrir*/
        HttpSession session_usuario = request.getSession();
        session_usuario.setAttribute("attr_user",user);       
        
        Element el = login.getUser(user);
        if(login.isUser(user, password)){
       //if(el!=null){
            //We redirect the user to another servlet
            //response.sendRedirect("user");
            Element aUser = login.getUser(user);
            System.out.println("Si existe cuenta");
            //The user exist so we can get information about her/his

            //The user is an administrator
            if (el.getAttributeValue(ATTR_TYPE_USER).equals(ADMINISTRATOR)) { /*Administrator*/
                response.sendRedirect("Administrador");
            }
            //The user is a teacher
            else if(el.getAttributeValue(ATTR_TYPE_USER).equals(TEACHER)){ /*Teacher*/
                response.sendRedirect("Teacher");
            }
            //The user is a student
            else if(el.getAttributeValue(ATTR_TYPE_USER).equals(STUDENT)){ /*Student*/
                response.sendRedirect("User");
            }
        } 
        //The user doesn't exists
        else {
            //We redirect the user to another servlet
            //response.sendRedirect("");
            System.out.println("This user hasn't been registered yet ");
            write.println("<html><head><title>User not registered</title></head>");
            write.println("<body>");
            write.println("<center><h2>This user has not been registered yet</h2></center>");
            write.println("<center><a href='back_end/session.html'>Volver</a></center>");
            write.println("</body></html>");
            write.close();
        }
        
    }

}

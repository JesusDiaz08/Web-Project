package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static Utilities.OurXML.*;

/**
 * Servlet_session: this class is a Servlet that we use to 
 * redirect a user to another servlet depending of the user type
 * also if the user exists or not
 **/
public class Servlet_login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter write = response.getWriter();
        String path = request.getRealPath("\\xml_code");
        path += "\\storage.xml";
        
        //Instance of a class that we use to search a user
        LoginValidator loginValidator = new LoginValidator(path);
        String user = request.getParameter("usuario");
        String password = request.getParameter("clave");
        //The user exists
        if(loginValidator.validateUser(user, password)){
            //We redirect the user to another servlet
            //response.sendRedirect("user");
            System.out.println("Si existe cuenta");
            write.println("<html>");
            write.println("<head>");
            if (loginValidator.getTypeUser(user,password).equals(TYPE_USER[0])) { /*Administrator*/
                write.println("<meta http-equiv='Refresh' content='1;url=back_end/admin.html'>");
            }else if(loginValidator.getTypeUser(user,password).equals(TYPE_USER[1])){ /*Teacher*/
                write.println("<meta http-equiv='Refresh' content='1;url=back_end/teacher.html'>");
            }else if(loginValidator.getTypeUser(user,password).equals(TYPE_USER[2])){ /*Student*/
                write.println("<meta http-equiv='Refresh' content='1;url=back_end/main.html'>");
            }
            write.println("<script>alert('Welcom'"+user+"!');</script>");
            write.println("</head>");
            write.println("<body></body>");
            write.println("</html>");
            write.close();
        } 
        //The user doesn't exists
        else {
            //We redirect the user to another servlet
            //response.sendRedirect("");
            System.out.println("No existe cuenta");
            write.println("<html><head><title>User not registered</title></head>");
            write.println("<body>");
            write.println("<center><h2>This user has not been registered yet</h2></center>");
            write.println("<center><a href='back_end/session.html'>Volver</a></center>");
            write.println("</body></html>");
            write.close();
        }
    }

}

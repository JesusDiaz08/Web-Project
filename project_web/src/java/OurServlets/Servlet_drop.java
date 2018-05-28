
package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kaimo
 */
public class Servlet_drop extends HttpServlet {
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
        System.out.println("I'm on Servlet_drop");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter write = response.getWriter();
        String path = request.getRealPath("\\xml_code");
        path += "\\storage.xml";
        
        String id_email = request.getParameter("id_email");
        LoginValidator validator = new LoginValidator(path);
        if (validator.dropUser(id_email))
            System.out.println("Droped user");
        else
            System.out.println("User did not drop");
        
        write.println("<html>");
                write.println("<head>");
                write.println("<meta http-equiv='Refresh' content='1;url=back_end/admin.html'>");
                write.println("<script>alert('User registered');</script>");
                write.println("</head>");
                write.println("<body></body>");
                write.println("</html>");
   }
}

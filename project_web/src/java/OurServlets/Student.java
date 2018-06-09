package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kaimo
 */
public class Student extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String path = request.getServletContext().getRealPath("/xml_code/storage.xml");
        HttpSession session = request.getSession();
        String str_user = String.valueOf(session.getAttribute("attr_user"));
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
            out.println("<head>");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
                out.println("<link rel='stylesheet' href='back_end/css/main_admin.css'>");
                out.println("<title>Alumno</title>");
            out.println("</head>");

            out.println("<body>");
                out.println("<div align='right'><nav>");
                out.println("<a href='back_end/log_in.html' target = '_top'> SIGN OUT </a>");
                out.println("</nav></div>");
                out.println("<div id='mySidenav' class='sidenav'>");
                    out.println("<a href='back_end/user/user_activity.html' id='myActivity'>Actividades</a>");
                    out.println("<a href='back_end/user/user_projects.html' id='myProjects'>Proyectos</a>");
                out.println("<div style='margin-left:130px;'>");
                    out.println("<h2>Bienvenido Alumno: "+str_user+"</h2>");
                out.println("</div>");
            out.println("</body>");
        out.println("</html>");
    }
}

package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

/**
 *
 * @author kaimo
 */
public class Administrador extends HttpServlet {
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
        PrintWriter write = response.getWriter();
        
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/xml_code/storage.xml");
        path = path.replace("\\build\\", "\\");
        System.out.println(path);
        LoginValidator validator = new LoginValidator(path);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userName = "erick_thod@hotmail.com";//(String)session.getAttribute("attr_user");
        validator.addProject("rvwe1rvw224", "{}{}{}{}[][]{}{]{}", userName, "myFirstProject");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
            out.println("<head>");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
                out.println("<link rel='stylesheet' href='back_end/css/main_admin.css'>");
                out.println("<style>");
                out.println("body{"
                        + " margin:0;" 
                        + "font-family: 'Century Gothic';"
                        + "height: 950px;}");
                out.println("</style>");
            out.println("</head>");

            out.println("<body>");
                out.println("<div align='right'><nav>");
                out.println("<a href='back_end/log_in.html' target = '_top'> SIGN OUT </a>");
                out.println("</nav></div>");
                out.println("<div id='mySidenav' class='sidenav'>");
                    out.println("<a href='back_end/administrator.html' id='user'>Usuarios</a>");
                out.println("</div>");
                out.println("<br/>\n" +
                        "<div id=\"mySidenav\" class=\"sidenav\">\n" +
                        "<a href='back_end/groups.html' id=\"group\">Grupos</a>\n" +
                        "</div>");
                out.println("<div style='margin-left:130px;'>");
                    out.println("<h2>Bienvenido Administrador: "+userName+"</h2>");
                out.println("</div>");
            out.println("</body>");
        out.println("</html>");
    }
}

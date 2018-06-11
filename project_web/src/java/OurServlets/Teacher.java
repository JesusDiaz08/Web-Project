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
public class Teacher extends HttpServlet {
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
        HttpSession UpDownLoad = request.getSession();
        UpDownLoad.setAttribute("repo_teacher",str_user); /*Se usará para crear una carpeta con el nombre del profesor
                                                            en donde se almacenarán sus subidas de archivos*/
        System.out.println("ESTOY EN TEACHER: "+str_user);
        out.println("<!DOCTYPE html>");
        out.println("<html>");
            out.println("<head>");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
                out.println("<link rel='stylesheet' href='back_end/css/main_admin.css'>");
                out.println("<title>Profesor</title>");
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
                    out.println("<a href='back_end/teacher_activity.html' id='createDiagram'>Crear diagrama</a>");
                out.println("</div>");
                out.println("<div id='mySidenav' class='sidenav'>");
                    out.println("<a href='back_end/teacher_asign.html' id='setActivity'>Asignar</a>");
                out.println("</div>");
                out.println("<div id='mySidenav' class='sidenav'>");
                    out.println("<a href='back_end/teacher_activity.html' id='evalue'>Evaluar</a>");
                out.println("</div>");
                
                out.println("<div style='margin-left:130px;'>");
                    out.println("<h2>Bienvenido Profesor: "+str_user+"</h2>");
                out.println("</div>");
            out.println("</body>");
        out.println("</html>");
    }
}

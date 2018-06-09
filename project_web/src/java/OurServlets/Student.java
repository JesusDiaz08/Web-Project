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
        PrintWriter write = response.getWriter();
        String path = request.getServletContext().getRealPath("/xml_code/storage.xml");
        System.out.println(path);
        
        String param_frame_right = "?"+"typeUser"+"="+"Alumno";
        String param_frame_left = "?";
        int num_content = 1;
        
        param_frame_left += "num_content" + "=" + num_content 
                         + "&" + "href0"+"="+"back_end/user/user_activity.html" + "&" + "content0" + "=" + "ACTIVIDAD"
                         + "&" + "href1"+"="+"back_end/user/user_projects.html" + "&" + "content1" + "=" + "MIS PROJECTOS";
                
        
        write.println("<!DOCTYPE html>");
        write.println("<html>");
        write.println("<head>");
        write.println("<title>Alumno</title>");
        write.println("</head>");
        write.println("<frameset cols='10%,*' noresize> ");
        write.println("<frame src='Frame_Left"+param_frame_left+"'>");
        write.println("<frame src='Frame_Rigth"+param_frame_right+"' name='contenidos' >");
        write.println("</frameset> ");
        write.println("<body>");
        write.println("</body>");
        write.println("</html>");
    }
}

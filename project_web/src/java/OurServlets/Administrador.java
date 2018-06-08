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
        String path = request.getRealPath("\\xml_code");
        path += "\\storage.xml";
        
        /*ServletContext context = request.getServletContext();
        String ruta = context.getRealPath("/") + "xml_code/storage.xml";*/
        
        String param_frame_right = "?"+"typeUser"+"="+"Administrator";
        String param_frame_left = "?";
        int num_content = 1;
        
        param_frame_left += "num_content" + "=" + num_content 
                         + "&" + "href0"+"="+"back_end/admin.html"
                         + "&" + "content0" + "=" + "USERS";
        
        
        
        write.println("<!DOCTYPE html>");
        write.println("<html>");
        write.println("<head>");
        write.println("<title>Administrador</title>");
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

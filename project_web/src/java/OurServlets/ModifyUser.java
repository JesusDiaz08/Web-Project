package OurServlets;

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
public class ModifyUser extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("I'm on Servlet_Update");
        String path = request.getServletContext().getRealPath("/xml_code/storage.xml");
        
        File file = new File(path);
        SAXBuilder saxBuilder = new SAXBuilder();
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
    }
}

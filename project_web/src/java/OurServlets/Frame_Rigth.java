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
public class Frame_Rigth extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("attr_user");
        String tipoUser= request.getParameter("typeUser");

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div align='right'><nav>");
            out.println("<a href='back_end/session.html' target = '_top'> SIGN OUT </a>");
            out.println("</nav></div>");
            out.println("<h1>");
            out.println("Bienvenido "+tipoUser+" : "+ userName);
            out.println("</h1>");
            out.println("</body>");
            out.println("</html>");       
    }
}

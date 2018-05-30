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
public class Frame_Left extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String valor= request.getParameter("num_content");
        int num_content = Integer.parseInt(valor);

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");

            if(num_content != 0){
                for(int i=0;i<num_content;i++){
                 out.println("<a href='"+request.getParameter("href"+i)+"' target='contenidos'> "+request.getParameter("content"+i)+" </a>");
                  out.println("<br />");
                }                
            }
            else{
                out.println("¡NOTA: Aun no hay acceso a contenido!");
            }

            out.println("</body>");
            out.println("</html>");       
    }

}

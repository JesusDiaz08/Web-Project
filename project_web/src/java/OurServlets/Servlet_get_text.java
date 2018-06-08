package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet_get_text extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int n = Integer.parseInt((String)request.getAttribute(""));
        
        out.println("!DOCTYPE html>");
        out.println("<html>");
            out.println("<head>");
                out.println("<title>Servlet get text</title>");
            out.println("</head>");
            
            out.println("<body>");
                out.println("<form action='' method='GET'>");
                    for(int i = 0; i < n; i++)
                        out.println("Ingrese texto: <input type='text' name='txt"+i+"'/><br/>");
                    out.println("<input type='submit'/>");
                out.println("</form>");
            out.println("</body>");
        out.println("</html>");
        
    }
    
    //Login
    //Registro
    //Cursos
    //Materiales visuales
    //Evaluaciones
    //Reportes
    
    //Conceptos
    //Ejercicios
}

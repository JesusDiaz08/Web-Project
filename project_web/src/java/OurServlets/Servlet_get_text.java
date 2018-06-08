package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet_get_text extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer n=Integer.parseInt(request.getParameter("num_elementos"));
        String texto=request.getParameter("texto");
        HttpSession session = request.getSession();
        session.setAttribute("num_elementos", String.valueOf(n));
        session.setAttribute("texto",texto);
        
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
            out.println("<head>");
                out.println("<title>Servlet get text</title>");
            out.println("</head>");
            
            out.println("<body>");
                out.println("<form action='Servlet_Diagram' method='GET'>");
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

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer n=Integer.parseInt(request.getParameter("num_elementos"));
        String texto=request.getParameter("txt-content");
        String nombre=request.getParameter("project_name");
        HttpSession session = request.getSession();
        String actual_user = String.valueOf(session.getAttribute("repo_teacher"));
        
        /*Recuperamos los valores necesarias y los subimos a sesión*/
        session.setAttribute("num_elementos", String.valueOf(n));/*Numero de elementos*/
        session.setAttribute("texto",texto);
        session.setAttribute("actualUser",actual_user);
        session.setAttribute("project_name",nombre);
        /*----------------------------------------------------------*/
        System.out.println("|---------");
        System.out.println("| Sesion actual: "+actual_user);
        System.out.println("|---------");
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
            out.println("<head>");
                out.println("<title>Asignar actividad</title>");
            out.println("</head>");
            System.out.println("Texto: "+texto);
            out.println("<body>");
                out.println("<form action='Servlet_Diagram' method='post'>");
                    for(int i = 0; i < n; i++)
                        out.println("Ingrese texto: <input type='text' name='txt"+i+"'/><br/>");
                    out.println("Imagen:<input type='text' name='img'/>");
                    out.println("<input type='submit'/>");
                out.println("</form>");
            out.println("</body>");
        out.println("</html>");
        
    }

}

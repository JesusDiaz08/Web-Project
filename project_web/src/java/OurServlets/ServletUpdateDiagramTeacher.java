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
 * @author Erick
 */
public class ServletUpdateDiagramTeacher extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{
       
       HttpSession session=request.getSession();
       String rtf=(String)session.getAttribute("RTF");
       System.out.println(rtf);
       String json=(String)session.getAttribute("JSON");
       System.out.println(json);
       PrintWriter pw=response.getWriter();
       pw.println("<!DOCTYPE html>");
       pw.println("<html>");
       pw.println("<head>");
       pw.println("<title>Diagrama</title>");
       pw.println("<link rel=\"stylesheet\" href='back_end/css/style_diagram.css'>");
       pw.println("<script src=\"konva.min.js\"></script>");
       pw.println("<meta charset=\"utf-8\">");
       pw.println("</head>");
       pw.println("<body onload='cargar()'>");
       pw.println(rtf);
       pw.println("<div id=\"container\"></div>");
       pw.println("<script>");
       /*Funcion para deserilizar canvas*/
       pw.println("function cargar(){var json="+json+"; var stage=Konva.Node.create(json,'container');}");
       /*Funcion para serializar canvas*/
        pw.println("function Serializar(){var json=stage.toJSON();document.getElementById('textarea1').value=json;}");
       pw.println("</script>");
       pw.println("<textarea name='json' id='textarea1' form='formulario' style='display:none' ></textarea>");
       pw.println("<form action='Teacher' id='formulario' method='get'>");
       pw.println("<input class='button b1' value='Actualizar' type='submit' onclick='Serializar()'/>");
       pw.println("</form>");
                    
       
       pw.println("</body>");
       
       pw.println("</html>");
       
    }

}

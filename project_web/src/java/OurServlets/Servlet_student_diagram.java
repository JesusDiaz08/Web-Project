package OurServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import static Utilities.OurXML.*;
import Utilities.User;
import java.io.FileOutputStream;
import javax.servlet.http.HttpSession;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Servlet_student_diagram extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{
       
       HttpSession session=request.getSession();
       
       PrintWriter pw=response.getWriter();
       pw.println("<!DOCTYPE html>");
       pw.println("<html>");
       pw.println("<head>");
       pw.println("<title>Diagrama</title>");
       pw.println("<link rel=\"stylesheet\" href='back_end/css/style_diagram.css'>");
       pw.println("<script src=\"konva.min.js\"></script>");
       pw.println("<meta charset=\"utf-8\">");
       pw.println("</head>");
       pw.println("<body>");
       pw.println("<div id=\"container\"></div>");
       pw.println("<script>");
       /*Funcion para deserilizar canvas*/
       pw.println("function cargar(){var json=document.getElementById('mtext').value; var stage=Konva.Node.create(json,'container');}");
       
       pw.println("</script>");
       pw.println("<input class='button b1' type='button' value='Cargar canvas' onclick='cargar();'/>\n" +
                    
                    "<textarea id='mtext'></textarea>");//De donde carga el SVG o el JSON
       pw.println("</body>");
       
       pw.println("</html>");
       
    }
}
    
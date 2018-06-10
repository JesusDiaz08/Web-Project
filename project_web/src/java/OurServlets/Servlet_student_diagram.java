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
       pw.println("<script src=\"fabric.min.js\"></script>");
       pw.println("<canvas id='m' width='1000' height='600'></canvas>");
       pw.println("<script type='text/javascript'>");
       pw.println("var canvas = new fabric.Canvas('m');");
       pw.println("var json='';");
       pw.println("function mFunction(){canvas.clear();}");
       pw.println("function myFunction(){json=document.getElementById('mtext').value;");
       pw.println("console.log(json);");
       pw.println("canvas.loadFromJSON(json);}");
       pw.println("function cargarSVG(){");
       pw.println("var contsvg=document.getElementById('mtext').value;");
       pw.println("console.log(contsvg);");
       pw.println("fabric.loadSVGFromString(contsvg,function(objects, options) {");//Para cargar desde un SVG (comienza con XML)
       pw.println("canvas.add.apply(canvas, objects);");
       pw.println("canvas.renderAll();});}");
       pw.println("</script>");
       pw.println("<input class='button b1' type='button' value='Limpiar canvas' onclick='mFunction();'/>\n" +
                    "<input class='button b1' type='button' value='Cargar canvas' onclick='myFunction();'/>\n" +
                    "<input class='button b1' type='button' value='SVG' onclick='cargarSVG();'/>\n" +
                    "<textarea id='mtext'></textarea>");//De donde carga el SVG o el JSON
       pw.println("</body>");
       
       pw.println("</html>");
       
    }
}
    
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
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Servlet_Diagram extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{
       Integer elementos=Integer.parseInt(request.getParameter("num_elementos"));
       String texto=request.getParameter("texto");
       
       PrintWriter pw=response.getWriter();
       String ruta = request.getRealPath("");
       ruta += "\\fabric.min.js";
       
        System.out.println("Path:"+ruta);
       pw.println("<!DOCTYPE html>");
       pw.println("<html>");
       pw.println("<head>");
       pw.println("<title>Diagrama</title>");
       //pw.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.1.0/fabric.all.min.js\" ></script>");
       //pw.println("<script src=\""+ruta.replace("\\build\\","\\")+"\"></script>");
       pw.println("<script src=\"fabric.min.js\"></script>");
       pw.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js'></script>");
       pw.println("</head>");
       pw.println("<body> ");
       
       Integer left=50,top=400;
       pw.println("<canvas id='mcanvas' width='"+300*elementos+"' height='600'></canvas>");
       pw.println("<script type=\"text/javascript\">");
       pw.println("var canvas = new fabric.Canvas('mcanvas');");
       pw.println("var text=new fabric.Text('"+texto+"',{left:300,top:10,fontSize:14});");
       pw.println("canvas.add(text);");
       pw.println("text.set('selectable',true);");
        for (int i = 0; i < elementos; i++) {
            /*Crear whitespaces*/
            pw.println("var rect"+i+" = new fabric.Rect({");
            pw.println("left:"+left+",top:"+top+",fill:'#1e90ff',width:150,height:100});");
            pw.println("canvas.add(rect"+i+");");
            pw.println("rect"+i+".set('selectable',true);");
            /*Crear objetos a mover*/
            pw.println("var recta"+i+" = new fabric.Rect({");
            pw.println("left:"+left+",top:"+(top-200)+",fill:'black',width:150,height:100});");
            pw.println("canvas.add(recta"+i+");");
            pw.println("recta"+i+".set('selectable',false);");
            
            pw.println("var group = new fabric.Group([ texto"+i+", recta"+i+" ], {\n" +
                  "  left: "+left+",\n" +
                  "  top: "+(top-200)+",\n" +
                  "});\n" +
                  "\n" +
                  "canvas.add(group);");
            
            left+=200;
        }
       
       pw.println("function myFunction(){console.log(JSON.stringify(canvas));}");
       
       
       pw.println("</script>");
       pw.println("<input class=\"button b1\" type=\"button\" value=\"Agregar\" onclick=\"myFunction();\"/>");
       pw.println("<input type='submit' name='Enviar'>");
       pw.println("</body>");
       
       pw.println("</html>");
    }
}
    
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

public class Servlet_Diagram extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{
       
       HttpSession session=request.getSession();
       
       Integer elementos=Integer.parseInt(String.valueOf(session.getAttribute("num_elementos")));
       String texto=String.valueOf(session.getAttribute("texto"));
       String imagen=request.getParameter("img");
        System.out.println("Imagen:"+imagen);
       PrintWriter pw=response.getWriter();
       
       pw.println("<!DOCTYPE html>");
       pw.println("<html>");
       pw.println("<head>");
       pw.println("<title>Diagrama</title>");
       pw.println("<link rel=\"stylesheet\" href='back_end/css/style_diagram.css'>");
       pw.println("<script src='konva.min.js'></script>");
       pw.println("<meta charset=\"utf-8\">");
       pw.println("</head>");
       pw.println("<body> ");
       /*Insertar texto que ingresa el profesor*/
       pw.println("<div style='margin-left:150px;"
                             +"margin-top: 100;"
                             +"margin-rigth:250'>");
            pw.println("<p width='300*"+elementos+"' height='600'>");
                pw.println(texto);
            pw.println("</p>");
       pw.println("</div>");
       pw.println("<div id=\"container\"></div>");
       pw.println("<script>");
       pw.println("function writeMessage(message) {\n" +
                    "        text.setText(message);\n" +
                    "        layer.draw();\n" +
                    "    }");
       pw.println("var width = window.innerWidth;");
       pw.println("var height = window.innerHeight;");
       pw.println("var stage = new Konva.Stage({\n" +
                    "        container: 'container',\n" +
                    "        width: width,\n" +
                    "        height: height\n" +
                    "    });"); 
       pw.println(" var layer = new Konva.Layer();");
       
       
       Integer x=10,y=200;
        for (int i = 0; i < elementos; i++) {
              /*Crear objetos fijos*/
            pw.println("var box"+i+" = new Konva.Rect({");
            pw.println("x:"+(x)+",y:"+(y+400)+",");
            pw.println("offset: [50, 25],");
            pw.println("width: 200,");
            pw.println("height: 100,");
            pw.println("fill: '#e0fcfc',");
            pw.println("stroke: 'black',");
            pw.println("strokeWidth: 4");
            pw.println("});");
            pw.println("layer.add(box"+i+");");
            /*Crear objetos a mover*/
            pw.println("var box"+i+" = new Konva.Rect({");
            pw.println("x:"+x+",y:"+y+",");
            pw.println("offset: [50, 25],");
            pw.println("width: 200,");
            pw.println("height: 100,");
            pw.println("fill: '#0098FF',");
            pw.println("stroke: 'black',");
            pw.println("strokeWidth: 4");
            pw.println("});");
            pw.println("  var group = new Konva.Group({\n" +
            "        x: "+x+",\n" +
            "        y: "+y+", draggable:true"+
            "    });");
            pw.println("var text"+i+" = new Konva.Text({x:"+(x+10)+",y:"+(y+10)+",fill:'white',text:'"+request.getParameter("txt"+i)+"'});");
            pw.println("group.add(box"+i+",text"+i+");");
            pw.println("layer.add(group);");
          
            
            x+=250;
        }
       /*Agregar imagen*/
       pw.println("var imageObj=new Image();");
       pw.println("imageObj.onload=function(){");
       pw.println("var imagen= new Konva.Image({");
       pw.println("x:500,y:50,image:imageObj,width:106,height:118});");
       pw.println("layer.add(imagen);");
       pw.println("stage.add(layer);");
       pw.println("};");
       
       pw.println("imageObj.src='"+imagen+"';");
       /*Funcion para serializar*/
       pw.println("function Serializar(){var json=stage.toJSON();document.getElementById('textarea1').value=json;}");
       pw.println("</script>");
       pw.println("<textarea name='json' id='textarea1' form='formulario' style='display:none' ></textarea>");
       pw.println("<form action='Servlet_Guardar' id='formulario' method='post'>");
       pw.println("<input class='button b1' value='Guardar diagrama' type='submit' onclick='Serializar()'/>");
       pw.println("</form>");
       pw.println("</body>");
       
       pw.println("</html>");
       
    }
    
 
}
    
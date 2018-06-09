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

public class Servlet_update extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{
        System.out.println("I'm on Servlet_Update");
        String path = request.getServletContext().getRealPath("/xml_code/storage.xml");//request.getRealPath("\\xml_code");
        //path += "\\storage.xml";
        
        File file = new File(path);
        SAXBuilder saxBuilder = new SAXBuilder();
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        String id = request.getParameter("id"); /*Puede ser correo o usuario*/
        
        LoginValidator verify = new LoginValidator(path);
        
        Element user = verify.getUser(id);
        if (user!=null) {
            crearformulario(writer,user);
        }
        
        Document document = null;
        Element rootElement = null;
        try{
            //The file storage.xml exists
            if(file.exists()){
                document = saxBuilder.build(file);
                rootElement = document.getRootElement();
            } 
            //storage.xml doesn't exists
            else {
                document = new Document();
                rootElement = new Element(ROOT);
            }
        } catch(IOException e){
            System.err.println("An IOException has occurred in Servlet_update.doPost " + e);
        } catch(Exception e){
            System.err.println("An Excpetion has occurred Servlet_update.doPost" + e);
        }
    }
    public static void crearformulario(PrintWriter pw, Element user)
    {
            String name = user.getChildText(NAME);
            String last_name = user.getChildText(LAST_NAME);
            String email = user.getAttributeValue(ATTR_EMAIL);
            String user_name = user.getAttributeValue(ATTR_USER_NAME);
            String password = user.getChildText(PASSWORD);
            String type_user = user.getAttributeValue(ATTR_TYPE_USER);
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>UPDATE</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<form action='Servlet_call_upd' method='POST'> </br>");
            pw.println("Nombre: <input type='text' value='"+name+"' name='name'/> </br>");
            pw.println("Apellido: <input type='text' value='"+last_name+"' name='last_name'/> </br>");
            pw.println("Email: <input type='email' value='"+email+"' name='email'/> </br>");
            pw.println("Password: <input type='password' value='"+password+"' name='password'/> </br>");
            pw.println("Username: <input type='text' value='"+user_name+"' name='user'/> </br>");
            pw.println("Tipo de usuario: <select name=\"type_user\" required value='"+type_user+"'>\n" +
"                         <option value=\"student\">Alumno</option>\n" +
"                         <option value=\"teacher\">Profesor</option>\n" +
"                         <option value=\"administrator\">Administrador</option>\n" +
"                     </select>");
            pw.println("<input type='submit' value='Update'/>");
            pw.println("</form>");
            pw.println("</body>");
            pw.println("</html>");
            
    }
}

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{
        
        String path = request.getRealPath("\\xml_code");
        path += "\\storage.xml";
        
        File file = new File(path);
        SAXBuilder saxBuilder = new SAXBuilder();
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        String name = "Efraín";
        String last_name = "Vargas";
        String email = "vargas.erick030997@gmail.com";
        String user_name = "eerick1997";
        String password = "123456";
        String type_user = "administrator";
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
            LoginValidator validator = new LoginValidator(path);
            if(!validator.isUser(email, user_name, password)){
                User user = new User(name, last_name, email, user_name, password, type_user);

                
                validator.updateUser("erick_thod@hotmail.com", user);
               
            }
        } catch(IOException e){
            System.err.println("An IOException has occurred in Servlet_update.doPost " + e);
        } catch(Exception e){
            System.err.println("An Excpetion has occurred Servlet_update.doPost" + e);
        }
    }
      
}

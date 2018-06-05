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
import java.util.List;

public class Servlet_joinGroup extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{
        String path = request.getRealPath("\\xml_code");
        path += "\\groups.xml";
        
        File file = new File(path);
        SAXBuilder saxBuilder = new SAXBuilder();
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        String id = request.getParameter("id"); /*Puede ser correo o usuario*/
        LoginValidator verify = new LoginValidator(path);
        Element user = verify.getUser(id);
        
        if (user!=null) {
            joinGroup(writer,user,verify);
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
    public static void joinGroup(PrintWriter pw, Element user,LoginValidator verify){
            String user_name = user.getAttributeValue(ATTR_USER_NAME);
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>JOIN GROUP</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<form action='Servlet_call_join' method='POST'> </br>");
            pw.println("<p>The student "+user_name+" is going to join to:</p>");
            List groups = verify.getGroupsFromXML();
            pw.println("Group: <select name='id-gpo' required>");
            for (int i = 0; i < groups.size(); i++) {
               Element group = (Element) groups.get(i);
               pw.println("<option value='gpo-"+i+"'>"+group.getChildText(NAME_GPO)+"</option>");
            }
            pw.println("</select>");
            pw.println("Turno: <select name='id-turno' required>");
                pw.print("<option value='turno-mat'>Matutino</option>");
                pw.print("<option value='turno-ves'>Vespertino</option>");
            pw.println("</select>");
            pw.println("<input type='submit' value='Join'/>");
            pw.println("</form>");
            pw.println("</body>");
            pw.println("</html>");
            
    }
}

package OurServlets;

import static Utilities.OurXML.*;
import Utilities.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap.SimpleEntry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Servlet_register extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter write = response.getWriter();
        String path = request.getRealPath("\\xml_code");
        path += "\\storage.xml";
        //System.out.println(" ->-> "+path);
        
        File file = new File(path);
        SAXBuilder saxBuilder = new SAXBuilder();
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String user_name = request.getParameter("user");
        String password = request.getParameter("password");       
        String type_user = request.getParameter("type_user");
        /** BEGIN XML treat**/
        
        User user = new User(name,last_name,email,user_name,password,type_user);
        Document document = null;
        Element rootElement = null;
        try {
            //The file exists
            if (file.exists()) {
                System.out.println("File exists ");
                //FileInputStream fileInputStream = new FileInputStream(file);
                //SAXBuilder saxBuilder = new SAXBuilder();
                document = saxBuilder.build(file);
                rootElement = document.getRootElement();
                //fileInputStream.close();
            } //The file doesn't exists
            else {
                System.out.println("File doesn't exists");
                document = new Document();
                rootElement = new Element(ROOT);
            }
            LoginValidator validator = new LoginValidator(path);
            SimpleEntry<String,String> is = validator.isUser(user);
            
            if (is.getKey().equals("0")) { /*User doesn't exist*/
                is.setValue(user.getType_user()); /*Establish type of user*/
                System.out.println("Adding a user data");
                Element child = setUserDatas(rootElement, user);
                rootElement.addContent(child);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
                xmlOutputter.output(document, fileOutputStream);
                //xmlOutputter.output(document, System.out);
                fileOutputStream.flush();
                fileOutputStream.close();
                write.println("<html>");
                write.println("<head>");
                write.println("<meta http-equiv='Refresh' content='1;url=back_end/admin.html'>");
                write.println("<script>alert('User registered');</script>");
                write.println("</head>");
                write.println("<body></body>");
                write.println("</html>");
                
            }else{
                //String register = request.getRealPath("\\back_end");
                //register += "register.html";
                System.out.println("User's already been registered");
                System.out.println("Try with another email");
                write.println("<html><head><title>User not found</title></head>");
                write.println("<body>");
                write.println("<center><h2>This user has already been registered</h2></center>");
                write.println("<center><h2>Try with another email</h2></center>");
                write.println("<center><a href='back_end/registro.html'>Volver</a></center>");
                write.println("</body></html>");
                write.close();
                //response.sendRedirect(register);
            }
        } catch(IOException e){ 
            System.err.println("An IOException has occurred " + e);
        } catch (Exception e) {
            System.err.println("An exception has occurred searching the XML document " + e);
        }
        /** END XML treat **/
    }

    /**
     * This method adds an user in the XML code using the datas captured in the form register
     * Parameters: Five strings that are the datas of an user
     * Return: An element object that contains infomation about an user
     **/
    private Element setUserDatas(Element element, User user) throws Exception{
        System.out.println("setUserDatas\n name = [" + user.getName()
                + "]\n last_name = [" + user.getLast_name()
                + "]\n user_name = [" + user.getUser_name() 
                + "]\n password = [" + user.getPassword() 
                + "]\n email = [" + user.getEmail() 
                + "]\n type_user = ["+user.getType_user()+"]");
        
        element = new Element(USER);
        element.setAttribute("type_user",user.getType_user());
        element.addContent(new Element(NAME).setText(user.getName()));
        element.addContent(new Element(LAST_NAME).setText(user.getLast_name()));
        element.addContent(new Element(USER_NAME).setText(user.getUser_name()));
        element.addContent(new Element(PASSWORD).setText(user.getPassword()));
        element.addContent(new Element(EMAIL).setText(user.getEmail()));
        element.addContent(new Element(PROJECTS).setText("algo"));
        return element;
    }
}

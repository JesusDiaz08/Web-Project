package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import static Utilities.OurXML.*;
import java.io.File;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Erick
 */
public class Servlet_getStudents extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>OurServlets.ServletgetStudents.doPost()");
        String path = request.getServletContext().getRealPath("\\xml_code\\storage.xml");
        path = path.replace("\\build\\", "\\");
        System.out.println(">>>" + path);
        LoginValidator validator = new LoginValidator(path);
        List users = validator.getUsersFromXML();
        System.out.println("List users from our xml file: " + users);
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < users.size(); i++){
            Element user = (Element)users.get(i);
           JSONObject jsonObject = new JSONObject();
           jsonObject.accumulate(ATTR_USER_NAME, user.getAttributeValue(ATTR_USER_NAME));
           jsonObject.accumulate(NAME, user.getChildText(NAME));
           jsonObject.accumulate(LAST_NAME, user.getChildText(LAST_NAME));
           jsonObject.accumulate(ATTR_EMAIL, user.getAttributeValue(ATTR_EMAIL));
           
             jsonObject.accumulate(ATTR_TYPE_USER, user.getAttributeValue(ATTR_TYPE_USER));
           
           jsonObject.accumulate(PASSWORD, user.getChildText(PASSWORD));
           if(user.getAttributeValue(ATTR_TYPE_USER).equals("student"))
           {
                jsonArray.put(jsonObject);  
           }
        }
        System.out.println(jsonArray.toString());
        response.getWriter().println(jsonArray.toString());
    }
}

package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import static Utilities.OurXML.*;
public class ServletGetGroups extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        System.out.println("OurServlets.ServletGetGroups.doGet()");
        String path = request.getServletContext().getRealPath("\\xml_code\\groups.xml");
        path = path.replace("\\build\\", "\\");
        System.out.println(">>> " + path);
        LoginValidator validator = new LoginValidator(path);
        List groups = validator.getGroupsFromXML();
        System.out.println("List groups from our xml file: " + groups);
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < groups.size(); i++){
            Element group = (Element)groups.get(i);
            JSONObject jSONObject = new JSONObject();
            jSONObject.accumulate(NAME_GPO, group.getChildText(NAME_GPO));
            jsonArray.put(jSONObject);
        }
        System.out.println(jsonArray.toString());
        response.getWriter().println(jsonArray.toString());
    }
}

package OurServlets;

import static Utilities.OurXML.*;
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

/**
 *
 * @author Erick
 */
public class ServletGetDiagrams extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        System.out.println("OurServlets.ServletGetDiagrams.doGet()");
        String path = request.getServletContext().getRealPath("\\xml_code\\storage.xml");
        path = path.replace("\\build\\", "\\");
        String name = request.getParameter("name_user");
        System.out.println(">>> " + path);
        LoginValidator validator = new LoginValidator(path);
        List diagrams = validator.getProjects(name);
        System.out.println(diagrams);
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < diagrams.size(); i++){
            Element diagram = (Element)diagrams.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate(ATTR_NAME_PROJECT, diagram.getAttributeValue(ATTR_NAME_PROJECT));
            jsonObject.accumulate(JSON, diagram.getChildText(JSON));
            jsonObject.accumulate(RTF, diagram.getChildText(RTF));
            jsonArray.put(jsonObject);
        }
        System.out.println(jsonArray.toString());
        response.getWriter().println(jsonArray.toString());
    }

}

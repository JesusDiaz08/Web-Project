package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static Utilities.OurXML.*;
/**
 *
 * @author Erick
 */
public class ServletGetJSONRTF extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        System.out.println("OurServlets.ServletGetJSONRTF.doPost()");
        HttpSession session = request.getSession();
        String JSON_str = request.getParameter(JSON);
        System.out.println(JSON_str);
        String RTF_str = request.getParameter(RTF);
        session.setAttribute(JSON, JSON_str);
        System.out.println(RTF_str);
        session.setAttribute(RTF, RTF_str);
        response.sendRedirect("ServletShowDiagram");
    }

}

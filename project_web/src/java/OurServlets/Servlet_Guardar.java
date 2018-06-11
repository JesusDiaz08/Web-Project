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

public class Servlet_Guardar extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{
        System.out.println("OurServlets.Servlet_Guardar.doPost()");
        String path = request.getServletContext().getRealPath("\\xml_code\\storage.xml");
        path = path.replace("\\build\\", "\\");
        System.out.println(">>> " + path);
        HttpSession session = request.getSession();
        String user_name = (String)session.getAttribute(NAME);
        LoginValidator validator = new LoginValidator(path);
        String json=request.getParameter("json");
        System.out.println("JSON:***********"+json);
        validator.addProject(RTF, json, user_name, PROJECTS);
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
        pw.println("</body>");
       
        pw.println("</html>");
       
    }
    
 
}
    
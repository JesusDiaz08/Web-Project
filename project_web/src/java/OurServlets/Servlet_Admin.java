package OurServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alber
 */
public class Servlet_Admin extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out=response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet_Admin</title>");            
            
            out.println(JS());
            
            out.println("</head>");
            out.println("<body>");
            
            out.println("Users: <input type='text' size='20' id='users' onkeyup='findUsers();' />");
            out.println("<div style=\"position:absolute;\" id=\"popup\">\n" +
"		<table id=\"user_table\" bgcolor=\"#FFFAFA\" border=\"0\"\n" +
"			cellspacing=\"0\" cellpadding=\"0\"/>\n" +
"			<tbody id=\"user_table_body\"></tbody>\n" +
"		</table>");
            
            out.println("</body>");
            out.println("</html>");

    }
    
    public static String JS()
    {
        String script="<script type='text/javascript'>\n"+
                //CreateXMLHttpRequest
                "function createXMLHttpRequest() {\n" +
                "if (window.ActiveXObject) {\n" + //Si el navegador es Internet Explorer
                    "xmlHttp = new ActiveXObject(\"Microsoft.XMLHTTP\");\n" +
                "}\n" +
                "else if (window.XMLHttpRequest) {\n" + //Cualquer otro navegador
                    "xmlHttp = new XMLHttpRequest();\n" +
                "}\n" +
                "}\n"+
                //Fin de CreateXMLHttpRequest
                "var xmlHttp;\n" +
"	var completeDiv;\n" +
"	var inputField;\n" +
"	var nameTable;\n" +
"	var nameTableBody;"+
                
                
                //initVars
                "function initVars() {\n" +
                "		inputField = document.getElementById('users');\n" +
                "		userTable = document.getElementById('user_table');\n" +
                "		completeDiv = document.getElementById('popup');\n" +
                "		userTableBody = document.getElementById('user_table_body');\n" +
                "	}"+
                //Fin de initVars
                
                //FindUsers
                "function findUsers() {	\n" +
                "initVars();\n" + 
                "if (inputField.value.length > 0) {\n" +
                "   createXMLHttpRequest();\n" +
                "   var url = 'http://localhost:8080/admin/hola2?users=' + escape(inputField.value);\n" +//Revisar la url
                "   xmlHttp.open('GET', url, true);\n" +
                "   xmlHttp.onreadystatechange = callback;\n" +
                "   xmlHttp.send(null);\n" +
                "} else {\n" +
                "   clearUsers();\n" +
                "}\n" +
                "}\n"+
                //Fin de FindUsers
                //Callback
                "function callback() {\n" +
                "if (xmlHttp.readyState == 4) {\n" +
                "   if (xmlHttp.status == 200) {\n" +
                "       var user =\n" +
                "       xmlHttp.responseXML\n" +
                "       .getElementsByTagName('user')[0].firstChild.data;\n" +
                "	setUsers(xmlHttp.responseXML.getElementsByTagName('user'));\n" +
                "   } else if (xmlHttp.status == 204){\n" +
                "       clearUsers();\n" +
                "   }\n" +
                "}\n" +
                "}"+
                //Fin de Callback
                //ClearUsers
                "function clearUsers() {\n" +
                "var ind = userTableBody.childNodes.length;\n" +
                "for (var i = ind - 1; i >= 0 ; i--) {\n" +
                "	userTableBody.removeChild(userTableBody.childNodes[i]);\n" +
                "}\n" +
                "completeDiv.style.border = 'none';\n" +
                "}\n"+
                //Fin de ClearUsers
                
                "</script>";//Fin del script
                
        return script;
    }
}
    
    
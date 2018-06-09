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
            
        System.out.println(request.getContextPath());
        out.println("<!DOCTYPE html>");
        out.println("<html>");
            out.println("<head>");
                out.println("<title>Administrator</title>");
                out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/back_end/css/style_admin.css' />");
                out.println("<script type='text/javascript'>");
                    out.println("var xml_http;");
                    out.println("var content;");
                    out.println("var dataUser;");
                    out.println("var users;");
                    out.println("var URL = " + request.getContextPath()+"/xml_code/storage.xml;");//getRoot() + 'storage.xml';");


                    out.println("function createXMLHttpRequest() {");
                        out.println("if (window.ActiveXObject) {");
                            out.println("xml_http = new ActiveXObject('Microsoft.XMLHTTP');");
                        out.println("}else if (window.XMLHttpRequest) { ");
                            out.println("xml_http = new XMLHttpRequest();");
                        out.println("}");
                    out.println("}");

                    out.println("function getRoot(){");
                        out.println("var rootPath = window.location.protocol + '//' + window.location.host + '/';");
                        out.println("if (window.location.hostname == 'localhost')");
                        out.println("{");
                            out.println("var path = window.location.pathname;");
                            out.println("if (path.indexOf('/') == 0){");
                                out.println("path = path.substring(1);");
                            out.println("}");
                            out.println("path = path.split('/', 1);");
                            out.println("if (path != ''){");
                                out.println("rootPath = '"+request.getContextPath()+"/xml_code/';" );
                            out.println("}");
                        out.println("}");
                        out.println("return rootPath;");
                    out.println("}");

                    out.println("function initVariables(){");
                        out.println("content = document.getElementById('table_body');");
                        out.println("dataUser = new Array();");
                    out.println("}");

                    out.println("function onLoad(){");
                    
                        out.println("initVariables();");
                        out.println("createXMLHttpRequest();");
                        out.println("xml_http.open('GET',"+request.getContextPath()+"/xml_code/storage.xml,true);");
                        out.println("xml_http.onreadystatechange = function (){");
                            out.println("if (xml_http.readyState === 4 && xml_http.status===200) {");
                                out.println("if(xml_http.responseXML !== null){");
                                    out.println("users = xml_http.responseXML.getElementsByTagName('user');");
                                    out.println("for(i=0; i<users.length;i++){");
                                        out.println("dataUser[0] = users[i].getAttribute('id_user');");
                                        out.println("dataUser[1] = xml_http.responseXML.getElementsByTagName('name')[i].firstChild.data;");
                                        out.println("dataUser[2] = users[i].getAttribute('id_email');");
                                        out.println("content.innerHTML += '<tr><td>'+dataUser[0]+'</td><td>'");
                                                                       out.println("+dataUser[1]+'</td><td>'");
                                                                       out.println("+dataUser[2]+'</td><td>'");
                                                                       out.println("+users[i].getAttribute('type_user')+'</td>'");
                                                                       out.println("+'<td><button class='button b1' onclick='dropUser('+'\''+dataUser[2]+'\''+')'>Eliminar</button></td>'");
                                                                       out.println("+'<td><button class='button b1' onclick='editUser(''+'\''+dataUser[2]+'\''+')'>Editar</button></td></tr>';");

                                    out.println("}");
                                out.println("}");
                            out.println("}");

                        out.println("};");
                        out.println("xml_http.send();");
                    out.println("}");

                    out.println("function addUser(){");
                        out.println("pop_ventana = window.open('registro.html','Registrar usuario','width=400,height=650');");
                    out.println("}");

                    out.println("function editUser(id_user){");
                        out.println("var parametro = '?id='+id_user;");
                        out.println("pop_ventana = window.open('../Servlet_update'+parametro,'Editar','width=400,height=650');");
                    out.println("}");

                    out.println("function dropUser(id_email){");
                        out.println("var parametro = '?id_email='+id_email;");
                        out.println("document.location.href='../Servlet_drop'+parametro;'");
                        out.println("var list = document.getElementById('table_body');");
                        out.println("list.removeChild(list.childNodes[id_email]);");
                        out.println("xmlHttp.onreadystatechange();                ");
                    out.println("}");
                    
                out.println("</script>");

            out.println("</head>");
            out.println("<body onload='onLoad()'>");
                out.println("<header>");
                    out.println("<div align='right'>");

                        out.println("<a href='back_end/session.html' target = '_top'>Sign Out</a>");
                    out.println("</div>");
                out.println("</header>");
                out.println("<h1 align='center'>ADMINISTRADOR</h1>");
                out.println("<div align='center'>");

                    out.println("<input class='button b1' type='button' value='Agregar' onclick='addUser();'/>");
                    out.println("<br/>");
                    out.println("<br/>");
                    out.println("<div class='datagrid'>");
                    out.println("<table border='2'>");
                        out.println("<thead>");
                            out.println("<tr>");
                                out.println("<th>USUARIO</th>");
                                out.println("<th>NOMBRE</th>");
                                out.println("<th>EMAIL</th>");
                                out.println("<th>TIPO</th>");
                                out.println("<th colspan='2'>ACCION</th>");
                            out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody id='table_body'>");
                            out.println("<tr>");
                            out.println("</tr>");
                        out.println("</tbody>");
                    out.println("</table>");
                    out.println("</div>");
                out.println("</div>");







                out.println("<div>ACCIONES");
                    out.println("<h1>DROP USER</h1>");
                    out.println("<form action='../Servlet_drop' method='post'>");
                        out.println("<input type='text' name='id_email' placeholder='Email' required/>");
                        out.println("<input type='submit' value='Drop'/>");
                    out.println("</form>");
                out.println("</div>");
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
    
    
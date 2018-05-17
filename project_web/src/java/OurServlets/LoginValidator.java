package OurServlets;

import Utilities.User;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;

public class LoginValidator {

    private final String path_XML;
    private HashMap users_registered;

    public LoginValidator(String Path_XML) {
        users_registered = new HashMap();
        this.path_XML = Path_XML;
        System.out.println("LoginValidator\n->" + path_XML);
    }

    /**
     * This function search a user and verify if the password is the correct.
     *
     * @param user_name
     * @param password
     * @return boolean
     *
     */
    public boolean validateUser(String user_name, String password) {
        /*Asign all whole users from XML*/
        users_registered = getUsersFromXML();
        //We search if the user exists and if the password is correct
        return (users_registered.containsKey(user_name)
                && users_registered.get(user_name).equals(password));
    }

    public SimpleEntry isUser(User user) {
        //======>  Single Entry
        //System.out.println("key: " + pair.getKey() + "    value:" + pair.getValue());
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document doc = builder.build(XML_file);
            Element rootNode = doc.getRootElement();
            /*<user></user>*/
            List chiildren = rootNode.getChildren("user");

            for (int i = 0; i < chiildren.size(); i++) {
                Element node = (Element) chiildren.get(i);
                if (node.getChildText("email").equals(user.getEmail())
                        || node.getChildText("user_name").equals(user.getUser_name())) {
                    return new SimpleEntry<>("1", node.getAttributeValue("type_user"));
                }
            }
        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
        return new SimpleEntry<>("0", "Nothing");
    }

    public HashMap getUsersFromXML() {
        HashMap users = new HashMap();
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document doc = builder.build(XML_file);
            Element rootNode = doc.getRootElement();
            /*<user></user>*/
            List chiildren = rootNode.getChildren("user");

            for (int i = 0; i < chiildren.size(); i++) {
                Element node = (Element) chiildren.get(i);
                users.put(node.getChildText("user_name"),
                        node.getChildText("password"));
            }
        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public String getTypeUser(String user_name, String password) {
        String type_user = "";
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document doc = builder.build(XML_file);
            Element rootNode = doc.getRootElement();
            /*<user></user>*/
            List chiildren = rootNode.getChildren("user");

            for (int i = 0; i < chiildren.size(); i++) {
                Element node = (Element) chiildren.get(i);
                if (node.getChildText("user_name").equals(user_name)
                        && node.getChildText("password").equals(password)) {
                    return node.getAttributeValue("type_user");
                }
            }
        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
        return type_user;
    }

    public boolean dropUser(String id_email) {
        boolean isDrop=false;
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document doc = builder.build(XML_file);
            Element root = doc.getRootElement(); /*<user></user>*/
            
            List usersXML = root.getChildren("user");
            Iterator iter = usersXML.iterator();

            
            while(iter.hasNext()){
                Element user = (Element)iter.next();
                if (user.getAttributeValue("id_email").equals(id_email)) {
                    iter.remove();
                    return !isDrop;
                }
            }
        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
        return isDrop;
    }
}

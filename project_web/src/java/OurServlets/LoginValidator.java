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

public class LoginValidator {
    
    private final String path_XML;
    private HashMap users_registered;
    
    
    public LoginValidator(String Path_XML){
        users_registered= new HashMap();
        this.path_XML = Path_XML;
        System.out.println("LoginValidator\n->"+path_XML);
    }
    
    
    /**
     * This function search a user and verify if the password
     * is the correct.
     * @param user_name 
     * @param password 
     * @return boolean
     **/
    
    public boolean validateUser(String user_name, String password){
        /*Asign all whole users from XML*/
        users_registered = getUsersFromXML();
        //We search if the user exists and if the password is correct
        return (users_registered.containsKey(user_name) && 
                users_registered.get(user_name).equals(password));
    }
    
    public boolean isUser(User user){
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document doc = (Document)builder.build(XML_file);
            Element rootNode = doc.getRootElement(); /*<user></user>*/
            List chiildren = rootNode.getChildren("user");
            
            for (int i = 0; i < chiildren.size(); i++) {
                Element node = (Element) chiildren.get(i);
                if (node.getChildText("email").equals(user.getEmail()) ||    
                    node.getChildText("user_name").equals(user.getUser_name()) ) {
                    return true;
                }
            }
        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public HashMap getUsersFromXML(){
        HashMap users = new HashMap();
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document doc = (Document)builder.build(XML_file);
            Element rootNode = doc.getRootElement(); /*<user></user>*/
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
}
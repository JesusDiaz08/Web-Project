package OurServlets;

import static Utilities.OurXML.*;
import Utilities.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.util.Iterator;
import java.util.List;


public class LoginValidator {

    private final String path_XML;
    
    public LoginValidator(String Path_XML) {
        this.path_XML = Path_XML;
        System.out.println("LoginValidator\n->" + path_XML);
    }

    /**
     * This function search a user and verify if the password is the correct.
     *
     * @param email
     * @param user_name
     * @param password
     * @return boolean
     *
     */

    public boolean isUser(String email, String user_name, String password) {
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document doc = builder.build(XML_file);
            Element rootNode = doc.getRootElement();
            /*<user></user>*/
            List chiildren = rootNode.getChildren(USER);

            for (int i = 0; i < chiildren.size(); i++) {  /*Verify if an user exists*/
                Element node = (Element) chiildren.get(i);
                return (node.getAttribute(ATTR_EMAIL).toString().equals(email) ||
                        node.getAttribute(ATTR_USER_NAME).toString().equals(user_name)) &&
                        node.getChildText(PASSWORD).equals(password);     
            }
            
        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List getUsersFromXML() {
        List user_element_list = null;
        SAXBuilder SAXbuilder = new SAXBuilder();
        File XML_file = new File(path_XML);
        
        try{
            Document document = SAXbuilder.build(XML_file);
            Element root_element = document.getRootElement();
            user_element_list = root_element.getChildren(USER);
        } catch(IOException e){
            System.err.println("An exception has occurred in LoginValidator.fillHashMap file maybe doesn't exists IOException " + e);
        } catch(JDOMException e){
            System.err.println("An exception has occurred in LoginValidator.fillHashMap maybe XML is not well conformed or don't valid JDOMException " + e);
        } catch(Exception e){
            System.err.println("An exception has occurred in LoginValidator.fillHashMap Exception " + e);
        }
        return user_element_list;
    }

     public Element getUser(String id){
        //Instance of a SAXBuilder object
        SAXBuilder SAXbuilder = new SAXBuilder();
        //Making an instance of File object and putting the path of our XML_file
        File XML_file = new File(path_XML);
        try{
            Document document = SAXbuilder.build(XML_file);
            Element root_element = document.getRootElement();
            List user_element_list = root_element.getChildren(USER);
            for(int i = 0; i < user_element_list.size(); i++){
                Element user_element = (Element)user_element_list.get(i);
                if(user_element.getChildText(ATTR_EMAIL).equals(id) ||
                        // the user_name is the same as the @param so we return an Element
                        user_element.getChildText(ATTR_USER_NAME).equals(id)){
                    //User found, we return the user
                    return user_element;
                }
            }
        } catch(IOException e){
            System.err.println("An exception has occurred in LoginValidator.fillHashMap file maybe doesn't exists IOException " + e);
        } catch(JDOMException e){
            System.err.println("An exception has occurred in LoginValidator.fillHashMap maybe XML is not well conformed or don't valid JDOMException " + e);
        } catch(Exception e){
            System.err.println("An exception has occurred in LoginValidator.fillHashMap Exception " + e);
        }
        return null;
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

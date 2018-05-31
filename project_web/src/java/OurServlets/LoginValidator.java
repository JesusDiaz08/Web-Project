package OurServlets;

import static Utilities.OurXML.*;
import Utilities.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.util.Iterator;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class LoginValidator {

    private String path_XML;

    public LoginValidator(String path_XML) {
        this.path_XML = path_XML;
        
        if(this.path_XML.contains("\\build\\"))
            this.path_XML = this.path_XML.replace("\\build\\", "\\");
        
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
    public boolean isUser(String id, String password) {
        System.out.println("OurServlets.LoginValidator.isUser() email " + id + " password " + password);
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document doc = builder.build(XML_file);
            Element rootNode = doc.getRootElement();
            /*<user></user>*/
            List chiildren = rootNode.getChildren(USER);

            for (int i = 0; i < chiildren.size(); i++) {
                /*Verify if an user exists*/
                Element node = (Element) chiildren.get(i);
                if ((node.getAttributeValue(ATTR_EMAIL).equals(id)
                        || node.getAttributeValue(ATTR_USER_NAME).equals(id))
                        && node.getChildText(PASSWORD).equals(password)) {
                    return true;
                }
            }

        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List getUsersFromXML() {
        System.out.println("OurServlets.LoginValidator.getUsersFromXML()");
        List user_element_list = null;
        SAXBuilder SAXbuilder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document document = SAXbuilder.build(XML_file);
            Element root_element = document.getRootElement();
            user_element_list = root_element.getChildren(USER);
            
        } catch (IOException e) {
            System.err.println("An exception has occurred in LoginValidator.getUsersFromXML file maybe doesn't exists IOException " + e);
        } catch (JDOMException e) {
            System.err.println("An exception has occurred in LoginValidator.getUsersFromXML maybe XML is not well conformed or don't valid JDOMException " + e);
        } catch (Exception e) {
            System.err.println("An exception has occurred in LoginValidator.getUsersFromXML Exception " + e);
        }
        return user_element_list;
    }

    public Element getUser(String id) {
        System.out.println("OurServlets.LoginValidator.getUser() id: " + id);
        //Instance of a SAXBuilder object
        SAXBuilder SAXbuilder = new SAXBuilder();
        //Making an instance of File object and putting the path of our XML_file
        File XML_file = new File(path_XML);
        try {
            Document document = SAXbuilder.build(XML_file);
            Element root_element = document.getRootElement();
            List user_element_list = root_element.getChildren(USER);
            for (int i = 0; i < user_element_list.size(); i++) {
                Element user_element = (Element) user_element_list.get(i);
                if (user_element.getAttributeValue(ATTR_EMAIL).equals(id)
                        || // the user_name is the same as the @param so we return an Element
                        user_element.getAttributeValue(ATTR_USER_NAME).equals(id)) {
                    //User found, we return the user
                    return user_element;
                }
            }
        } catch (IOException e) {
            System.err.println("An exception has occurred in LoginValidator.getUser file maybe doesn't exists IOException " + e);
        } catch (JDOMException e) {
            System.err.println("An exception has occurred in LoginValidator.getUser maybe XML is not well conformed or don't valid JDOMException " + e);
        } catch (Exception e) {
            System.err.println("An exception has occurred in LoginValidator.getUser Exception " + e);
        }
        return null;
    }

    public boolean dropUser(String id_email) {
        System.out.println(" - - - OurServlets.LoginValidator.dropUser() id_email: " + id_email + " - - - ");
        boolean isDrop = false;
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

            Document doc = builder.build(XML_file);
            Element root = doc.getRootElement();
            /*<users></users>*/

            List usersXML = root.getChildren(USER);
            Iterator iter = usersXML.iterator();

            while (iter.hasNext()) {
                Element user = (Element) iter.next();
                if (user.getAttributeValue(ATTR_EMAIL).equals(id_email)) {
                    System.out.println("User exists so we can drop it " + user.getAttributeValue(ATTR_EMAIL));
                    iter.remove();
                    System.out.println(">>isDrop: " + !isDrop);
                    xmlOutputter.output(doc, new FileWriter(XML_file));
                    return !isDrop;
                }
            }
        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(">>isDrop: " + isDrop);
        return isDrop;
    }

    public void updateUser(String id, User user) {
        System.out.println("OurServlets.LoginValidator.updateUser() id " + id + " "
                + " user " + user);
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            Document doc = builder.build(XML_file);
            Element root = doc.getRootElement();
            System.out.println("root_element " + root);
            List usersXML = root.getChildren(USER);
            System.out.println("usersXML " + usersXML + " size " + usersXML.size());
            for (int i = 0; i < usersXML.size(); i++) {
                Element element = (Element) usersXML.get(i);
                if ((element.getAttributeValue(ATTR_EMAIL).equals(id)
                        || element.getAttributeValue(ATTR_USER_NAME).equals(id))) {

                    System.out.println("User found " + element.getAttributeValue(ATTR_EMAIL));
                    System.out.println("Now user has element " + element.getAttributeValue(ATTR_USER_NAME));
                    
                    Attribute attr_email = element.getAttribute(ATTR_EMAIL);
                    attr_email.setValue(user.getEmail());
                    Attribute attr_user_name = element.getAttribute(ATTR_USER_NAME);
                    attr_user_name.setValue(user.getUser_name());
                    Attribute attr_type = element.getAttribute(ATTR_TYPE_USER);
                    attr_type.setValue(user.getType_user());
                    Element element_name = element.getChild(NAME);
                    element_name.setText(user.getName());
                    Element element_last_name = element.getChild(LAST_NAME);
                    element_last_name.setText(user.getLast_name());
                    Element element_password = element.getChild(PASSWORD);
                    element_password.setText(user.getPassword());
                    xmlOutputter.output(doc, new FileOutputStream(XML_file));
                }

            }
        } catch (IOException e) {
            System.err.println("An exception has occurred in LoginValidator.updateUser file maybe doesn't exists IOException " + e);
        } catch (JDOMException e) {
            System.err.println("An exception has occurred in LoginValidator.updateUser maybe XML is not well conformed or don't valid JDOMException " + e);
        } catch (Exception e) {
            System.err.println("An exception has occurred in LoginValidator.updateUser Exception " + e);
        }
    }
   

}

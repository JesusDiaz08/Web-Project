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
    //private String path_XML_gpo;
    
    /**
     * Description: Constructor of LoginValidator Class
     *
     * @param path_XML 
     */
    public LoginValidator(String path_XML) {
        this.path_XML = path_XML;
        System.out.println("LoginValidator\n->" + path_XML);
    }
    
    
    /**
     * Description: Constructor of LoginValidator Class
     *
     * @param path_XML_gpo 
     * @param gpo
     */
    /**public LoginValidator(String gpo,String path_XML_gpo) {  /*Get all whole data of groups.xml*/
        /**this.path_XML_gpo = path_XML_gpo;
        
        System.out.println("LoginValidator\n->" + path_XML_gpo);
    }**/
    
    
    /**
     * Description: This function search a user and verify if the password is the correct.
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
    
    
    /**
     * Description: This function gets a list object of users from XML.
     * 
     * @return List
     */
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
    
    
    /**
     * Description: This function gets the specify user 
     *              who wants to do some operation.
     * @param id
     * @return Element
     */    
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
                if (user_element.getAttributeValue(ATTR_USER_NAME).equals(id)
                        || // the user_name is the same as the @param so we return an Element
                        user_element.getAttributeValue(ATTR_USER_NAME).equals(id)) {
                    System.out.println("I've found: " + id);
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
    
    
    /**
     * Description: This function drop, if is exists, an user from storage XML
     *              
     * @param id_email 
     * @return boolean
     */
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
    
    
    /**
     * Description: This function update an user from storage XML.
     * 
     * @param id
     * @param user
     * @return void
     */
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
    
    
    /**
     * Description: This function verify if an group exists into groups XML.
     * 
     * @param turno
     * @param nameGpo 
     * @return boolean
     */
    public boolean isGroup(String turno, String nameGpo) {
        System.out.println("OurServlets.LoginValidator.isGroup() Turno: " + turno + " Name " + nameGpo);
        SAXBuilder builder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document doc = builder.build(XML_file);
            Element rootNode = doc.getRootElement();
            /*<user></user>*/
            List chiildren = rootNode.getChildren(GROUP);
            for (int i = 0; i < chiildren.size(); i++) {
                /*Verify if the group exists*/
                Element node = (Element) chiildren.get(i);
                if (node.getChildText(NAME_GPO).equals(nameGpo) &&
                    node.getChildText(TURNO_GPO).equals(turno)) {
                    return true;
                }
            }

        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    
    /**
     * Description: This function gets all whole groups from groups XML.
     *  
     * @return List
     */
    public List getGroupsFromXML() {
        System.out.println("OurServlets.LoginValidator.getGroupsFromXML()");
        List groups_elements_list = null;
        SAXBuilder SAXbuilder = new SAXBuilder();
        File XML_file = new File(path_XML);
        try {
            Document document = SAXbuilder.build(XML_file);
            Element root_element = document.getRootElement();
            groups_elements_list = root_element.getChildren();
            
        } catch (IOException e) {
            System.err.println("An exception has occurred in LoginValidator.getGroupsFromXML file maybe doesn't exists IOException " + e);
        } catch (JDOMException e) {
            System.err.println("An exception has occurred in LoginValidator.getGroupsFromXML maybe XML is not well conformed or don't valid JDOMException " + e);
        } catch (Exception e) {
            System.err.println("An exception has occurred in LoginValidator.getGroupsFromXML Exception " + e);
        }
        return groups_elements_list;
    }
    
    
    /**
     * Description: This function gets the specify groups 
     *              which wants to do some operation.
     * @param turno
     * @param nameGpo 
     * @return Element
     */    
    public Element getGroup(String turno, String nameGpo) {
        System.out.println("OurServlets.LoginValidator.getGpo() nameGpo: " + nameGpo);
        //Instance of a SAXBuilder object
        SAXBuilder SAXbuilder = new SAXBuilder();
        //Making an instance of File object and putting the path of our XML_file
        File XML_file = new File(path_XML);
        try {
            Document document = SAXbuilder.build(XML_file);
            Element root_element = document.getRootElement();
            List group_element_list = root_element.getChildren(GROUP);
            for (int i = 0; i < group_element_list.size(); i++) {
                Element group = (Element) group_element_list.get(i);
                if (group.getChildText(NAME_GPO).equals(nameGpo) && 
                    group.getChildText(TURNO_GPO).equals(turno))
                    return group;
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
    
    public void addProject(String RTF_value, String JSON_value, String user, String project_name){
        System.out.println("OurServlets.LoginValidator.isRTF_saved() RTF = [" + RTF + "]"
                + " JSON = [" + JSON + "] user = [" + user + "]");
        //Probably we catch an exception
        SAXBuilder saxBuilder = new SAXBuilder();
        File file = new File(path_XML);
        try{
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            Document document = saxBuilder.build(file);
            //First of all we need to find the user that has created the diagram
            //Element user_element = getUser(user);
            List elements = document.getRootElement().getChildren();
            Element user_element = null;
            for(int i = 0; i < elements.size(); i++){
                user_element = (Element)elements.get(i);
                if(user_element.getAttributeValue(ATTR_EMAIL).equals(user)){
                    System.out.println("I've found " + user);
                    break;
                }
            }
            Element user_projects = user_element.getChild(PROJECTS);
            //We create a new node that contains information about the project
            Element project = new Element(PROJECT);
            //Project name
            Element RTF_ELEMENT = new Element(RTF).addContent(RTF_value);
            Element JSON_ELEMENT = new Element(JSON).addContent(JSON_value);
            Attribute name_project = new Attribute(ATTR_NAME_PROJECT, project_name);
            project.addContent(RTF_ELEMENT);
            project.addContent(JSON_ELEMENT);
            project.setAttribute(name_project);
            user_projects.addContent(project);   
            System.out.println(((Element)user_projects.getChildren(PROJECT).get(0)).getAttribute(ATTR_NAME_PROJECT));
            //user_element.addContent(user_projects);
            
            
            xmlOutputter.output(document, new FileOutputStream(file));
            
        } catch(Exception e) {
            System.err.println("An exception has occurred in LoginValidator.addProject() " + e);
        }
    }
    
    public Element getProject(String project_name, String user_name){
        System.out.println("OurServlets.LoginValidator.getProject() project_name = ["+ project_name+"] "
                + " user_name = [" + user_name + "]");
        //Probably we cath an exception
        try{
            //Now we need to get the info in an specific project
            List projects = getProjects(user_name);
            System.out.println(projects);
            for(int i = 0; i < projects.size(); i++){
                //We get each element
                Element project = (Element)projects.get(i);
                System.out.println(project);
                //We compare the name of the element
                if(project.getAttributeValue(ATTR_NAME_PROJECT).equals(project_name)){
                    System.out.println(">>> I've found the project " + project_name + " of user " + user_name);
                    //If the project exits we can return the data
                    return project;
                }
            }
        }catch(Exception e){
            System.err.println("An exception has occurred in LoginValidator.getProject() " + e);
        }
        return null;
    }
    
    public List getProjects(String user){
        System.out.println("OurServlets.LoginValidator.getProjects() user = [" + user + "]");
        //Probably we catch an exception
        try{
            Element user_element = getUser(user);
            Element user_projects = user_element.getChild(PROJECTS);
            System.out.println("user projects : " + user_projects);
            System.out.println("List projects: " + user_projects.getChildren());
            return user_projects.getChildren();
        }catch(Exception e){
            System.err.println("An exception has occurred in LoginValidator.getProjects " + e);
        }
        return null;
    }
    
    public void dropProject(String user, String project_name){
        System.out.println("OurServlets.LoginValidator.dropProject() user = [" + user + "] "
                + " project_name = [" + project_name + "]");
        SAXBuilder saxBuilder = new SAXBuilder();
        File xml_file = new File(path_XML);
        //Probably we catch an exception
        try{
            
            System.out.println("Try clause");
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            Document document = saxBuilder.build(xml_file);
            Element root = document.getRootElement();
            List users_elements = root.getChildren();
            for(int i = 0; i < users_elements.size(); i++){
                System.out.println("users " + users_elements.toString());
                
                Element user_element = (Element)users_elements.get(i);
               
                if(user_element.getAttributeValue(ATTR_USER_NAME).equals(user)){
                    List projects = user_element.getChild(PROJECTS).getChildren();
                    for(int j = 0; j  < projects.size(); j++){
                        Element project = (Element)projects.get(j);
                        if(project.getAttributeValue(ATTR_NAME_PROJECT).equals(project_name)){
                            ((Element)(projects.get(j))).removeContent();
                            System.out.println("Project found");
                            xmlOutputter.output(document, new FileOutputStream(xml_file));
                            return;
                        }
                    }
                }
            }
        } catch(Exception e){
            System.err.println("An exception has occurred in LoginValidator.dropProject" + e);
        }
    }

}

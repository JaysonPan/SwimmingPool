package demo.mytest;

import act.Act;
import act.controller.Controller;
import act.inject.DefaultValue;
import act.util.Output;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
public class MyTest extends Controller.Util{
	
	List<String> categories = new ArrayList<>();
	List<List<String>> tests = new ArrayList<>();
	
	
	public void loadStable() throws ParserConfigurationException, SAXException, IOException {
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document dom = db.parse("stable.xml");
	    Element docEle = dom.getDocumentElement();
	    NodeList category_list = docEle.getChildNodes();
	    if (category_list != null) {
	        int category_amount= category_list.getLength();
	        for (int i = 1; i < category_amount; i++) {
	            if (category_list.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element test = (Element) category_list.item(i);
	                if ("test".equals(test.getNodeName())) {
                		List<String> class_name_list = new ArrayList<>();
	                	NodeList class_list = test.getElementsByTagName("class");
	                	if(class_list != null){
	                		int class_amount = class_list.getLength();
	                		for(int j = 0; j < class_amount; j++){
	    	                    String name = class_list.item(j).getAttributes().getNamedItem("name").getTextContent();
	    	                    class_name_list.add(name);
	                		}	                		
	                	}
	                	categories.add(test.getAttribute("name"));
	                	tests.add(class_name_list);
	                }
	            }
	        }
	   }
	}
	
    @GetAction("/")
    public Result home() throws ParserConfigurationException, SAXException, IOException {
    	loadStable();
    	List<Integer> amount = new ArrayList<>();
    	for (int i = 0; i < categories.size(); i++) {
    		amount.add(i);
    	}
        return render(categories, tests, amount);
    }
    
    @PostAction("/work")
    public void createWork(Work work){
    		System.out.println("Get it!!!!!!!!!!!!!!!!" + work);
    }

    public static void main(String[] args) throws Exception {
    	Act.start();
    }
    
    
    
 
}

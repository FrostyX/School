package zp4jv;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Config {
	
	public static final String DEFAULT_PATH = System.getProperty("user.dir") + "/../config.xml";
	private String path = null;
	
	public Config(String path) {
		this.path = path;
	}

	public ArrayList<VHostConfig> getVHosts() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document doc = documentBuilder.parse(new DataInputStream(new FileInputStream(path)));

		ArrayList<VHostConfig> vhosts = new ArrayList<VHostConfig>();
		NodeList nList = doc.getElementsByTagName("vhost");
		for(int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				VHostConfig vhost = new VHostConfig (
					Integer.valueOf(eElement.getAttribute("port")),
					eElement.getAttribute("name"),
					eElement.getAttribute("documentRoot")
				);
				vhosts.add(vhost);
			}
		}
		return vhosts;
	}

	public void save(ArrayList<VHostConfig> vhosts) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		saveVHosts(vhosts);
	}
	
	public void initializeConfigFile() throws SAXException, IOException, ParserConfigurationException, TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		NodeList vhosts = doc.getElementsByTagName("vhosts");
		if(vhosts.getLength() != 0) 
			return;

		Element element = doc.createElement("vhosts");
		element.setTextContent("\n");
		doc.appendChild(element);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	}
	
	private void saveVHosts(ArrayList<VHostConfig> vhosts) throws SAXException, IOException, ParserConfigurationException, TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(path);

		Node vhostsNode = doc.getElementsByTagName("vhosts").item(0);
		NodeList list = vhostsNode.getChildNodes();

		for(int i = 0; i < list.getLength(); i++) {

			Node node = list.item(i);
			if("vhost".equals(node.getNodeName())) {
				vhostsNode.removeChild(node);
			}
		}
		
		for(VHostConfig vhost : vhosts) {
			Element element = doc.createElement("vhost");
			element.setAttribute("port", String.valueOf(vhost.getPort()));
			element.setAttribute("name", vhost.getName());
			element.setAttribute("documentRoot", vhost.getDocumentRoot());
			vhostsNode.appendChild(element);
		}

		// http://stackoverflow.com/a/1384816/3285282
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getName() {
		File f = new File(path);
		return f.getName();
	}
}

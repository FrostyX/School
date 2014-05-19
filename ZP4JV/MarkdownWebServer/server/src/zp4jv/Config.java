package zp4jv;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Config {
	
	public static final String DEFAULT_CONFIG = "config.xml";
	

	public static ArrayList<VHostConfig> getVHosts() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document doc = documentBuilder.parse(new DataInputStream(new FileInputStream(DEFAULT_CONFIG)));

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
}

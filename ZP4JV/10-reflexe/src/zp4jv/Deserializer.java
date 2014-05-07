package zp4jv;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Deserializer {
	
	private Document doc;

	public Deserializer(InputStream input) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		doc = documentBuilder.parse(input);
	}
	
	public Object deserialize(Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object o = clazz.newInstance();
		Node node = doc.getFirstChild();
		
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
	        Node child = node.getChildNodes().item(i);
	        String key = child.getNodeName();
			String value = child.getTextContent();
			Method method = findMethod(o, key);
			
			invokeMethod(method, o, value);
		}
		return o;
	}
	
	private Method findMethod(Object o, String key) {
		for (Method method : o.getClass().getMethods()) {
			if(method.getAnnotation(Deserialize.class) != null) {
				if(key.equals(method.getAnnotation(Deserialize.class).as())) {
					return method;
				}
			}
		}
		return null;
	}
	
	private void invokeMethod(Method method, Object o, String value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class<?> type = method.getParameterTypes()[0];
		if (type == String.class) {
			method.invoke(o, value);	
		} else if (type == double.class) {
			method.invoke(o, Double.parseDouble(value));
		} else if (type == int.class) {
			method.invoke(o, Integer.parseInt(value));
		}
	}
}

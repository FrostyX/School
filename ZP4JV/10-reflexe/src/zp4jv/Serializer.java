package zp4jv;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Serializer {
	
	private XMLStreamWriter doc;

	public Serializer(OutputStream output) throws XMLStreamException {
		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
		doc = xmlOutputFactory.createXMLStreamWriter(output);
	}

	public void serialize(Object obj) throws XMLStreamException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		storeHeader(obj.getClass().getAnnotation(Serialize.class).as());
		for(Method method : obj.getClass().getDeclaredMethods()) {

			if(method.getAnnotation(Serialize.class) != null) {
				String key = method.getAnnotation(Serialize.class).as();
				Object result =  method.invoke(obj);
				store(key, result.toString());
			}
		}
		storeFooter();
	}
	
	private void storeHeader(String className) throws XMLStreamException {
		doc.writeStartElement(className);
	}
	
	private void store(String key, String value) throws XMLStreamException {
		doc.writeStartElement(key);
		doc.writeCharacters(value);
		doc.writeEndElement();
	}

	private void storeFooter() throws XMLStreamException {
		doc.writeEndElement();
	}
}

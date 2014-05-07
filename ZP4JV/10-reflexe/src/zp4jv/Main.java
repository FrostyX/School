package zp4jv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		try {
			
			String storage = "/tmp/serialized";
			FileOutputStream os = new FileOutputStream(storage);
			FileInputStream is = new FileInputStream(storage);

			Serializer serializer = new Serializer(new DataOutputStream(os));
			serializer.serialize(new Employee("Jakub Kadlčík", 21, 0));
			os.close();
			
			Deserializer deserializer = new Deserializer(new DataInputStream(is));
			Employee emp = (Employee) deserializer.deserialize(Employee.class);
			is.close();

			System.out.printf("%s - %d - %.2f", emp.getName(), emp.getAge(), emp.getSalary());

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}

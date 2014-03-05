package zp4jv;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class DOMTimesheetReaderWriter implements TimesheetReaderWriter {

	@Override
	public Timesheet loadTimesheet(InputStream input) throws Exception {
		// DOM
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document doc = documentBuilder.parse(input);

		// http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
		ArrayList<Activity> activities = new ArrayList<Activity>();
		for(int i = 0; i < doc.getElementsByTagName("activity").getLength(); i++) {

			Activity activity = new Activity(
				tagAttributeContent(doc, "activity", "date", i),
				Integer.valueOf(tagAttributeContent(doc, "activity", "hours", i)),
				tagContent(doc, "activity", i)
			);
			activities.add(activity);
		}

		Timesheet sheet = new Timesheet(
			tagContent(doc, "name"),
			tagContent(doc, "occupation"),
			activities
		);
		return sheet;
	}

	@Override
	public void storeTimesheet(OutputStream output, Timesheet timesheet) throws Exception {
		// DOM
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document doc = documentBuilder.newDocument();

		// Timesheet
		Element rootElement = doc.createElement("timesheet");
		doc.appendChild(rootElement);

		// Name
		Element nameNode = doc.createElement("name");
		nameNode.setTextContent(timesheet.getName());
		rootElement.appendChild(nameNode);

		// Occupation
		Element occupationNode = doc.createElement("occupation");
		occupationNode.setTextContent(timesheet.getOccupation());
		rootElement.appendChild(occupationNode);

		// Activities
		Element activitiesNode = doc.createElement("activities");
		rootElement.appendChild(activitiesNode);

		for(Activity a : timesheet.getActivities()) {
			Element activityNode = doc.createElement("activity");
			activityNode.setAttribute("date", a.getDate());
			activityNode.setAttribute("hours", String.valueOf(a.getHours()));
			activityNode.setTextContent(a.getText());
			activitiesNode.appendChild(activityNode);
		}

		DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
		DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");

		LSSerializer serializer = impl.createLSSerializer();
		LSOutput lsOutput = impl.createLSOutput();
		lsOutput.setByteStream(output);
		serializer.write(doc, lsOutput);
	}

	private String tagContent(Document doc, String tag) {
		return tagContent(doc, tag, 0);
	}

	private String tagContent(Document doc, String tag, int index) {
		return doc.getElementsByTagName(tag).item(index).getTextContent();
	}

	private String tagAttributeContent(Document doc, String tag, String attribute, int index) {
		return doc.getElementsByTagName(tag).item(index).getAttributes().getNamedItem(attribute).getTextContent();
	}
}

package zp4jv;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class StAXTimesheetReaderWriter implements TimesheetReaderWriter {

	@Override
	public Timesheet loadTimesheet(InputStream input) throws Exception {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(input);

		Timesheet sheet = new Timesheet();
		ArrayList<Activity> activities = new ArrayList<Activity>();

		String element = "";
		while (reader.hasNext()) {

			switch (reader.next()) {
			case XMLStreamReader.START_ELEMENT:
				element = reader.getName().toString();
				if(element.equals("activity")) {
					activities.add(new Activity(
						reader.getAttributeLocalName(0),
						Integer.valueOf(reader.getAttributeValue(1)),
						""
					));
				}
				break;

			case XMLStreamReader.END_ELEMENT:
				// Workaround - z nějakého dúvodu se START_ELEMENT pro každý tag splní dvakrát
				reader.next();
				break;

			case XMLStreamReader.CHARACTERS:
				if(element.equals("name")) {
					sheet.setName(reader.getText());
				}
				else if(element.equals("occupation")) {
					sheet.setOccupation(reader.getText());
				}
				else if(element.equals("activity")) {
					activities.get(activities.size() - 1).setText(reader.getText());
				}
				break;
			}
		}

		sheet.setActivities(activities);
		return sheet;
	}

	@Override
	public void storeTimesheet(OutputStream output, Timesheet timesheet) throws Exception {
		// StAX
		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
		XMLStreamWriter xmlWriter = xmlOutputFactory.createXMLStreamWriter(output);
		
		xmlWriter.writeStartDocument();
		xmlWriter.writeStartElement("timesheet");
		
		// Name
		xmlWriter.writeStartElement("name");
		xmlWriter.writeCharacters(timesheet.getName());
		xmlWriter.writeEndElement();
		
		// Occupation
		xmlWriter.writeStartElement("occupation");
		xmlWriter.writeCharacters(timesheet.getOccupation());
		xmlWriter.writeEndElement();

		// Activities
		xmlWriter.writeStartElement("activities");
		for(Activity activity : timesheet.getActivities()) {
			xmlWriter.writeStartElement("activity");
			xmlWriter.writeAttribute("date", activity.getDate());
			xmlWriter.writeAttribute("hours", String.valueOf(activity.getHours()));
			xmlWriter.writeCharacters(activity.getText());
			xmlWriter.writeEndElement();
		}
		xmlWriter.writeEndElement(); 

		xmlWriter.writeEndElement(); // timesheet
		xmlWriter.writeEndDocument();
	}
}

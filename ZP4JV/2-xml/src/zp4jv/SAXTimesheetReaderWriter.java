package zp4jv;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class SAXTimesheetReaderWriter implements TimesheetReaderWriter {

	private Timesheet sheet = new Timesheet();
	private ArrayList<Activity> activities = new ArrayList<Activity>();

	@Override
	public Timesheet loadTimesheet(InputStream input) throws Exception {

		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();

		parser.parse(input, new DefaultHandler() {
			private String element;

			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				element = qName;
				if(element.equals("activity")) {
					activities.add(new Activity(
						attributes.getValue("date"),
						Integer.valueOf(attributes.getValue("hours")),
						""
					));
				}
			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				if(element.equals("name")) {
					sheet.setName(new String(ch, start, length));
				}
				else if(element.equals("occupation")) {
					sheet.setOccupation(new String(ch, start, length));
				}
				else if(element.equals("activity")) {
					activities.get(activities.size() - 1).setText(new String(ch, start, length));
				}

				// Workaround - z nějakého dúvodu se startElement pro každý tag volá dvakrát
				element = "";
			}
		});

		sheet.setActivities(activities);
		return sheet;
	}

	@Override
	public void storeTimesheet(OutputStream output, Timesheet timesheet) throws SAXWriteException {
		throw new SAXWriteException("SAX doesn't support storing data");
	}
}

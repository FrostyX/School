package zp4jv;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

public class Main {

	public static void main(String[] args) {

		String xmlFile = "/home/frostyx/docs/skola/upol/ZP4JV/2-xml/data/timesheets/tomas-marny.xml";
		try {
			// I/O
			DataInputStream is = null;
			OutputStream os = null;

			// 1. DOM
			is = new DataInputStream(new FileInputStream(xmlFile));
			os = new ByteArrayOutputStream();

			DOMTimesheetReaderWriter dom = new DOMTimesheetReaderWriter();
			Timesheet domSheet = dom.loadTimesheet(is);
			dom.storeTimesheet(os, domSheet);
			System.out.println(domSheet);

			// 2. SAX
			is = new DataInputStream(new FileInputStream(xmlFile));
			os = new ByteArrayOutputStream();

			SAXTimesheetReaderWriter sax = new SAXTimesheetReaderWriter();
			Timesheet saxSheet = sax.loadTimesheet(is);
			System.out.println(saxSheet);


			// 3. StAX
			is = new DataInputStream(new FileInputStream(xmlFile));
			os = new ByteArrayOutputStream();

			StAXTimesheetReaderWriter stax = new StAXTimesheetReaderWriter();
			Timesheet staxSheet = stax.loadTimesheet(is);
			stax.storeTimesheet(os, staxSheet);
			System.out.println(staxSheet);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package zp4jv;

import java.io.InputStream;
import java.io.OutputStream;

public interface TimesheetReaderWriter {

	/**
	* Nacte ze streamu XML soubor a dle nej vytvori prislusny objekt timesheet
	*/
	public Timesheet loadTimesheet(InputStream input) throws Exception;

	/**
	* Ulozi do prislusneho streamu XML soubor predstavujici dany timesheet
	*/
	public void storeTimesheet(OutputStream output, Timesheet timesheet) throws Exception;

}

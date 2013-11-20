package zp3jv;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IO {
	/**
	 * Výstupní zařízení.
	 * Podporované: standardní výstup, textový soubor
	 * Pro výstup do souboru, nastavte do této proměnné cestu
	 */
	private static String output = null;

	public static void writeLine(Object obj) {
		if (output == null)
			System.out.println(obj);
		else {
		}
	}

	public static void write(File f) {
		for(String line : f.getContent()) {
			System.out.println(line);
		}
	}

	// @TODO napsat .ToString() pro File
	//public static void write(Object obj) {
		//writeLine(obj);
	//}

	public static ArrayList<String> readFile(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		ArrayList<String> file = new ArrayList<String>();

		String line;
		while((line = reader.readLine()) != null) {
			file.add(line);
		}
		reader.close();
		return file;
	}

	/**
	 * @return the output
	 */
	public static String getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public static void setOutput(String output) {
		IO.output = output;
	}
}

package zp3jv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

public class IO {

	private static boolean colored = true;
	private static boolean numberLines = false;

	/**
	 * Výstupní zařízení.
	 * Podporované: standardní výstup, textový soubor
	 * Pro výstup do souboru, nastavte do této proměnné cestu
	 */
	private static String output = null;

	public static void write(DiffFile file) throws FileNotFoundException, IOException {
		if(!colored) {
			write(file.toString());
		} else {
			String s = "";
			for(DiffLine line : file.getContent())
				s += colorForSymbol(line.getSymbol())
					+ line.toString() + System.getProperty("line.separator");
			write(s);
		}
	}

	public static void write(Object obj) throws FileNotFoundException, IOException {
		if (output == null)
			System.out.print(obj);
		else {
			write(obj, new PrintWriter(output));
		}
	}

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

	private static void write(Object obj, Writer w) throws IOException {
		Writer writer = w;
		writer.write(obj.toString());
		writer.close();
	}

	private static String colorForSymbol(String symbol) {
		if(symbol == Diff.KEEP_SYMBOL) return Console.COLORS.get("RESET");
		if(symbol == Diff.ADD_SYMBOL)  return Console.COLORS.get("GREEN");
		if(symbol == Diff.DEL_SYMBOL)  return Console.COLORS.get("RED");
		return null;
	}

	/**
	 * @return the colored
	 */
	public static boolean isColored() {
		return colored;
	}

	/**
	 * @param colored the colored to set
	 */
	public static void setColored(boolean colored) {
		IO.colored = colored;
	}

	/**
	 * @return the numberLines
	 */
	public static boolean isNumberLines() {
		return numberLines;
	}

	/**
	 * @param numberLines the numberLines to set
	 */
	public static void setNumberLines(boolean numberLines) {
		IO.numberLines = numberLines;
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

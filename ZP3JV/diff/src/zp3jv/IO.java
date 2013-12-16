package zp3jv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

/* Slouží pro vstup a výstup z programu
 * @author Jakub Kadlčík
 * @version 1.0
 */
public class IO {

	private static boolean colored = true;
	private static boolean numberLines = false;

	/**
	 * Výstupní zařízení.
	 * Podporované: standardní výstup, textový soubor
	 * Pro výstup do souboru, nastavte do této proměnné cestu
	 */
	private static String output = null;

	/**
	  * Vypíše virtuální soubor diffu. Na základě hodnot třídy (ne)vypíše čísla řádků a barvičky
	  * @param file - Vypisovaný soubor
	  * @throws FileNotFoundException - Soubor neexistuje, Nedostatečné oprávnění
	  * @throws IOException
	  */
	public static void write(DiffFile file) throws FileNotFoundException, IOException {

		// Počet cifer posledního číslá řádku
		// http://stackoverflow.com/a/1306751
		int width = (int)(Math.log10(file.getContent().get(file.getContent().size()-1).getNumber())+1);

		StringBuilder s = new StringBuilder();
		for(DiffLine line : file.getContent()) {
			if(colored) {
				s.append(colorForSymbol(line.getSymbol()));
			}
			if(numberLines) {
				// Zarovná čísla řádků do sloupečků (podle čísla s největším počtem cifer)
				String f1 = !line.getSymbol().equals(Diff.ADD_SYMBOL) ? String.valueOf(line.getNumber()) : "";
				String f2 = !line.getSymbol().equals(Diff.DEL_SYMBOL) ? String.valueOf(line.getNumber()) : "";
				s.append(String.format("%-" + width + "s | %" + width + "s | ", f1, f2));
			}
			s.append(line.toString() + System.getProperty("line.separator"));
		}
		write(s);
	}

	/**
	  * Vypíše objekt na požadovaném rozhraní
	  * Podporované: standardní výstup, textový soubor
	  * @param obj - Vypisovaný objekt
	  * @throws FileNotFoundException - Soubor neexistuje, Nedostatečné oprávnění
	  * @throws IOException
	  */
	public static void write(Object obj) throws FileNotFoundException, IOException {
		if (output == null)
			System.out.format(obj.toString());
		else {
			write(obj, new PrintWriter(output));
		}
	}

	/**
	  * Vrátí obsah textového souboru
	  * @param path - Cesta k souboru
	  * @throws IOException
	  */
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
	  * Zapíše objekt na rozhraní předepsane Writer-em
	  * @param obj - Zapisovaný ojekt
	  * @param w - Třída implementující rozhraní Writer {@link java.io.Writer}
	  * @throws IOException
	  */
	private static void write(Object obj, Writer w) throws IOException {
		Writer writer = w;
		writer.write(obj.toString());
		writer.close();
	}

	/**
	  * Vrátí obarvení textu podle symbolu operace
	  * @param symbol - Symbol operace
	  * @return Řetězec obarvující konzoli daného OS
	  * @see zp3jv.Console.Constants
	  * @see zp3jv.Diff
	  */
	private static String colorForSymbol(String symbol) {
		if(symbol.equals(Diff.ADD_SYMBOL)) return Console.Constants.GREEN;
		if(symbol.equals(Diff.DEL_SYMBOL)) return Console.Constants.RED;
		return Console.Constants.RESET;
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

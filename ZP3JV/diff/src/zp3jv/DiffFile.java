package zp3jv;

import java.util.ArrayList;

/* Reprezentuje virtuální soubor jež je výsledkem porovnání dvou textových souborů
 * @author Jakub Kadlčík
 * @version 1.0
 */
public class DiffFile {

	private ArrayList<DiffLine> content = new ArrayList<DiffLine>();

	/**
	 * Vytvoří nový virtuální soubor pro výsledek porovnání dvou textových souborů
	 */
	public DiffFile() {
	}

	/**
	  * Přidá řádek do virtuálního souboru
	  * @param line - Přidávaný řádek
	  * @return void
	  */
	public void add(DiffLine line) {
		content.add(line);
	}

	/**
	  * Připojí jeden virtuální soubor na konec druhého
	  * @param line - Přidávaný soubor
	  * @return void
	  */
	public void add(DiffFile file) {
		for(DiffLine line : file.getContent())
			content.add(line);
	}

	/**
	 * @return the content
	 */
	public ArrayList<DiffLine> getContent() {
		return content;
	}

	/**
	  * Vrátí obsah celého souboru jako řetězec.
	  */
	@Override
	public String toString() {

		String s = "";
		for(DiffLine line : content) {
			s += line.toString() + System.getProperty("line.separator");
		}
		return s;
	}
}

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

	/*
	public String _toString() {
		String s = "";
		String lastSymbol = "";
		String lastAction = "";
		int lastChangedLine = 0;
		int next = 0;
		for(int i = 0; i < content.size(); i++) {
			DiffLine line = content.get(i);

			if(line.getSymbol() == Diff.KEEP_SYMBOL)
				continue;

			//System.out.println(line.getNumber() + "vs" + next);
			if((lastSymbol != line.getSymbol()) || (line.getNumber() > next)) {
				String nextSymbol = nextChangedSymbol(i);
				//String nextSymbolWithoutKeep = nextChangedSymbolWithoutKeep(i);

				// Tady to failne
				//s += line.getNumber()-1-next+": if(("+nextSymbolWithoutKeep+"=="+Diff.KEEP_SYMBOL+") || ((("+lastAction+" == \"a\") || ("+lastAction+" == \"d\")) && ("+nextSymbol+" == "+line.getSymbol()+")))\n";
				//if((nextSymbol == Diff.KEEP_SYMBOL) || (((lastAction == "a") || (lastAction == "d")) && (nextSymbol == line.getSymbol()))) {
				//s+= line.getNumber()-1-next+
				//s+= next-(line.getNumber()-1)+": if(("+nextSymbolWithoutKeep+".equals("+Diff.KEEP_SYMBOL+")) || ((("+lastAction+".equals(\"a\")) || ("+lastAction+".equals(\"d\"))) && ("+nextSymbol+".equals("+line.getSymbol()+"))))";
				//if((nextSymbol.equals(Diff.KEEP_SYMBOL)) || (((lastAction.equals("a")) || (lastAction.equals("d"))) && (nextSymbol.equals(line.getSymbol())))) {
				if((nextSymbol.equals(Diff.KEEP_SYMBOL)) || (((lastAction.equals("a")) || (lastAction.equals("d"))) && (nextSymbol.equals(line.getSymbol())))) {
					s +=
						line.getNumber()-1-next
						+ "a"
						+ line.getNumber()
						+ (nextChangedLineNumber(i) > line.getNumber() ? "," + nextChangedLineNumber(i+1) : "")
					;
					lastAction = "a";
				}
				else {
					if(line.getNumber() > lastChangedLine) {
						s +=
							line.getNumber()-next
							+ "c"
							+ line.getNumber()
							+ "," + nextChangedLineNumber(i+1)
						;
						lastChangedLine = line.getNumber();
						lastAction = "c";
					} else {
						s += "---";
					}
				}
				next = nextChangedLineNumber(i);
				s += System.getProperty("line.separator");
			}

			s += line.toString() + System.getProperty("line.separator");
			lastSymbol = line.getSymbol();
		}
		return s;
	}

	protected int nextChangedLineNumber(int k) {
		String symbol = content.get(k).getSymbol();
		int keep = 0;
		int i;
		for(i = k; i < content.size(); i++) {
			if(content.get(i).getSymbol() == Diff.KEEP_SYMBOL) {
				keep++;
			} else {
				if(content.get(i).getSymbol() != symbol)
					return content.get(i).getNumber();
			}
		}
		return i-keep-1;
	}

	protected String nextChangedSymbol(int k) {
		String symbol = content.get(k).getSymbol();
		int i;
		for(i = k; i < content.size()-1; i++) {
			if(content.get(i).getSymbol() != symbol)
				break;
		}
		return content.get(i).getSymbol();
	}

	protected String nextChangedSymbolWithoutKeep(int k) {
		String symbol = content.get(k).getSymbol();
		int i;
		for(i = k; i < content.size()-1; i++) {
			if(content.get(i).getSymbol() == Diff.KEEP_SYMBOL)
				continue;

			if(content.get(i).getSymbol() != symbol)
				break;
		}
		return content.get(i).getSymbol();
	}
	*/
}

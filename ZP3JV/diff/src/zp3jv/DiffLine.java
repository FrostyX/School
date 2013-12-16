package zp3jv;

/* Reprezentuje řádek virtuálního souboru jež je výsledkem porovnání dvou textových souborů
 * @author Jakub Kadlčík
 * @version 1.0
 */
public class DiffLine {
	private int number;
	private String symbol;
	private String text;

	/**
	 * Vytvoří nový řádek virtualního souboru diffu
	 * @param number - Číslo řádku
	 * @param symbol - Symbol prováděné operace {@link zp3jv.Diff.KEEP_SYMBOL}, {@link zp3jv.Diff.ADD_SYMBOL}, {@link zp3jv.Diff.DEL_SYMBOL}
	 * @param text - Text řádku
	 */
	public DiffLine(int number, String symbol, String text) {
		this.number = number;
		this.symbol = symbol;
		this.text = text;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DiffLine))
			return false;
		DiffLine other = (DiffLine) obj;
		if (number != other.number)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	/**
	 * Vrátí symbol následovaný textem řádku, jako řetězec
	 */
	@Override
	public String toString() {
		return symbol + " " + text;
	}
}



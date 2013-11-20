package zp3jv;

public class DiffLine {
	private int number;
	private String symbol;
	private String text;

	/**
	 * @param number
	 * @param symbol
	 * @param text
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
}



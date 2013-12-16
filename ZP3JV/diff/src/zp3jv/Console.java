package zp3jv;

/*
 * Konzolové prostředí
 * @author Jakub Kadlčík
 * @version 1.0
 */
public class Console {

	/**
	  * Konstanty konzolového prostředí
	  * Pouze pro UNIXové operační systémy
	  * http://www.tldp.org/HOWTO/Bash-Prompt-HOWTO/x329.html
	  * @author Jakub Kadlčík
	  * @version 1.0
	  */
	public class Constants {
		public static final String RESET  = "\033[0m";
		public static final String RED    = "\033[1;31m";
		public static final String GREEN  = "\033[1;32m";
		public static final String BLUE   = "\033[1;34m";
		public static final String YELLOW = "\033[1;33m";
		public static final String PURPLE = "\033[1;35m";
	}
}

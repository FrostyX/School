package zp3jv;

import java.util.HashMap;

public class Console {

	/**
	  * http://www.tldp.org/HOWTO/Bash-Prompt-HOWTO/x329.html
	  */
	@SuppressWarnings("serial")
	public static final HashMap<String, String> COLORS = new HashMap<String, String>() {{
		put("RESET",  "\033[0m");
		put("RED",    "\033[1;31m");
		put("GREEN",  "\033[1;32m");
		put("BLUE",   "\033[1;34m");
		put("YELLOW", "\033[1;33m");
		put("PURPLE", "\033[1;35m");
	}};
}

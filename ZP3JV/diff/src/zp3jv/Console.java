package zp3jv;

import java.util.HashMap;

public class Console {
	@SuppressWarnings("serial")
	public static final HashMap<String, String> COLORS = new HashMap<String, String>() {{
		put("RESET", "\\u001B[0m");
		put("RED", "\\u001B[31m");
	}};
}

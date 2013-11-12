package zp3jv;

import java.lang.StringBuilder;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Deque;

public class AwesomeString {
	public static String repeatChar1(char c, int n) {
		String s = "";
		for (int i = 0; i < n; i++)
			s += c;
		return s;
	}

	public static String repeatChar2(char c, int n) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < n; i++) {
			s.append(c);
		}
		return s.toString();
	}

	public static Map<String, Integer> freq(String s) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		StringBuilder word = new StringBuilder();
		for (char c : s.toCharArray()) {
			if ((c == '.') || (c == ',') || (c == ' ')
					|| ((c >= '0') && (c <= '9'))) {
				if (!word.toString().equals("")) {
					int occur = map.containsKey(word.toString()) ? map.get(word.toString()) : 0;
					map.put(word.toString(), occur + 1);
					word = new StringBuilder();
				}
			} else
				word.append(c);
		}
		return map;
	}

	public static Map<String, Integer> freqIgnoreCase(String s) {
		return AwesomeString.freq(s.toLowerCase());
	}

	public static int rpnCalc(String expr) {
		return rpnCalc(expr, new HashMap<String, Integer>());
	}

	public static int rpnCalc(String expr, Map<String, Integer> variables) {
		String separator = " ";
		Deque<Integer> stack = new LinkedList<Integer>();

		for (String element : expr.split(separator)) {
			if (isNumber(element))
				stack.push(Integer.parseInt(element));
			else if (variables.containsKey(element)) {
				stack.push(variables.get(element));
			} else {
				int a = stack.pop();
				int b = stack.pop();
				char operator = element.charAt(0);
				stack.push(doOperation(operator, b, a));
			}
		}
		return stack.pop();
	}

	private static boolean isNumber(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	private static int doOperation(char operation, int a, int b) {
		switch (operation) {
			case '+': return a + b;
			case '-': return a - b;
			case '*': return a * b;
			case '/': return a / b;
		}
		return 0;
	}
}

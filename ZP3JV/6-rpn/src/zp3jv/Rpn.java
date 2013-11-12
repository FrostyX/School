package zp3jv;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Jednoduchý interpret výrazů v polské notaci
 * @author Jakub Kadlčík
 * @version 1.0
 */
public class Rpn{

	public static final String T = "#t";
	public static final String F = "#f";
	public static final String comparators[] = {"<", ">", "=", "!=", "<=", ">="};
	public static final char arithmeticOperators[] = {'+', '-', '*', '/'};


	/**
	 * Vyhodnotí výraz v polské notaci
	 * @param expr - Výraz k vyhodnocení
	 * @return Číslo, #t, nebo #f
	 * @throws IllegalArgumentException - hodnoty a, b jsou vzájemně nesrovnatelné
	 * @throws UnsupportedOperationException - comparator není mezi podporovanými operátory
	 * @throws StringIndexOutOfBoundsException - Přebývající bílý znak
	 * @throws NoSuchElementException - nedostatek operandů
	 */
	public static Object rpnCalc(String expr) throws IllegalArgumentException,
			UnsupportedOperationException, NoSuchElementException {

		return rpnCalc(expr, new HashMap<String, Object>());
	}

	/**
	 * Vyhodnotí výraz v polské notaci
	 * @param expr - Výraz k vyhodnocení
	 * @param variables - Sada proměnných
	 * @return Číslo, #t, nebo #f
	 * @throws IllegalArgumentException - hodnoty a, b jsou vzájemně nesrovnatelné
	 * @throws UnsupportedOperationException - comparator není mezi podporovanými operátory
	 * @throws NoSuchElementException - nedostatek operandů
	 * @throws StringIndexOutOfBoundsException - Přebývající bílý znak
	 * @throws ArithmeticException - Dělení nulou
	 */
	public static Object rpnCalc(String expr, Map<String, Object> variables)
			throws IllegalArgumentException, UnsupportedOperationException,
			NoSuchElementException, ArithmeticException {

		String separator = " ";
		Deque<Object> stack = new LinkedList<Object>();

		// Pokud byla provedena operace s desetinným číslem, chceme vrátit desetinné číslo.
		// Nehledě na to, že desetinná část bude rovna nule.
		boolean returnFloat = false;

		List<String> exprSplit =  Arrays.asList(expr.split(separator));
		//for (String element : expr.split(separator)) {
		for (String element : exprSplit) {
			// Číslo
			if (isNumber(element)) {
				if(isFloat(element)) {
					returnFloat = true;
				}
				stack.push(Float.parseFloat(element));
			}

			// Pravdivostní hodnota
			else if (element.equals(T) || element.equals(F)) {
				stack.push(element);
			}

			// Proměnná
			else if (variables.containsKey(element)) {
				stack.push(variables.get(element));
			}

			// Operátor
			else {
				// Ternární operátor
				int i = exprSplit.indexOf(element);
				if((exprSplit.size() > i+1) && (exprSplit.get(i + 1).equals("?"))) {
					stack.push(element);
				}

				else if(element.equals("?")) {
					String operation = stack.pop().toString();
					float a = (Float) stack.pop();
					float b = (Float) stack.pop();
					float c = (Float) stack.pop();
					float d = (Float) stack.pop();
					stack.push(compare(operation, b, a).equals(T) ? c : d);

				}

				// Operátor porovnání
				else if(Arrays.asList(comparators).contains(element)) {
					Object a = stack.pop();
					Object b = stack.pop();
					stack.push(compare(element, b, a));

				}

				// Aritmetický operátor
				else if(new String(arithmeticOperators).contains(element)) {
					char operator = element.charAt(0);
					float a = (Float) stack.pop();
					float b = (Float) stack.pop();
					stack.push(doOperation(operator, b, a));
				}

				// Neznámý operátor
				else throw new UnsupportedOperationException();
			}
		}

		Object r = stack.pop();
		if(r instanceof String) {
			return r;
		} else if(returnFloat) {
			return r;
		} else {
			return ((Float)r).intValue();
		}
	}

	/**
	 * Porovná dvě hodnoty
	 * @param comparator - Operátor porovnání (podporované: "<", ">", "=", "!=", "<=", ">=")
	 * @param a
	 * @param b
	 * @return #f nebo #t
	 * @throws IllegalArgumentException - hodnoty a, b jsou vzájemně nesrovnatelné
	 * @throws UnsupportedOperationException - comparator není mezi podporovanými operátory
	 */
	private static String compare(String comparator, Object a, Object b)
			throws IllegalArgumentException, UnsupportedOperationException {
		// Číslo vs nečíselná hodnota
		if((isNumber(a.toString())) ^ (isNumber(b.toString()))) {
			throw new IllegalArgumentException();
		}

		// Číslo vs číslo
		else if((isNumber(a.toString())) && (isNumber(b.toString()))) {
			return compareArguments(comparator, (Float)a, (Float)b) ? T : F;
		}

		// Nečíselná hodnota vs nečíselná hodnota
		else {
			return compareArguments(comparator, a.toString(), b.toString()) ? T : F;
		}
	}

	/**
	 * Zjistí, zda může být argument převeden na číslo
	 * @param s
	 * @return boolean
	 */
	private static boolean isNumber(String s) {
		return isFloat(s) || isInteger(s);
	}

	/**
	 * Zjistí, zda může být argument převeden na desetinné číslo
	 * (Pouze desetinné číslo - musí tedy mít desetinnou čárku)
	 * @param s
	 * @return boolean
	 */
	private static boolean isFloat(String s) {
		try {
			Float.parseFloat(s);
		}
		catch (NumberFormatException e) {
			return false;
		}
		return isInteger(s) ? false : true;
	}

	/**
	 * Zjistí, zda může být argument převeden na celé číslo
	 * @param s
	 * @return boolean
	 */
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Provede aritmetickou operaci se zadanými operandy
	 * @param operation - Aritmetický operátor (podporované: '+', '-', '*', '/')
	 * @param a - Číslo
	 * @param b - Číslo
	 * @return Desetinné číslo
	 * @throws ArithmeticException - Dělení nulou
	 * @throws UnsupportedOperationException - operation není mezi podporovanými operátory
	 */
	private static float doOperation(char operation, float a, float b)
			throws UnsupportedOperationException, ArithmeticException {
		if((operation == '/') && (b == 0.0))
			throw new ArithmeticException("divide by zero");
		switch (operation) {
			case '+': return a + b;
			case '-': return a - b;
			case '*': return a * b;
			case '/': return a / b;
		}
		throw new UnsupportedOperationException();
	}

	// Java podporuje switch na datovém typu String až od verze 1.7,
	// kterou bohužel nemám k dispozici

	/**
	 * Porovná dva číselné argumenty
	 * @param comparator - Symbol porovnání (podporované: "<", ">", "=", "!=", "<=", ">=")
	 * @param a - Číslo
	 * @param b - Číslo
	 * @return boolean
	 * @throws UnsupportedOperationException - comparator není mezi podporovanými operátory
	 */
	private static boolean compareArguments(String comparator, float a, float b)
			throws UnsupportedOperationException {
		if      (comparator.equals("="))  { return a == b; }
		else if (comparator.equals("!=")) { return a != b; }
		else if (comparator.equals(">"))  { return a > b;  }
		else if (comparator.equals("<"))  { return a < b;  }
		else if (comparator.equals(">=")) { return a >= b; }
		else if (comparator.equals("<=")) { return a <= b; }
		throw new UnsupportedOperationException();
	}

	/**
	 * Porovná dva pravdivostní argumenty reprezentované pomocí #f, #t
	 * @param comparator - Symbol porovnání (podporované: "<", ">", "=", "!=", "<=", ">=")
	 * @param a - Číslo
	 * @param b - Číslo
	 * @return boolean
	 * @throws UnsupportedOperationException - comparator není mezi podporovanými operátory
	 */
	private static boolean compareArguments(String comparator, String a,
			String b) throws UnsupportedOperationException {
		if      (comparator.equals("="))  { return a.equals(b); }
		else if (comparator.equals("!=")) { return !a.equals(b); }
		else if (comparator.equals(">"))  { return a.equals(T) && b.equals(F); }
		else if (comparator.equals("<"))  { return a.equals(F) && b.equals(T); }
		else if (comparator.equals(">=")) { return a.equals(b) || (a.equals(T) && b.equals(F)); }
		else if (comparator.equals("<=")) { return a.equals(b) || (a.equals(F) && b.equals(T)); }
		throw new UnsupportedOperationException();
	}
}

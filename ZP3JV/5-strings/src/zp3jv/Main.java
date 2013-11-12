package zp3jv;

import java.util.Map;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		/*
		 * 1
		 */
		long t1 = System.currentTimeMillis();
		AwesomeString.repeatChar1('*', 10000);
		long t2 = System.currentTimeMillis();

		long t3 = System.currentTimeMillis();
		AwesomeString.repeatChar2('*', 10000);
		long t4 = System.currentTimeMillis();

		System.out.println("s += c: " + (t2 - t1));
		System.out.println("StringBuilder: " + (t4 - t3));
		System.out.println("-------------------------");

		/*
		 * 2
		 */
		String s1 = "Some foo bar string where foo is twice and bar 3 times, cause bar.";
		for (Map.Entry<String, Integer> entry : AwesomeString.freq(s1).entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("-------------------------");

		/*
		 * 3
		 */
		String s2 = "Some fOo BaR string where foo is twice and bAr 3 times, cause baR.";
		for (Map.Entry<String, Integer> entry : AwesomeString.freqIgnoreCase(s2).entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("-------------------------");

		/*
		 * 4
		 */
		String e1 = "1 32 + 42 * 5 + 66 -";
		System.out.println(e1 + " = " + AwesomeString.rpnCalc(e1));

		/*
		 * 5
		 */
		String e2 = "1 x + 42 * 5 + y -";
		Map<String, Integer> variables = new HashMap<String, Integer>();
		variables.put("x", 32);
		variables.put("y", 66);
		System.out.println(e1 + " = " + AwesomeString.rpnCalc(e2, variables));
	}
}

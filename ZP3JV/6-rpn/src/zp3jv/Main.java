package zp3jv;
import java.util.NoSuchElementException;

public class Main {

	public static void main(String[] args) {
		String e1 = "1 -32 + -42 * 5 + 66 -";
		String e2 = "1.1 2 +";
		String e3 = "1 1 !=";
		String e4 = "#t #f >=";
		String e5 = "1 2 3 4 < ?";

		try {
			System.out.println(e1 + " = " + Rpn.rpnCalc(e1));
			System.out.println(e2 + " = " + Rpn.rpnCalc(e2));
			System.out.println(e3 + " : " + Rpn.rpnCalc(e3));
			System.out.println(e4 + " : " + Rpn.rpnCalc(e4));
			System.out.println(e5 + " : " + Rpn.rpnCalc(e5));
		}
		catch (UnsupportedOperationException e) {
			System.out.println("Neexistující operátor!");
		}
		catch (IllegalArgumentException e) {
			System.out.println("Neporovnatelné hodnoty!");
		}
		catch (NoSuchElementException e) {
			System.out.println("Nedostatek operandů!");
		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("Výraz není ve správném tvaru. Interpret požaduje striktní dodržení bílých znaků!");
		}
		catch (ArithmeticException e) {
			System.out.println("Neumím dělit nulou. Vy ano?");
		}
	}
}

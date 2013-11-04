package zp3jv;

public class AwesomeArray {
	static int[] odd(int[] numbers) {
		/*
		 * Nejprve je potřeba zjistit počet lichých čísel Nelze vytvořit pole o
		 * stejné velikosti jako má výchozí, protože by na konci pole objevily
		 * nuly
		 */
		int oddCount = 0;
		for (int n : numbers) {
			if (n % 2 != 0)
				oddCount++;

		}

		// Vytvoříme pole pouze z lichých čísel a vrátíme
		int[] oddNumbers = new int[oddCount];
		int i = 0;
		for (int n : numbers)
			if (n % 2 != 0) {
				oddNumbers[i] = n;
				i++;
			}
		return oddNumbers;
	}

	static int[] merge(int[] a, int[] b) {
		int n = a.length + b.length;
		int[] merged = new int[n];
		for (int i = 0, ai = 0, bi = 0; i < n; i++) {
			if ((bi >= b.length) || ((ai < a.length) && (a[ai] < b[bi]))) {
				merged[i] = a[ai];
				ai++;
			} else {
				merged[i] = b[bi];
				bi++;
			}
		}
		return merged;
	}
}

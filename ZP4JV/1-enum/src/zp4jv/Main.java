package zp4jv;

public class Main {

	public static void main(String[] args) {
		// 1
		AnimalFarm farm = new AnimalFarm();
		farm.add(new Animal("Alík", AnimalSpecies.DOG, Sex.MALE));
		farm.add(new Animal("Bobík", AnimalSpecies.DUCK, Sex.FEMALE));
		farm.add(new Animal("Chubaka", AnimalSpecies.DOG, Sex.FEMALE));
		farm.add(new Animal("Donald", AnimalSpecies.DUCK, Sex.MALE));

		for(Animal animal : farm.list()) {
			System.out.println(animal);
		}

		// 2
		System.out.println(avg(1, 2, 3, 4));

		// 3
		String s = "A: %0; B: %1";
		System.out.println(formatStr(s, 1, "XY"));
	}

	public static double avg(double... numbers) {
		if(numbers.length == 0)
			return Double.NaN;

		double sum = 0;
		for(double number : numbers)
			sum += number;
		return sum / numbers.length;
	}

	public static String formatStr(String format, Object... args) {
		String output = "";
		for (int i = 0; i < format.length(); i++) {
			char c = format.charAt(i);
			if(c == '%') {
				int to = findNumberDelimiter(format.substring(i+1));
				int index = Integer.parseInt(format.substring(i+1, i+1+to));
				output += args[index].toString();
				i += index + 1;
			}
			else {
				output += c;
			}
		}
		return output;
	}

	private static int findNumberDelimiter(String s) {
		int i = 0;
		for(char c : s.toCharArray()) {
			if((c < '0') || (c > '9'))
				return i;
			i++;
		}
		return i;
	}
}

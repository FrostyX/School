package zp4jv;

import java.util.ArrayList;

public class AnimalFarm {
	private ArrayList<Animal> animals = new ArrayList<Animal>();

	public void add(Animal animal) {
		animals.add(animal);
	}

	public ArrayList<Animal> list() {
		return animals;
	}
}

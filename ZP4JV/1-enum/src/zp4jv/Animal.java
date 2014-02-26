package zp4jv;

public class Animal {
	private String name;
	private AnimalSpecies species;
	private Sex sex;

	public Animal(String name, AnimalSpecies species, Sex sex) {
		this.name = name;
		this.species = species;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AnimalSpecies getSpecies() {
		return species;
	}

	public void setSpecies(AnimalSpecies species) {
		this.species = species;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String toString() {
		return name + " je " 
			+ (sex.equals(Sex.MALE) ? species.getMale() : species.getFemale()) 
			+ " a dělá " + species.getSound();
	}
}

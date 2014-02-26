package zp4jv;

public enum AnimalSpecies {
	DOG ("pes", "fena", "haf-haf"),
	DUCK ("kačer", "kačena", "ga-ga");

	private String male;
	private String female;
	private String sound;

	AnimalSpecies(String male, String female, String sound) {
		this.male = male;
		this.female = female;
		this.sound = sound;
	}

	public String getMale() {
		return male;
	}

	public String getFemale() {
		return female;
	}

	public String getSound() {
		return sound;
	}
}

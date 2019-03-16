package me.krodnar.sevenkey.models;

public enum ChordGroup {
	TRIAD("Triad"),
	SUSPENDED("Suspended"),
	SIXTH("Sixth"),
	SEVENTH("Seventh"),
	NINTH("Ninth"),
	ELEVEN("Eleven"),
	THIRTEENTH("Thirteenth");

	private String name;

	ChordGroup(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}
}

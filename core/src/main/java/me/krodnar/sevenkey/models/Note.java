package me.krodnar.sevenkey.models;


public enum Note {
	BS("B#"),
	C("C"),

	CS("C#"),
	DB("Db"),

	D("D"),

	DS("D#"),
	EB("Eb"),

	E("E"),
	FB("Fb"),

	ES("E#"),
	F("F"),

	FS("F#"),
	GB("Gb"),

	G("G"),

	GS("G#"),
	AB("Ab"),

	A("A"),

	AS("A#"),
	BB("Bb"),

	B("B"),
	CB("Cb");

	private String notation;

	Note(String notation) {
		this.notation = notation;
	}

	public String getNotation() {
		return notation;
	}
}


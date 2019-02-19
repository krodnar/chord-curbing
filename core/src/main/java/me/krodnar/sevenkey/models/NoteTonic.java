package me.krodnar.sevenkey.models;


public enum NoteTonic {
	C("C"),
	CS("C#"),
	D("D"),
	DS("D#"),
	E("E"),
	F("F"),
	FS("F#"),
	G("G"),
	GS("G#"),
	A("A"),
	AS("A#"),
	B("B");

	private String notation;

	NoteTonic(String notation) {
		this.notation = notation;
	}

	public String getNotation() {
		return notation;
	}
}


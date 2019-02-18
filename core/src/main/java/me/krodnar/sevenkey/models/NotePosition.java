package me.krodnar.sevenkey.models;


public enum NotePosition {
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

	NotePosition(String notation) {
		this.notation = notation;
	}

	public String getNotation() {
		return notation;
	}
}


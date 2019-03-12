package me.krodnar.sevenkey.models;

import java.util.ArrayList;
import java.util.List;

import static me.krodnar.sevenkey.models.Note.Type.*;

public enum Note {
	BS(0, "B#", SHARP),
	C(0, "C", NATURAL),

	CS(1, "C#", SHARP),
	DB(1, "Db", FLAT),

	D(2, "D", NATURAL),

	DS(3, "D#", SHARP),
	EB(3, "Eb", FLAT),

	E(4, "E", NATURAL),
	FB(4, "Fb", FLAT),

	ES(5, "E#", SHARP),
	F(5, "F", NATURAL),

	FS(6, "F#", SHARP),
	GB(6, "Gb", FLAT),

	G(7, "G", NATURAL),

	GS(8, "G#", SHARP),
	AB(8, "Ab", FLAT),

	A(9, "A", NATURAL),

	AS(10, "A#", SHARP),
	BB(10, "Bb", FLAT),

	B(11, "B", NATURAL),
	CB(11, "Cb", FLAT);

	public enum Type {
		NATURAL, SHARP, FLAT
	}

	private int index;
	private String notation;
	private Type type;

	Note(int index, String notation, Type type) {
		this.index = index;
		this.notation = notation;
		this.type = type;
	}

	public static Note getByIndex(int index, Type type) {
		if (index < 0 || index > 11) {
			throw new IllegalArgumentException("Index is out of bounds (0 to 11).");
		}

		for (Note note : values()) {
			if (note.index == index && note.type == type) {
				return note;
			}
		}

		return null;
	}

	public static Note[] getByIndex(int index) {
		if (index < 0 || index > 11) {
			throw new IllegalArgumentException("Index is out of bounds (0 to 11).");
		}

		List<Note> notes = new ArrayList<>();
		for (Note note : values()) {
			if (note.index == index) {
				notes.add(note);
			}
		}

		return notes.toArray(new Note[]{});
	}

	public int getIndex() {
		return index;
	}

	public String getNotation() {
		return notation;
	}

	public Type getType() {
		return type;
	}
}


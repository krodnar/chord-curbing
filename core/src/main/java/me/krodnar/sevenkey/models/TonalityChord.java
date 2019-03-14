package me.krodnar.sevenkey.models;

import java.util.Map;

public class TonalityChord extends ConcreteChord {

	private final static Map<Integer, String> DEGREE_SYMBOLS = Map.of(
			1, "I",
			2, "II",
			3, "III",
			4, "IV",
			5, "V",
			6, "VI",
			7, "VII");

	private Tonality tonality;
	private int degree;
	private Note note;

	public TonalityChord(Tonality tonality, Chord chord, int degree, Note note, Octave octave) {
		super(chord, Key.of(note, octave));
		this.tonality = tonality;
		this.degree = degree;
		this.note = note;
	}

	@Override
	public String getNotation() {
		return note.getNotation() + tonality.getName() + DEGREE_SYMBOLS.get(degree);
	}

	public Tonality getTonality() {
		return tonality;
	}

	public int getDegree() {
		return degree;
	}

	public Note getNote() {
		return note;
	}
}

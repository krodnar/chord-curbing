package me.krodnar.sevenkey.models;

import java.util.List;
import java.util.Map;

import static me.krodnar.sevenkey.models.Note.*;

public class Tonality {

	public static final List<Note> MAJOR_NOTES = List.of(C, G, D, A, E, B, CB, FS, GB, DB, CS, AB, EB, BB, F);
	public static final List<Note> MINOR_NOTES = List.of(A, E, B, FS, CS, GS, AB, DS, EB, BB, AS, F, C, G, D);

	public static final List<Integer> DEGREES = List.of(0, 1, 2, 3, 4, 5, 6);

	public static final Map<Integer, String> MAJOR_DEGREE_SYMBOLS = Map.of(
			0, "I",
			1, "II",
			2, "III",
			3, "IV",
			4, "V",
			5, "VI",
			6, "VII");

	public static final Map<Integer, String> MINOR_DEGREE_SYMBOLS = Map.of(
			0, "i",
			1, "ii",
			2, "iii",
			3, "iv",
			4, "v",
			5, "vi",
			6, "vii");

	public enum Mode {
		MAJOR, MINOR
	}

	private static final List<Integer> MAJOR_DISTANCES = List.of(0, 2, 4, 5, 7, 9, 11, 12);
	private static final List<Integer> MINOR_DISTANCES = List.of(0, 2, 3, 5, 7, 8, 10, 12);

	private String name;
	private Chord[] chords;
	private Mode mode;

	public Tonality(String name, Mode mode, Chord[] chords) {
		this.name = name;
		this.mode = mode;
		this.chords = chords;
	}

	public static Tonality of(TonalityType type) {
		ChordType[] chordTypes = type.getChordTypes();
		Chord[] chords = new Chord[chordTypes.length];

		for (int i = 0; i < chordTypes.length; i++) {
			chords[i] = Chord.of(chordTypes[i]);
		}

		return new Tonality(type.getName(), type.getMode(), chords);
	}

	public ConcreteChord getConcreteChord(int degree, Note note, Octave octave, int inversion) {
		return TonalityChord.of(this, degree, note, octave, inversion);
	}

	public Chord getChord(int degree) {
		return chords[degree];
	}

	public Note getNote(int degree, Note rootNote) {
		List<Integer> distances = mode == Mode.MAJOR ? MAJOR_DISTANCES : MINOR_DISTANCES;
		int index = rootNote.getIndex() + distances.get(degree);
		return Note.getByIndex(index % 12)[0];
	}

	public Chord[] getChords() {
		return chords;
	}

	public String getName() {
		return name;
	}

	public Mode getMode() {
		return mode;
	}

	@Override
	public String toString() {
		return getName();
	}
}

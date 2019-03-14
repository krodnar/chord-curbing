package me.krodnar.sevenkey.models;

public class Tonality {

	private static final int[] MAJOR_STEPS = new int[]{2, 2, 1, 2, 2, 2, 1};
	private static final int[] MINOR_STEPS = new int[]{2, 1, 2, 2, 1, 2, 2};

	private static final int[] MAJOR_DISTANCES = new int[]{2, 4, 5, 7, 9, 11, 12};
	private static final int[] MINOR_DISTANCES = new int[]{2, 3, 5, 7, 8, 10, 12};

	public enum Mode {
		MAJOR, MINOR
	}

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

	public ConcreteChord getChord(int degree, Note note, Octave octave) {
		Chord chord = chords[degree];
		Key[] keys = getKeys(note, octave);
		return new TonalityChord(this, chord, degree, note, octave);
	}

	public Key[] getKeys(Note rootNote, Octave octave) {
		Key[] keys = new Key[8];
		int[] indexes = new int[7];
		int[] distances = mode == Mode.MAJOR ? MAJOR_DISTANCES : MINOR_DISTANCES;

		int startIndex = rootNote.getIndex();
		for (int i = 0; i < distances.length; i++) {
			indexes[i] = startIndex + distances[i];
		}

		keys[0] = Key.of(rootNote, octave);
		for (int i = 0; i < distances.length; i++) {
			keys[i] = Key.of(indexes[i]);
		}

		return keys;
	}

	public Chord[] getChords() {
		return chords;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}
}

package me.krodnar.sevenkey.models;

public class TonalityChord extends ConcreteChord {

	private Tonality tonality;
	private int degree;
	private Note rootNote;
	private Note tonalityRootNote;

	private TonalityChord(Tonality tonality, Chord chord, int degree, Note rootNote, Note tonalityRootNote, Octave octave) {
		super(chord, Key.of(rootNote, octave));
		this.tonality = tonality;
		this.degree = degree;
		this.rootNote = rootNote;
		this.tonalityRootNote = tonalityRootNote;
	}

	public static TonalityChord of(Tonality tonality, int degree, Note tonalityRootNote, Octave octave) {
		return TonalityChord.of(tonality, degree, tonalityRootNote, octave, 0);
	}

	public static TonalityChord of(Tonality tonality, int degree, Note tonalityRootNote, Octave octave, int inversion) {
		Note rootNote = tonality.getNote(degree, tonalityRootNote);
		Chord chord = tonality.getChord(degree);
		chord = chord.inverse(inversion);

		return new TonalityChord(tonality, chord, degree, rootNote, tonalityRootNote, octave);
	}

	@Override
	public String getNotation() {
		String notation = tonalityRootNote.getNotation() + " " + tonality.getName();

		if (tonality.getMode() == Tonality.Mode.MAJOR) {
			notation += " " + Tonality.MAJOR_DEGREE_SYMBOLS.get(degree);
		} else {
			notation += " " + Tonality.MINOR_DEGREE_SYMBOLS.get(degree);
		}

		if (getChord().isInverted()) {
			notation += " (" + getChord().getInversion() + " inversion)";
		}

		return notation;
	}

	public Tonality getTonality() {
		return tonality;
	}

	public int getDegree() {
		return degree;
	}

	public Note getRootNote() {
		return rootNote;
	}

	public Note getTonalityRootNote() {
		return tonalityRootNote;
	}
}

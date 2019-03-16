package me.krodnar.sevenkey.models;

public class NoteChord extends ConcreteChord {

	private Note note;
	private Octave octave;

	public NoteChord(Chord chord, Note note, Octave octave) {
		super(chord, Key.of(note, octave));
		this.note = note;
		this.octave = octave;
	}

	@Override
	public String getNotation() {
		String notation = note.getNotation() + octave.getIndex() + " " + getChord().getNaming();

		if (getChord().isInverted()) {
			notation += " (" + getChord().getInversion() + " inversion)";
		}

		return notation;
	}

	public Note getNote() {
		return note;
	}
}

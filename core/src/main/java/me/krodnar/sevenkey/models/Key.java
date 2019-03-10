package me.krodnar.sevenkey.models;

import java.util.Random;

public final class Key implements Comparable<Key> {

	public static final int MIN_INDEX = 0;
	public static final int MAX_INDEX = 131;

	private int index;
	private Note note;
	private Note[] notes;
	private Octave octave;

	private static final Random RANDOM = new Random();

	private Key(int index, Note note, Note[] notes, Octave octave) {
		this.index = index;
		this.note = note;
		this.notes = notes;
		this.octave = octave;
	}

	public static Key of(Note note, Octave octave) {
		int index = octave.getStartIndex() + note.getIndex();
		Note[] notes = Note.getByIndex(note.getIndex());
		return new Key(index, note, notes, octave);
	}

	public static Key of(int index) {
		int noteIndex = index % 12;
		int octaveIndex = (int) Math.floor(((float) index) / 12);

		Octave octave = Octave.getByIndex(octaveIndex);
		Note[] notes = Note.getByIndex(noteIndex);
		Note note;

		if (notes.length == 1) {
			note = notes[0];
		} else {
			note = Math.random() > 0.5 ? notes[0] : notes[1];
		}

		return new Key(index, note, notes, octave);
	}

	public static Key of(int index, Note.Type noteType) {
		int noteIndex = index % 12;
		int octaveIndex = (int) Math.floor(((float) index) / 12);

		Octave octave = Octave.getByIndex(octaveIndex);
		Note note = Note.getByIndex(noteIndex, noteType);
		Note[] notes = Note.getByIndex(noteIndex);

		return new Key(index, note, notes, octave);
	}

	public static Key getRandom() {
		return Key.of(RANDOM.nextInt(MAX_INDEX - MIN_INDEX + 1) + MIN_INDEX);
	}

	public static Key getRandomInRange(int minIndex, int maxIndex) {
		if (minIndex < MIN_INDEX || maxIndex > MAX_INDEX) {
			throw new IllegalArgumentException("Indexes should be in proper range.");
		}

		return Key.of(RANDOM.nextInt(maxIndex - minIndex + 1) + minIndex);
	}

	public static Key getRandomInRange(Octave octave) {
		return getRandomInRange(octave.getStartIndex(), octave.getEndIndex());
	}

	public static Key getRandomInRange(Octave minOctave, Octave maxOctave) {
		return minOctave == maxOctave ?
				getRandomInRange(minOctave) :
				getRandomInRange(minOctave.getStartIndex(), maxOctave.getEndIndex());
	}

	public static Key getByIndex(int index) {
		return Key.of(index);
	}

	public int getIndex() {
		return index;
	}

	public Octave getOctave() {
		return octave;
	}

	public Note getNote() {
		return note;
	}

	public String getNaming() {
		if (notes.length == 2) {
			return "(" + notes[0].getNotation() + "/" + notes[1].getNotation() + ") " + octave.getIndex();
		}
		return note.getNotation() + octave.getIndex();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Key)) return false;
		return index == ((Key) o).index;
	}

	@Override
	public int compareTo(Key o) {
		return Integer.compare(index, o.index);
	}

	@Override
	public String toString() {
		return getNaming();
	}
}

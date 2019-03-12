package me.krodnar.sevenkey.models;

import java.util.Set;
import java.util.TreeSet;

public class ConcreteChord {

	private Key rootKey;
	private Chord chord;
	private Note note;
	private TreeSet<Key> keys = new TreeSet<>();

	private ConcreteChord(Chord chord, Key rootKey) {
		this.rootKey = rootKey;
		this.chord = chord;

		TreeSet<Integer> keysIndex = chord.getKeysIndex(rootKey);
		for (Integer index : keysIndex) {
			keys.add(Key.getByIndex(index));
		}
	}

	private ConcreteChord(Chord chord, Note note, Octave octave) {
		this(chord, Key.of(note, octave));
		this.note = note;
	}

	public static ConcreteChord of(Chord chord, Key rootKey) {
		return new ConcreteChord(chord, rootKey);
	}

	public static ConcreteChord of(Chord chord, Note note, Octave octave) {
		Key key = Key.of(note, octave);
		return new ConcreteChord(chord, note, octave);
	}

	public Key getRootKey() {
		return rootKey;
	}

	public Chord getChord() {
		return chord;
	}

	public Set<Key> getKeys() {
		return keys;
	}

	public String getNotation() {
		String notation;
		if (note == null) {
			notation = rootKey + " " + chord.getNaming();
		} else {
			notation = note.getNotation() + rootKey.getOctave().getIndex() + " " + chord.getNaming();
		}

		if (chord.isInverted()) {
			notation += " (" + chord.getInversion() + " inversion)";
		}

		return notation;
	}

	@Override
	public String toString() {
		return getNotation();
	}
}

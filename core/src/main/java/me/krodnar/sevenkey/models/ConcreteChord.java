package me.krodnar.sevenkey.models;

import java.util.SortedSet;
import java.util.TreeSet;

public class ConcreteChord {

	private Key rootKey;
	private Chord chord;
	private TreeSet<Key> keys;

	public ConcreteChord(Chord chord, Key rootKey) {
		this.rootKey = rootKey;
		this.chord = chord;

		setKeys(chord);
	}

	public void inverse(int inversion) {
		chord = chord.inverse(inversion);
		setKeys(chord);
	}

	private void setKeys(Chord chord) {
		keys = new TreeSet<>();
		TreeSet<Integer> keysIndex = chord.getKeysIndex(rootKey);
		for (Integer index : keysIndex) {
			keys.add(Key.getByIndex(index));
		}
	}

	public Key getRootKey() {
		return rootKey;
	}

	public Chord getChord() {
		return chord;
	}

	public SortedSet<Key> getKeys() {
		return keys;
	}

	public String getNotation() {
		String notation = rootKey + " " + chord.getNaming();

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

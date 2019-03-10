package me.krodnar.sevenkey.models;

import java.util.Set;
import java.util.TreeSet;

public class ConcreteChord {

	private Key key;
	private Chord chord;
	private TreeSet<Key> keys = new TreeSet<>();

	public ConcreteChord(Chord chord, Key key) {
		this.key = key;
		this.chord = chord;

		TreeSet<Integer> keysIndex = chord.getKeysIndex(key);
		for (Integer index : keysIndex) {
			keys.add(Key.getByIndex(index));
		}
	}

	public Key getKey() {
		return key;
	}

	public Chord getChord() {
		return chord;
	}

	public Set<Key> getKeys() {
		return keys;
	}

	public String getNotation() {
		String notation = key + " " + chord.getNaming();
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

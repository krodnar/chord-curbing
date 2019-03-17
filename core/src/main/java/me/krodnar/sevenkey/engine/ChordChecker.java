package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Key;

import javax.sound.midi.ShortMessage;
import java.util.Set;
import java.util.TreeSet;

public class ChordChecker {

	private ConcreteChord chord;
	private Set<Key> pressedKeys = new TreeSet<>();

	public ChordChecker() {

	}

	public boolean check() {
		return isCorrectChord();
	}

	private void noteOn(Key key) {
		pressedKeys.add(key);
	}

	private void noteOff(Key key) {
		pressedKeys.remove(key);
	}

	public void setChord(ConcreteChord chord) {
		this.chord = chord;
	}

	public boolean isCorrectChord() {
		return chord != null && chord.getKeys().equals(pressedKeys);
	}

	public ConcreteChord getChord() {
		return chord;
	}

	public Set<Key> getPressedKeys() {
		return pressedKeys;
	}

	public void noteCommand(Key key, int command) {
		if (command == ShortMessage.NOTE_ON) noteOn(key);
		else if (command == ShortMessage.NOTE_OFF) noteOff(key);
	}
}

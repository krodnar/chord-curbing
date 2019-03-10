package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Note;

import javax.sound.midi.ShortMessage;
import java.util.Set;
import java.util.TreeSet;

public class ChordChecker {

	private ConcreteChord chord;
	private Set<Note> pressedNotes = new TreeSet<>();

	public ChordChecker() {

	}

	public boolean check() {
		return isCorrectChord();
	}

	private void noteOn(Note note) {
		pressedNotes.add(note);
	}

	private void noteOff(Note note) {
		pressedNotes.remove(note);
	}

	public void setChord(ConcreteChord chord) {
		this.chord = chord;
	}

	public boolean isCorrectChord() {
		return chord != null && chord.getNotes().equals(pressedNotes);
	}

	public ConcreteChord getChord() {
		return chord;
	}

	public Set<Note> getPressedNotes() {
		return pressedNotes;
	}

	public void noteCommand(Note note, int command) {
		if (command == ShortMessage.NOTE_ON) noteOn(note);
		else if (command == ShortMessage.NOTE_OFF) noteOff(note);
	}
}

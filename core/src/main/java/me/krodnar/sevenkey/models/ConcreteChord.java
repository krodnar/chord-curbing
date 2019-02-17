package me.krodnar.sevenkey.models;

import java.util.Set;
import java.util.TreeSet;

public class ConcreteChord {

	private Note note;
	private Chord chord;
	private TreeSet<Note> notes = new TreeSet<>();

	public ConcreteChord(Chord chord, Note note) {
		this.note = note;
		this.chord = chord;

		TreeSet<Integer> notesIndex = chord.getNotesIndex(note);
		for (Integer index : notesIndex) {
			notes.add(Note.getByIndex(index));
		}
	}

	public Note getNote() {
		return note;
	}

	public Chord getChord() {
		return chord;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public String getNotation() {
		String notation = note + " " + chord.getNaming();
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

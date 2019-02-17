package me.krodnar.sevenkey.core;

import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Note;

import javax.sound.midi.ShortMessage;
import java.util.Set;

public interface TrainerListener {

	void onStart();

	void onStop();

	void onCorrectChord(ConcreteChord chord);

	void onWrongChord(ConcreteChord chord, Set<Note> pressedNotes);

	void onNextChord(ConcreteChord chord);

	void onNoteOn(Note pressed, Set<Note> allNotes);

	void onNoteOff(Note released, Set<Note> allNotes);

	default void onNoteCommand(Note note, int command, Set<Note> pressedNotes) {
		if (command == ShortMessage.NOTE_ON) onNoteOn(note, pressedNotes);
		else if (command == ShortMessage.NOTE_OFF) onNoteOff(note, pressedNotes);
	}
}

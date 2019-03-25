package me.krodnar.sevenkey.core;

import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Key;

import javax.sound.midi.ShortMessage;
import java.util.Set;

public interface TrainerListener {

	void onCorrectChord(ConcreteChord chord);

	void onWrongChord(ConcreteChord chord, Set<Key> pressedKeys);

	void onNextChord(ConcreteChord chord);

	void onNoteOn(Key pressed, Set<Key> allKeys);

	void onNoteOff(Key released, Set<Key> allKeys);
}

package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.NoteTonic;

import java.util.List;

public interface ChordPickAlgorithm {

	ConcreteChord getChord();

	void setChordsPool(List<Chord> chordsPool);

	void setInversionsPool(List<Integer> inversionsPool);

	void setNoteTonicsPool(List<NoteTonic> noteTonicsPool);
}

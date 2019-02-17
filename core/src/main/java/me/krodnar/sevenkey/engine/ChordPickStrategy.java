package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Octave;

import java.util.List;

public interface ChordPickStrategy {

	ConcreteChord getChord();

	void setChordsPool(List<Chord> chordsPool);

	void setInversionsPool(List<Integer> inversionsPool);

	void setOctaveRange(Octave startOctave, Octave endOctave);

	void setStartOctave(Octave octave);

	void setEndOctave(Octave octave);
}

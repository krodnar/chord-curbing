package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Note;
import me.krodnar.sevenkey.models.Octave;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class RandomChordPickStrategy implements ChordPickStrategy {

	private static final Random RANDOM = new Random();

	private List<Chord> chordsPool;
	private List<Integer> inversionsPool;

	private Octave startOctave = Octave.CM;
	private Octave endOctave = Octave.C9;

	private int pickedInversion;
	private ArrayList<Integer> availableInversions;

	@Override
	public ConcreteChord getChord() throws IllegalStateException {

		if (chordsPool == null || chordsPool.isEmpty()) {
			throw new IllegalStateException("No chords in pool.");
		}

		if (inversionsPool == null || inversionsPool.isEmpty()) {
			throw new IllegalStateException("No inversions in pool.");
		}

		availableInversions = new ArrayList<>(inversionsPool);
		List<Chord> possibleChords = getPossibleChords();

		List<ConcreteChord> possibleConcreteChords;
		do {
			if (possibleChords.isEmpty()) {
				possibleChords = getPossibleChords();
			}

			Chord chord = possibleChords.remove(RANDOM.nextInt(possibleChords.size()));
			chord.inverse(pickedInversion);

			possibleConcreteChords = getPossibleConcreteChords(chord);
		} while (possibleConcreteChords.isEmpty());

		return possibleConcreteChords.get(RANDOM.nextInt(possibleConcreteChords.size()));
	}

	private List<ConcreteChord> getPossibleConcreteChords(Chord chord) {
		List<ConcreteChord> possibleConcreteChords = new ArrayList<>();

		int startNoteIndex = startOctave.getStartIndex();
		int endNoteIndex = endOctave.getEndIndex();

		for (int i = startNoteIndex; i < endNoteIndex; i++) {
			Note note = Note.getByIndex(i);
			TreeSet<Integer> notesIndex = chord.getNotesIndex(note);
			if (notesIndex.first() > startNoteIndex && notesIndex.last() < endNoteIndex) {
				possibleConcreteChords.add(new ConcreteChord(chord, note));
			}
		}

		return possibleConcreteChords;
	}

	private List<Chord> getPossibleChords() {
		List<Chord> possibleChords;

		if (availableInversions.isEmpty()) {
			throw new IllegalStateException("No possible chords.");
		}

		do {
			pickedInversion = availableInversions.remove(RANDOM.nextInt(availableInversions.size()));
			possibleChords = new ArrayList<>();

			for (Chord chord : chordsPool) {
				if (pickedInversion <= chord.getMaxInversion()) {
					possibleChords.add(chord);
				}
			}
		} while (possibleChords.isEmpty() && !availableInversions.isEmpty());

		if (possibleChords.isEmpty()) {
			throw new IllegalStateException("No possible chords.");
		}

		return possibleChords;
	}

	@Override
	public void setChordsPool(List<Chord> chordsPool) {
		this.chordsPool = new ArrayList<>(chordsPool);
	}

	@Override
	public void setInversionsPool(List<Integer> inversionsPool) {
		this.inversionsPool = new ArrayList<>(inversionsPool);
	}

	@Override
	public void setStartOctave(Octave octave) {
		startOctave = octave;
	}

	@Override
	public void setEndOctave(Octave octave) {
		endOctave = octave;
	}

	@Override
	public void setOctaveRange(Octave startOctave, Octave endOctave) {
		this.startOctave = startOctave;
		this.endOctave = endOctave;
	}
}

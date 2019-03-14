package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class RandomChordPickAlgorithm implements ChordPickAlgorithm {

	private static final Random RANDOM = new Random();

	private ChordPicker picker;

	private List<Chord> chordsPool;
	private List<Integer> inversionsPool;
	private List<Note> notesPool;

	private int pickedInversion;
	private ArrayList<Integer> availableInversions;

	public RandomChordPickAlgorithm(ChordPicker picker) {
		this.picker = picker;
	}

	@Override
	public ConcreteChord getChord() throws IllegalStateException {

		if (chordsPool == null || chordsPool.isEmpty()) {
			throw new IllegalStateException("No chords in pool.");
		}

		if (inversionsPool == null || inversionsPool.isEmpty()) {
			throw new IllegalStateException("No inversions in pool.");
		}

		if (notesPool == null || notesPool.isEmpty()) {
			throw new IllegalStateException("No notes in pool.");
		}

		availableInversions = new ArrayList<>(inversionsPool);
		List<Chord> possibleChords = getPossibleChords();

		List<ConcreteChord> possibleConcreteChords;
		do {
			if (possibleChords.isEmpty()) {
				possibleChords = getPossibleChords();
			}

			Chord chord = possibleChords.remove(RANDOM.nextInt(possibleChords.size()));
			chord = chord.inverse(pickedInversion);

			possibleConcreteChords = getPossibleConcreteChords(chord);
		} while (possibleConcreteChords.isEmpty());

		return possibleConcreteChords.get(RANDOM.nextInt(possibleConcreteChords.size()));
	}

	private List<ConcreteChord> getPossibleConcreteChords(Chord chord) {
		List<ConcreteChord> possibleConcreteChords = new ArrayList<>();

		int startKeyIndex = picker.getStartOctave().getStartIndex();
		int endKeyIndex = picker.getEndOctave().getEndIndex();

		for (int i = startKeyIndex; i < endKeyIndex; i++) {
			Key key = Key.getByIndex(i);

			if (!notesPool.contains(key.getNote())) {
				continue;
			}

			TreeSet<Integer> keysIndex = chord.getKeysIndex(key);
			if (keysIndex.first() > startKeyIndex && keysIndex.last() < endKeyIndex) {
				possibleConcreteChords.add(new ConcreteChord(chord, key));
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
	public void setNotesPool(List<Note> notesPool) {
		this.notesPool = notesPool;
	}
}

package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Key;
import me.krodnar.sevenkey.models.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;

class RandomChordPickAlgorithm implements ChordPickAlgorithm {

	private static final Random RANDOM = new Random();

	private ChordPicker picker;

	private List<Chord> chordsPool;
	private List<Note> notesPool;

	private int pickedInversion;
	private ArrayList<Integer> inversionsPool;

	RandomChordPickAlgorithm(ChordPicker picker) {
		this.picker = picker;
	}

	@Override
	public ConcreteChord pickChord() {

		chordsPool = new ArrayList<>();
		picker.chordsPool.forEach((key, value) -> {
			if (value) chordsPool.add(key);
		});

		notesPool = new ArrayList<>();
		picker.notesPool.forEach((key, value) -> {
			if (value) notesPool.add(key);
		});

		inversionsPool = new ArrayList<>(picker.inversionsPool);

		if (chordsPool.isEmpty()) {
			throw new IllegalStateException("No chords in pool.");
		}

		if (inversionsPool.isEmpty()) {
			throw new IllegalStateException("No inversions in pool.");
		}

		if (notesPool.isEmpty()) {
			throw new IllegalStateException("No notes in pool.");
		}

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

			SortedSet<Integer> keysIndex = chord.getKeysIndex(key);
			if (keysIndex.first() > startKeyIndex && keysIndex.last() < endKeyIndex) {
				possibleConcreteChords.add(new ConcreteChord(chord, key));
			}
		}

		return possibleConcreteChords;
	}

	private List<Chord> getPossibleChords() {
		List<Chord> possibleChords;

		if (inversionsPool.isEmpty()) {
			throw new IllegalStateException("No possible chords.");
		}

		do {
			pickedInversion = inversionsPool.remove(RANDOM.nextInt(inversionsPool.size()));
			possibleChords = new ArrayList<>();

			for (Chord chord : chordsPool) {
				if (pickedInversion <= chord.getMaxInversion()) {
					possibleChords.add(chord);
				}
			}
		} while (possibleChords.isEmpty() && !inversionsPool.isEmpty());

		if (possibleChords.isEmpty()) {
			throw new IllegalStateException("No possible chords.");
		}

		return possibleChords;
	}
}

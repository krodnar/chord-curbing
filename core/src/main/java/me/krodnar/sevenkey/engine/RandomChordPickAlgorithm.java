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
	private List<NotePosition> notePositionsPool;

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

		if (notePositionsPool == null || notePositionsPool.isEmpty()) {
			throw new IllegalStateException("No positions in pool.");
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

		int startNoteIndex = picker.getStartOctave().getStartIndex();
		int endNoteIndex = picker.getEndOctave().getEndIndex();

		for (int i = startNoteIndex; i < endNoteIndex; i++) {
			Note note = Note.getByIndex(i);

			if (!notePositionsPool.contains(note.getPosition())) {
				continue;
			}

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
	public void setNotePositionsPool(List<NotePosition> notePositionsPool) {
		this.notePositionsPool = notePositionsPool;
	}
}

package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.*;

import java.util.*;

class TonalityChordPickAlgorithm implements ChordPickAlgorithm {

	private static final Random RANDOM = new Random();

	private ChordPicker picker;

	private List<Octave> octavesRange;
	private int minIndex;
	private int maxIndex;

	TonalityChordPickAlgorithm(ChordPicker picker) {
		this.picker = picker;
	}

	@Override
	public ConcreteChord pickChord() {

		minIndex = picker.getStartOctave().getStartIndex();
		maxIndex = picker.getEndOctave().getEndIndex();

		octavesRange = new ArrayList<>();
		for (Octave octave : Octave.values()) {
			if (octave.getIndex() >= picker.getStartOctave().getIndex() &&
					octave.getIndex() <= picker.getEndOctave().getIndex()) {
				octavesRange.add(octave);
			}
		}

		if (picker.inversionsPool.isEmpty()) {
			throw new IllegalStateException("No inversions in pool.");
		}

		List<Tonality> tonalities = getIncluded(picker.tonalitiesPool);
		while (!tonalities.isEmpty()) {
			Tonality tonality = removeRandom(tonalities);

			ConcreteChord chord = null;
			switch (tonality.getMode()) {

				case MAJOR:
					chord = getConcreteChord(tonality, picker.majorNotesPool, picker.majorDegrees);
					break;
				case MINOR:
					chord = getConcreteChord(tonality, picker.minorNotesPool, picker.minorDegrees);
					break;
			}

			if (chord != null) return chord;
		}

		throw new IllegalStateException("No possible chords.");
	}

	private ConcreteChord getConcreteChord(Tonality tonality, Map<Note, Boolean> notesPool, List<Integer> degreesPool) {
		List<Note> notes = getIncluded(notesPool);
		while (!notes.isEmpty()) {
			Note note = removeRandom(notes);

			List<Integer> degrees = new ArrayList<>(degreesPool);
			while (!degrees.isEmpty()) {
				Integer degree = removeRandom(degrees);

				List<Integer> inversions = new ArrayList<>(picker.inversionsPool);
				while (!inversions.isEmpty()) {
					Integer inversion = removeRandom(inversions);

					ConcreteChord chord = getPossibleChord(tonality, note, degree, inversion);
					if (chord != null) return chord;
				}
			}
		}

		return null;
	}

	private ConcreteChord getPossibleChord(Tonality tonality, Note note, Integer degree, Integer inversion) {
		List<Octave> octaves = new ArrayList<>(octavesRange);

		while (!octaves.isEmpty()) {
			Octave octave = removeRandom(octaves);

			ConcreteChord chord = tonality.getChord(degree, note, octave);
			chord.inverse(inversion);

			SortedSet<Key> keys = chord.getKeys();
			if (keys.first().getIndex() >= minIndex && keys.last().getIndex() <= maxIndex) {
				return chord;
			}
		}

		return null;
	}

	private <T> T removeRandom(List<T> list) {
		return list.remove(RANDOM.nextInt(list.size()));
	}

	private <T> List<T> getIncluded(Map<T, Boolean> pool) {
		List<T> values = new ArrayList<>();

		pool.forEach((value, included) -> {
			if (included) values.add(value);
		});

		return values;
	}
}

package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.NoteTonic;
import me.krodnar.sevenkey.models.Octave;

import java.util.*;
import java.util.stream.Collectors;

public class ChordPicker {

	private ChordPickAlgorithm algorithm = new RandomChordPickAlgorithm(this);

	private List<Chord> chords;
	private Set<String> types = new LinkedHashSet<>();

	private Octave startOctave = Octave.CM;
	private Octave endOctave = Octave.C9;

	private Map<Chord, Boolean> chordsPool = new HashMap<>();
	private List<Integer> inversionsPool = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
	private Map<NoteTonic, Boolean> noteTonicsPool = new HashMap<>();

	public ChordPicker(List<Chord> chords) {
		this.chords = chords;
		processChords(chords);

		for (NoteTonic tonic : NoteTonic.values()) {
			noteTonicsPool.put(tonic, true);
		}
	}

	public ConcreteChord getChord() throws IllegalStateException {
		List<Chord> chords = new ArrayList<>();
		chordsPool.forEach((key, value) -> {
			if (value) chords.add(key);
		});

		List<NoteTonic> noteTonics = new ArrayList<>();
		noteTonicsPool.forEach((key, value) -> {
			if (value) noteTonics.add(key);
		});

		algorithm.setChordsPool(chords);
		algorithm.setInversionsPool(inversionsPool);
		algorithm.setNoteTonicsPool(noteTonics);
		return algorithm.getChord();
	}

	public void includeType(String type) {
		chordsPool.keySet().stream()
				  .filter(chord -> chord.getType().equals(type))
				  .forEach(chord -> chordsPool.put(chord, true));
	}

	public void excludeType(String type) {
		chordsPool.keySet().stream()
				  .filter(chord -> chord.getType().equals(type))
				  .forEach(chord -> chordsPool.put(chord, false));
	}

	public void includeChord(Chord chord) {
		chordsPool.put(chord, true);
	}

	public void excludeChord(Chord chord) {
		chordsPool.put(chord, false);
	}

	public void includeInversion(int inversion) {
		if (!inversionsPool.contains(inversion)) {
			inversionsPool.add(inversion);
		}
	}

	public void excludeInversion(int inversion) {
		inversionsPool.remove(((Integer) inversion));
	}

	public void includeNoteTonic(NoteTonic tonic) {
		noteTonicsPool.put(tonic, true);
	}

	public void excludeNoteTonic(NoteTonic tonic) {
		noteTonicsPool.put(tonic, false);
	}

	public void setStartOctave(Octave startOctave) {
		this.startOctave = startOctave;
	}

	public void setEndOctave(Octave endOctave) {
		this.endOctave = endOctave;
	}

	public void setOctaveRange(Octave startOctave, Octave endOctave) {
		this.startOctave = startOctave;
		this.endOctave = endOctave;
	}

	public Octave getStartOctave() {
		return startOctave;
	}

	public Octave getEndOctave() {
		return endOctave;
	}

	public List<Chord> getChords(String type) {
		return chords.stream()
					 .filter(chord -> chord.getType().equals(type))
					 .collect(Collectors.toCollection(ArrayList::new));
	}

	public List<Chord> getChords() {
		return chords;
	}

	public Set<String> getTypes() {
		return types;
	}

	private void processChords(List<Chord> chords) {
		for (Chord chord : chords) {
			chordsPool.put(chord, true);
			types.add(chord.getType());
		}
	}
}

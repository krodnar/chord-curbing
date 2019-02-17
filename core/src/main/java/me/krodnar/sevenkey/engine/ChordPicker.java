package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Octave;

import java.util.*;
import java.util.stream.Collectors;

public class ChordPicker {

	private ChordPickStrategy strategy = new RandomChordPickStrategy();

	private List<Chord> chords;
	private Map<Chord, Boolean> chordsPool = new HashMap<>();
	private Set<String> types = new LinkedHashSet<>();

	private List<Integer> inversionsPool = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));

	public ChordPicker(List<Chord> chords) {
		this.chords = chords;
		processChords(chords);
	}

	public ConcreteChord getChord() throws IllegalStateException {
		List<Chord> chords = new ArrayList<>();
		chordsPool.forEach((key, value) -> {
			if (value) {
				chords.add(key);
			}
		});

		strategy.setChordsPool(chords);
		strategy.setInversionsPool(inversionsPool);
		return strategy.getChord();
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

	public void setStartOctave(Octave startOctave) {
		strategy.setStartOctave(startOctave);
	}

	public void setEndOctave(Octave endOctave) {
		strategy.setEndOctave(endOctave);
	}

	public void setOctaveRange(Octave startOctave, Octave endOctave) {
		strategy.setOctaveRange(startOctave, endOctave);
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

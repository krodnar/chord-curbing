package me.krodnar.sevenkey.engine;

import me.krodnar.sevenkey.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class ChordPicker {

	public enum PickMode {
		RANDOM, TONALITY
	}

	private static final List<Integer> INVERSIONS = List.of(0, 1, 2, 3, 4, 5, 6);
	private static final List<Integer> DEGREES = List.of(1, 2, 3, 4, 5, 6, 7);

	private PickMode pickMode = PickMode.RANDOM;
	private ChordPickAlgorithm algorithm = new RandomChordPickAlgorithm(this);

	private List<Chord> chords;
	private Set<String> types = new LinkedHashSet<>();

	private Octave startOctave = Octave.CM;
	private Octave endOctave = Octave.C9;

	Map<Note, Boolean> notesPool = new HashMap<>();
	Map<Chord, Boolean> chordsPool = new HashMap<>();
	List<Integer> inversionsPool = new ArrayList<>(INVERSIONS);

	Map<Tonality, Boolean> tonalitiesPool = new HashMap<>();
	Map<Note, Boolean> majorNotesPool = new HashMap<>();
	Map<Note, Boolean> minorNotesPool = new HashMap<>();
	List<Integer> majorDegrees = new ArrayList<>(DEGREES);
	List<Integer> minorDegrees = new ArrayList<>(DEGREES);

	public ChordPicker(List<Chord> chords) {
		this.chords = chords;
		processChords(chords);

		for (Note note : Note.values()) {
			notesPool.put(note, true);
		}

		for (Note note : Tonality.MAJOR_NOTES) {
			majorNotesPool.put(note, true);
		}

		for (Note note : Tonality.MINOR_NOTES) {
			minorNotesPool.put(note, true);
		}

		for (TonalityType type : TonalityType.values()) {
			tonalitiesPool.put(Tonality.of(type), true);
		}
	}

	public ConcreteChord getChord() throws IllegalStateException {
		return algorithm.pickChord();
	}

	public void setPickMode(PickMode pickMode) {
		this.pickMode = pickMode;

		switch (pickMode) {

			case RANDOM:
				algorithm = new RandomChordPickAlgorithm(this);
				break;
			case TONALITY:
				algorithm = new TonalityChordPickAlgorithm(this);
				break;
		}
	}

	public PickMode getPickMode() {
		return pickMode;
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

	public void includeNote(Note note) {
		notesPool.put(note, true);
	}

	public void excludeNote(Note note) {
		notesPool.put(note, false);
	}

	public void includeMajorTonalityNote(Note note) {
		majorNotesPool.put(note, true);
	}

	public void excludeMajorTonalityNote(Note note) {
		majorNotesPool.put(note, false);
	}

	public void includeMinorTonalityNote(Note note) {
		minorNotesPool.put(note, true);
	}

	public void excludeMinorTonalityNote(Note note) {
		minorNotesPool.put(note, false);
	}

	public void includeMajorDegree(int degree) {
		if (!majorDegrees.contains(degree)) {
			majorDegrees.add(degree);
		}
	}

	public void excludeMajorDegree(int degree) {
		majorDegrees.remove(((Integer) degree));
	}

	public void includeMinorDegree(int degree) {
		if (!minorDegrees.contains(degree)) {
			minorDegrees.add(degree);
		}
	}

	public void excludeMinorDegree(int degree) {
		minorDegrees.remove(((Integer) degree));
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

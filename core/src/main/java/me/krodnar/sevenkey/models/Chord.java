package me.krodnar.sevenkey.models;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chord {

	private String type;
	private String naming;
	private int inversion;
	private List<Integer> steps = new ArrayList<>();

	public Chord() {
		steps.add(0);
	}

	public Chord(String type, String naming, List<Integer> steps) {
		this.type = type;
		this.naming = naming;
		this.steps = new ArrayList<>(steps);
		this.steps.add(0, 0);
	}

	public Chord(Chord chord) {
		this.type = chord.type;
		this.naming = chord.naming;
		this.inversion = chord.inversion;

		this.steps = new ArrayList<>(chord.steps);
	}

	public static List<Chord> inversions(Chord chord) {
		return IntStream.range(0, chord.getMaxInversion())
						.mapToObj(i -> Chord.inversion(chord, i))
						.collect(Collectors.toList());
	}

	public static Chord inversion(Chord chord, int inversion) {
		Chord inverted = new Chord(chord);
		inverted.inverse(inversion);
		return inverted;
	}

	public TreeSet<Integer> getKeysIndex(Key key) {
		TreeSet<Integer> indices = new TreeSet<>();

		int index = key.getIndex();
		for (int i = 0; i < steps.size(); i++) {
			index += steps.get(i);
			indices.add(i < inversion ? index + 12 : index);
		}

		return indices;
	}

	public void inverse(int inversion) {
		if (inversion < 0 || inversion > steps.size() - 1) {
			throw new IllegalArgumentException("Inversion should be in range between 0 and number of chord's steps.");
		}

		this.inversion = inversion;
	}

	public String getNotation() {
		String notation = type + " " + naming;
		if (isInverted()) {
			notation += " (" + inversion + " inversion)";
		}

		return notation;
	}

	public int getMaxInversion() {
		return steps.size() - 1;
	}

	public int getInversion() {
		return inversion;
	}

	public boolean isInverted() {
		return inversion != 0;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNaming() {
		return naming;
	}

	public void setNaming(String naming) {
		this.naming = naming;
	}

	public List<Integer> getSteps() {
		return steps;
	}

	public void setSteps(List<Integer> steps) {
		this.steps = new ArrayList<>(steps);
		this.steps.add(0, 0);
	}

	public void addStep(int step) {
		steps.add(step);
	}

	public void removeStep(int index) {
		steps.remove(index);
	}

	@Override
	public String toString() {
		return getNotation();
	}
}

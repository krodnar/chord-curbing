package me.krodnar.sevenkey.models;

import java.util.TreeSet;

public class Chord {

	private String type;
	private String naming;
	private int inversion;
	private int[] steps;

	public Chord(String type, String naming, int inversion, int... steps) {
		this.type = type;
		this.naming = naming;
		this.inversion = inversion;
		this.steps = steps;
	}

	public Chord(Chord chord) {
		this.type = chord.type;
		this.naming = chord.naming;
		this.inversion = chord.inversion;
		this.steps = chord.steps;
	}

	public TreeSet<Integer> getKeysIndex(Key key) {
		TreeSet<Integer> indices = new TreeSet<>();

		int index = key.getIndex();
		for (int i = 0; i < steps.length; i++) {
			index += steps[i];
			indices.add(i < inversion ? index + 12 : index);
		}

		return indices;
	}

	public Chord inverse(int inversion) {
		if (inversion < 0 || inversion > steps.length - 1) {
			throw new IllegalArgumentException("Inversion should be in range between 0 and number of chord's steps.");
		}

		return new Chord(type, naming, inversion, steps);
	}

	public String getNotation() {
		String notation = type + " " + naming;
		if (isInverted()) {
			notation += " (" + inversion + " inversion)";
		}

		return notation;
	}

	public int getMaxInversion() {
		return steps.length - 1;
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

	public String getNaming() {
		return naming;
	}

	public int[] getSteps() {
		return steps;
	}

	public String toString() {
		return getNotation();
	}
}

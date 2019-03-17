package me.krodnar.sevenkey.models;

import static me.krodnar.sevenkey.models.ChordGroup.*;

public enum ChordType {
	//region Triads
	MAJOR(TRIAD, "Major", 4, 3),
	MINOR(TRIAD, "Minor", 3, 4),
	AUGMENTED(TRIAD, "Augmented", 4, 4),
	DIMINISHED(TRIAD, "Diminished", 3, 3),
	LYDIAN(TRIAD, "Lydian", 4, 2),
	//endregion

	//region Suspended
	SUS4(SUSPENDED, "Sus4", 5, 2),
	SUS2(SUSPENDED, "Sus2", 2, 5),
	//endregion

	//region Sixth
	MAJ6(SIXTH, "Maj6", 4, 3, 2),
	MIN6(SIXTH, "Min6", 3, 4, 2),
	//endregion

	//region Seventh
	MAJ7(SEVENTH, "Maj7", 4, 3, 4),
	MAJ7_S5(SEVENTH, "Maj7(#5)", 4, 4, 3),
	MAJ7_B5(SEVENTH, "Maj7(b5)", 4, 2, 5),
	MIN7(SEVENTH, "Min7", 3, 4, 3),

	DOM7(SEVENTH, "Dom7", 4, 3, 3),
	DOM7_B5(SEVENTH, "Dom7(b5)", 4, 2, 4),
	DOM7_S5(SEVENTH, "Dom7(#5)", 4, 4, 2),

	MIN7_B5(SEVENTH, "Min7(b5)", 3, 3, 4),
	MIN7_B5_BB7(SEVENTH, "Min7(b5)(bb7)", 3, 3, 3),

	MINMAJ7(SEVENTH, "MinMaj7", 3, 4, 4),
	MINMAJ7_B5(SEVENTH, "MinMaj7(b5)", 3, 3, 5),

	AUG7(SEVENTH, "Aug7", 4, 4, 2),
	AUGMAJ7(SEVENTH, "AugMaj7", 4, 4, 3),
	ADD6(SEVENTH, "add6", 4, 3, 2, 1),
	//endregion

	//region Ninth
	MAJ7_9(NINTH, "Maj7(9)", 4, 3, 4, 3),
	MAJ7_S5_9(NINTH, "Maj7(#5)(9)", 4, 4, 3, 3),
	MAJ7_B5_9(NINTH, "Maj7(b5)(9)", 4, 2, 5, 3),
	MAJ7_B9(NINTH, "Maj7(b9) ", 4, 3, 4, 2),
	MAJ7_S5_B9(NINTH, "Maj7(#5)(b9) ", 4, 4, 3, 2),
	MAJ7_B5_B9(NINTH, "Maj7(b5)(b9) ", 4, 2, 5, 2),

	MIN7_9(NINTH, "Min7(9)", 3, 4, 3, 4),
	MIN7_B5_9(NINTH, "Min7(b5)(9)", 3, 3, 4, 4),
	MIN7_B9(NINTH, "Min7(b9) ", 3, 4, 3, 3),
	MIN7_B5_B9(NINTH, "Min7(b5)(b9)", 3, 3, 4, 3),

	DOM7_9(NINTH, "Dom7(9)", 4, 3, 3, 4),
	DOM7_B5_9(NINTH, "Dom7(b5)(9)", 4, 2, 4, 4),
	DOM7_S5_9(NINTH, "Dom7(#5)(9)", 4, 4, 2, 4),
	DOM7_B9(NINTH, "Dom7(b9)", 4, 3, 3, 3),
	DOM7_B5_B9(NINTH, "Dom7(b5)(b9)", 4, 2, 4, 3),

	DIM7_9(NINTH, "Dim7(9)", 3, 3, 3, 5),
	DIM7_B9(NINTH, "Dim7(b9)", 3, 3, 3, 4),

	AUG7_9(NINTH, "Aug7(9) ", 4, 4, 2, 4),
	AUG7_B9(NINTH, "Aug7(b9) ", 4, 4, 2, 3),
	AUGMAJ7_9(NINTH, "AugMaj7(9)", 4, 4, 3, 3),
	AUGMAJ7_B9(NINTH, "AugMaj7(b9)", 4, 4, 3, 2),
	//endregion

	//region Eleven
	MAJ7_9_11(ELEVEN, "maj7(9)(11)", 4, 3, 4, 3, 3),
	MAJ7_9_S11(ELEVEN, "maj7(9)(#11)", 4, 3, 4, 3, 4),
	MAJ7_S5_9_11(ELEVEN, "Maj7(#5)(9)(11)", 4, 4, 3, 3, 3),
	MAJ7_S5_9_S11(ELEVEN, "Maj7(#5)(9)(#11)", 4, 4, 3, 3, 4),
	MAJ7_B5_9_11(ELEVEN, "Maj7(b5)(9)(11)", 4, 2, 5, 3, 3),
	MAJ7_B5_9_S11(ELEVEN, "Maj7(b5)(9)(#11)", 4, 2, 5, 3, 4),
	MAJ7_B9_11(ELEVEN, "Maj7(b9)(11)", 4, 3, 4, 2, 4),
	MAJ7_B9_S11(ELEVEN, "maj7(b9)(#11)", 4, 3, 4, 2, 5),
	MAJ7_S5_B9_11(ELEVEN, "Maj7(#5)(b9)(11)", 4, 4, 3, 2, 4),
	MAJ7_S5_B9_S11(ELEVEN, "Maj7(#5)(b9)(#11)", 4, 4, 3, 2, 5),
	MAJ7_B5_B9_11(ELEVEN, "Maj7(b5)(b9)(11)", 4, 2, 5, 2, 4),
	MAJ7_B5_B9_S11(ELEVEN, "Maj7(b5)(b9)(#11)", 4, 2, 5, 2, 5),
	MIN7_9_11(ELEVEN, "Min7(9)(11)", 3, 4, 3, 4, 3),
	MIN7_9_S11(ELEVEN, "Min7(9)(#11)", 3, 4, 3, 4, 4),
	MIN7_B5_9_11(ELEVEN, "Min7(b5)(9)(11)", 3, 3, 4, 4, 3),
	MIN7_B5_9_S11(ELEVEN, "Min7(b5)(9)(#11)", 3, 3, 4, 4, 4),
	MIN7_B9_11(ELEVEN, "Min7(b9)(11)", 3, 4, 3, 3, 4),
	MIN7_B9_S11(ELEVEN, "Min7(b9)(#11)", 3, 4, 3, 3, 5),
	MIN7_B5_B9_11(ELEVEN, "Min7(b5)(b9)(11)", 3, 3, 4, 3, 4),
	MIN7_B5_B9_S11(ELEVEN, "Min7(b5)(b9)(#11)", 3, 3, 4, 3, 5),

	DOM7_9_11(ELEVEN, "Dom7(9)(11)", 4, 3, 3, 4, 3),
	DOM7_9_S11(ELEVEN, "Dom7(9)(#11)", 4, 3, 3, 4, 4),
	DOM7_B5_9_11(ELEVEN, "Dom7(b5)(9)(11)", 4, 2, 4, 4, 3),
	DOM7_B5_9_S11(ELEVEN, "Dom7(b5)(9)(#11)", 4, 2, 4, 4, 4),
	DOM7_S5_9_11(ELEVEN, "Dom7(#5)(9)(11)", 4, 4, 2, 4, 3),
	DOM7_S5_9_S11(ELEVEN, "Dom7(#5)(9)(#11)", 4, 4, 2, 4, 4),
	DOM7_B9_11(ELEVEN, "Dom7(b9)(11)", 4, 3, 3, 3, 4),
	DOM7_B9_S11(ELEVEN, "Dom7(b9)(#11)", 4, 3, 3, 3, 5),
	DOM7_B5_B9_11(ELEVEN, "Dom7(b5)(b9)(11)", 4, 2, 4, 3, 4),
	DOM7_B5_B9_S11(ELEVEN, "Dom7(b5(b9)(#11)", 4, 2, 4, 3, 5),

	DIM7_9_11(ELEVEN, "dim7(9)(11)", 3, 3, 3, 5, 3),
	DIM7_9_S11(ELEVEN, "dim7(9)(#11)", 3, 3, 3, 5, 4),
	DIM7_B9_11(ELEVEN, "dim7(b9)(11)", 3, 3, 3, 4, 4),
	DIM7_B9_S11(ELEVEN, "dim7(b9)(#11)", 3, 3, 3, 4, 5),

	AUG7_9_11(ELEVEN, "Aug7(9)(11)", 4, 4, 2, 4, 3),
	AUG7_9_S11(ELEVEN, "Aug7(9)(#11)", 4, 4, 2, 4, 4),
	AUG7_B9_11(ELEVEN, "Aug7(b9)(11)", 4, 4, 2, 3, 4),
	AUG7_B9_S11(ELEVEN, "Aug7(b9)(#11)", 4, 4, 2, 3, 5),
	AUGMAJ7_9_11(ELEVEN, "AugMaj7(9)(11)", 4, 4, 3, 3, 3),
	AUGMAJ7_9_S11(ELEVEN, "AugMaj7(9)(#11)", 4, 4, 3, 3, 4),
	AUGMAJ7_B9_11(ELEVEN, "AugMaj7(b9)(11)", 4, 4, 3, 2, 4),
	AUGMAJ7_B9_S11(ELEVEN, "AugMaj7(b9)(#11)", 4, 4, 3, 2, 5),
	//endregion

	//region Thirteenth
	MAJ7_9_11_13(THIRTEENTH, "maj7(9)(11)(13)", 4, 3, 4, 3, 3, 4),
	MAJ7_9_S11_13(THIRTEENTH, "maj7(9)(#11)(13)", 4, 3, 4, 3, 4, 3),
	MAJ7_S5_9_11_13(THIRTEENTH, "Maj7(#5)(9)(11)(13)", 4, 4, 3, 3, 3, 4),
	MAJ7_S5_9_S11_13(THIRTEENTH, "Maj7(#5)(9)(#11)(13)", 4, 4, 3, 3, 4, 3),
	MAJ7_B5_9_11_13(THIRTEENTH, "Maj7(b5)(9)(11)(13)", 4, 2, 5, 3, 3, 4),
	MAJ7_B5_9_S11_13(THIRTEENTH, "Maj7(b5)(9)(#11)(13)", 4, 2, 5, 3, 4, 3),
	MAJ7_B9_11_13(THIRTEENTH, "Maj7(b9)(11)(13)", 4, 3, 4, 2, 4, 4),
	MAJ7_B9_S11_13(THIRTEENTH, "maj7(b9)(#11)(13)", 4, 3, 4, 2, 5, 3),
	MAJ7_S5_B9_11_13(THIRTEENTH, "Maj7(#5)(b9)(11)(13)", 4, 4, 3, 2, 4, 4),
	MAJ7_S5_B9_S11_13(THIRTEENTH, "Maj7(#5)(b9)(#11)(13)", 4, 4, 3, 2, 5, 3),
	MAJ7_B5_B9_11_13(THIRTEENTH, "Maj7(b5)(b9)(11)(13)", 4, 2, 5, 2, 4, 4),
	MAJ7_B5_B9_S11_13(THIRTEENTH, "Maj7(b5)(b9)(#11)(13)", 4, 2, 5, 2, 5, 3),
	MIN7_9_11_13(THIRTEENTH, "Min7(9)(11)(13)", 3, 4, 3, 4, 3, 4),
	MIN7_9_S11_13(THIRTEENTH, "Min7(9)(#11)(13)", 3, 4, 3, 4, 4, 3),
	MIN7_B5_9_11_13(THIRTEENTH, "Min7(b5)(9)(11)(13)", 3, 3, 4, 4, 3, 4),
	MIN7_B5_9_S11_13(THIRTEENTH, "Min7(b5)(9)(#11)(13)", 3, 3, 4, 4, 4, 3),
	MIN7_B9_11_13(THIRTEENTH, "Min7(b9)(11)(13)", 3, 4, 3, 3, 4, 4),
	MIN7_B9_S11_13(THIRTEENTH, "Min7(b9)(#11)(13)", 3, 4, 3, 3, 5, 3),
	MIN7_B5_B9_11_13(THIRTEENTH, "Min7(b5)(b9)(11)(13)", 3, 3, 4, 3, 4, 4),
	MIN7_B5_B9_S11_13(THIRTEENTH, "Min7(b5)(b9)(#11)(13)", 3, 3, 4, 3, 5, 3),

	DOM7_9_11_13(THIRTEENTH, "Dom7(9)(11)(13)", 4, 3, 3, 4, 3, 4),
	DOM7_9_S11_13(THIRTEENTH, "Dom7(9)(#11)(13)", 4, 3, 3, 4, 4, 3),
	DOM7_B5_9_11_13(THIRTEENTH, "Dom7(b5)(9)(11)(13)", 4, 2, 4, 4, 3, 4),
	DOM7_B5_9_S11_13(THIRTEENTH, "Dom7(b5)(9)(#11)(13)", 4, 2, 4, 4, 4, 3),
	DOM7_S5_9_11_13(THIRTEENTH, "Dom7(#5)(9)(11)(13)", 4, 4, 2, 4, 3, 4),
	DOM7_S5_9_S11_13(THIRTEENTH, "Dom7(#5)(9)(#11)(13)", 4, 4, 2, 4, 4, 3),
	DOM7_B9_11_13(THIRTEENTH, "Dom7(b9)(11)(13)", 4, 3, 3, 3, 4, 4),
	DOM7_B9_S11_13(THIRTEENTH, "Dom7(b9)(#11)(13)", 4, 3, 3, 3, 5, 3),
	DOM7_B5_B9_11_13(THIRTEENTH, "Dom7(b5)(b9)(11)(13)", 4, 2, 4, 3, 4, 4),
	DOM7_B5_B9_S11_13(THIRTEENTH, "Dom7(b5(b9)(#11)(13)", 4, 2, 4, 3, 5, 3),

	DIM7_9_11_13(THIRTEENTH, "dim7(9)(11)(13)", 3, 3, 3, 5, 3, 4),
	DIM7_9_S11_13(THIRTEENTH, "dim7(9)(#11)(13)", 3, 3, 3, 5, 4, 3),
	DIM7_B9_11_13(THIRTEENTH, "dim7(b9)(11)(13)", 3, 3, 3, 4, 4, 4),
	DIM7_B9_S11_13(THIRTEENTH, "dim7(b9)(#11)(13)", 3, 3, 3, 4, 5, 3),

	AUG7_9_11_13(THIRTEENTH, "Aug7(9)(11)(13)", 4, 4, 2, 4, 3, 4),
	AUG7_9_S11_13(THIRTEENTH, "Aug7(9)(#11)(13)", 4, 4, 2, 4, 4, 3),
	AUG7_B9_11_13(THIRTEENTH, "Aug7(b9)(11)(13)", 4, 4, 2, 3, 4, 4),
	AUG7_B9_S11_13(THIRTEENTH, "Aug7(b9)(#11)(13)", 4, 4, 2, 3, 5, 3),
	AUGMAJ7_9_11_13(THIRTEENTH, "AugMaj7(9)(11)(13)", 4, 4, 3, 3, 3, 4),
	AUGMAJ7_9_S11_13(THIRTEENTH, "AugMaj7(9)(#11)(13)", 4, 4, 3, 3, 4, 3),
	AUGMAJ7_B9_11_13(THIRTEENTH, "AugMaj7(b9)(11)(13)", 4, 4, 3, 2, 4, 4),
	AUGMAJ7_B9_S11_13(THIRTEENTH, "AugMaj7(b9)(#11)(13)", 4, 4, 3, 2, 5, 3);
	//endregion

	private ChordGroup group;
	private String name;
	private int[] steps;

	ChordType(ChordGroup group, String name, int... steps) {
		this.group = group;
		this.name = name;

		this.steps = new int[steps.length + 1];
		this.steps[0] = 0;
		System.arraycopy(steps, 0, this.steps, 1, steps.length);
	}

	public ChordGroup getGroup() {
		return group;
	}

	public String getName() {
		return name;
	}

	public int[] getSteps() {
		return steps;
	}

	@Override
	public String toString() {
		return group + " " + name;
	}
}

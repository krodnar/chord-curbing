package me.krodnar.sevenkey.models;

import static me.krodnar.sevenkey.models.ChordType.*;
import static me.krodnar.sevenkey.models.Tonality.*;

public enum TonalityType {
	TRIAD_MAJOR("Triads Major", Mode.MAJOR, MAJOR, MINOR, MINOR, MAJOR, MAJOR, MINOR, DIMINISHED),
	TRIAD_MINOR("Triads Minor", Mode.MINOR, MINOR, DIMINISHED, MAJOR, MINOR, MINOR, MAJOR, MAJOR),

	SEVENTH_MAJOR("Seventh Major", Mode.MAJOR, MAJ7, MIN7, MIN7, MAJ7, DOM7, MIN7, MIN7_B5),
	SEVENTH_MINOR("Seventh Minor", Mode.MINOR, MIN7, MIN7_B5, MAJ7, MIN7, MIN7, MAJ7, DOM7),

	NINTH_MAJOR("Ninth Major", Mode.MAJOR, MAJ7_9, MIN7_9, MIN7_B9, MAJ7_9, DOM7_9, MIN7_9, MIN7_B5_B9),
	NINTH_MINOR("Ninth Minor", Mode.MINOR, MIN7_9, MIN7_B5_B9, MAJ7_9, MIN7_9, MIN7_B9, MAJ7_9, DOM7_9),

	ELEVENTH_MAJOR("Eleventh Major", Mode.MAJOR, MAJ7_9_11, MIN7_9_11, MIN7_B9_11, MAJ7_9_S11, DOM7_9_11, MIN7_9_11, MIN7_B5_B9_11),
	ELEVENTH_MINOR("Eleventh Minor", Mode.MINOR, MIN7_9_11, MIN7_B5_B9_11, MAJ7_9_11, MIN7_9_11, MIN7_B9_11, MAJ7_9_S11, DOM7_9_11);

	private String name;
	private Mode mode;
	private ChordType[] chordTypes;

	TonalityType(String name, Mode mode, ChordType... chordTypes) {
		this.name = name;
		this.mode = mode;
		this.chordTypes = chordTypes;
	}

	public String getName() {
		return name;
	}

	public ChordType[] getChordTypes() {
		return chordTypes;
	}

	public Mode getMode() {
		return mode;
	}
}

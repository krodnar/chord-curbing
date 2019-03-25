package me.krodnar.sevenkey.model;

import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.engine.ChordChecker;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.models.ChordType;

import java.util.LinkedList;
import java.util.List;

public class MainModel {

	private Trainer trainer;
	private ChordPicker chordPicker;
	private ChordChecker chordChecker;

	public MainModel() {
		this.trainer = createTrainer();
		this.chordPicker = trainer.getPicker();
		this.chordChecker = trainer.getChecker();
	}

	public MainModel(Trainer trainer) {
		this.trainer = trainer;
		this.chordPicker = trainer.getPicker();
		this.chordChecker = trainer.getChecker();
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public ChordPicker getChordPicker() {
		return chordPicker;
	}

	public ChordChecker getChordChecker() {
		return chordChecker;
	}

	private Trainer createTrainer() {
		List<Chord> chords = new LinkedList<>();

		for (ChordType type : ChordType.values()) {
			Chord chord = Chord.of(type);
			chords.add(chord);
		}

		ChordPicker picker = new ChordPicker(chords);
		return new Trainer(picker);
	}
}

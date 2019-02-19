package me.krodnar.sevenkey.model;

import javafx.scene.control.Alert;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.engine.ChordChecker;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.resources.Resources;
import me.krodnar.sevenkey.tools.ChordReader;

import java.io.IOException;
import java.util.ArrayList;
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
		List<Chord> chords = new ArrayList<>();

		try {
			chords = ChordReader.readChords(Resources.value.CHORDS.url());
		} catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(Resources.strings.error_chord_reader);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

		ChordPicker picker = new ChordPicker(chords);
		return new Trainer(picker);
	}
}

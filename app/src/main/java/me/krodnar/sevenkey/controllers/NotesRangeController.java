package me.krodnar.sevenkey.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.models.Octave;
import me.krodnar.sevenkey.resources.Resources;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.net.URL;
import java.util.*;

public class NotesRangeController implements Initializable {

	private static final Octave DEF_START_OCTAVE = Octave.CM;
	private static final Octave DEF_END_OCTAVE = Octave.C9;

	private Trainer trainer;

	@FXML
	private ChoiceBox<Octave> startOctaveChoice;
	@FXML
	private ChoiceBox<Octave> endOctaveChoice;

	public NotesRangeController(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startOctaveChoice.setItems(FXCollections.observableArrayList(Octave.values()));
		endOctaveChoice.setItems(FXCollections.observableArrayList(Octave.values()));

		startOctaveChoice.valueProperty().addListener((observable, oldOctave, newOctave) -> {
			trainer.getPicker().setStartOctave(newOctave);
		});

		endOctaveChoice.valueProperty().addListener((observable, oldOctave, newOctave) -> {
			trainer.getPicker().setEndOctave(newOctave);
		});

		startOctaveChoice.getSelectionModel().select(DEF_START_OCTAVE);
		endOctaveChoice.getSelectionModel().select(DEF_END_OCTAVE);
	}
}

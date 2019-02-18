package me.krodnar.sevenkey.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.models.NoteTonic;
import me.krodnar.sevenkey.models.Octave;

import java.net.URL;
import java.util.*;

public class RangeController implements Initializable {

	private static final Octave DEF_START_OCTAVE = Octave.CM;
	private static final Octave DEF_END_OCTAVE = Octave.C9;

	private ChordPicker picker;

	@FXML
	private ChoiceBox<Octave> startOctaveChoice;
	@FXML
	private ChoiceBox<Octave> endOctaveChoice;

	public RangeController(ChordPicker picker) {
		this.picker = picker;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initOctavesRange();
	}

	private void initOctavesRange() {
		startOctaveChoice.setItems(FXCollections.observableArrayList(Octave.values()));
		endOctaveChoice.setItems(FXCollections.observableArrayList(Octave.values()));

		startOctaveChoice.valueProperty().addListener((observable, oldOctave, newOctave) -> {
			picker.setStartOctave(newOctave);
		});

		endOctaveChoice.valueProperty().addListener((observable, oldOctave, newOctave) -> {
			picker.setEndOctave(newOctave);
		});

		startOctaveChoice.getSelectionModel().select(DEF_START_OCTAVE);
		endOctaveChoice.getSelectionModel().select(DEF_END_OCTAVE);
	}
}

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

public class NotesRangeController implements Initializable {

	private static final Octave DEF_START_OCTAVE = Octave.CM;
	private static final Octave DEF_END_OCTAVE = Octave.C9;

	private Trainer trainer;
	private ChordPicker picker;

	@FXML
	private ChoiceBox<Octave> startOctaveChoice;
	@FXML
	private ChoiceBox<Octave> endOctaveChoice;
	@FXML
	private HBox notePositionsRoot;

	public NotesRangeController(Trainer trainer) {
		this.trainer = trainer;
		this.picker = trainer.getPicker();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initOctavesRange();
		initNotePositions();
	}

	private void initNotePositions() {
		for (NoteTonic position : NoteTonic.values()) {
			CheckBox checkBox = new CheckBox(position.getNotation());
			checkBox.selectedProperty().set(true);
			checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue) picker.includeNoteTonic(position);
				else picker.excludeNoteTonic(position);
			});

			notePositionsRoot.getChildren().add(checkBox);
		}
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

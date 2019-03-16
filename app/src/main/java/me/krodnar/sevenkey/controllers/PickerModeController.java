package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import me.krodnar.sevenkey.engine.ChordPicker;

import java.net.URL;
import java.util.ResourceBundle;

public class PickerModeController implements Initializable {

	private ChordPicker picker;

	@FXML
	private RadioButton chordsRadio;
	@FXML
	private RadioButton tonalitiesRadio;

	public PickerModeController(ChordPicker picker) {
		this.picker = picker;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chordsRadio.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
			if (isSelected) picker.setPickMode(ChordPicker.PickMode.RANDOM);
		});

		tonalitiesRadio.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
			if (isSelected) picker.setPickMode(ChordPicker.PickMode.TONALITY);
		});

		chordsRadio.selectedProperty().setValue(true);
	}
}

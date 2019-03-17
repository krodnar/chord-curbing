package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.models.Note;
import me.krodnar.sevenkey.models.Tonality;

import java.net.URL;
import java.util.ResourceBundle;

public class TonalitiesController implements Initializable {

	private ChordPicker picker;

	@FXML
	private HBox majorNotesRoot;
	@FXML
	private HBox minorNotesRoot;

	public TonalitiesController(ChordPicker picker) {
		this.picker = picker;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (Note note : Tonality.MAJOR_NOTES) {
			CheckBox checkBox = new CheckBox(note.getNotation());
			checkBox.selectedProperty().setValue(true);
			checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue) {
					picker.includeMajorTonalityNote(note);
				} else {
					picker.excludeMajorTonalityNote(note);
				}
			});
			majorNotesRoot.getChildren().add(checkBox);
		}

		for (Note note : Tonality.MINOR_NOTES) {
			CheckBox checkBox = new CheckBox(note.getNotation());
			checkBox.selectedProperty().setValue(true);
			checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue) {
					picker.includeMinorTonalityNote(note);
				} else {
					picker.excludeMinorTonalityNote(note);
				}
			});
			minorNotesRoot.getChildren().add(checkBox);
		}
	}
}

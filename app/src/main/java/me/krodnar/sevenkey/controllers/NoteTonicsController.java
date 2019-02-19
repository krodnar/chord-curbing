package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.models.NoteTonic;

import java.net.URL;
import java.util.ResourceBundle;

public class NoteTonicsController implements Initializable {

	private ChordPicker picker;

	@FXML
	private HBox noteTonicsRoot;

	public NoteTonicsController(ChordPicker picker) {
		this.picker = picker;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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

			noteTonicsRoot.getChildren().add(checkBox);
		}
	}
}

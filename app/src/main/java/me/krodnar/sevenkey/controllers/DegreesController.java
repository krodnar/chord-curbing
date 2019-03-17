package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.models.Tonality;

import java.net.URL;
import java.util.ResourceBundle;

import static me.krodnar.sevenkey.models.Tonality.MAJOR_DEGREE_SYMBOLS;
import static me.krodnar.sevenkey.models.Tonality.MINOR_DEGREE_SYMBOLS;

public class DegreesController implements Initializable {

	private ChordPicker picker;

	@FXML
	private HBox majorDegreesRoot;
	@FXML
	private HBox minorDegreesRoot;

	public DegreesController(ChordPicker picker) {
		this.picker = picker;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int degree : Tonality.DEGREES) {
			CheckBox majorCheckBox = new CheckBox(MAJOR_DEGREE_SYMBOLS.get(degree));
			majorCheckBox.selectedProperty().setValue(true);
			majorCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue) {
					picker.includeMajorDegree(degree);
				} else {
					picker.excludeMajorDegree(degree);
				}
			});
			majorDegreesRoot.getChildren().add(majorCheckBox);

			CheckBox minorCheckBox = new CheckBox(MINOR_DEGREE_SYMBOLS.get(degree));
			minorCheckBox.selectedProperty().setValue(true);
			minorCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue) {
					picker.includeMinorDegree(degree);
				} else {
					picker.excludeMinorDegree(degree);
				}
			});
			minorDegreesRoot.getChildren().add(minorCheckBox);
		}
	}
}

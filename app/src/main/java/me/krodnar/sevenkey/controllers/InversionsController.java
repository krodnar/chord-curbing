package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.resources.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InversionsController implements Initializable {

	private ChordPicker picker;

	@FXML
	private Pane inversionsRoot;

	public InversionsController(ChordPicker picker) {
		this.picker = picker;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int inversion = 0; inversion <= 6; inversion++) {
			CheckBox checkBox = null;

			try {
				checkBox = FXMLLoader.load(Resources.layout.ITEM_INVERSION.url());
			} catch (IOException e) {
				e.printStackTrace();
			}

			initCheckBox(checkBox, inversion);
			inversionsRoot.getChildren().add(checkBox);
		}
	}

	private void initCheckBox(CheckBox checkBox, int inversion) {
		String name = inversion == 0 ? Resources.strings.root_note : String.valueOf(inversion);
		checkBox.setText(name);
		checkBox.setSelected(true);
		checkBox.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
			if (isSelected) picker.includeInversion(inversion);
			else picker.excludeInversion(inversion);
		});
	}
}

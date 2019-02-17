package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.resources.Resources;
import me.krodnar.sevenkey.tools.ChordReader;
import me.krodnar.sevenkey.models.Chord;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	private Trainer trainer;

	@FXML
	private VBox pickerRoot;
	@FXML
	private HBox inversionsRoot;
	@FXML
	private VBox trainerRoot;
	@FXML
	private FlowPane settingsRoot;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trainer = createTrainer();

		initTrainer();
		initSettings();
		initPicker();
		initInversions();
	}

	private void initInversions() {
		FXMLLoader loader = new FXMLLoader(Resources.layout.INVERSIONS.url());
		loader.setResources(Resources.getBundle());

		InversionsController inversionsController = new InversionsController(trainer.getPicker());
		loader.setController(inversionsController);
		loader.setRoot(inversionsRoot);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initTrainer() {
		FXMLLoader loader = new FXMLLoader(Resources.layout.TRAINER.url());
		loader.setResources(Resources.getBundle());

		TrainerController trainerController = new TrainerController(trainer);
		loader.setController(trainerController);
		loader.setRoot(trainerRoot);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initSettings() {
		FXMLLoader loader = new FXMLLoader(Resources.layout.SETTINGS.url());
		loader.setResources(Resources.getBundle());

		SettingsController controller = new SettingsController(trainer);
		loader.setController(controller);
		loader.setRoot(settingsRoot);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initPicker() {
		FXMLLoader loader = new FXMLLoader(Resources.layout.PICKER.url());
		loader.setResources(Resources.getBundle());

		PickerController pickerController = new PickerController(trainer.getPicker());
		loader.setController(pickerController);
		loader.setRoot(pickerRoot);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

		ChordPicker suggester = new ChordPicker(chords);
		return new Trainer(suggester);
	}

	public void close() {
		trainer.stop();
	}
}

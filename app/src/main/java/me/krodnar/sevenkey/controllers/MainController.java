package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.main.App;
import me.krodnar.sevenkey.main.ScreenManager;
import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.resources.Resources;
import me.krodnar.sevenkey.tools.ChordReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	private Trainer trainer;

	@FXML
	private HBox pickerRoot;
	@FXML
	private HBox inversionsRoot;
	@FXML
	private VBox trainerRoot;
	@FXML
	private Button settingsButton;
	@FXML
	private FlowPane octavesRangeRoot;
	@FXML
	private HBox tonicsRoot;

	public MainController(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTrainer();
		initNotesRange();
		initPicker();
		initInversions();
		initTonics();

		settingsButton.setOnAction(event -> {
			App.setScreen(ScreenManager.Screen.SETTINGS);
		});
	}

	private void initTonics() {
		FXMLLoader loader = new FXMLLoader(Resources.layout.TONICS.url());
		loader.setResources(Resources.getBundle());

		NoteTonicsController controller = new NoteTonicsController(trainer.getPicker());
		loader.setController(controller);
		loader.setRoot(tonicsRoot);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	private void initNotesRange() {
		FXMLLoader loader = new FXMLLoader(Resources.layout.NOTES.url());
		loader.setResources(Resources.getBundle());

		RangeController controller = new RangeController(trainer.getPicker());
		loader.setController(controller);
		loader.setRoot(octavesRangeRoot);

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

	public void close() {
		trainer.stop();
	}
}

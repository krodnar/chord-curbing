package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.krodnar.sevenkey.main.App;
import me.krodnar.sevenkey.utils.ScreenManager;
import me.krodnar.sevenkey.model.MainModel;
import me.krodnar.sevenkey.resources.Resources;
import me.krodnar.sevenkey.utils.FxmlUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	private MainModel mainModel;

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

	public MainController(MainModel mainModel) {
		this.mainModel = mainModel;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			FxmlUtils.multiRootLoad()
					 .add(
							 Resources.layout.PICKER.url(),
							 new PickerController(mainModel.getChordPicker()),
							 pickerRoot)
					 .add(
							 Resources.layout.INVERSIONS.url(),
							 new InversionsController(mainModel.getChordPicker()),
							 inversionsRoot)
					 .add(
							 Resources.layout.TRAINER.url(),
							 new TrainerController(mainModel.getTrainer()),
							 trainerRoot)
					 .add(
							 Resources.layout.RANGE.url(),
							 new RangeController(mainModel.getChordPicker()),
							 octavesRangeRoot)
					 .add(
							 Resources.layout.TONICS.url(),
							 new NoteTonicsController(mainModel.getChordPicker()),
							 tonicsRoot)
					 .load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		settingsButton.setOnAction(event -> {
			App.setScreen(ScreenManager.Screen.SETTINGS);
		});
	}

	public void close() {
		mainModel.getTrainer().stop();
	}
}

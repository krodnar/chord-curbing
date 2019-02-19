package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.main.App;
import me.krodnar.sevenkey.main.ScreenManager;
import me.krodnar.sevenkey.resources.Resources;
import me.krodnar.sevenkey.utils.FxmlUtils;

import java.io.IOException;
import java.net.URL;
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
		try {
			FxmlUtils.multiRootLoad()
					 .add(
							 Resources.layout.PICKER.url(),
							 new PickerController(trainer.getPicker()),
							 pickerRoot)
					 .add(
							 Resources.layout.INVERSIONS.url(),
							 new InversionsController(trainer.getPicker()),
							 inversionsRoot)
					 .add(
							 Resources.layout.TRAINER.url(),
							 new TrainerController(trainer),
							 trainerRoot)
					 .add(
							 Resources.layout.RANGE.url(),
							 new RangeController(trainer.getPicker()),
							 octavesRangeRoot)
					 .add(
							 Resources.layout.TONICS.url(),
							 new NoteTonicsController(trainer.getPicker()),
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
		trainer.stop();
	}
}

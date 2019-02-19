package me.krodnar.sevenkey.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.krodnar.sevenkey.controllers.MainController;
import me.krodnar.sevenkey.controllers.SettingsController;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.resources.Resources;
import me.krodnar.sevenkey.tools.ChordReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

	private Trainer trainer;
	private static ScreenManager screenManager;

	@Override
	public void start(Stage primaryStage) throws IOException {
		trainer = createTrainer();

		System.setProperty("prism.lcdtext", "false");
		System.setProperty("prism.text", "t2k");

		FXMLLoader trLoader = new FXMLLoader(Resources.layout.MAIN.url());
		MainController mainController = new MainController(trainer);
		trLoader.setController(mainController);
		Parent root = trLoader.load();

		Scene trainerScene = new Scene(root);

		FXMLLoader prefLoader = new FXMLLoader(Resources.layout.SETTINGS.url());
		SettingsController settingsController = new SettingsController(trainer);
		prefLoader.setController(settingsController);
		Parent prefs = prefLoader.load();

		Scene settingsScene = new Scene(prefs);

		screenManager = new ScreenManager(primaryStage);
		screenManager
				.addScreen(settingsScene, ScreenManager.Screen.SETTINGS)
				.addScreen(trainerScene, ScreenManager.Screen.TRAINER);

		primaryStage.setTitle(Resources.strings.app_name);
		primaryStage.getIcons().add(new Image(Resources.drawable.FAVICON.stream()));
		primaryStage.setScene(trainerScene);
		primaryStage.setOnCloseRequest(windowEvent -> mainController.close());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setScreen(ScreenManager.Screen screen) {
		screenManager.setScreen(screen);
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
}

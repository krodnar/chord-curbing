package me.krodnar.sevenkey.main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.krodnar.sevenkey.controllers.MainController;
import me.krodnar.sevenkey.controllers.SettingsController;
import me.krodnar.sevenkey.model.MainModel;
import me.krodnar.sevenkey.resources.Resources;
import me.krodnar.sevenkey.utils.FxmlUtils;
import me.krodnar.sevenkey.utils.ScreenManager;

import java.io.IOException;

public class App extends Application {

	private MainModel mainModel = new MainModel();
	private static ScreenManager screenManager;

	@Override
	public void start(Stage primaryStage) throws IOException {
		// enables font smoothing
		System.setProperty("prism.lcdtext", "false");
		System.setProperty("prism.text", "t2k");

		MainController mainController = new MainController(mainModel);
		Parent main = FxmlUtils.load(Resources.layout.MAIN.url(), mainController);

		SettingsController settingsController = new SettingsController(mainModel);
		Parent settings = FxmlUtils.load(Resources.layout.SETTINGS.url(), settingsController);

		Scene scene = new Scene(main);

		screenManager = new ScreenManager(scene);
		screenManager
				.addScreen(main, ScreenManager.Screen.TRAINER)
				.addScreen(settings, ScreenManager.Screen.SETTINGS);

		primaryStage.setTitle(Resources.strings.app_name);
		primaryStage.getIcons().add(new Image(Resources.drawable.FAVICON.stream()));
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(windowEvent -> mainController.close());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setScreen(ScreenManager.Screen screen) {
		screenManager.setScreen(screen);
	}
}

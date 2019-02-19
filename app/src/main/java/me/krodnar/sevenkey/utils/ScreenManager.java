package me.krodnar.sevenkey.utils;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ScreenManager {

	private Stage stage;
	private Map<Screen, Scene> screens = new HashMap<>();

	public ScreenManager(Stage stage) {
		this.stage = stage;
	}

	public ScreenManager addScreen(Scene scene, Screen screen) {
		screens.put(screen, scene);
		return this;
	}

	public void setScreen(Screen screen) {
		stage.setScene(screens.get(screen));
	}

	public enum Screen {
		TRAINER,
		SETTINGS
	}
}

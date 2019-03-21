package me.krodnar.sevenkey.utils;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.EnumMap;
import java.util.Map;

public class ScreenManager {

	private Scene scene;
	private Map<Screen, Parent> screens = new EnumMap<>(Screen.class);

	public ScreenManager(Scene scene) {
		this.scene = scene;
	}

	public ScreenManager addScreen(Parent parent, Screen screen) {
		screens.put(screen, parent);
		return this;
	}

	public void setScreen(Screen screen) {
		scene.setRoot(screens.get(screen));
	}

	public enum Screen {
		TRAINER,
		SETTINGS
	}
}

package me.krodnar.sevenkey.utils;

import javafx.fxml.FXMLLoader;
import me.krodnar.sevenkey.resources.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FxmlUtils {

	public static <C, R> void rootLoad(URL layout, C controller, R root) throws IOException {
		FXMLLoader loader = new FXMLLoader(layout);
		loader.setResources(Resources.getBundle());
		loader.setController(controller);
		loader.setRoot(root);
		loader.load();
	}

	public static <C, R> R load(URL layout, C controller) throws IOException {
		FXMLLoader loader = new FXMLLoader(layout);
		loader.setResources(Resources.getBundle());
		loader.setController(controller);
		return loader.load();
	}

	public static MultiRootLoadBuilder multiRootLoad() {
		return new MultiRootLoadBuilder();
	}

	public static class MultiRootLoadBuilder {

		private Map<URL, Object> controllers = new HashMap<>();
		private Map<URL, Object> roots = new HashMap<>();

		public <C, R> MultiRootLoadBuilder add(URL layout, C controller, R root) {
			controllers.put(layout, controller);
			roots.put(layout, root);
			return this;
		}

		public void load() throws IOException {
			for (Map.Entry<URL, Object> entry : controllers.entrySet()) {
				URL layout = entry.getKey();
				Object controller = entry.getValue();
				Object root = roots.get(layout);

				rootLoad(layout, controller, root);
			}
		}
	}
}

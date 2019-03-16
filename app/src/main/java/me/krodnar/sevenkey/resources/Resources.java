package me.krodnar.sevenkey.resources;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("HardCodedStringLiteral")
public final class Resources {

	private static ResourceBundle bundle = ResourceBundle.getBundle("/values/strings");

	public static final class layout {
		public static final ResourceDescriptor MAIN = new ResourceDescriptor("/fxml/main.fxml");
		public static final ResourceDescriptor PICKER = new ResourceDescriptor("/fxml/picker.fxml");
		public static final ResourceDescriptor TRAINER = new ResourceDescriptor("/fxml/trainer.fxml");
		public static final ResourceDescriptor INVERSIONS = new ResourceDescriptor("/fxml/inversions.fxml");
		public static final ResourceDescriptor RANGE = new ResourceDescriptor("/fxml/range.fxml");
		public static final ResourceDescriptor TONICS = new ResourceDescriptor("/fxml/tonics.fxml");
		public static final ResourceDescriptor SETTINGS = new ResourceDescriptor("/fxml/settings.fxml");
		public static final ResourceDescriptor TONALITIES = new ResourceDescriptor("/fxml/tonalities.fxml");
		public static final ResourceDescriptor PICKER_MODE = new ResourceDescriptor("/fxml/picker_mode.fxml");
		public static final ResourceDescriptor DEGREES = new ResourceDescriptor("/fxml/degrees.fxml");

		public static final ResourceDescriptor ITEM_INVERSION = new ResourceDescriptor("/fxml/item_inversion.fxml");
		public static final ResourceDescriptor ITEM_CHORDS_TOGGLE= new ResourceDescriptor("/fxml/item_chords_toggle.fxml");
	}

	public static final class drawable {
		public static final ResourceDescriptor FAVICON = new ResourceDescriptor("/icons/favicon.png");
	}

	public static final class value {
		public static final ResourceDescriptor CHORDS = new ResourceDescriptor("/values/chords.txt");
	}

	public static final class strings {

		public static final String app_name = bundle.getString("app.name");

		public static final String root_note = bundle.getString("root_note");

		public static final String settings_input = bundle.getString("settings.input");
		public static final String settings_output = bundle.getString("settings.output");

		public static final String error_default_header = bundle.getString("error.header_default");
		public static final String error_chord_picker = bundle.getString("error.chord_picker");
		public static final String error_chord_reader = bundle.getString("error.chord_reader");
		public static final String error_connecting_device = bundle.getString("error.device_connection");

	}

	public static ResourceBundle getBundle() {
		return bundle;
	}

	public static class ResourceDescriptor {

		private String path;

		public ResourceDescriptor(String path) {
			this.path = path;
		}

		public String path() {
			return path;
		}

		public URL url() {
			return Resources.class.getResource(path);
		}

		public InputStream stream() {
			return Resources.class.getResourceAsStream(path);
		}
	}
}
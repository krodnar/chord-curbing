package me.krodnar.sevenkey.resources;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("HardCodedStringLiteral")
public final class Resources {

	private static ResourceBundle bundle = ResourceBundle.getBundle("/values/strings");

	public static final class layout {
		public static ResourceDescriptor MAIN = new ResourceDescriptor("/fxml/main.fxml");
		public static ResourceDescriptor DEVICES = new ResourceDescriptor("/fxml/devices.fxml");
		public static ResourceDescriptor PICKER = new ResourceDescriptor("/fxml/picker.fxml");
		public static ResourceDescriptor TRAINER = new ResourceDescriptor("/fxml/trainer.fxml");
		public static ResourceDescriptor INVERSIONS = new ResourceDescriptor("/fxml/inversions.fxml");
		public static ResourceDescriptor NOTES = new ResourceDescriptor("/fxml/range.fxml");
		public static ResourceDescriptor TONICS = new ResourceDescriptor("/fxml/tonics.fxml");
		public static ResourceDescriptor SETTINGS = new ResourceDescriptor("/fxml/settings.fxml");

		public static ResourceDescriptor ITEM_INVERSION = new ResourceDescriptor("/fxml/item_inversion.fxml");
		public static ResourceDescriptor ITEM_CHORDS_TOGGLE= new ResourceDescriptor("/fxml/item_chords_toggle.fxml");
	}

	public static final class drawable {
		public static ResourceDescriptor FAVICON = new ResourceDescriptor("/icons/favicon.png");
	}

	public static final class value {
		public static ResourceDescriptor CHORDS = new ResourceDescriptor("/values/chords.txt");
	}

	public static final class strings {

		public static String app_name = bundle.getString("app.name");

		public static String root_note = bundle.getString("root_note");

		public static String error_default_header = bundle.getString("error.header_default");
		public static String error_chord_picker = bundle.getString("error.chord_picker");
		public static String error_chord_reader = bundle.getString("error.chord_reader");
		public static String error_connecting_device = bundle.getString("error.device_connection");

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
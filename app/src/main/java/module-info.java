module sevenkey.app {
	requires sevenkey.core;

	requires javafx.controls;
	requires javafx.fxml;

	requires java.desktop;

	opens me.krodnar.sevenkey.main to
			javafx.fxml,
			javafx.graphics;

	opens me.krodnar.sevenkey.controllers to
			javafx.fxml,
			javafx.graphics;

	opens me.krodnar.sevenkey.views to
			javafx.fxml,
			javafx.graphics;
}
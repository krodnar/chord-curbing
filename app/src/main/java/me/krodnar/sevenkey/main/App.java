package me.krodnar.sevenkey.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.krodnar.sevenkey.controllers.MainController;
import me.krodnar.sevenkey.resources.Resources;

import java.io.IOException;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		System.setProperty("prism.lcdtext", "false");
		System.setProperty("prism.text", "t2k");

		FXMLLoader loader = new FXMLLoader(Resources.layout.MAIN.url());
		MainController controller = new MainController();
		loader.setController(controller);
		Parent root = loader.load();

		Scene scene = new Scene(root);

		primaryStage.setTitle(Resources.strings.app_name);
		primaryStage.getIcons().add(new Image(Resources.drawable.FAVICON.stream()));
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(windowEvent -> controller.close());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

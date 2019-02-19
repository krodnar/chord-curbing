package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.main.App;
import me.krodnar.sevenkey.main.ScreenManager;
import me.krodnar.sevenkey.resources.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

	private Trainer trainer;

	@FXML
	private FlowPane devicesRoot;
	@FXML
	private Button saveButton;

	public SettingsController(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initDevicesList();

		saveButton.setOnAction(e -> App.setScreen(ScreenManager.Screen.TRAINER));
	}

	private void initDevicesList() {
		FXMLLoader loader = new FXMLLoader(Resources.layout.DEVICES.url());
		loader.setResources(Resources.getBundle());

		DeviceListController controller = new DeviceListController(trainer);
		loader.setController(controller);
		loader.setRoot(devicesRoot);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package me.krodnar.sevenkey.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.main.App;
import me.krodnar.sevenkey.main.ScreenManager;
import me.krodnar.sevenkey.resources.Resources;
import me.krodnar.sevenkey.utils.FxmlUtils;

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
		DeviceListController controller = new DeviceListController(trainer);

		try {
			FxmlUtils.rootLoad(Resources.layout.DEVICES.url(), controller, devicesRoot);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

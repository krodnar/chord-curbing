package me.krodnar.sevenkey.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import me.krodnar.sevenkey.main.App;
import me.krodnar.sevenkey.model.MainModel;
import me.krodnar.sevenkey.resources.Resources;
import me.krodnar.sevenkey.utils.MidiDevicesTool;
import me.krodnar.sevenkey.utils.ScreenManager;
import me.krodnar.sevenkey.views.NoSelectionModel;
import me.krodnar.sevenkey.views.ToggleButtonCell;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

	private MainModel mainModel;

	@FXML
	private ListView<MidiDevice.Info> devicesInRoot;
	@FXML
	private ListView<MidiDevice.Info> devicesOutRoot;
	@FXML
	private Button saveButton;

	public SettingsController(MainModel mainModel) {
		this.mainModel = mainModel;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		saveButton.setOnAction(e -> App.setScreen(ScreenManager.Screen.TRAINER));

		devicesInRoot.setSelectionModel(new NoSelectionModel<>());
		devicesOutRoot.setSelectionModel(new NoSelectionModel<>());

		devicesInRoot.setCellFactory(ToggleButtonCell.forListView(Resources.strings.settings_input, info -> {
			BooleanProperty property = new SimpleBooleanProperty();

			property.addListener((observable, wasToggled, isToggled) -> {
				MidiDevice device;

				try {
					device = MidiSystem.getMidiDevice(info);

					if (isToggled) {
						mainModel.getTrainer().addInput(device);
					} else {
						mainModel.getTrainer().removeInput(device);
					}
				} catch (MidiUnavailableException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(Resources.strings.error_connecting_device);
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
			});

			return property;
		}));

		devicesOutRoot.setCellFactory(ToggleButtonCell.forListView(Resources.strings.settings_output, info -> {
			BooleanProperty property = new SimpleBooleanProperty();

			property.addListener((observable, wasToggled, isToggled) -> {
				MidiDevice device;

				try {
					device = MidiSystem.getMidiDevice(info);

					if (isToggled) {
						mainModel.getTrainer().addOutput(device);
					} else {
						mainModel.getTrainer().removeOutput(device);
					}
				} catch (MidiUnavailableException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(Resources.strings.error_connecting_device);
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
			});

			return property;
		}));

		MidiDevicesTool devicesTool = new MidiDevicesTool();
		devicesInRoot.setItems(devicesTool.getInputDevices());
		devicesOutRoot.setItems(devicesTool.getOutputDevices());
	}
}

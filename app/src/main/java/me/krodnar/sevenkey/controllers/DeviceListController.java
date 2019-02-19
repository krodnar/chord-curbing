package me.krodnar.sevenkey.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.resources.Resources;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DeviceListController implements Initializable {

	private Trainer trainer;

	@FXML
	private ChoiceBox<MidiDevice.Info> devicesList;

	public DeviceListController(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initDevicesList();
	}

	private void initDevicesList() {
		updateDevicesList();

		devicesList.valueProperty().addListener((observableValue, oldInfo, newInfo) -> {
			try {
				MidiDevice device = MidiSystem.getMidiDevice(newInfo);
				trainer.setDevice(device);
			} catch (MidiUnavailableException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(Resources.strings.error_connecting_device);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				devicesList.valueProperty().setValue(oldInfo);
			}
		});

		devicesList.onMousePressedProperty().setValue(event -> updateDevicesList());
		devicesList.getSelectionModel().select(0);
	}

	private void updateDevicesList() {
		ObservableList<MidiDevice.Info> oldInfos = devicesList.getItems();
		ObservableList<MidiDevice.Info> infos = FXCollections.observableList(getDeviceInfos());

		if (!oldInfos.equals(infos)) {
			for (MidiDevice.Info info : infos) {
				if (!oldInfos.contains(info)) {
					oldInfos.add(info);
				}
			}

			oldInfos.removeIf(oldInfo -> !infos.contains(oldInfo));
		}
	}

	private List<MidiDevice.Info> getDeviceInfos() {
		List<MidiDevice.Info> infos = new ArrayList<>(Arrays.asList(MidiSystem.getMidiDeviceInfo()));

		infos.removeIf(info -> {
			try {
				MidiDevice device = MidiSystem.getMidiDevice(info);
				return device.getMaxTransmitters() == 0;
			} catch (MidiUnavailableException e) {
				return false;
			}
		});

		return infos;
	}
}

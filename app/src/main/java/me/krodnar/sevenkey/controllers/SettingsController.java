package me.krodnar.sevenkey.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.models.Octave;
import me.krodnar.sevenkey.resources.Resources;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.net.URL;
import java.util.*;

public class SettingsController implements Initializable {

	private static final Octave DEF_START_OCTAVE = Octave.CM;
	private static final Octave DEF_END_OCTAVE = Octave.C9;

	private Trainer trainer;

	@FXML
	private ChoiceBox<MidiDevice.Info> devicesList;
	@FXML
	private ChoiceBox<Octave> startOctaveChoice;
	@FXML
	private ChoiceBox<Octave> endOctaveChoice;

	public SettingsController(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startOctaveChoice.setItems(FXCollections.observableArrayList(Octave.values()));
		endOctaveChoice.setItems(FXCollections.observableArrayList(Octave.values()));

		startOctaveChoice.valueProperty().addListener((observable, oldOctave, newOctave) -> {
			trainer.getPicker().setStartOctave(newOctave);
		});

		endOctaveChoice.valueProperty().addListener((observable, oldOctave, newOctave) -> {
			trainer.getPicker().setEndOctave(newOctave);
		});

		startOctaveChoice.getSelectionModel().select(DEF_START_OCTAVE);
		endOctaveChoice.getSelectionModel().select(DEF_END_OCTAVE);

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

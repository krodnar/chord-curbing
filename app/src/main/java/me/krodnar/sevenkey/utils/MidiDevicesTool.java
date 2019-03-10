package me.krodnar.sevenkey.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MidiDevicesTool {

	private ObservableList<MidiDevice.Info> inputDevices = FXCollections.observableArrayList();
	private ObservableList<MidiDevice.Info> outputDevices = FXCollections.observableArrayList();

	public MidiDevicesTool() {
		updateDevices();
	}

	public void updateDevices() {
		List<MidiDevice.Info> updatedIn = new ArrayList<>();
		List<MidiDevice.Info> updatedOut = new ArrayList<>();

		for (MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
			try {
				MidiDevice device = MidiSystem.getMidiDevice(info);

				if (device.getMaxReceivers() != 0) {
					updatedOut.add(info);
				}

				if (device.getMaxTransmitters() != 0) {
					updatedIn.add(info);
				}
			} catch (MidiUnavailableException ignored) {
			}
		}

		for (MidiDevice.Info info : updatedIn) {
			if (!inputDevices.contains(info)) {
				inputDevices.add(info);
			}
		}
		inputDevices.retainAll(updatedIn);

		for (MidiDevice.Info info : updatedOut) {
			if (!outputDevices.contains(info)) {
				outputDevices.add(info);
			}
		}
		outputDevices.retainAll(updatedOut);
	}

	public ObservableList<MidiDevice.Info> getInputDevices() {
		return inputDevices;
	}

	public ObservableList<MidiDevice.Info> getOutputDevices() {
		return outputDevices;
	}
}

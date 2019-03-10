package me.krodnar.sevenkey.tools;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import java.util.HashSet;
import java.util.Set;

public class MidiDevicesManager {

	private Set<MidiDevice> devicesIn = new HashSet<>();
	private Set<MidiDevice> devicesOut = new HashSet<>();

	private Receiver trainerReceiver;

	public MidiDevicesManager(Receiver trainerReceiver) {
		this.trainerReceiver = trainerReceiver;
	}

	public void addDeviceOut(MidiDevice device) throws MidiUnavailableException {
		if (device.getMaxReceivers() == 0) {
			throw new IllegalArgumentException(device.getDeviceInfo() + " is not an output device.");
		}

		devicesOut.add(device);
		Receiver receiver = device.getReceiver();

		for (MidiDevice deviceIn : devicesIn) {
			Transmitter transmitter = deviceIn.getTransmitter();
			transmitter.setReceiver(receiver);
		}

		device.open();
	}

	public void removeDeviceOut(MidiDevice device) {
		device.close();
		devicesOut.remove(device);
	}

	public void addDeviceIn(MidiDevice device) throws MidiUnavailableException {
		if (device.getMaxTransmitters() == 0) {
			throw new IllegalArgumentException(device.getDeviceInfo() + " is not an input device.");
		}

		devicesIn.add(device);
		Transmitter transmitter = device.getTransmitter();
		transmitter.setReceiver(trainerReceiver);

		for (MidiDevice deviceOut : devicesOut) {
			Receiver receiver = deviceOut.getReceiver();
			transmitter.setReceiver(receiver);
		}

		device.open();
	}

	public void removeDeviceIn(MidiDevice device) {
		device.close();
		devicesIn.remove(device);
	}

	public void close() {
		for (MidiDevice device : devicesIn) {
			device.close();
		}

		for (MidiDevice device : devicesOut) {
			device.close();
		}
	}
}

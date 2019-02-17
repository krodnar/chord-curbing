package me.krodnar.sevenkey.tools;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;

public class MidiDeviceManager {

	private MidiDevice device;
	private Transmitter transmitter;

	public MidiDeviceManager() {
	}

	public MidiDeviceManager(MidiDevice device) {
		this.device = device;
	}

	public boolean open() throws MidiUnavailableException {
		if (device == null) {
			return false;
		}

		transmitter = device.getTransmitter();
		device.open();

		return true;
	}

	public boolean close() {
		if (device == null) {
			return false;
		}

		device.close();
		return true;
	}

	public MidiDevice getDevice() {
		return device;
	}

	public void setDevice(MidiDevice device) {
		if (this.device != null) {
			close();
		}

		this.device = device;
	}

	public Transmitter getTransmitter() {
		return transmitter;
	}
}

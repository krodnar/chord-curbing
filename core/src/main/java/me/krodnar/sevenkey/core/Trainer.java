package me.krodnar.sevenkey.core;

import me.krodnar.sevenkey.engine.ChordChecker;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.tools.MidiDeviceManager;
import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Note;

import javax.sound.midi.*;

public class Trainer {

	private ChordChecker checker;
	private ChordPicker picker;

	private MidiDeviceManager deviceManager = new MidiDeviceManager();
	private TrainerListener listener;

	public Trainer(ChordPicker picker) {
		this(new ChordChecker(), picker);
	}

	public Trainer(ChordChecker checker, ChordPicker picker) {
		this.checker = checker;
		this.picker = picker;
	}

	public void start() throws MidiUnavailableException {
		deviceManager.open();
		deviceManager.getTransmitter().setReceiver(new CheckingReceiver());

		if (listener != null) listener.onStart();
	}

	public void stop() {
		deviceManager.close();
		if (listener != null) listener.onStop();
	}

	public void nextChord() throws IllegalStateException {
		ConcreteChord chord = picker.getChord();
		checker.setChord(chord);
		if (listener != null) listener.onNextChord(chord);
	}

	public MidiDevice getDevice() {
		return deviceManager.getDevice();
	}

	public void setDevice(MidiDevice device) throws MidiUnavailableException {
		deviceManager.setDevice(device);
		start();
	}

	public ChordChecker getChecker() {
		return checker;
	}

	public void setChecker(ChordChecker checker) {
		this.checker = checker;
	}

	public ChordPicker getPicker() {
		return picker;
	}

	public void setPicker(ChordPicker picker) {
		this.picker = picker;
	}

	public TrainerListener getListener() {
		return listener;
	}

	public void setListener(TrainerListener listener) {
		this.listener = listener;
	}

	private class CheckingReceiver implements Receiver {

		@Override
		public void send(MidiMessage midiMessage, long timeStamp) {
			if (midiMessage instanceof ShortMessage) {
				ShortMessage message = (ShortMessage) midiMessage;
				Note note = Note.getByIndex(message.getData1());

				int command = message.getCommand();
				checker.noteCommand(note, command);
				if (listener != null) listener.onNoteCommand(note, command, checker.getPressedNotes());

				if (checker.check()) {
					if (listener != null) listener.onCorrectChord(checker.getChord());
				} else {
					if (listener != null) listener.onWrongChord(checker.getChord(), checker.getPressedNotes());
				}
			}
		}

		@Override
		public void close() {

		}
	}
}

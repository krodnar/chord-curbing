package me.krodnar.sevenkey.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import me.krodnar.sevenkey.core.Trainer;
import me.krodnar.sevenkey.core.TrainerListener;
import me.krodnar.sevenkey.models.ConcreteChord;
import me.krodnar.sevenkey.models.Note;
import me.krodnar.sevenkey.resources.Resources;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;

public class TrainerController implements Initializable {

	private Trainer trainer;

	@FXML
	private Label chordLabel;
	@FXML
	private Label notesLabel;
	@FXML
	private Label pressedNotesLabel;
	@FXML
	private Button nextChordButton;

	public TrainerController(Trainer trainer) {
		this.trainer = trainer;
		trainer.setListener(new DefaultListener());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		nextChordButton.setOnAction(actionEvent -> nextChord());
	}

	private void nextChord() {
		try {
			trainer.nextChord();
		} catch (IllegalStateException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(Resources.strings.error_chord_picker);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	private class DefaultListener implements TrainerListener {

		@Override
		public void onCorrectChord(ConcreteChord chord) {
			Platform.runLater(() -> nextChord());
		}

		@Override
		public void onWrongChord(ConcreteChord chord, Set<Note> pressedNotes) {

		}

		@Override
		public void onNextChord(ConcreteChord chord) {
			Platform.runLater(() -> {
				chordLabel.setText(chord.getNotation());
				notesLabel.setText(Arrays.toString(chord.getNotes().toArray()));
			});
		}

		@Override
		public void onNoteOn(Note pressed, Set<Note> allNotes) {
			Platform.runLater(() -> pressedNotesLabel.setText(
					Arrays.toString(allNotes.toArray())
			));
		}

		@Override
		public void onNoteOff(Note released, Set<Note> allNotes) {
			Platform.runLater(() -> pressedNotesLabel.setText(
					Arrays.toString(allNotes.toArray())
			));
		}
	}
}

package me.krodnar.sevenkey.controllers;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import me.krodnar.sevenkey.engine.ChordPicker;
import me.krodnar.sevenkey.models.Chord;
import me.krodnar.sevenkey.resources.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PickerController implements Initializable {

	private ChordPicker picker;
	private Map<Chord, BooleanProperty> chordsPool = new HashMap<>();

	@FXML
	private Pane chordsRoot;

	public PickerController(ChordPicker picker) {
		this.picker = picker;

		for (Chord chord : picker.getChords()) {
			BooleanProperty property = new SimpleBooleanProperty(true);
			chordsPool.put(chord, property);

			property.addListener((observable, oldValue, newValue) -> {
				if (newValue) picker.includeChord(chord);
				else picker.excludeChord(chord);
			});
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Set<String> types = picker.getTypes();
		for (String type : types) {
			Pane chordsList = null;
			try {
				chordsList = getChordsList(type, picker.getChords(type));
			} catch (IOException e) {
				e.printStackTrace();
			}
			chordsRoot.getChildren().add(chordsList);
		}
	}

	private Pane getChordsList(String type, List<Chord> chords) throws IOException {
		ListView<Chord> chordsList = new ListView<>(FXCollections.observableList(chords));
		chordsList.setCellFactory(CheckBoxListCell.forListView(chord -> chordsPool.get(chord), new StringConverter<>() {
			@Override
			public String toString(Chord chord) {
				return chord.getNaming();
			}

			@Override
			public Chord fromString(String string) {
				return null;
			}
		}));

		List<BooleanProperty> properties = chords.stream()
												 .map(chord -> chordsPool.get(chord))
												 .collect(Collectors.toList());

		CheckBox checkBox = getCheckBox(type, chordsList, properties);

		return new VBox(checkBox, chordsList);
	}

	private CheckBox getCheckBox(String type, ListView<Chord> chordsList, List<BooleanProperty> properties) throws IOException {
		CheckBox checkBox = FXMLLoader.load(Resources.layout.ITEM_CHORDS_TOGGLE.url());

		ChangeListener<Boolean> listener = (observable, wasSelected, isSelected) -> {
			if (isSelected) {
				properties.forEach(property -> property.set(true));
				chordsList.setDisable(false);
			} else {
				properties.forEach(property -> property.set(false));
				chordsList.setDisable(true);
			}
		};

		checkBox.setText(type);
		checkBox.selectedProperty().set(true);
		checkBox.selectedProperty().addListener(listener);

		BooleanBinding allUnselected = Bindings.createBooleanBinding(
				() -> properties.stream().noneMatch(ObservableBooleanValue::get),
				properties.toArray(new Observable[0]));

		BooleanBinding allSelected = Bindings.createBooleanBinding(
				() -> properties.stream().allMatch(ObservableBooleanValue::get),
				properties.toArray(new Observable[0]));

		BooleanBinding indeterminate = allSelected.not().and(allUnselected.not());

		allSelected.addListener((observable, wasSelected, isSelected) -> {
			if (isSelected) checkBox.selectedProperty().set(true);
		});

		allUnselected.addListener((observable, wasUnselected, isUnselected) -> {
			if (isUnselected) checkBox.selectedProperty().set(false);
		});

		indeterminate.addListener((observable, wasIndeterminate, isIndeterminate) -> {
			checkBox.setIndeterminate(isIndeterminate);
		});

		// set binding as user data to retain weak listener reference
		checkBox.setUserData(indeterminate);
		return checkBox;
	}
}

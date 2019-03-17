package me.krodnar.sevenkey.views;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import me.krodnar.sevenkey.resources.Resources;

import java.io.IOException;

public class ToggleButtonCell<T> extends ListCell<T> {

	private BooleanProperty booleanProperty;
	private View view;

	private SimpleStringProperty buttonName =
			new SimpleStringProperty(this, "buttonName"); //NON-NLS

	private SimpleObjectProperty<StringConverter<T>> stringConverter =
			new SimpleObjectProperty<>(this, "stringConverter"); //NON-NLS

	private SimpleObjectProperty<Callback<T, BooleanProperty>> toggledStateCallback =
			new SimpleObjectProperty<>(this, "toggledStateCallback"); //NON-NLS

	public static <T> Callback<ListView<T>, ListCell<T>> forListView(
			final String buttonName,
			final Callback<T, BooleanProperty> getSelectedProperty) {
		return list -> new ToggleButtonCell<>(buttonName, getSelectedProperty);
	}

	public static <T> Callback<ListView<T>, ListCell<T>> forListView(
			final String buttonName,
			final Callback<T, BooleanProperty> getSelectedProperty,
			final StringConverter<T> converter) {
		return list -> new ToggleButtonCell<>(buttonName, getSelectedProperty, converter);
	}

	public ToggleButtonCell(String buttonName, Callback<T, BooleanProperty> toggleStateCallback) {
		this(buttonName, toggleStateCallback, null);
	}

	public ToggleButtonCell(String buttonName,
							Callback<T, BooleanProperty> toggleStateCallback,
							StringConverter<T> stringConverter) {
		super();
		this.buttonName.set(buttonName);
		this.toggledStateCallback.set(toggleStateCallback);
		this.stringConverter.set(stringConverter);

		view = new View();
	}

	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);

		if (!empty && item != null) {
			if (toggledStateCallback.get() == null) {
				throw new NullPointerException(
						"The ToggleButtonCell toggleStateCallback can not be null");
			}

			view.toggleButton.setText(buttonName.get());

			view.label.setText(stringConverter.get() != null ? stringConverter.get().toString(item) : item.toString());
			setGraphic(view);

			if (booleanProperty != null) {
				view.toggleButton.selectedProperty().unbindBidirectional(booleanProperty);
			}

			booleanProperty = toggledStateCallback.get().call(item);

			if (booleanProperty != null) {
				view.toggleButton.selectedProperty().bindBidirectional(booleanProperty);
			}
		} else {
			setText(null);
			setGraphic(null);
		}
	}

	public String getButtonName() {
		return buttonName.get();
	}

	public SimpleStringProperty buttonNameProperty() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName.set(buttonName);
	}

	public StringConverter getStringConverter() {
		return stringConverter.get();
	}

	public SimpleObjectProperty<StringConverter<T>> stringConverterProperty() {
		return stringConverter;
	}

	public void setStringConverter(StringConverter<T> stringConverter) {
		this.stringConverter.set(stringConverter);
	}

	public Callback getToggledStateCallback() {
		return toggledStateCallback.get();
	}

	public SimpleObjectProperty<Callback<T, BooleanProperty>> toggledStateCallbackProperty() {
		return toggledStateCallback;
	}

	public void setToggledStateCallback(Callback<T, BooleanProperty> toggledStateCallback) {
		this.toggledStateCallback.set(toggledStateCallback);
	}

	// TODO: 10.03.2019 Add cell class with custom view
	private static class View extends HBox {

		private Label label;
		private ToggleButton toggleButton;

		public View() {
			label = new Label();
			toggleButton = new ToggleButton();

			label.setPrefWidth(300);
			setAlignment(Pos.CENTER_LEFT);
			getChildren().addAll(label, toggleButton);
		}
	}
}

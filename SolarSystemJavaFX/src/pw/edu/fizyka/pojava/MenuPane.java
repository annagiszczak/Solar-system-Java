package pw.edu.fizyka.pojava;

import javax.security.auth.callback.Callback;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MenuPane extends BorderPane {

	ListView<String> list;
	Label label;
	Button save_button;
	
	public MenuPane() {
		list = new ListView<String>();
		ObservableList<String> items =FXCollections.observableArrayList (
		    "S³oñce", "Merkury", "Wenus", "Ziemia", "Mars", "Jowisz", "Saturn", "Uran", "Neptun");
		list.setItems(items);
		
		list.setPrefHeight(100);
		
		save_button = new Button("Zapisz dane do pliku");
		Label label1 = new Label("INFO");
		label1.setFont(new Font("Arial", 24));
		label1.setTextFill(Color.web("#B233FF"));
		label1.setMaxWidth(Double.MAX_VALUE);
        label1.setAlignment(Pos.CENTER);
        label = new Label("Mars");
        label.setStyle("-fx-background-color: #e7cbfb;");
		
        Label sound = new Label ("Dzwiek: W³/Wy³");
        Button ANG = new Button("ANG");
        Button PL = new Button("PL");
        Button start = new Button("start");
        Button stop = new Button("stop");
        
        HBox HBoxGrandChild1 = new HBox(ANG, PL);
        VBox VBoxChild1 = new VBox(sound, HBoxGrandChild1);
        HBox HBoxChild2 = new HBox(start, stop);
        HBox HBoxParent = new HBox(VBoxChild1, HBoxChild2);
		VBox VBoxMother = new VBox(label1, list, label, save_button, HBoxParent);
		String css = this.getClass().getResource("stylesheet.css").toExternalForm();
		String css2 = this.getClass().getResource("soundbuttons.css").toExternalForm();
		this.getStylesheets().add(css);
		HBoxGrandChild1.getStylesheets().add(css2);
		HBox.setMargin(VBoxChild1, new Insets(8,8,8,8));
		VBox.setMargin(list, new Insets(8,8,8,8));
		VBox.setMargin(label, new Insets(8,8,8,8));
		VBox.setMargin(save_button, new Insets(8,8,8,8));
		
		HBox.setHgrow(VBoxMother, Priority.ALWAYS);
		
		VBoxMother.prefHeightProperty().bind(this.heightProperty()); // coœ nie dzia³a to i nie przeciaga vboxa do koñca
		// HBox.setMargin(bottom_button1, new Insets(8,8,8,8));
		
		this.setLeft(VBoxMother);
		Slider slider = new Slider(0, 100, 0);
		Label labelslider = new Label("Timespeed");
		
        VBox vBox2 = new VBox(labelslider, slider);
        this.setRight(vBox2); 
        // HBox.setHgrow(stack, Priority.ALWAYS);
	}

	public void displaySelected(MouseEvent event) {
		String planet = list.getSelectionModel().getSelectedItem();
		if (planet == "Ziemia")
		{
			label.setText("Ziemia");
		}
	}
	
	public MenuPane(Node arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MenuPane(Node arg0, Node arg1, Node arg2, Node arg3, Node arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}

}

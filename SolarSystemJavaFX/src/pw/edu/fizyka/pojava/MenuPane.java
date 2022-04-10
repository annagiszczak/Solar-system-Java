///Wojciech Grunwald

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MenuPane extends BorderPane {

	ListView<String> list;
	ObservableList<String> planetsList;
	Label label;
	Button save_button;
	
	public MenuPane() {	
		list = new ListView<String>();
		planetsList =FXCollections.observableArrayList (
		    "Slonce", "Merkury", "Wenus", "Ziemia", "Mars", "Jowisz", "Saturn", "Uran", "Neptun");
		list.setItems(planetsList);
		
		list.setPrefHeight(150);
		
		save_button = new Button("Zapisz dane do pliku");
		Label label1 = new Label("INFO");
		label1.setFont(new Font("Arial", 24));
		label1.setTextFill(Color.web("#B233FF"));
		label1.setMaxWidth(Double.MAX_VALUE);
        label1.setAlignment(Pos.CENTER);
        label = new Label("Mars");
        label.setStyle("-fx-background-color: #e7cbfb;");
        //setFill(Color.web("#81c483"));
		
        Label sound = new Label ("Dzwiek: ");
        Button buttonSoundON = new Button("On");
        Button buttonSoundOFF = new Button("OFF");
        Button ANG = new Button("ANG");
        Button PL = new Button("PL");
        Button start = new Button("start");
        Button stop = new Button("stop");
        
        HBox Support = new HBox(sound, buttonSoundON, buttonSoundOFF);
        HBox HBoxChild1 = new HBox(start, stop);
        HBox HBoxChild2 = new HBox(sound, buttonSoundON, buttonSoundOFF);
        HBox HBoxChild3 = new HBox(ANG, PL);
        VBox VBoxParent = new VBox(HBoxChild1, HBoxChild2, HBoxChild3);
		VBox VBoxMother = new VBox(label1, list);
		VBox VBoxCenter = new VBox(label, save_button);
		String css = this.getClass().getResource("stylesheet.css").toExternalForm();
		String css2 = this.getClass().getResource("soundbuttons.css").toExternalForm();
		this.getStylesheets().add(css);
		HBoxChild3.getStylesheets().add(css2);
		VBox.setMargin(HBoxChild1, new Insets(8,8,8,8));
		VBox.setMargin(HBoxChild2, new Insets(8,8,8,8));
		VBox.setMargin(HBoxChild3, new Insets(8,8,8,8));
		VBox.setMargin(list, new Insets(8,8,8,8));
		VBox.setMargin(label, new Insets(8,8,8,8));
		VBox.setMargin(save_button, new Insets(8,8,8,8));
		
		
		VBoxMother.prefHeightProperty().bind(this.heightProperty());
		VBoxCenter.prefHeightProperty().bind(this.heightProperty().multiply(1.5));
		// co� nie dzia�a to i nie przeciaga vboxa do ko�ca
		// HBox.setMargin(bottom_button1, new Insets(8,8,8,8));
		
		GridPane Panel = new GridPane();
		Panel.add(VBoxMother, 0, 0);
		Panel.add(VBoxCenter, 0, 1);
		Panel.add(VBoxParent, 0, 2);
		this.setLeft(Panel);
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
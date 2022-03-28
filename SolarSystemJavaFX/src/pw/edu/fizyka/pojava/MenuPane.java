package pw.edu.fizyka.pojava;

import javax.security.auth.callback.Callback;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MenuPane extends BorderPane {

	public MenuPane() {
		ListView<String> list = new ListView<String>();
		ObservableList<String> items =FXCollections.observableArrayList (
		    "S³oñce", "Merkury", "Wenus", "Ziemia", "Mars", "Jowisz", "Saturn", "Uran", "Neptun");
		list.setItems(items);
		
		
		list.setPrefHeight(70);
		
		Label label1 = new Label("Info");
		label1.setFont(new Font("Arial", 24));
		label1.setMaxWidth(Double.MAX_VALUE);
        label1.setAlignment(Pos.CENTER);
		
		VBox VBox1 = new VBox(label1, list);
		VBox1.getStylesheets().add("/pw/edu/fizyka/pojava/resources/stylesheet.css");
		VBox1.setStyle("-fx-background-color: #e7cbfb;");
		
		this.setLeft(VBox1);
		Slider slider = new Slider(0, 100, 0);

        VBox vBox = new VBox(slider);
        this.setRight(vBox); 
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

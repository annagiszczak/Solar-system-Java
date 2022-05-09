///Wojciech Grunwald

package pw.edu.fizyka.pojava;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.callback.Callback;
import javax.swing.JFileChooser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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

	ObservableList<String> planetsList;
	
	//labels
	Label label;
	Label sound;
	Label infoLabel;
	Label labelSlider;
	
	//inne
	static Slider timeSlider;
	static double sliderT;
	
	
	//buttons
	Button save_button;
	static Button buttonSoundON;
    static Button buttonSoundOFF;
    Button ANG;
    Button PL;
    static Button start;
    static Button stop;
	
    ChoiceBox<String> cb;
    
	@SuppressWarnings("unchecked")
	public MenuPane() {	
        ArrayList<String> Info = new ArrayList<String>();
        Info.add("Masa: (1,98855 ± 0,00025) ×10^30 kg \nPromień: (696 342 ± 65) km \nObjętość: 1,4093×10^18 km^3 \nGęstość średnia: 1408 kg/m^3 \nPrędkość obrotu: 1,887 km/s");
        Info.add("Odległość od Słońca: 57 909 170 km\nMasa: 3,3011×10^23 kg \nPromień: 2439,7 km \nObjętość: 6,083×1010 km^3 \nGęstość średnia: 5427 kg/m^3 \nOkres obrotu: 58 d 15 h 26 m\nPrędkość obrotu: 10,892 km/h");
        Info.add("Odległość od Słońca: 108 208 926 km\nMasa: 4,867×10^24 kg \nPromień: 6051,8 km \nObjętość: 9,2843×1011 km^3 \nGęstość średnia: 5243 kg/m^3 \nOkres obrotu: −5832,6 h \nPrędkość obrotu: 6,52 km/h");
        Info.add("Odległość od Słońca: 149 597 887 km\nMasa: 5,97219×10^24 kg \nPromień: 6371,008 km \nObjętość: 1,083206916846×10^12 km^3 \nGęstość średnia: 5513 kg/m^3 \nOkres obrotu: 23,9345 h\nPrędkość obrotu: 7,2921150(1)×10^-5 rad/s");
        Info.add("Odległość od Słońca: 227 936 637 km\nMasa: 6,4171×10^23 kg \nPromień: 3389,5 km \nObjętość: 1,6318×10^11 km^3 \nGęstość średnia: 3933 kg/m^3 \nOkres obrotu: 24 h 37 min \nPrędkość obrotu: 868,22 km/h");
        Info.add("Odległość od Słońca: 778 412 027 km\nMasa: 1,89819×10^27 kg \nPromień: 69 911 km \nObjętość: 1,43128×10^15 km^3 \nGęstość średnia: 1326 kg/m^3 \nOkres obrotu: 9,9250 h \nPrędkość obrotu: 45 360 km/h");
        Info.add("Odległość od Słońca: 1 426 725 413 km\nMasa: 5,6834×10^26 kg \nPromień: 58 232 km \nObjętość: 8,2713×10^14 km^3 \nGęstość średnia: 687 kg/m^3 \nOkres obrotu: 10,656 h \nPrędkość obrotu: 35 500 km/h");
        Info.add("Odległość od Słońca: 2 870 972 220 km\nMasa: 	8,6813×10^25 kg \nPromień: 25 362 km \nObjętość: 6,833×1013 km^3 \nGęstość średnia: 1271 kg/m^3 \nOkres obrotu: −17,24 h \nPrędkość obrotu: 9320 km/h");
        Info.add("Odległość od Słońca: 4 498 252 900 km\nMasa: 1,02413×10^26 kg \nPromień: 24 622 km \nObjętość: 6,254×10^13 km^3 \nGęstość średnia: 1638 kg/m^3 \nOkres obrotu: 16,11 h\nPrędkość obrotu: 2.68 km/s");
		
		
        
		planetsList = FXCollections.observableArrayList (
		    "Słońce", "Merkury", "Wenus", "Ziemia", "Mars", "Jowisz", "Saturn", "Uran", "Neptun");
		cb = new ChoiceBox<String>(planetsList);
		cb.getSelectionModel().selectFirst();
		cb.setPrefHeight(100);
		
		//label info
		infoLabel = new Label("INFO");
		infoLabel.setFont(new Font("Arial", 24));
		infoLabel.setTextFill(Color.web("#B233FF"));
		infoLabel.setMaxWidth(Double.MAX_VALUE);
		infoLabel.setAlignment(Pos.CENTER);
		infoLabel.setId("info");
        label = new Label("");
        label.setStyle("-fx-background-color: #e7cbfb;");
        //setFill(Color.web("#81c483"));
        
        //save button
        save_button = new Button("Zapisz dane do pliku");
        
        save_button.setOnAction(new EventHandler<ActionEvent> () {
        	public void handle(ActionEvent arg) {
        		JFileChooser saver = new JFileChooser("./");
        		int returnVal = saver.showSaveDialog(saver);
        		File file = saver.getSelectedFile();
        		if (returnVal == JFileChooser.APPROVE_OPTION)
        		{
        			try {
        				
        				if(!file.exists()) {
        					file.createNewFile();
        				}
        				
        				FileWriter fw = new FileWriter(file.getAbsoluteFile());
        				BufferedWriter bw = new BufferedWriter(fw);
        				bw.write(cb.getValue() + "\n");
        				bw.write(label.getText());
        				bw.close();
        				
        			}
        			catch (IOException e) {
        				e.printStackTrace();
        			}
        		}
        	}
        });
        
		//sound
        sound = new Label ("Dzwiek: ");
        buttonSoundON = new Button("On");
        buttonSoundOFF = new Button("OFF");
        
        //wielojezycznosc
        ANG = new Button("ANG");
        PL = new Button("PL");
        
        //zatrzymanie i odtwarzanie czasu
        start = new Button("start");
        stop = new Button("stop");

//        HBox Support = new HBox(sound, buttonSoundON, buttonSoundOFF);
        HBox HBoxChild1 = new HBox(start, stop);
        HBox HBoxChild2 = new HBox(sound, buttonSoundON, buttonSoundOFF);
        HBox HBoxChild3 = new HBox(ANG, PL);
        VBox VBoxParent = new VBox(HBoxChild1, HBoxChild2, HBoxChild3);
		VBox VBoxMother = new VBox(infoLabel, cb);
		VBox VBoxCenter = new VBox(label, save_button);
		String css = this.getClass().getResource("stylesheet.css").toExternalForm();
		String css2 = this.getClass().getResource("soundbuttons.css").toExternalForm();
		this.getStylesheets().add(css);
		HBoxChild3.getStylesheets().add(css2);
		VBox.setMargin(HBoxChild1, new Insets(8,8,8,8));
		VBox.setMargin(HBoxChild2, new Insets(8,8,8,8));
		VBox.setMargin(HBoxChild3, new Insets(8,8,8,8));
		VBox.setMargin(cb, new Insets(8,8,8,8));
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
		
		
		timeSlider = new Slider(0, 100, 0);
		timeSlider.setMajorTickUnit(8.0);
		timeSlider.setMinorTickCount(3);
		timeSlider.setSnapToTicks(true);
		timeSlider.setShowTickMarks(true);
		timeSlider.setShowTickLabels(true);
//		timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
//				// TODO Auto-generated method stub
//				sliderT = timeSlider.getValue();
//				
//			}
//			//slider.getValue();
//		
//		});
		
		labelSlider = new Label("Timespeed");
		
		
        VBox vBox2 = new VBox(labelSlider, timeSlider);
        this.setRight(vBox2); 
        // HBox.setHgrow(stack, Priority.ALWAYS);
        
		label.setText(Info.get(0));
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
    		public void changed(ObservableValue ov, Number value, Number new_value) {
    			label.setText(Info.get(new_value.intValue()));
    		}
    	});
        
        cb.setTooltip(new Tooltip("Select the planet"));
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
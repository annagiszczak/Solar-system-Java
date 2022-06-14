///Wojciech Grunwald

package pw.edu.fizyka.pojava;

import java.util.Locale;
import java.util.ResourceBundle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


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
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuPane extends BorderPane {

	ObservableList<String> planetsList;
	
	private static final DecimalFormat dfZero = new DecimalFormat("0.00");
	
	//labels
	Label label;
	Label sound;
	Label infoLabel;
	Label labelSlider;
	Label valueLabel;
	Label planetLabel;
	
	//inne
	static Slider timeSlider;
	static double sliderT;
    static String value;
	
    HBox HBoxChild1;
    HBox HBoxChild2;
    HBox HBoxChild3;
    VBox VBoxParent;
	VBox VBoxMother;
	VBox VBoxCenter;
	VBox vBox2;
	
	GridPane Panel;
	
	//buttons
	Button save_button;
	static Button buttonSoundON;
    static Button buttonSoundOFF;
    Button ANG;
    Button PL;
    static Button start;
    static Button stop;
	
    ArrayList<String> Info;
    ChoiceBox<String> cb;
    
    static String language = "pl";
    
    Locale locale;
    ResourceBundle messages;
    
    void GUI()
    {
    	Info = new ArrayList<String>();
		for (int i=1; i<=9; i++)
        {
        	String ii = String.valueOf(i);
        	Info.add(messages.getString("Info"+ii));
        }
		
		planetsList = FXCollections.observableArrayList (
			    messages.getString("Sun"), messages.getString("Mercury"), messages.getString("Wenus"), 
			    messages.getString("Earth"), messages.getString("Mars"), messages.getString("Jupiter"), 
			    messages.getString("Saturn"), messages.getString("Uran"), messages.getString("Neptun"));
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
		
	    save_button = new Button(messages.getString("save_data"));
	    
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
        sound = new Label (messages.getString("sound"));
        buttonSoundON = new Button(messages.getString("soundON"));
        buttonSoundOFF = new Button(messages.getString("soundOFF"));
        
        //wielojezycznosc
        ANG = new Button("ANG");
        PL = new Button("PL");
        
        //zatrzymanie i odtwarzanie czasu
        start = new Button("start");
        stop = new Button("stop");

//        HBox Support = new HBox(sound, buttonSoundON, buttonSoundOFF);
        HBoxChild1 = new HBox(start, stop);
        HBoxChild2 = new HBox(sound, buttonSoundON, buttonSoundOFF);
        HBoxChild3 = new HBox(ANG, PL);
        VBoxParent = new VBox(HBoxChild1, HBoxChild2, HBoxChild3);
		VBoxMother = new VBox(infoLabel, cb);
		VBoxCenter = new VBox(label, save_button);
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
		
		ANG.setOnAction(new EventHandler<ActionEvent> () {
        	public void handle(ActionEvent arg) {
        		changeLanguage("en");
        	}
        });
		
		PL.setOnAction(new EventHandler<ActionEvent> () {
        	public void handle(ActionEvent arg) {
        		changeLanguage("pl");
        	}
        });
		
		VBoxMother.prefHeightProperty().bind(this.heightProperty());
		VBoxCenter.prefHeightProperty().bind(this.heightProperty().multiply(1.5));
		// HBox.setMargin(bottom_button1, new Insets(8,8,8,8));
		
		Panel = new GridPane();
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
		
		label.setText(Info.get(0));
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
    		public void changed(ObservableValue<? extends Number> ov, Number value, Number new_value) {
    			label.setText(Info.get(new_value.intValue()));
    		}
    	});
        
        
        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
        	public void changed(ObservableValue<? extends Number> ov, 
        			Number old_val, Number new_val) {
        			value = dfZero.format(new_val.doubleValue());
        			valueLabel.setText("Prędkość*"+value);
        	}
        });
		
        labelSlider = new Label(messages.getString("Timespeed"));
        planetLabel = new Label(messages.getString("PlanetLabel"));
        
        planetLabel.setWrapText(true);
        
        valueLabel = new Label();
        vBox2 = new VBox(labelSlider, timeSlider, valueLabel, planetLabel);
        planetLabel.setMaxWidth(150);
        this.setRight(vBox2); 
        // HBox.setHgrow(stack, Priority.ALWAYS);
        
        cb.setTooltip(new Tooltip("Select the planet"));
    }
    
    void changeLanguage(String lang)
    {
    	locale = new Locale(lang);
		messages = ResourceBundle.getBundle("pw.edu.fizyka.pojava.messages", locale);
		
		GUI();
    }
    
	@SuppressWarnings("unchecked")
	public MenuPane() {	
		
		try {
			locale = new Locale(language);
			messages = ResourceBundle.getBundle("pw.edu.fizyka.pojava.messages", locale);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		GUI();
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
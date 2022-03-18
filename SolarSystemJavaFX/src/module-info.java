module SolarSystemJavaFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics; //added
	
	opens application to javafx.graphics, javafx.fxml;
	opens pw.edu.fizyka.pojava to javafx.graphics, javafx.fxml;
}
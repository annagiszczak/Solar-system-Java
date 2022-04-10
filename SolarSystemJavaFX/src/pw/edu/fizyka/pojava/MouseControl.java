//Anna Giszczak
package pw.edu.fizyka.pojava;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


//Gdy przesuwamy wzd³u¿ osi X przedmiot to przesuwa siê on wokó³ osi Y 
//Gdyb przesuwamy wzd³u¿ osi Y przedmiot to przesuwa siê on wokó³ osi X 
public class MouseControl {

	//scene<-group<-aRotate<-angleA<-(anchorAngleA, clickedA)
	private double clickedX, clickedY; //punkty x y na ekranie
	private double clickedAngleX = 0; //kat o jaki sie przesunelo
	private double clickedAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(0); //kat pod jakim patrzymy
	private final DoubleProperty angleY = new SimpleDoubleProperty(0);
	  
	public MouseControl(Group group, SubScene scene, Stage stage) {
	    Rotate xRotate;
	    Rotate yRotate;
	    group.getTransforms().addAll(//obracanie siê grupy wokó³ osi
	        xRotate = new Rotate(0, Rotate.X_AXIS), 
	        yRotate = new Rotate(0, Rotate.Y_AXIS)
	    );											
	    //powiazanie, jak zmienia siê angleA to zmienia sie tez aRotate
	    xRotate.angleProperty().bind(angleX);
	    yRotate.angleProperty().bind(angleY);

	    scene.setOnMousePressed(event -> { //bez tego mo¿na dzia³aæ, jednak grupa magicznie przeskakuje z miejsca na miesjce bo nie pamieta u³o¿eñ wczeœniejszych
	      clickedX = event.getSceneX();
	      clickedY = event.getSceneY();
	      clickedAngleX = angleX.get();
	      clickedAngleY = angleY.get();
	    });

	    scene.setOnMouseDragged(event -> {
	    	 angleX.set(clickedAngleX - (clickedY - event.getSceneY())); //(clickedY - event.getSceneY()) to odleglosc o ktora sie draggowalo
		     angleY.set(clickedAngleY + (clickedX - event.getSceneX()));

	    });
	    
	    //przybli¿anie i oddalanie
	    stage.addEventHandler(ScrollEvent.SCROLL, event -> { 
		      double delta = event.getDeltaY(); //przesuwanie do przodu i do ty³u, getDeltaX odczutuje przesuniecia na boki
		      group.translateZProperty().set(group.getTranslateZ() - delta); //zmienia odleglosc o tyle o ile siê zwiêkszy³o przesuniêcie
		    });

	    
	  }
}

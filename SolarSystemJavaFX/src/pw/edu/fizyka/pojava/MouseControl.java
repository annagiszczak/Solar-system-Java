//Anna Giszczak
package pw.edu.fizyka.pojava;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


//Gdy przesuwamy wzd�u� osi X przedmiot to przesuwa si� on wok� osi Y 
//Gdyb przesuwamy wzd�u� osi Y przedmiot to przesuwa si� on wok� osi X 
public class MouseControl {

	//scene<-group<-aRotate<-angleA<-(anchorAngleA, clickedA)
	private double clickedX, clickedY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(0);
	  
	public MouseControl(Group group, SubScene scene, Stage stage) {
	    Rotate xRotate;
	    Rotate yRotate;
	    group.getTransforms().addAll(//obracanie si� grupy wok� osi
	        xRotate = new Rotate(0, Rotate.X_AXIS), 
	        yRotate = new Rotate(0, Rotate.Y_AXIS)
	    );											
	    //powiazanie, jak zmienia si� angleA to zmienia sie tez aRotate
	    xRotate.angleProperty().bind(angleX);
	    yRotate.angleProperty().bind(angleY);

	    scene.setOnMousePressed(event -> { //bez tego mo�na dzia�a�, jednak grupa magicznie przeskakuje z miejsca na miesjce bo nie pamieta u�o�e� wcze�niejszych
	      clickedX = event.getSceneX();
	      clickedY = event.getSceneY();
	      anchorAngleX = angleX.get();
	      anchorAngleY = angleY.get();
	    });

	    scene.setOnMouseDragged(event -> {
	    	 angleX.set(anchorAngleX - (clickedY - event.getSceneY())); //(clickedY - event.getSceneY()) to odleglosc o ktora sie draggowalo
		     angleY.set(anchorAngleY + (clickedX - event.getSceneX()));

	    });
	    
	    //przybli�anie i oddalanie
	    stage.addEventHandler(ScrollEvent.SCROLL, event -> { 
		      double delta = event.getDeltaY(); //przesuwanie do przodu i do ty�u, getDeltaX odczutuje przesuniecia na boki
		      group.translateZProperty().set(group.getTranslateZ() - delta); //zmienia odleglosc o tyle o ile si� zwi�kszy�o przesuni�cie
		    });

	    
	  }
}

package pl.edu.fizyka.pojava;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


//tymczasowo z youtuba, potem mo¿na przeinaczyæ
public class MouseControl {

	private double anchorX, anchorY;
	  private double anchorAngleX = 0;
	  private double anchorAngleY = 0;
	  private final DoubleProperty angleX = new SimpleDoubleProperty(0);
	  private final DoubleProperty angleY = new SimpleDoubleProperty(0);
	  
	public MouseControl(Group group, SubScene scene, Stage stage) {
	    Rotate xRotate;
	    Rotate yRotate;
	    group.getTransforms().addAll(
	        xRotate = new Rotate(0, Rotate.X_AXIS),
	        yRotate = new Rotate(0, Rotate.Y_AXIS)
	    );
	    xRotate.angleProperty().bind(angleX);
	    yRotate.angleProperty().bind(angleY);

	    scene.setOnMousePressed(event -> {
	      anchorX = event.getSceneX();
	      anchorY = event.getSceneY();
	      anchorAngleX = angleX.get();
	      anchorAngleY = angleY.get();
	    });

	    scene.setOnMouseDragged(event -> {
	      angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
	      angleY.set(anchorAngleY + anchorX - event.getSceneX());
	    });

	    stage.addEventHandler(ScrollEvent.SCROLL, event -> {
	      double delta = event.getDeltaY();
	      group.translateZProperty().set(group.getTranslateZ() + delta);
	    });
	  }
}

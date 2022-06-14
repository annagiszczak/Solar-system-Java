package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

public class SampleController implements Initializable{
	

	@FXML
	private SubScene subScene;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	    Sphere sphere = new Sphere(50);

	    Group group = new Group();
	    group.getChildren().add(sphere);
	    
	    group.setLayoutX(100);
	    group.setLayoutY(100);
	    
	    group.getChildren().add(sphere);
	    subScene.setRoot(group);
	    
//	    Camera camera = new PerspectiveCamera(true);
//	    Scene scene = new Scene(group, 700, 400);
//	    scene.setFill(Color.SILVER);
//	    scene.setCamera(camera);
//
//	    camera.translateZProperty().set(-500);
//
//	    camera.setNearClip(1);
//	    camera.setFarClip(1000);
	    
		
	}

}

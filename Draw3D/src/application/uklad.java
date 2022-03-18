package application;


import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class uklad {
	  private static final int WIDTH = 1400;
	  private static final int HEIGHT = 800;
	public uklad(){
		    Sphere sphere = new Sphere(50);

		    Group group = new Group();
		    group.getChildren().add(sphere);

		    Camera camera = new PerspectiveCamera();
		    Scene scene = new Scene(group, WIDTH, HEIGHT);
		    //scene.setFill(Color.SILVER);
		    //scene.setCamera(camera);

		    sphere.translateXProperty().set(WIDTH / 2);
		    sphere.translateYProperty().set(HEIGHT / 2);
		}
}


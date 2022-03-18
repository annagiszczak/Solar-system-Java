package pl.edu.fizyka.pojava;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;

public class SolarSystemCamera extends PerspectiveCamera {

	public SolarSystemCamera() {
		Camera camera = new PerspectiveCamera(true);
		camera.setNearClip(1);
		camera.setFarClip(1000);
		camera.translateZProperty().set(-200);
	}

	public SolarSystemCamera(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}

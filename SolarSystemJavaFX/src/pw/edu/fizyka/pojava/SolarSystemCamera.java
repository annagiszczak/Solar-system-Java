package pw.edu.fizyka.pojava;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;

public class SolarSystemCamera extends PerspectiveCamera {

	public SolarSystemCamera() {
		
	}

	public SolarSystemCamera(boolean arg0) {
		super(arg0);
		this.setNearClip(1);
		this.setFarClip(50000);
		this.translateZProperty().set(-2000);

	}

}

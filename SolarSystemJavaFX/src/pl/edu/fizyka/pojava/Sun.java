package pl.edu.fizyka.pojava;

import javafx.scene.Node;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Sun extends Sphere {

	  
	private final PointLight pointLight;
	public Sun() {
		this.pointLight = new PointLight();
		// TODO Auto-generated constructor stub
	}

	public Sun(double arg0) {
		super(arg0);
		pointLight = new PointLight();
			    pointLight.setColor(Color.RED);
			    pointLight.getTransforms().add(new Translate(0, 0, 0));
			    pointLight.setRotationAxis(Rotate.Z_AXIS);

			    //PhongMaterial material = new PhongMaterial();
			   // material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/illuminati2.jpg")));
			    Sphere sphere = new Sphere(arg0);
			    sphere.getTransforms().setAll(pointLight.getTransforms());
			    sphere.rotateProperty().bind(pointLight.rotateProperty());
			    //sphere.setMaterial(material);
			    sphere.rotationAxisProperty().bind(pointLight.rotationAxisProperty());
	}

	public Sun(double arg0, int arg1) {
		super(arg0, arg1);
		this.pointLight = new PointLight();
		
		// TODO Auto-generated constructor stub
	}

}

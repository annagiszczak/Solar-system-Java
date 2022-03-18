package pw.edu.fizyka.pojava;

import javafx.scene.PointLight;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
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
		PhongMaterial material = new PhongMaterial();
		//material.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/spec.jpg")));
		//material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/sunmap.jpg")));
	    material.setDiffuseMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/sunmap.jpg")));
		//material.setSpecularColor(Color.WHITE);
		this.getTransforms().setAll(pointLight.getTransforms());
		this.rotateProperty().bind(pointLight.rotateProperty());
		this.setMaterial(material);
		this.rotationAxisProperty().bind(pointLight.rotationAxisProperty());
	}

	public Sun(double arg0, int arg1) {
		super(arg0, arg1);
		this.pointLight = new PointLight();
		
		// TODO Auto-generated constructor stub
	}

}

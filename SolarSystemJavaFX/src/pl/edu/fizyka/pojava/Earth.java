package pl.edu.fizyka.pojava;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

public class Earth extends Sphere {

	public Earth() {
		// TODO Auto-generated constructor stub
	}

	public Earth(double arg0) {
		super(arg0);
		//rysowanie orbity
		Circle orbit = new Circle(0,0,50);
		orbit.setStrokeWidth(0.1);
		orbit.setFill(Color.TRANSPARENT);
		orbit.setStroke(Color.WHITE);
		
		//przesuniecie o r od slonca
		this.getTransforms().add(new Translate(50, 0, 0));
		
		PhongMaterial material = new PhongMaterial();
		//material.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/spec.jpg")));
		//material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/sunmap.jpg")));
	    material.setDiffuseMap(new Image(getClass().getResourceAsStream("/pl/edu/fizyka/pojava/resources/earth-d.jpg")));
		//material.setSpecularColor(Color.WHITE);
		this.setMaterial(material);
	}

	public Earth(double arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

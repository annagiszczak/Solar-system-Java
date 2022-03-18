package pl.edu.fizyka.pojava;

import javafx.scene.paint.Color;
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
		
		//this.getTransforms().add(new Translate(0, 0, 0));
	}

	public Earth(double arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

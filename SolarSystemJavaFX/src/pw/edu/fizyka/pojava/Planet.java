package pw.edu.fizyka.pojava;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

public class Planet extends Sphere {
	//R promien planety
	//r odleglosc od slonca
	// jakies inne
	Circle orbit;
	public Planet() {
		// TODO Auto-generated constructor stub
	}

	public Planet(double R, double r, String nazwa1, String nazwa2, String nazwa3, String nazwa4) {
		super(R);
		//rysowanie orbity
//				orbit = new Circle(0,0,r);
//				orbit.setStrokeWidth(0.1);
//				orbit.setFill(Color.TRANSPARENT);
//				orbit.setStroke(Color.BLACK);
				
				//przesuniecie o r od slonca
				this.getTransforms().add(new Translate(r, 0, 0));
				
				PhongMaterial material = new PhongMaterial();
				
//			    material.setDiffuseMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/earth-d.jpg")));

				//material.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/spec.jpg")));
				//material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/sunmap.jpg")));
			    material.setDiffuseMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/"+nazwa1+".jpg")));
			    if (nazwa2.isEmpty()==false)
			    	material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/"+nazwa2+".jpg")));
			    if (nazwa3.isEmpty()==false)
			    	material.setSpecularMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/"+nazwa3+".tif")));
			    if (nazwa4.isEmpty()==false)
			    	material.setBumpMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/"+nazwa4+".tif")));
			    
			    
				//material.setSpecularColor(Color.WHITE);
				this.setMaterial(material);
	}

	public Planet(double arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

///Anna Giszczak
package pw.edu.fizyka.pojava;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

public class Planet extends Sphere {

	public Planet() {
		// TODO Auto-generated constructor stub
	}

	public Planet(double R, double r, String diffuseMap, String selfIlluminationMap, String specularMap, String bumpMap) {
		super(R);	
				//przesuniecie o r od slonca
				this.getTransforms().add(new Translate(r, 0, 0));
				
				PhongMaterial material = new PhongMaterial();

			    material.setDiffuseMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/"+diffuseMap+".jpg")));
			    if (selfIlluminationMap.isEmpty()==false)
			    	material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/"+selfIlluminationMap+".jpg")));
			    if (specularMap.isEmpty()==false)
			    	material.setSpecularMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/"+specularMap+".tif")));
			    if (bumpMap.isEmpty()==false)
			    	material.setBumpMap(new Image(getClass().getResourceAsStream("/pw/edu/fizyka/pojava/resources/"+bumpMap+".tif")));

				this.setMaterial(material);
	}

	public Planet(double arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

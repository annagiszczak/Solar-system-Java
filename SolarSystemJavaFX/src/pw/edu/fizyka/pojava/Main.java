//Anna Giszczak
//Music: https://www.bensound.com
package pw.edu.fizyka.pojava;

import java.io.File;
import java.net.MalformedURLException;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	
	private static final int WIDTH_OF_MAINSCENE=1400;
	private static final float HEIGHT_OF_MAINSCENE = 800;
	private static final int WIDTH_OF_SUBSCENE=1000;
	private static final float HEIGHT_OF_SUBSCENE = 800;
	private Planet [] planet;
	private Group []planetsGroup;
	
	//planets statics final 
	private static final double R [] = {20,60,70,35,150,110,80,75};
	private static final double r [] = {550,650,800,1000,1300,1700,2000,2200};
	private static final String diffuseMap [] = {"mercury","venusatmosphere","earth","mars","jupiter","saturn","uranus","neptune"};
	private static final String selfIlluminationMap [] = {"", "", "earth_night", "", "","", "", ""};
	private static final String specularMap [] = {"", "", "earth_specular", "", "","", "", ""};
	private static final String bumpMap [] = {"", "", "earth_map", "", "","", "", ""};
	private static final double orbitalSpeed [] = {0.179037631,-0.052737733,0.04260547,0.064693979,0.00170871,0.001467668,0.002426124,0.001988414};
	private static final double planetsSpeed [] = {4.73926*Math.pow(10, -5),1,85322*Math.pow(10, -5),1.14134*Math.pow(10, -5),6.05795*Math.pow(10, -6),9.64238*Math.pow(10, -7),3.89542*Math.pow(10, -7),1.35707*Math.pow(10, -7),6.87816*Math.pow(10, -8)};	
	
	
	double sliderT;
	
	@Override
	public void start(Stage primaryStage)throws MalformedURLException {
		//Stage->
			//Scene->
				//BorderPane(ManuPane):
					//Top: Menu
					//Left: Jakis bar, info, guziki wszystkie czy cos
					//Bottom: pasek do zmiany czasu
					//Right: moze guziki, trzeba pomyslec nad rozkÂ³adem
					//Center:
						//->SubScene:
							//uklad s³oneczny, kamera, poruszanie myszka itp itd
		
		boolean is3DSupported = Platform.isSupported(ConditionalFeature.SCENE3D);
        if(!is3DSupported) {
           System.out.println("Sorry, 3D is not supported in JavaFX on this platform.");
           return;
        }
		
		MenuPane menuPane = new MenuPane();
		menuPane.setStyle("-fx-background-color: #e7cbfb;");
		
		//Stworzone tymczasowo, tutaj bedzie znajdowac sie ukÂ³ad sÂ³oneczny
		Group universeGroup = new Group();
		planetsGroup = new Group[8];
		for(int i = 0; i < 8; i++) {
			planetsGroup[i]= new Group();
			universeGroup.getChildren().add(planetsGroup[i]);
		}
		
		Group moonsGroup = new Group();
		Sun sun = new Sun();
		planet = new Planet[8];
		for(int i = 0; i < 8; i++) {
			
			planet[i]= new Planet(R[i], r[i], diffuseMap[i], selfIlluminationMap[i], specularMap[i], bumpMap[i]);
			planetsGroup[i].getChildren().add(planet[i]);
		}
		universeGroup.getChildren().addAll(sun.createSun());
		
		
		//playing sound
		BackgroundMusic music = new BackgroundMusic();
		
		//to wykorzystam do obrotu w ruchu do okola osi
		Point3D p= new Point3D(0,1,0);
		Point3D h= new Point3D(1,0,0);
		Point3D c= new Point3D(0,0,1);
		
		
		//proba ruchu planet,ale kreci sie w z³¹ strone bo osie x,y a nie x,z, albo to sie zatrzyma albo dopasuje sie reszte ukladu pod to
		//czy mozna narysowac elipse 2D w osiach X I Z?
		//czy mozna przyjac ze jest 60framów?
		//jak dodac ksiezyce jak jest timer?
		MenuPane.timeSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				sliderT = MenuPane.timeSlider.getValue();
				
			}
			//slider.getValue();
		
		});
		AnimationTimer timer = new AnimationTimer() {
		      @Override
		      public void handle(long now) {
		    	  for(int i = 0;i<8; i++) {
		    		  
		    		  //kreci dokooko³a osi planety
		    		  planetsGroup[i].setRotationAxis(p);
		    		  planetsGroup[i].setRotate(planetsGroup[i].getRotate() + planetsSpeed[i]*sliderT);
		    		  //kreci dooko³a s³onca
		    		  planet[i].setRotationAxis(p);
		    		  planet[i].setRotate(planet[i].getRotate() + orbitalSpeed[i]*sliderT);
		    		  //planet[i].setRotationAxis(h);
		    		  //planet[i].setRotate(planet[i].getRotate() + 0.1*i+1);
		    	  }
		      }
		    };
		    timer.start();
		    
		//2d w 3d i sie zacina
		
//		Ellipse ellipseEarth = new Ellipse();
//		ellipseEarth.setRadiusX(sun.sun.getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 170);
//        ellipseEarth.setRadiusY(sun.sun.getBoundsInLocal().getHeight() / 2.0 + 0.98329134 * 170);
// 
//        PathTransition transitionEarth = new PathTransition();
//        transitionEarth.setPath(ellipseEarth);
//        transitionEarth.setNode(planet[2]);
//        transitionEarth.setInterpolator(Interpolator.LINEAR);
//        transitionEarth.setDuration(Duration.seconds(12.000017421));
//        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        transitionEarth.setCycleCount(Timeline.INDEFINITE);
// 
//        transitionEarth.play();
//        ellipseEarth.setVisible(false);
		
		//Group universe = new Group();
		//universe.getChildren().add(group);
		  
		//stworzenie subsceny i ustawienie jej na Å“rodku
		SubScene subScene = new SubScene(universeGroup, WIDTH_OF_SUBSCENE, HEIGHT_OF_SUBSCENE,true,SceneAntialiasing.BALANCED);
		subScene.setFill(Color.BLACK);
		
		//subScene.setFill(Color.BEIGE); //na czas testowy potem sie zmieni
		menuPane.setCenter(subScene);
		
		//Dodanie kamery i przesuniecie grupy na srodek
		subScene.setCamera(new SolarSystemCamera(true));
		
		//glowna scena
		Scene mainScene = new Scene(menuPane, WIDTH_OF_MAINSCENE, HEIGHT_OF_MAINSCENE, true);
		
		//ruszanie myszka, trzeba bedzie potem rozwinÂ¹Ã¦
		MouseControl mouseControl = new MouseControl(universeGroup, subScene, primaryStage);
		
		
		//ustawienia primaryStage
		primaryStage.setTitle("Uklad sloneczny");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

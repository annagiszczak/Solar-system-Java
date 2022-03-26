package pw.edu.fizyka.pojava;

import java.io.File;
import java.net.MalformedURLException;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	
	private static final int WIDTH_OF_MAINSCENE=1400;
	private static final float HEIGHT_OF_MAINSCENE = 800;
	private static final int WIDTH_OF_SUBSCENE=1000;
	private static final float HEIGHT_OF_SUBSCENE = 800;
	private Planet [] planet;
	private static final double R [] = {20,60,70,35,150,110,80,75};
	private static final double r [] = {550,650,800,1000,1300,1700,2000,2200};
	private static final String name1 [] = {"mercury","venusatmosphere","earth","mars","jupiter","saturn","uranus","neptune"};
	private static final String name2 [] = {"", "", "earth_night", "", "","", "", ""};
	private static final String name3 [] = {"", "", "earth_specular", "", "","", "", ""};
	private static final String name4 [] = {"", "", "earth_map", "", "","", "", ""};
	Group []planetsGroup;
	String path = "/pw/edu/fizyka/pojava/resources/music.mp3"; 

	
	@Override
	public void start(Stage primaryStage)throws MalformedURLException {
		//Stage->
			//Scene->
				//BorderPane(ManuPane):
					//Top: Menu
					//Left: Jakis bar, info, guziki wszystkie czy cos
					//Bottom: pasek do zmiany czasu
					//Right: moze guziki, trzeba pomyslec nad rozk³adem
					//Center:
						//->SubScene:
							//uk³ad s³oneczny, kamera, poruszanie myszk¹ itp itd
		
		boolean is3DSupported = Platform.isSupported(ConditionalFeature.SCENE3D);
        if(!is3DSupported) {
           System.out.println("Sorry, 3D is not supported in JavaFX on this platform.");
           return;
        }
		
		MenuPane menuPane = new MenuPane();
		
		//Stworzone tymczasowo, tutaj bêdzie znajdowaæ siê uk³ad s³oneczny
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
			planet[i]= new Planet(R[i], r[i], name1[i], name2[i], name3[i], name4[i]);
			planetsGroup[i].getChildren().add(planet[i]);
		}
		universeGroup.getChildren().addAll(sun.createSun());
		
		
		//tutaj chce dodac muzyczke
		Media sound = new Media(getClass().getResource(path).toExternalForm());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		
		//proba ruchu planet,ale kreci sie w z�� strone bo osie x,y a nie x,z, albo to sie zatrzyma albo dopasuje sie reszte ukladu pod to
		Ellipse ellipseEarth = new Ellipse();
		ellipseEarth.setRadiusX(sun.sun.getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 170);
        ellipseEarth.setRadiusY(sun.sun.getBoundsInLocal().getHeight() / 2.0 + 0.98329134 * 170);
 
        PathTransition transitionEarth = new PathTransition();
        transitionEarth.setPath(ellipseEarth);
        transitionEarth.setNode(planet[2]);
        transitionEarth.setInterpolator(Interpolator.LINEAR);
        transitionEarth.setDuration(Duration.seconds(12.000017421));
        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionEarth.setCycleCount(Timeline.INDEFINITE);
 
        transitionEarth.play();
        ellipseEarth.setVisible(false);
		
		//Group universe = new Group();
		//universe.getChildren().add(group);
		  
		//stworzenie subsceny i ustawienie jej na œrodku
		SubScene subScene = new SubScene(universeGroup, WIDTH_OF_SUBSCENE, HEIGHT_OF_SUBSCENE,true,SceneAntialiasing.BALANCED);
		subScene.setFill(Color.BLACK);
		
		//subScene.setFill(Color.BEIGE); //na czas testowy potem sie zmieni
		menuPane.setCenter(subScene);
		
		//Dodanie kamery i przesuniecie grupy na srodek
		subScene.setCamera(new SolarSystemCamera(true));
		
		//glowna scena
		Scene mainScene = new Scene(menuPane, WIDTH_OF_MAINSCENE, HEIGHT_OF_MAINSCENE, true);
		
		//ruszanie myszka, trzeba bedzie potem rozwin¹æ
		MouseControl mouseControl = new MouseControl(universeGroup, subScene, primaryStage);
		
		
		//ustawienia primaryStage
		primaryStage.setTitle("Uklad sloneczny");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
//	//obraz w tle lub czarny ekran??
//	  private ImageView prepareImageView() {
//		    Image image = new Image(Main.class.getResourceAsStream("/pw/edu/fizyka/pojava/resources/milkyway.jpg"));
//		    ImageView imageView = new ImageView(image);
//		    imageView.setFitWidth(WIDTH_OF_SUBSCENE);
//		    imageView.setPreserveRatio(true);
//		    imageView.getTransforms().add(new Translate(-image.getWidth() / 2, -image.getHeight() / 2, 800));
//		    return imageView;
//		  }

	public static void main(String[] args) {
		launch(args);
	}
}

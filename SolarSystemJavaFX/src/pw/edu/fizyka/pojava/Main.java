package pw.edu.fizyka.pojava;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	

	
	@Override
	public void start(Stage primaryStage) {
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
		Group group = new Group();
		Sun sun = new Sun();
		planet = new Planet[8];
		for(int i = 0; i < 8; i++) {
			planet[i]= new Planet(R[i], r[i], name1[i], name2[i], name3[i], name4[i]);
			group.getChildren().add(planet[i]);
		}
		group.getChildren().addAll(sun.createSun());
		
		//Group universe = new Group();
		//universe.getChildren().add(group);
		  
		//stworzenie subsceny i ustawienie jej na œrodku
		SubScene subScene = new SubScene(group, WIDTH_OF_SUBSCENE, HEIGHT_OF_SUBSCENE,true,SceneAntialiasing.BALANCED);
		subScene.setFill(Color.BLACK);
		
		//subScene.setFill(Color.BEIGE); //na czas testowy potem sie zmieni
		menuPane.setCenter(subScene);
		
		//Dodanie kamery i przesuniecie grupy na srodek
		subScene.setCamera(new SolarSystemCamera(true));
		
		//glowna scena
		Scene mainScene = new Scene(menuPane, WIDTH_OF_MAINSCENE, HEIGHT_OF_MAINSCENE, true);
		
		//ruszanie myszk¹, trzeba bedzie potem rozwin¹æ
		MouseControl mouseControl = new MouseControl(group, subScene, primaryStage);
		
		
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

package pw.edu.fizyka.pojava;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final int WIDTH_OF_MAINSCENE=1400;
	private static final float HEIGHT_OF_MAINSCENE = 800;
	private static final int WIDTH_OF_SUBSCENE=800;
	private static final float HEIGHT_OF_SUBSCENE = 600;
	private Planet [] planet;
	private static final double R [] = {20,60,70,35,150,110,80,75};
	private static final double r [] = {550,650,800,1000,1300,1700,2000,2200};
	private static final String name [] = {"mercury","venusatmosphere","earth","mars","jupiter","saturn","uranus","neptune"};

	
	@Override
	public void start(Stage primaryStage) {
		//Stage->
			//Scene->
				//BorderPane(ManuPane):
					//Top: Menu
					//Left: Jakis bar, info, guziki wszystkie czy cos
					//Bottom: pasek do zmiany czasu
					//Right: moze guziki, trzeba pomyslec nad rozk�adem
					//Center:
						//->SubScene:
							//uk�ad s�oneczny, kamera, poruszanie myszk� itp itd
		
		MenuPane menuPane = new MenuPane();
		
		//Stworzone tymczasowo, tutaj b�dzie znajdowa� si� uk�ad s�oneczny
		Group group = new Group();
		Sun sun = new Sun();
		//group.getChildren().add(sun);
		planet = new Planet[8];
		for(int i = 0; i < 8; i++) {
			planet[i]= new Planet(R[i], r[i], name[i]);
			group.getChildren().add(planet[i]);
		}
		group.getChildren().addAll(sun.createSun());
		group.getChildren().add(prepareImageView());
		
		  
		//stworzenie subsceny i ustawienie jej na �rodku
		SubScene subScene = new SubScene(group, WIDTH_OF_SUBSCENE, HEIGHT_OF_SUBSCENE,true,SceneAntialiasing.BALANCED);
		subScene.setFill(Color.BEIGE); //na czas testowy potem sie zmieni
		menuPane.setCenter(subScene);
		
		//Dodanie kamery i przesuniecie grupy na srodek
		subScene.setCamera(new SolarSystemCamera(true));
		
		//glowna scena
		Scene mainScene = new Scene(menuPane, WIDTH_OF_MAINSCENE, HEIGHT_OF_MAINSCENE, true);
		
		//ruszanie myszk�, trzeba bedzie potem rozwin��
		MouseControl mouseControl = new MouseControl(group, subScene, primaryStage);
		
		
		//ustawienia primaryStage
		primaryStage.setTitle("Uklad sloneczny");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	//obraz w tle
	  private ImageView prepareImageView() {
		    Image image = new Image(Main.class.getResourceAsStream("/pw/edu/fizyka/pojava/resources/milkyway.jpg"));
		    ImageView imageView = new ImageView(image);
		    imageView.setPreserveRatio(false);
		    imageView.getTransforms().add(new Translate(-image.getWidth() / 2, -image.getHeight() / 2, 800));
		    return imageView;
		  }

	public static void main(String[] args) {
		launch(args);
	}
}

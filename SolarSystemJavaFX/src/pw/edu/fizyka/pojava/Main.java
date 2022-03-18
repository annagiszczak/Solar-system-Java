package pw.edu.fizyka.pojava;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final int WIDTH_OF_MAINSCENE=1400;
	private static final float HEIGHT_OF_MAINSCENE = 800;
	private static final int WIDTH_OF_SUBSCENE=800;
	private static final float HEIGHT_OF_SUBSCENE = 600;

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
		
		MenuPane menuPane = new MenuPane();
		
		//Stworzone tymczasowo, tutaj bêdzie znajdowaæ siê uk³ad s³oneczny
		Group group = new Group();
		Sun sun = new Sun(40);
		Earth earth = new Earth(50);
		group.getChildren().add(earth);
		group.getChildren().add(sun);
		
		
		
		  
		//stworzenie subsceny i ustawienie jej na œrodku
		SubScene subScene = new SubScene(group, WIDTH_OF_SUBSCENE, HEIGHT_OF_SUBSCENE, true,SceneAntialiasing.BALANCED);
		subScene.setFill(Color.BEIGE); //na czas testowy potem sie zmieni
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

	public static void main(String[] args) {
		launch(args);
	}
}

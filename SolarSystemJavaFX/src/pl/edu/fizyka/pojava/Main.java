package pl.edu.fizyka.pojava;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
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
				//BorderPane:
					//Top: Menu
					//Left: Jakis bar, info, guziki wszystkie czy cos
					//Bottom: pasek do zmiany czasu
					//Right: moze guziki, trzeba pomyslec nad rozk³adem
					//Center:
						//->SubScene:
							//uk³ad s³oneczny, kamera, poruszanie myszk¹ itp itd
		
		BorderPane borderPane = new BorderPane();
		
		//Stworzone tymczasowo, tutaj bêdzie znajdowaæ siê uk³ad s³oneczny
		Group group = new Group();
		
		//stworzenie subsceny i ustawienie jej na œrodku
		SubScene subScene = new SubScene(group, WIDTH_OF_SUBSCENE, HEIGHT_OF_SUBSCENE);
		borderPane.setCenter(subScene);
		
		Scene mainScene = new Scene(borderPane, WIDTH_OF_MAINSCENE, HEIGHT_OF_MAINSCENE, true);
		
		//ustawienia primaryStage
		primaryStage.setTitle("Uklad sloneczny");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

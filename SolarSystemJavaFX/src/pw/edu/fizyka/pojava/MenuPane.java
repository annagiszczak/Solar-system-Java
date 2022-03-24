package pw.edu.fizyka.pojava;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuPane extends BorderPane {

	public MenuPane() {
		ObservableList<String> names = FXCollections.observableArrayList("Jerry", "Elaine", "George", "Kramer",
				"Newman", "Morty", "Helen", "Frank", "Estelle");
		
		ListView<String> listView = new ListView<>(names);
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("Selected item: " + newValue);
			}
		});
		this.setLeft(listView); // taki 
		Slider slider = new Slider(0, 100, 0);

        VBox vBox = new VBox(slider);
        this.setRight(vBox); //jakies pojedyncze wstawilam ale to usun i zamien na lepsiejsze zeby to mialo rece i nogi 
        //jak cos to pisz i pytaj  ustaw tez t³o paneli jakieœ ³adne 
	}

	public MenuPane(Node arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MenuPane(Node arg0, Node arg1, Node arg2, Node arg3, Node arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}

}

package application;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class Mojuklad extends Application {
    public Sphere earth;
    public Sphere moon;
    @Override
    public void start(Stage primaryStage) {
        // Button btn = new Button();
        // btn.setText("Say 'Hello World'");
        // btn.setOnAction(new EventHandler<ActionEvent>() {
        //
        // @Override
        // public void handle(ActionEvent event) {
        // System.out.println("Hello World!");
        // }
        // });
        Sphere star = new Sphere(45);
        earth = new Sphere(45);
        moon = new Sphere(25);
 
        Ellipse ellipseEarth = new Ellipse();
        // ellipseEarth.setCenterX(star.getTranslateX());
        // ellipseEarth.setCenterY(star.getTranslateY());
        // ellipseEarth.translateXProperty().bind(star.translateXProperty());
        // ellipseEarth.translateYProperty().bind(star.translateYProperty());
        ellipseEarth.setRadiusX(star.getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 170);
        ellipseEarth.setRadiusY(star.getBoundsInLocal().getHeight() / 2.0 + 0.98329134 * 170);
 
        PathTransition transitionEarth = new PathTransition();
        transitionEarth.setPath(ellipseEarth);
        transitionEarth.setNode(earth);
        transitionEarth.setInterpolator(Interpolator.LINEAR);
        transitionEarth.setDuration(Duration.seconds(10.000017421));
        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionEarth.setCycleCount(Timeline.INDEFINITE);
 
        transitionEarth.play();
 
        Ellipse ellipseMoon = new Ellipse();
        // ellipseMoon.setCenterX(earth.getTranslateX());
        // ellipseMoon.setCenterY(earth.getTranslateY());
        // ellipseMoon.translateXProperty().bind(earth.translateXProperty());
        // ellipseMoon.translateYProperty().bind(earth.translateYProperty());
        ellipseMoon.setRadiusX(earth.getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 70);
        ellipseMoon.setRadiusY(earth.getBoundsInLocal().getHeight() / 2.0 + 0.98329134 * 70);
        // ellipse.setStrokeWidth(5);
 
        PathTransition transitionMoon = new PathTransition();
        transitionMoon.setPath(ellipseMoon);
        transitionMoon.setNode(moon);
        transitionMoon.setInterpolator(Interpolator.LINEAR);
        transitionMoon.setDuration(Duration.seconds(1.000017421));
        transitionMoon.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionMoon.setCycleCount(Timeline.INDEFINITE);
        transitionMoon.play();
 
         
        /*
         * Hide the ellipse shadows
         */
        ellipseMoon.setVisible(false);
        ellipseEarth.setVisible(false);
 
        /* here we create a new pane which we bind to earths location, add moon 
         * into it and then add the pane to the root pane
         */
        StackPane root = new StackPane();
        StackPane moonPane = new StackPane();
        moonPane.translateXProperty().bind(earth.translateXProperty());
        moonPane.translateYProperty().bind(earth.translateYProperty());
        moonPane.setMaxSize(100, 100);
        //color for the new pane to see it
        moonPane.setStyle("-fx-background-color: #EAEAAE;"); 
 
        root.getChildren().add(star);
        moonPane.getChildren().add(moon);
        root.getChildren().add(moonPane);
        root.getChildren().add(ellipseEarth);
        root.getChildren().add(earth);
 
        Scene scene = new Scene(root, 800, 600);
 
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
}
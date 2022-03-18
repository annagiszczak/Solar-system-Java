package application;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * @author afsal villan
 * @version 1.0
 *
 * http://www.genuinecoder.com
 */
public class Main17 extends Application {

  private static final float WIDTH = 1400;
  private static final float HEIGHT = 800;

  private double anchorX, anchorY;
  private double anchorAngleX = 0;
  private double anchorAngleY = 0;
  private final DoubleProperty angleX = new SimpleDoubleProperty(0);
  private final DoubleProperty angleY = new SimpleDoubleProperty(0);

  private final Sphere sphere = new Sphere(150);


  @Override
  public void start(Stage primaryStage) {
	  
    Camera camera = new PerspectiveCamera(true);
    camera.setNearClip(1);
    camera.setFarClip(10000);
    camera.translateZProperty().set(-1000);

    Group world = new Group();
    world.getChildren().add(prepareEarth());

    Slider slider = prepareSlider();
    world.translateZProperty().bind(slider.valueProperty());
    

    Group root = new Group();
    
//    BorderPane panel = new BorderPane();
//    Button guzik = new Button();
//    Button guzik2 = new Button();
//    Button guzik3 = new Button();
//    Button guzik4 = new Button();
//    Button guzik5 = new Button();
//    Button guzik6 = new Button();
//    panel.setLeft(guzik);
//    panel.setCenter(guzik6);
//    panel.setRight(guzik5);
//    panel.setTop(guzik4);
//    panel.setBottom(guzik3);
//    panel.setRight(guzik2);
    
    //VBox vbox = prepareVBox();
    root.getChildren().add(world);
    //root.getChildren().add(prepareImageView());
    root.getChildren().add(slider);
    root.getChildren().add(panel);
    //root.getChildren().add(vbox);

    Scene scene = new Scene(root, WIDTH, HEIGHT, true);
    scene.setFill(Color.SILVER);
    scene.setCamera(camera);

    initMouseControl(world, scene, primaryStage);

    primaryStage.setTitle("Genuine Coder");
    primaryStage.setScene(scene);
    primaryStage.show();

    prepareAnimation();
  }

  private void prepareAnimation() {
	  
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        sphere.rotateProperty().set(sphere.getRotate() + 0.2);
      }
    };
    timer.start();
  }

//  private ImageView prepareImageView() {
//    Image image = new Image(Main17.class.getResourceAsStream("/resources/galaxy/galaxy.jpg"));
//    ImageView imageView = new ImageView(image);
//    imageView.setPreserveRatio(true);
//    imageView.getTransforms().add(new Translate(-image.getWidth() / 2, -image.getHeight() / 2, 800));
//    return imageView;
//  }

  private Slider prepareSlider() {
    Slider slider = new Slider();
    slider.setMax(800);
    slider.setMin(-400);
    slider.setPrefWidth(300d);
    slider.setLayoutX(-150);
    slider.setLayoutY(200);
    slider.setShowTickLabels(true);
    slider.setTranslateZ(5);
    slider.setStyle("-fx-base: black");
    return slider;
  }
  
  private VBox prepareVBox() {
	    VBox vbox = new VBox();
	    Button guzik = new Button();
	    vbox.getChildren().add(guzik);
	    vbox.setAlignment(Pos.TOP_LEFT);
	    //vbox.setPrefWidth(300);
	    vbox.setLayoutX(-200);
	    vbox.setLayoutY(-200);
	    vbox.setTranslateZ(5);
	    vbox.setStyle("-fx-base: black");
	    return vbox;
	  }

  private Node prepareEarth() {
//    PhongMaterial earthMaterial = new PhongMaterial();
//    earthMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-d.jpg")));
//    earthMaterial.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-l.jpg")));
//    earthMaterial.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-s.jpg")));
//    earthMaterial.setBumpMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-n.jpg")));

    sphere.setRotationAxis(Rotate.Y_AXIS);
    //sphere.setMaterial(earthMaterial);
    return sphere;
  }

  private void initMouseControl(Group group, Scene scene, Stage stage) {
    Rotate xRotate;
    Rotate yRotate;
    group.getTransforms().addAll(
        xRotate = new Rotate(0, Rotate.X_AXIS),
        yRotate = new Rotate(0, Rotate.Y_AXIS)
    );
    xRotate.angleProperty().bind(angleX);
    yRotate.angleProperty().bind(angleY);

    scene.setOnMousePressed(event -> {
      anchorX = event.getSceneX();
      anchorY = event.getSceneY();
      anchorAngleX = angleX.get();
      anchorAngleY = angleY.get();
    });

    scene.setOnMouseDragged(event -> {
      angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
      angleY.set(anchorAngleY + anchorX - event.getSceneX());
    });

    stage.addEventHandler(ScrollEvent.SCROLL, event -> {
      double delta = event.getDeltaY();
      group.translateZProperty().set(group.getTranslateZ() + delta);
    });
  }
  public static void main(String[] args) {
	    launch(args);
	  }
}
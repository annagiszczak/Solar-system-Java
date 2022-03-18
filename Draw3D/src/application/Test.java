package application;

import application.Maintest.SmartGroup;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 * @author afsal villan
 * @version 1.0
 *
 * http://www.genuinecoder.com
 */
public class Test extends Application {

//  private static final float WIDTH = 1400;
//  private static final float HEIGHT = 800;
//  double tims=2;
//
//  private double anchorX, anchorY;
//  private double anchorAngleX = 0;
//  private double anchorAngleY = 0;
//  private final DoubleProperty angleX = new SimpleDoubleProperty(0);
//  private final DoubleProperty angleY = new SimpleDoubleProperty(0);
//
//  @Override
	private static final float WIDTH = 1400;
	  private static final float HEIGHT = 800;
	  double tims=2;

	  private double anchorX, anchorY;
	  private double anchorAngleX = 0;
	  private double anchorAngleY = 0;
	  private final DoubleProperty angleX = new SimpleDoubleProperty(0);
	  private final DoubleProperty angleY = new SimpleDoubleProperty(0);

  public void start(Stage primaryStage) {
	  BorderPane p = new BorderPane();
	  Button guzik = new Button();
	  
	  Sphere earth = prepareSphere();
	  Sphere mars = prepareSphere2();
	  Circle orbit = new Circle(0,0,50);
	  orbit.setStrokeWidth(0.1);
	  orbit.setFill(Color.TRANSPARENT);
	  orbit.setStroke(Color.WHITE);
	    
	  SmartGroup group = new SmartGroup();
	  group.getChildren().add(earth);
	  group.getChildren().addAll(prepareLightSource());
	  group.getChildren().add(mars);
	  group.getChildren().add(orbit);
	    //group.getChildren().add(root);
	  Camera camera = new PerspectiveCamera(true);
	  camera.setNearClip(1);
	  camera.setFarClip(1000);
	  camera.translateZProperty().set(-200);
	  SubScene sub = new SubScene(group, 600, 600);
	  p.setCenter(sub);
	  sub.setFill(Color.SILVER);
	  
	  sub.setCamera(camera);
	  
	  group.translateXProperty().set(0);
	  group.translateYProperty().set(0);
	  group.translateZProperty().set(0);

	  initMouseControl(group, sub, primaryStage);


	    AnimationTimer timer = new AnimationTimer() {
	      @Override
	      public void handle(long now) {
	        earth.setRotate(earth.getRotate() + 0.1);
	        mars.setRotate(mars.getRotate() +0.2);
	      }
	    };
	    timer.start();
	  
	  
	  p.setLeft(guzik);;
	  primaryStage.setTitle("Uklad sloneczny!");
	  primaryStage.setScene(new Scene(p,1000,800, true));
	  primaryStage.show();
  }
  
  private final PointLight pointLight = new PointLight();

  private Node[] prepareLightSource() {
    pointLight.setColor(Color.RED);
    pointLight.getTransforms().add(new Translate(0, 0, 0));
    pointLight.setRotationAxis(Rotate.Z_AXIS);

    //PhongMaterial material = new PhongMaterial();
   // material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/illuminati2.jpg")));
    Sphere sphere = new Sphere(8);
    sphere.getTransforms().setAll(pointLight.getTransforms());
    sphere.rotateProperty().bind(pointLight.rotateProperty());
    //sphere.setMaterial(material);
    sphere.rotationAxisProperty().bind(pointLight.rotationAxisProperty());

    return new Node[]{pointLight, sphere};
  }
private Sphere prepareSphere() {
	Sphere sphere = new Sphere(5);
	sphere.getTransforms().add(new Translate(-50, -50, 0));
	
	return sphere;
}

private Sphere prepareSphere2() {
    
    Sphere sphere = new Sphere(6);
    sphere.getTransforms().add(new Translate(0, -50, 0));
    return sphere;
  }
class SmartGroup extends Group {

    Rotate r;
    Transform t = new Rotate();

    void rotateByX(int ang) {
      r = new Rotate(ang, Rotate.X_AXIS);
      t = t.createConcatenation(r);
      this.getTransforms().clear();
      this.getTransforms().addAll(t);
    }

    void rotateByY(int ang) {
      r = new Rotate(ang, Rotate.Y_AXIS);
      t = t.createConcatenation(r);
      this.getTransforms().clear();
      this.getTransforms().addAll(t);
    }
  }
private void initMouseControl(SmartGroup group, SubScene scene, Stage stage) {
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
//	BorderPane root = new BorderPane();
//	//SubScene subscena = new SubScene(root);
//	root.setTop(new Button("Top"));
//	root.setBottom(new Button("Top"));
//	root.setLeft(new Button("Top"));
//	root.setRight(new Button("Top"));
//	
//    Sphere earth = prepareSphere();
//    Sphere mars = prepareSphere2();
//    
//    
//    Circle orbit = new Circle(0,0,50);
//    orbit.setStrokeWidth(0.1);
//    orbit.setFill(Color.TRANSPARENT);
//    orbit.setStroke(Color.WHITE);
//    
//    SmartGroup group = new SmartGroup();
//    group.getChildren().add(earth);
//    group.getChildren().addAll(prepareLightSource());
//    group.getChildren().add(mars);
//    group.getChildren().add(orbit);
//    //group.getChildren().add(root);
//    Camera camera = new PerspectiveCamera(true);
//    camera.setNearClip(1);
//    camera.setFarClip(1000);
//    camera.translateZProperty().set(-200);
//
//    Scene scene = new Scene(group, WIDTH, HEIGHT, true);
//    //scene.add(root);
//    //((Group) scene.getRoot()).getChildren().add(root);
//    scene.setFill(Color.SILVER);
//    scene.setCamera(camera);
//
//    group.translateXProperty().set(0);
//    group.translateYProperty().set(0);
//    group.translateZProperty().set(0);
//
//    initMouseControl(group, scene, primaryStage);
//
//    primaryStage.setTitle("uk³ad s³onomeczny");
//    primaryStage.setScene(scene);
//    primaryStage.show();
//
//    AnimationTimer timer = new AnimationTimer() {
//      @Override
//      public void handle(long now) {
//        earth.setRotate(earth.getRotate() + 0.1);
//        mars.setRotate(mars.getRotate() +0.2);
//      }
//    };
//    timer.start();
//  }
//
//  private final PointLight pointLight = new PointLight();
//
//  private Node[] prepareLightSource() {
//    pointLight.setColor(Color.RED);
//    pointLight.getTransforms().add(new Translate(0, 0, 0));
//    pointLight.setRotationAxis(Rotate.Z_AXIS);
//
//    //PhongMaterial material = new PhongMaterial();
//   // material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/illuminati2.jpg")));
//    Sphere sphere = new Sphere(8);
//    sphere.getTransforms().setAll(pointLight.getTransforms());
//    sphere.rotateProperty().bind(pointLight.rotateProperty());
//    //sphere.setMaterial(material);
//    sphere.rotationAxisProperty().bind(pointLight.rotationAxisProperty());
//
//    return new Node[]{pointLight, sphere};
//  }
//
//  private Sphere prepareSphere() {
//    //PhongMaterial material = new PhongMaterial();
//    //material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/wood.jpg")));
//    
//    Sphere sphere = new Sphere(5);
//    sphere.getTransforms().add(new Translate(-50, -50, 0));
//    //sphere.setMaterial(material);
//    return sphere;
//  }
//  
//  private Sphere prepareSphere2() {
//	    //PhongMaterial material = new PhongMaterial();
//	    //material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/wood.jpg")));
//	    
//	    Sphere sphere = new Sphere(6);
//	    sphere.getTransforms().add(new Translate(0, -50, 0));
//	    //sphere.setMaterial(material);
//	    return sphere;
//	  }
//
//  private void initMouseControl(SmartGroup group, Scene scene, Stage stage) {
//    Rotate xRotate;
//    Rotate yRotate;
//    group.getTransforms().addAll(
//        xRotate = new Rotate(0, Rotate.X_AXIS),
//        yRotate = new Rotate(0, Rotate.Y_AXIS)
//    );
//    xRotate.angleProperty().bind(angleX);
//    yRotate.angleProperty().bind(angleY);
//
//    scene.setOnMousePressed(event -> {
//      anchorX = event.getSceneX();
//      anchorY = event.getSceneY();
//      anchorAngleX = angleX.get();
//      anchorAngleY = angleY.get();
//    });
//
//    scene.setOnMouseDragged(event -> {
//      angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
//      angleY.set(anchorAngleY + anchorX - event.getSceneX());
//    });
//
//    stage.addEventHandler(ScrollEvent.SCROLL, event -> {
//      double delta = event.getDeltaY();
//      group.translateZProperty().set(group.getTranslateZ() + delta);
//    });
//  }

  public static void main(String[] args) {
    launch(args);
  }

//  class SmartGroup extends Group {
//
//    Rotate r;
//    Transform t = new Rotate();
//
//    void rotateByX(int ang) {
//      r = new Rotate(ang, Rotate.X_AXIS);
//      t = t.createConcatenation(r);
//      this.getTransforms().clear();
//      this.getTransforms().addAll(t);
//    }
//
//    void rotateByY(int ang) {
//      r = new Rotate(ang, Rotate.Y_AXIS);
//      t = t.createConcatenation(r);
//      this.getTransforms().clear();
//      this.getTransforms().addAll(t);
//    }
//  }
}
//
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//    
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//    	Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
//        primaryStage.setTitle("Hello World!");
//        Scene scene = new Scene(root, 700, 500, true);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//}
//
///*Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
//primaryStage.setTitle("Hello World!");
//Scene scene = new Scene(root, 700, 500, true);
//primaryStage.setScene(scene);
//primaryStage.show();*/
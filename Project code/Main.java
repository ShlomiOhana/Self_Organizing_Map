import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		// constructing our scene
		URL url = getClass().getResource("OpenningPage.fxml");
		AnchorPane pane = FXMLLoader.load( url );
		Scene scene = new Scene( pane );
		// setting the stage
		primaryStage.setScene( scene );
		primaryStage.setTitle( "wellcome" );
		primaryStage.show();
	}

	public static List<Double> addAll( int a, int b, int c)
	{
		List<Double> l = new ArrayList<Double>();
		l.add((double) a);
		l.add((double) b);
		l.add((double) c);
		return l;
	}
	public static void main(String[] args) {
		launch(args);
		List<DataVector> d1 = new ArrayList<DataVector>();
		/*red*/
		DataVector v1 = new DataVector(addAll(255,0,0));
		
		DataVector v2 = new DataVector(addAll(255,51,51));
		
		
		/*green*/
		DataVector v3 = new DataVector(addAll(0,153,0));
		
		DataVector v4 = new DataVector(addAll(0,255,0));
		
		
		/*blue*/
		DataVector v5 = new DataVector(addAll(0,0,255));
		
		DataVector v6 = new DataVector(addAll(102,102,255));
		
		
		/*pink*/
		DataVector v7 = new DataVector(addAll(255,51,103));
		
		DataVector v8= new DataVector(addAll(255,102,178));
		
		/*green*/
		DataVector v9 = new DataVector(addAll(102,204,0));
		
		/*blue light*/
		DataVector v10 = new DataVector(addAll(0,255,255));
		
		d1.add(v1);
		d1.add(v10);
		d1.add(v5);
		d1.add(v9);
		d1.add(v3);
		d1.add(v6);
		d1.add(v4);
		d1.add(v8);
		d1.add(v7);
		d1.add(v2);
		
		
		Som som = new Som(2, 2, 3);
		System.out.println(som);
		som.setSomMap(1000, d1);
		som.printMap();
		

	}


}

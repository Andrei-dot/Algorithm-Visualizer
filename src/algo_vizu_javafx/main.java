package algo_vizu_javafx;

import algo_vizu_javafx.views.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

	/*
	 * Model MVVM	
	 */
	
	// playlist jfx :https://www.youtube.com/watch?v=VkbRLNwfjV4&list=PLrSOXFDHBtfGPyx7UHfsJtrdnpa_ix0ah&index=2

	// Stage = fenetre (window) primaryStage=>fenetre principale, peut y avoir dautres
	/*
	 * Stage secondStage = new Stage();
	 * secondStage.show();
	 */
	
	// Scene :
	//		- associée à une fenetre, on y ajoute des elements enfants (tree,nodes)
	// new Scene(<root>, w, h);
	 
	private final String winTitle		= "Algorithm Visualizer";
	private final int winWidth			= 1280, 
					  winHeight			= 720;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Scene sc		= new Scene(new MainView());
			
			primaryStage.setScene(sc);
			primaryStage.setResizable(false);
			
			primaryStage.setHeight(winHeight);
			primaryStage.setWidth(winWidth);
			primaryStage.setTitle(winTitle);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

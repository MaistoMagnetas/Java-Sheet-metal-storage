package application;
	

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		Login login = new Login(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

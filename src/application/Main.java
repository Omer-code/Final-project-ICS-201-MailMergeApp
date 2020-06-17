package application;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	public static void main(String[] args) {

		launch(args);
	}

	
	//this method will open the introduction and application windows
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {

		try {
			////////////////////////****///////////////
			Parent Awindow = FXMLLoader.load(getClass().getResource("/application/intro.fxml"));
			Scene scene2 = new Scene(Awindow, 350, 200);
			Stage newWinsow = new Stage();
			newWinsow.initModality(Modality.APPLICATION_MODAL);
			newWinsow.setTitle("Introduction");
			newWinsow.setScene(scene2);
			newWinsow.setResizable(false);
			newWinsow.showAndWait();
			///////////////////////*****////////////////////////
			Parent window = FXMLLoader.load(getClass().getResource("/application/sample.fxml"));
			Scene scene = new Scene(window, 1000, 600);
			primaryStage.setTitle("Best Mail Merge");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

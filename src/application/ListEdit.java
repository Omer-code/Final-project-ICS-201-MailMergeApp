package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListEdit extends Application {

    int i = 3;
    
    @FXML
    ListView<String> list;
    
    @Override
  public void start(Stage primaryStagec) throws IOException {
      
    	ObservableList<String> items = FXCollections.observableArrayList("test1", "test2");
        ListView<String> list = new ListView<>(items);
        list.setCellFactory(TextFieldListCell.forListView());
        list.setEditable(true);

        Button btn = new Button();
        btn.setText("Add Contact");
        btn.setOnAction((ActionEvent event) -> {
            list.getItems().add(i - 1, "test" + i);
            list.edit(i - 2);
            i++;
        });

        VBox root = new VBox(list, btn);

        Scene scene = new Scene(root);
		Stage primaryStage = new Stage();

        
        primaryStage.setTitle("test!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
   
    }

    public static void main(String[] args) {
        launch(args);
    }
}
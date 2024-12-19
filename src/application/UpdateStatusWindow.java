package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UpdateStatusWindow {
	static String[] updateIdAndStatus = {"","","1"};
	public static String[] Display() {
	Stage updateStatusWindow = new Stage();
	updateStatusWindow.initModality(Modality.APPLICATION_MODAL);
	updateStatusWindow.setTitle("Update Status Window");
	updateStatusWindow.setHeight(200);
	updateStatusWindow.setWidth(400);
	updateStatusWindow.setMaxHeight(200);
	updateStatusWindow.setMinHeight(200);
	updateStatusWindow.setMaxWidth(400);
	updateStatusWindow.setMinWidth(400);
	
	AnchorPane updateOrderRoot = new AnchorPane();
	Scene updateOrderScene = new Scene(updateOrderRoot,400,200);
	
	Text updateStatusText = new Text("Id");{
		updateStatusText.setFont(Font.font(14));
		updateStatusText.setLayoutX(25);
		updateStatusText.setLayoutY(45);
	}
	
	TextField updateStatusTextField = new TextField();
	{	
		updateStatusTextField.setPromptText("Enter Order Id");
		updateStatusTextField.setLayoutX(25);
		updateStatusTextField.setLayoutY(50);
		updateStatusTextField.setPrefHeight(30);
	}
	ComboBox<String> updateStatusCB = new ComboBox<String>();
	{
		updateStatusCB.getItems().addAll("Complete", "Incomplete");
		updateStatusCB.setValue("Complete");
		updateStatusCB.setLayoutX(205);
		updateStatusCB.setLayoutY(50);
		updateStatusCB.setPrefSize(75, 30);
	}
	Button updateStatusButton = new Button("Update");
	{
		updateStatusButton.setLayoutX(290);
		updateStatusButton.setLayoutY(50);
		updateStatusButton.setPrefSize(75, 30);
		updateStatusButton.setOnAction(e->{
			updateIdAndStatus[0] = updateStatusTextField.getText();
			updateIdAndStatus[1] = updateStatusCB.getSelectionModel().getSelectedItem().toString();
			updateIdAndStatus[2] = "1";
			updateStatusWindow.close();
		});
	}
	Button closeStatusButton = new Button("Close");
	{
		closeStatusButton.setLayoutX(15);
		closeStatusButton.setLayoutY(120);
		closeStatusButton.setPrefSize(75,30);
		closeStatusButton.setOnAction(e->{
			updateIdAndStatus[2] = "2";
			updateStatusWindow.close();
		});
		
	}
	
	updateStatusWindow.setScene(updateOrderScene);
	updateOrderRoot.getChildren().addAll(updateStatusText, updateStatusTextField,updateStatusCB,updateStatusButton,closeStatusButton);
//	updateOrderRoot.getChildren().addAll(updateStatusTextField,updateStatusCB,updateStatusButton,closeStatusButton);
	updateStatusWindow.showAndWait();
	
	return updateIdAndStatus;
	}
}
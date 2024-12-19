package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RemoveOrderWindow {
	static String[] updateIdAndStatus = {"","1"};
	public static String[] Display() {
	Stage removeOrderWindow = new Stage();
	removeOrderWindow.initModality(Modality.APPLICATION_MODAL);
	removeOrderWindow.setTitle("Remove Window");
	removeOrderWindow.setHeight(200);
	removeOrderWindow.setWidth(400);
	removeOrderWindow.setMaxHeight(200);
	removeOrderWindow.setMinHeight(200);
	removeOrderWindow.setMaxWidth(400);
	removeOrderWindow.setMinWidth(400);
	
	AnchorPane removeOrderRoot = new AnchorPane();
	Scene removeOrderScene = new Scene(removeOrderRoot,400,200);
	
	Text removeOrderText = new Text("Id");{
		removeOrderText.setFont(Font.font(14));
		removeOrderText.setLayoutX(25);
		removeOrderText.setLayoutY(73);
	}
	
	TextField removeStatusTextField = new TextField();
	{	
		removeStatusTextField.setPromptText("Enter Order Id");
		removeStatusTextField.setLayoutX(58);
		removeStatusTextField.setLayoutY(50);
		removeStatusTextField.setPrefHeight(30);
	}
		Button removeStatusButton = new Button("Remove");
	{
		removeStatusButton.setLayoutX(255);
		removeStatusButton.setLayoutY(50);
		removeStatusButton.setPrefSize(75, 30);
		removeStatusButton.setOnAction(e->{
			updateIdAndStatus[0] = removeStatusTextField.getText();
			updateIdAndStatus[1] = "1";
			removeOrderWindow.close();
		});
	}
	Button closeStatusButton = new Button("Close");
	{
		closeStatusButton.setLayoutX(15);
		closeStatusButton.setLayoutY(120);
		closeStatusButton.setPrefSize(75,30);
		closeStatusButton.setOnAction(e->{
			updateIdAndStatus[1] = "2";
			removeOrderWindow.close();
		});
		
	}
	
	removeOrderWindow.setScene(removeOrderScene);
	removeOrderRoot.getChildren().addAll(removeOrderText,removeStatusTextField,removeStatusButton,closeStatusButton);
	removeOrderWindow.showAndWait();
	
	return updateIdAndStatus;
	}
}
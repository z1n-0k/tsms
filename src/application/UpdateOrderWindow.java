package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdateOrderWindow {
	static String[] updatedOrder;
	static Boolean wasClosed;
	public static String[] Display() {
		
		Stage addOrderWindow = new Stage();
		addOrderWindow.initModality(Modality.APPLICATION_MODAL);
		addOrderWindow.setTitle("Update Order Window");
//		OrderManagementSystem OMS = new OrderManagementSystem();
		
		Text addOrderId = new Text("ID");
		{
			addOrderId.setFont(Font.font(14));
			addOrderId.setLayoutX(115);
			addOrderId.setLayoutY(120);
		}
		TextField addOrderIdField = new TextField();
		{
			addOrderIdField.setFont(Font.font(14));
			addOrderIdField.setLayoutX(220);
			addOrderIdField.setLayoutY(100);
			addOrderIdField.setPrefWidth(300);
		}
		Text addOrderName = new Text("Name");
		{
			addOrderName.setFont(Font.font(14));
			addOrderName.setLayoutX(115);
			addOrderName.setLayoutY(160);
		}
		TextField addOrderNameField = new TextField();
		{
			addOrderNameField.setFont(Font.font(14));
			addOrderNameField.setLayoutX(220);
			addOrderNameField.setLayoutY(140);
			addOrderNameField.setPrefWidth(300);
		}
		Text addOrderPhoneNo = new Text("Phone No");
		{
			addOrderPhoneNo.setFont(Font.font(14));
			addOrderPhoneNo.setLayoutX(115);
			addOrderPhoneNo.setLayoutY(200);
		}
		TextField addOrderPhoneNoField = new TextField();
		{
			addOrderPhoneNoField.setFont(Font.font(14));
			addOrderPhoneNoField.setLayoutX(220);
			addOrderPhoneNoField.setLayoutY(180);
			addOrderPhoneNoField.setPrefWidth(300);
		}
		Text addOrderDeadline = new Text("Deadline");
		{
			addOrderDeadline.setFont(Font.font(14));
			addOrderDeadline.setLayoutX(115);
			addOrderDeadline.setLayoutY(240);
		}
		DatePicker addOrderDeadlineDatePicker = new DatePicker();
		{
			addOrderDeadlineDatePicker.setLayoutX(220);
			addOrderDeadlineDatePicker.setLayoutY(220);
			addOrderDeadlineDatePicker.setPrefWidth(300);
			addOrderDeadlineDatePicker.setPrefHeight(30.5);
		}
		Text addMeasurements = new Text("Measurements");
		{
			addMeasurements.setFont(Font.font(14));
			addMeasurements.setLayoutX(115);
			addMeasurements.setLayoutY(300);
		}
		TextField addOrderFieldM1 = new TextField();
		{
			addOrderFieldM1.setPromptText("Chest");
			addOrderFieldM1.setFont(Font.font(14));
			addOrderFieldM1.setLayoutX(220);
			addOrderFieldM1.setLayoutY(280);
			addOrderFieldM1.setPrefWidth(75);
		}
		TextField addOrderFieldM2 = new TextField();
		{
			addOrderFieldM2.setPromptText("Waist");
			addOrderFieldM2.setFont(Font.font(14));
			addOrderFieldM2.setLayoutX(295);
			addOrderFieldM2.setLayoutY(280);
			addOrderFieldM2.setPrefWidth(75);
		}
		TextField addOrderFieldM3 = new TextField();
		{
			addOrderFieldM3.setPromptText("Hip");
			addOrderFieldM3.setFont(Font.font(14));
			addOrderFieldM3.setLayoutX(370);
			addOrderFieldM3.setLayoutY(280);
			addOrderFieldM3.setPrefWidth(75);
		}
		ComboBox<String> orderSizeCB = new ComboBox<String>();
		{
			orderSizeCB.getItems().addAll("Small", "Medium", "Large");
			orderSizeCB.setLayoutX(445);
			orderSizeCB.setLayoutY(280);
			orderSizeCB.setPrefSize(75, 30);
		}
		Text addStatus= new Text("Status");
		{
			addStatus.setFont(Font.font(14));
			addStatus.setLayoutX(170);
			addStatus.setLayoutY(350);
		}
		ComboBox<String> orderStatusCB = new ComboBox<String>();
		{
			orderStatusCB.getItems().addAll("Complete", "Incomplete");
			orderStatusCB.setLayoutX(225);
			orderStatusCB.setLayoutY(330);
			orderStatusCB.setPrefSize(75, 30);
		}
		
		Text assignedToText= new Text("Assign To");
		{
			assignedToText.setFont(Font.font(14));
			assignedToText.setLayoutX(310);
			assignedToText.setLayoutY(350);
		}
		ComboBox<String> assignedToCB = new ComboBox<String>();
		{
			for (String str: Connections.readUsername()) {
				assignedToCB.getItems().add(str);
			}
			assignedToCB.setLayoutX(385);
			assignedToCB.setLayoutY(330);
			assignedToCB.setPrefSize(75, 30);
		}

		Button saveButton = new Button("Save");
		{
			saveButton.setFont(Font.font(14));
			saveButton.setLayoutX(150);
			saveButton.setLayoutY(400);
			saveButton.setPrefSize(75, 30);
		}
		
		saveButton.setOnAction(e -> {
//			String TXT = "";
			wasClosed = false;
			System.out.println("savedatathroughfiling");
			
			// 0id,1name,2number,3date,4chest,5waist,6hip,7size,8staatus \n
			updatedOrder = new String[10];
			updatedOrder[0] = addOrderIdField.getText();
			updatedOrder[1] = addOrderNameField.getText();
			updatedOrder[2] = addOrderPhoneNoField.getText();
			updatedOrder[3] = addOrderDeadlineDatePicker.getValue().toString();
			updatedOrder[4] = addOrderFieldM1.getText();
			updatedOrder[5] = addOrderFieldM2.getText();
			updatedOrder[6] = addOrderFieldM3.getText();
			updatedOrder[7] =  orderSizeCB.getSelectionModel().getSelectedItem().toString();
			updatedOrder[8] = orderStatusCB.getSelectionModel().getSelectedItem().toString();
			updatedOrder[9] = assignedToCB.getSelectionModel().getSelectedItem().toString();
			addOrderWindow.close();
		});
		Button closeButton = new Button("Close");
		{
			closeButton.setFont(Font.font(14));
			closeButton.setLayoutX(445);
			closeButton.setLayoutY(400);
			closeButton.setPrefSize(75, 30);
		}
		
		closeButton.setOnAction(e -> {
			System.out.println("closebuttonclicked");
			wasClosed = true;
			addOrderWindow.close();
			// return to ADMIN INTERFACE
		});

		addOrderWindow.setHeight(500);
		addOrderWindow.setWidth(600);
		addOrderWindow.setMaxHeight(500);
		addOrderWindow.setMinHeight(500);
		addOrderWindow.setMaxWidth(600);
		addOrderWindow.setMinWidth(600);
		
		AnchorPane addOrderRoot = new AnchorPane();
		Scene addOrderScene = new Scene(addOrderRoot, 600, 500);
		addOrderWindow.setScene(addOrderScene);
		addOrderRoot.getChildren().addAll(addOrderIdField,addOrderNameField,addOrderPhoneNoField,addOrderDeadlineDatePicker, addOrderDeadline,  addOrderName,
				addMeasurements, addOrderFieldM1, addOrderFieldM2, addOrderFieldM3, orderSizeCB, 
				orderStatusCB, assignedToText,assignedToCB,saveButton, addOrderPhoneNo,addStatus,addOrderId,closeButton);
		addOrderWindow.showAndWait();
	
		
		return updatedOrder;
	}
	
}
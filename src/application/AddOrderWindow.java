package application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
//import java.util.Arraylist;

public class AddOrderWindow {
	static Order order = new Order();
	static Arraylist<Order> addOrderArraylist = new Arraylist<>();
	public static Arraylist<Order> Display() {

		Stage addOrderWindow = new Stage();
		addOrderWindow.initModality(Modality.APPLICATION_MODAL);
		addOrderWindow.setTitle("Add New Order");
//		OrderManagementSystem OMS = new OrderManagementSystem();
		
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
		Text assignedToText= new Text("Assign To");
		{
			assignedToText.setFont(Font.font(14));
			assignedToText.setLayoutX(270);
			assignedToText.setLayoutY(350);
		}
		ComboBox<String> assignedToCB = new ComboBox<String>();
		{
			for (String str: Connections.readUsername()) {
				assignedToCB.getItems().add(str);
			}
			assignedToCB.setLayoutX(345);
			assignedToCB.setLayoutY(330);
			assignedToCB.setPrefSize(75, 30);
		}

		Button saveButton = new Button("Save");
		{
			saveButton.setFont(Font.font(14));
			saveButton.setLayoutX(115);
			saveButton.setLayoutY(400);
			saveButton.setPrefSize(75, 30);
		}
		saveButton.setOnAction(e -> {
			System.out.println("savedatathroughfiling");
			
			// name,number,date,chest,waist,hip,size \n
			
			String addOrderNameText = addOrderNameField.getText();
			String addOrderDate = addOrderDeadlineDatePicker.getValue().toString();
			String addOrderPhoneText = addOrderPhoneNoField.getText();
			String addOrderChest = addOrderFieldM1.getText();
			String addOrderWaist = addOrderFieldM2.getText();
			String addOrderHips = addOrderFieldM3.getText();
			String addOrderSize =  orderSizeCB.getSelectionModel().getSelectedItem().toString();
			String addOrderAssignedTo = assignedToCB.getSelectionModel().getSelectedItem().toString();
			
			Measurements measurement = new Measurements(addOrderChest,addOrderWaist,addOrderHips,addOrderSize);
			order = new Order(addOrderNameText,measurement,addOrderDate,addOrderPhoneText,addOrderAssignedTo);
			
			System.out.println(order.toString());
			addOrderArraylist.add(order);
			
			addOrderNameField.clear();
			addOrderPhoneNoField.clear();
			addOrderFieldM1.clear();
			addOrderFieldM2.clear();
			addOrderFieldM3.clear();
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
			
			addOrderWindow.close();
			// return to ADMIN interface
		});

		AnchorPane addOrderRoot = new AnchorPane();
		Scene addOrderScene = new Scene(addOrderRoot, 600, 500);
		addOrderWindow.setScene(addOrderScene);
		addOrderRoot.getChildren().addAll(addOrderNameField,addOrderPhoneNoField,addOrderDeadlineDatePicker, addOrderDeadline,  addOrderName,
				addMeasurements, addOrderFieldM1, addOrderFieldM2, addOrderFieldM3, orderSizeCB,assignedToText, assignedToCB, saveButton,
				closeButton, addOrderPhoneNo );
		addOrderWindow.showAndWait();
		
		return addOrderArraylist;
	}
	public static void clearArraylist() {
		addOrderArraylist = new Arraylist<>();
	}
	
}
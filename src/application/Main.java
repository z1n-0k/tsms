package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {

//		Image image = new Image("TSMS-Icon.png");
		OrderManagementSystem managementSystem = new OrderManagementSystem();
		Connections connection = new Connections(); //filing
		managementSystem.addOrderList(connection.readOrders("data.txt"));
		
		UserManager userManager = new UserManager();
		userManager.addUserList(connection.readUsers());
		
		AnchorPane root = new AnchorPane();
		AnchorPane adminManagementRoot = new AnchorPane();
		AnchorPane employeeManagementRoot = new AnchorPane();

		Scene scene = new Scene(root, 600, 500);
		Scene adminManagementScene = new Scene(adminManagementRoot, 600, 500);
		Scene employeeManagementScene = new Scene(employeeManagementRoot, 600, 500);
		
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);

		Text title = new Text("Taylor Shop Management System");
		{
			title.setFont(Font.font(18));
			title.applyCss();
			title.setLayoutX(160);
			title.setLayoutY(76);
		}
		Text usernameLabel = new Text("Username");
		{
			usernameLabel.setFont(Font.font(14));
			usernameLabel.setLayoutX(98);
			usernameLabel.setLayoutY(173);
		}
		Text passwordLabel = new Text("Password");
		{
			passwordLabel.setFont(Font.font(14));
			passwordLabel.setLayoutX(98);
			passwordLabel.setLayoutY(250);
		}
		TextField usernameField = new TextField();
		{
			usernameField.setFont(Font.font(14));
			usernameField.setLayoutX(201);
			usernameField.setLayoutY(155);
		}
		PasswordField passwordField = new PasswordField();
		{
			passwordField.setFont(Font.font(14));
			passwordField.setLayoutX(201);
			passwordField.setLayoutY(235);
		}
		Button loginButton = new Button("Login");
		{
			loginButton.setFont(Font.font(14));
			loginButton.setLayoutX(262);
			loginButton.setLayoutY(315);
		}
		Button closeButton = new Button("Close");
		{
			closeButton.setFont(Font.font(14));
			closeButton.setLayoutX(400);
			closeButton.setLayoutY(400);
		}
		closeButton.setOnAction(e -> {
			stage.close();
		});
		loginButton.setOnAction(e -> {
			String password = passwordField.getText();
			String username = usernameField.getText();

			usernameField.clear();
			passwordField.clear();

			if (username.equals("admin") && password.equals("admin")) {
//				User user = new User(username, password, "admin");
				stage.setScene(adminManagementScene);
			} else {
				String currentUser = "0";
				for (User user: userManager.getUsers()) {
					if (username.equals(user.getUsername())){
						currentUser = user.getUsername();
						userManager.setUser(currentUser);
						System.out.println("employee id: " + currentUser + " logged in");
						stage.setScene(employeeManagementScene);
						break;
					}
				}
			}

		});

		root.getChildren().addAll(title, usernameLabel, passwordLabel, usernameField, passwordField, loginButton,
				closeButton);
		
//======================================================
//                      ADMIN
//======================================================
		
		Button adminLogoutButton = new Button("Logout");
		{
			adminLogoutButton.setFont(Font.font(14));
			adminLogoutButton.setLayoutX(20);
			adminLogoutButton.setLayoutY(400);
		}

		adminLogoutButton.setOnAction(e -> {
			stage.setScene(scene);

		});

		Label adminlabel = new Label("Admin!");
		Button addOrderButton = new Button("Add Order");
		{
			{
				addOrderButton.setFont(Font.font(14));
				addOrderButton.setLayoutX(180);
				addOrderButton.setLayoutY(30);
			}
			addOrderButton.setOnAction(e -> {
				System.out.println("Addorderbuttonclicked");
				Arraylist<Order> orders = AddOrderWindow.Display();
				managementSystem.addOrderList(orders);
				//TODO
				File file = new File("data.txt");
				try {
					FileWriter writer = new FileWriter(file);
					System.out.println("159");
					
					Arraylist<Order> orderArraylist = managementSystem.getOrderList();
					writer.write("");
					System.out.println(orderArraylist.size());
					
					//TODO
					Arraylist<File> employeeFile = new Arraylist<File>();
					Arraylist<FileWriter> employeeWriter = new Arraylist<FileWriter>();
					
					for (User employee: userManager.getUsers()) {
						File tempEmployeeFile = new File(employee.getUsername()+".txt");
						employeeFile.add(tempEmployeeFile);
						FileWriter tempEmployeeFileWriter = new FileWriter(tempEmployeeFile);
						employeeWriter.add(tempEmployeeFileWriter);
					}

					for (Order order: orderArraylist) {
						connection.writeOrder(order,order.getAssignedEmployeeID()+".txt");
						connection.writeOrder(order,"data.txt");
					}
					
					writer.close();
					for (FileWriter tempEmployeeFileWriter: employeeWriter) {
						tempEmployeeFileWriter.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				AddOrderWindow.clearArraylist();
				System.out.println(managementSystem.getTotalOrders());
			});
		}
		
		Button removeOrderButton = new Button("Remove Order");
		{
			{
				removeOrderButton.setFont(Font.font(14));
				removeOrderButton.setLayoutX(410);
				removeOrderButton.setLayoutY(30);
			}
			removeOrderButton.setOnAction(e -> {
				String[] updateStatusOutput = RemoveOrderWindow.Display();
				System.out.println(updateStatusOutput[1]);
				
				if (updateStatusOutput[1].equals("1")) {
					String id = updateStatusOutput[0];
					managementSystem.removeOrder(id);
					File file = new File("data.txt");
					try {
						FileWriter writer = new FileWriter(file);
					
						System.out.println("159");
						Arraylist<Order> orderArraylist = managementSystem.getOrderList();
						writer.write("");
						System.out.println(orderArraylist.size());
						
						//TODO
						Arraylist<File> employeeFile = new Arraylist<File>();
						Arraylist<FileWriter> employeeWriter = new Arraylist<FileWriter>();
						
						for (User employee: userManager.getUsers()) {
							File tempEmployeeFile = new File(employee.getUsername()+".txt");
							employeeFile.add(tempEmployeeFile);
							FileWriter tempEmployeeFileWriter = new FileWriter(tempEmployeeFile);
							employeeWriter.add(tempEmployeeFileWriter);
						}

						for (Order order: orderArraylist) {
							connection.writeOrder(order,order.getAssignedEmployeeID()+".txt");
							connection.writeOrder(order,"data.txt");
							System.out.println(order.getAssignedEmployeeID());
						}
						
						writer.close();
						for (FileWriter tempEmployeeFileWriter: employeeWriter) {
							tempEmployeeFileWriter.close();
						}
						
						System.out.println("id: "+id+"removed");
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
				}
			});
		}

		Button adminUpdateButton = new Button("Update Order");
		{
			{
				adminUpdateButton.setFont(Font.font(14));
				adminUpdateButton.setLayoutX(285);
				adminUpdateButton.setLayoutY(30);
			}
			adminUpdateButton.setOnAction(e -> {
				String[] updatedOrder = UpdateOrderWindow.Display();
				int i = 0;
				if(!UpdateOrderWindow.wasClosed) {
				System.out.println("adminUpdateButtonPressed");
				for (Order order:managementSystem.getOrderList()) {
					if (updatedOrder[0].equals(order.getCurrentId())) {
						managementSystem.getOrderList().get(i).setData(updatedOrder);
						
						System.out.println(order.toString());
						break;
					}
					i ++;
				}
				
				File file = new File("data.txt");
				try {
					
					FileWriter writer = new FileWriter(file);
					Arraylist<Order> orderArraylist = managementSystem.getOrderList();
					Arraylist<File> employeeFile = new Arraylist<File>();
					Arraylist<FileWriter> employeeWriter = new Arraylist<FileWriter>();
					
					for (User employee: userManager.getUsers()) {
						File tempEmployeeFile = new File(employee.getUsername()+".txt");
						employeeFile.add(tempEmployeeFile);
						FileWriter tempEmployeeFileWriter = new FileWriter(tempEmployeeFile);
						employeeWriter.add(tempEmployeeFileWriter);
					}
//					BufferedWriter employeeBufferedWriter;
					for (Order order: orderArraylist) {
						connection.writeOrder(order,order.getAssignedEmployeeID()+".txt");
						connection.writeOrder(order,"data.txt");
					}
					
					writer.close();
					for (FileWriter tempEmployeeFileWriter: employeeWriter) {
						tempEmployeeFileWriter.close();
					}
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}	
				}
			});
		}

		Button adminUpdateStatusButton = new Button("Update Status");
		{ 
			adminUpdateStatusButton.setFont(Font.font(14));
			adminUpdateStatusButton.setLayoutX(50);
			adminUpdateStatusButton.setLayoutY(30);
			adminUpdateStatusButton.setOnAction(e -> {
				System.out.println("updateorderstatusbuttonclicked");
				String[] updateStatusOutput = UpdateStatusWindow.Display();
				System.out.println(updateStatusOutput[2]);
				
				if (updateStatusOutput[2].equals("1")) {
					String id = updateStatusOutput[0];
					String status = updateStatusOutput[1];
					System.out.println(updateStatusOutput[0]+updateStatusOutput[1]+updateStatusOutput[2]);
					managementSystem.updateOrderStatus(id,status);
					
					File file = new File("data.txt");
					try {
						
						FileWriter writer = new FileWriter(file);
						writer.write("");
						Arraylist<Order> orderArraylist = managementSystem.getOrderList();
						Arraylist<File> employeeFile = new Arraylist<File>();
						Arraylist<FileWriter> employeeWriter = new Arraylist<FileWriter>();
						
						for (User employee: userManager.getUsers()) {
							File tempEmployeeFile = new File(employee.getUsername()+".txt");
							employeeFile.add(tempEmployeeFile);
							FileWriter tempEmployeeFileWriter = new FileWriter(tempEmployeeFile);
							employeeWriter.add(tempEmployeeFileWriter);
						}
//						BufferedWriter employeeBufferedWriter;
						for (Order order: orderArraylist) {
							connection.writeOrder(order,order.getAssignedEmployeeID()+".txt");
							connection.writeOrder(order,"data.txt");
							System.out.println(order.getAssignedEmployeeID());
						}
						
						writer.close();
						for (FileWriter tempEmployeeFileWriter: employeeWriter) {
							tempEmployeeFileWriter.close();
						}
						
						System.out.println("updated status of "+id+" to "+status);
					} catch (IOException e1) {	
						e1.printStackTrace();
					}
				}
			});
		}

		ScrollPane adminRoot = new ScrollPane();
		{
			adminRoot.setLayoutX(20);
			adminRoot.setLayoutY(70);
			adminRoot.setPrefHeight(320);
			adminRoot.setPrefWidth(537.5);
			String text = connection.readFile("data.txt");
			System.out.println("(admin) data file read");
			String[] txt = text.split("\n");
			text = "";
			int count = 1;
			for (String str:txt) {
				text += count +"\t"+str + "\n";
				count ++;
			}
			Text texet = new Text(text);
			texet.setFont(new Font("Courier New",16));
			adminRoot.setContent(texet);
		}
		
		Label adminSortLabel = new Label("Sort By: ");
		{
			adminSortLabel.setLayoutX(310);
			adminSortLabel.setLayoutY(406);
		}
		
		ComboBox<String> adminSortCB = new ComboBox<String>();
		{
			adminSortCB.getItems().addAll("ID", "Deadline");
			adminSortCB.setValue("ID");
			adminSortCB.setLayoutX(370);
			adminSortCB.setLayoutY(400);
			adminSortCB.setPrefWidth(100);
			adminSortCB.setPrefHeight(27);
			adminSortCB.setOnAction(e->{
				System.out.println(adminSortCB.getValue());
				if (adminSortCB.getValue().equals("ID")) {
					String text = null;
					try {
						text = connection.readFile("data.txt");
						System.out.println("readFileUsingScannerForSorting");
						String[] txt = text.split("\n");
						text = "";
						int count = 1;
						for (String str:txt) {
							text += count +"\t"+str+ "\n";
							count ++;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Text txt = new Text(text);
					txt.setFont(Font.font("Courier New", 16));
					adminRoot.setContent(txt);
				}else {
					String text = null;
					try {
						text = connection.readFile("data.txt");
						System.out.println("readusertextfileusingscanner");
						String[] txt = text.split("\n");
						text = "";
						
						//GETTING DATES FROM TEXT FILE
						int[] deadlinetxt = new int[txt.length];
						int i = 0;
						for (String str:txt) {
							deadlinetxt[i] = Integer.parseInt(str.split("\t")[4].replace("-",""));
							i += 1;
					}
						
						txt = SortingAlgo.bubbleSort(txt,deadlinetxt);
						
						// actually printing do not change
						int count = 1;
						for (String str:txt) {
							text += count +"\t"+str+ "\n";
							count ++;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Text txt = new Text(text);
					txt.setFont(Font.font("Courier New", 16));
					adminRoot.setContent(txt);
				}
			});
		}
		
		Button adminRefreshButton = new Button("Refresh");
		{
			adminRefreshButton.setFont(Font.font(14));
			adminRefreshButton.setLayoutX(490);
			adminRefreshButton.setLayoutY(400);
			adminRefreshButton.setOnAction(e->{
				adminSortCB.setValue("ID");
				if (adminSortCB.getValue().equals("ID")) {
					String text = null;
					try {
						text = connection.readFile("data.txt");
						System.out.println("readusertextfileusingscanner");
						String[] txt = text.split("\n");
						text = "";
						int count = 1;
						for (String str:txt) {
							text += count +"\t"+str+ "\n";
							count ++;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Text txt = new Text(text);
					txt.setFont(Font.font("Courier New", 16));
					adminRoot.setContent(txt);
				} 
			});
		}
		
		adminManagementRoot.getChildren().addAll(adminlabel,adminSortLabel,adminUpdateStatusButton,addOrderButton,
												adminUpdateButton,removeOrderButton,
												adminLogoutButton,adminSortCB,adminRefreshButton,  
												adminRoot);
		
//======================================================
//				         EMPLOYEE
//======================================================
		
		
		
		Button employeeUpdateButton = new Button("Update Order Status");
		{
			employeeUpdateButton.setFont(Font.font(14));
			employeeUpdateButton.setLayoutX(222);
			employeeUpdateButton.setLayoutY(30);
			
			//TODO
			employeeUpdateButton.setOnAction(e->{
				String[] updateStatusOutput = UpdateStatusWindow.Display();
				System.out.println(updateStatusOutput[2]);
				
				if (updateStatusOutput[2].equals("1")) {
					String id = updateStatusOutput[0];
					String status = updateStatusOutput[1];
					System.out.println(updateStatusOutput[0]+updateStatusOutput[1]+updateStatusOutput[2]);
					managementSystem.updateOrderStatus(id,status);
					
					File file = new File("data.txt");
					try {
						
						FileWriter writer = new FileWriter(file);
						writer.write("");
						Arraylist<Order> orderArraylist = managementSystem.getOrderList();
						Arraylist<File> employeeFile = new Arraylist<File>();
						Arraylist<FileWriter> employeeWriter = new Arraylist<FileWriter>();
						
						for (User employee: userManager.getUsers()) {
							File tempEmployeeFile = new File(employee.getUsername()+".txt");
							employeeFile.add(tempEmployeeFile);
							FileWriter tempEmployeeFileWriter = new FileWriter(tempEmployeeFile);
							employeeWriter.add(tempEmployeeFileWriter);
						}
//						BufferedWriter employeeBufferedWriter;
						for (Order order: orderArraylist) {
							connection.writeOrder(order,order.getAssignedEmployeeID()+".txt");
							connection.writeOrder(order,"data.txt");
							System.out.println(order.getAssignedEmployeeID());
						}
						
						writer.close();
						for (FileWriter tempEmployeeFileWriter: employeeWriter) {
							tempEmployeeFileWriter.close();
						}
						
						System.out.println("updated status of "+id+" to "+status);
					} catch (IOException e1) {	
						e1.printStackTrace();
					}
				}
			});
		}
		
		Button employeeLogoutButton = new Button("Logout");
		{
			employeeLogoutButton.setFont(Font.font(14));
			employeeLogoutButton.setLayoutX(25);
			employeeLogoutButton.setLayoutY(400);
			employeeLogoutButton.setOnAction(e -> {
				stage.setScene(scene);
			});
		}
		
		Label employeelabel = new Label("Employee!");
		ScrollPane employeeRoot = new ScrollPane();
		{
			employeeRoot.setLayoutX(300);
			employeeRoot.setLayoutY(120);
			employeeRoot.setPrefHeight(220);
			employeeRoot.setPrefWidth(250);
			String text = "";
			try {
				String fileToRead = "data.txt";
				System.out.println(fileToRead);
				text = connection.readFile(fileToRead);
				System.out.println("(employee) dataFileReadUsingScanner");
				String[] txt = text.split("\n");
				text = "";
				int count = 1;
				for (String str:txt) {
					text += count +"\t"+str+ "\n";
					count ++;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Text txt = new Text(text);
			txt.setFont(Font.font(14));
			employeeRoot.setContent(txt);
		}
		
		int fontsize = 18;
		
		Text CurrentOrderLabel = new Text("Current Order");
		{
			CurrentOrderLabel.setFont(Font.font(fontsize+4));
			CurrentOrderLabel.setLayoutX(75);
			CurrentOrderLabel.setLayoutY(120-14);
		}
		Text AllOrderLabel = new Text("All Orders");
		{
			AllOrderLabel.setFont(Font.font(fontsize+4));
			AllOrderLabel.setLayoutX(360);
			AllOrderLabel.setLayoutY(120-14);
		}
		Text IDLabel = new Text("ID");
		{
			IDLabel.setFont(Font.font(fontsize));
			IDLabel.setLayoutX(115-30);
			IDLabel.setLayoutY(120+20);
		}
		
		Text IDValueLabel = new Text("null");
		{
			IDValueLabel.setFont(Font.font(fontsize));
			IDValueLabel.setLayoutX(200-30);
			IDValueLabel.setLayoutY(120+20);
		}
		
		Text ChestLabel = new Text("Chest");
		{
			ChestLabel.setFont(Font.font(fontsize));
			ChestLabel.setLayoutX(115-30);
			ChestLabel.setLayoutY(160+20);
		}
		
		Text ChestValuelabel = new Text("null");
		{
			ChestValuelabel.setFont(Font.font(fontsize));
			ChestValuelabel.setLayoutX(200-30);
			ChestValuelabel.setLayoutY(160+20);
		}
		
		Text WaistLabel = new Text("Waist");
		{
			WaistLabel.setFont(Font.font(fontsize));
			WaistLabel.setLayoutX(115-30);
			WaistLabel.setLayoutY(200+20);
		}
		Text WaistValueLabel = new Text("null");
		{
			WaistValueLabel.setFont(Font.font(fontsize));
			WaistValueLabel.setLayoutX(200-30);
			WaistValueLabel.setLayoutY(200+20);
		}
		
		Text HipLabel = new Text("Hip");
		{
			HipLabel.setFont(Font.font(fontsize));
			HipLabel.setLayoutX(115-30);
			HipLabel.setLayoutY(240+20);
		}
		
		Text HipValueLabel = new Text("null");
		{
			HipValueLabel.setFont(Font.font(fontsize));
			HipValueLabel.setLayoutX(200-30);
			HipValueLabel.setLayoutY(240+20);
		}
		Text SizeLabel = new Text("Size");
		{
			SizeLabel.setFont(Font.font(fontsize));
			SizeLabel.setLayoutX(115-30);
			SizeLabel.setLayoutY(280+20);
		}
		
		Text SizeValueLabel = new Text("null");
		{
			SizeValueLabel.setFont(Font.font(fontsize));
			SizeValueLabel.setLayoutX(200-30);
			SizeValueLabel.setLayoutY(280+20);
		}
		
		Label employeeSortLabel = new Label("Sort By: ");
		{
			employeeSortLabel.setLayoutX(310);
			employeeSortLabel.setLayoutY(406);
		}
		
		ComboBox<String> employeeSortCB = new ComboBox<String>();
		{
			employeeSortCB.getItems().addAll("ID", "Deadline");
			employeeSortCB.setValue("ID");
			employeeSortCB.setLayoutX(370);
			employeeSortCB.setLayoutY(400);
			employeeSortCB.setPrefWidth(100);
			employeeSortCB.setPrefHeight(27);
			
			employeeSortCB.setOnAction(e->{
				System.out.println(employeeSortCB.getValue());
				if (employeeSortCB.getValue().equals("ID")) {
					String text = null;
					try {
						String fileToRead = userManager.currentUser + ".txt";
						text = connection.readFile(fileToRead);
						System.out.println("readusertextfileusingscanner");
						String[] txt = text.split("\n");
						text = "";
						int count = 1;
						for (String str:txt) {
							text += count +"\t"+str+ "\n";
							count ++;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Text txt = new Text(text);
					txt.setFont(Font.font("Courier New", 16));
					employeeRoot.setContent(txt);
				}else {
					String text = null;
					try {
						String fileToRead = userManager.currentUser + ".txt";
						text = connection.readFile(fileToRead);
						String[] txt = text.split("\n");
						text = "";
						
						//GETTING DATES FROM TEXT FILE
						int[] deadlinetxt = new int[txt.length];
						int i = 0;
						for (String str:txt) {
							deadlinetxt[i] = Integer.parseInt(str.split("\t")[4].replace("-",""));
							i += 1;
						}
						
						txt = SortingAlgo.mergeSort(txt,deadlinetxt);
												
						// actually printing donot change
						int count = 1;
						for (String str:txt) {
							text += count +"\t"+str+ "\n";
							count ++;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Text txt = new Text(text);
					txt.setFont(Font.font("Courier New", 16));
					employeeRoot.setContent(txt);
				}
			});
		}
		
		Button employeeRefreshButton = new Button("Refresh");
		{
			employeeRefreshButton.setFont(Font.font(14));
			employeeRefreshButton.setLayoutX(490);
			employeeRefreshButton.setLayoutY(400);
			employeeRefreshButton.setOnAction(e->{
				employeeSortCB.setValue("ID");
				if (employeeSortCB.getValue().equals("ID")) {
					String text = null;
					try {
						String fileToRead = userManager.currentUser + ".txt";
						text = connection.readFile(fileToRead);
						System.out.println("readusertextfileusingscanner");
						String[] txt = text.split("\n");
						text = "";
						int count = 1;
						for (String str:txt) {
							text += count +"\t"+str+ "\n";
							count ++;
						}
						//26789
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					String temptxt = text;
					for(String str: temptxt.split("\n")) {
						String[] tempString = str.split("\t");
						if(tempString[10].equals("Incomplete")) {
							IDValueLabel.setText(tempString[2]);
							ChestValuelabel.setText(tempString[6]);
							WaistValueLabel.setText(tempString[7]);
							HipValueLabel.setText(tempString[8]);
							System.out.println("gand horha");
							SizeValueLabel.setText(tempString[9]);
							break;
						}
					}
					
					Text txt = new Text(text);
					txt.setFont(Font.font("Courier New", 16));
					employeeRoot.setContent(txt);
				}
			});
		}
		
		employeeManagementRoot.getChildren().addAll(employeelabel,employeeRoot,employeeUpdateButton,employeeLogoutButton,
													employeeSortLabel,employeeSortCB,employeeRefreshButton,
													ChestLabel, ChestValuelabel, HipLabel, HipValueLabel, 
													WaistLabel, WaistValueLabel, IDLabel, IDValueLabel
													,CurrentOrderLabel, AllOrderLabel, SizeLabel, SizeValueLabel);

		{
//			stage.getIcons().add(image);
			stage.setTitle("Taylor Shop Management System");
			stage.setScene(scene);
			stage.setMinWidth(600);
			stage.setMinHeight(500);
			stage.setMaxWidth(600);
			stage.setMaxHeight(500);
			stage.setWidth(600);
			stage.setHeight(500);
		}
		
		stage.show();
	
	}
}
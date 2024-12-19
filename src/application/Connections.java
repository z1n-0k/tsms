package application;

import java.io.IOException;
//import java.util.Arraylist;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Connections {
	
	String text;
	File file = new File("data.txt");
	File userFile = new File("userData.txt");
	Order order = new Order();
	Measurements measurement = new Measurements();
	
	public Arraylist<Order> readOrders(String path){
		Arraylist<Order> orderArraylist = new Arraylist<>();
		try {
//			String orderIdData = "";
			List<String> myFile = Files.readAllLines(Paths.get(path));
			
			for(String line:myFile){
				String[] orderAttributes = line.split("\t");
				String id,name,num,date,chest,waist,hip,size,status,assignedTo;
				assignedTo = orderAttributes[0];
				id = orderAttributes[1];
				name = orderAttributes[2];
				num = orderAttributes[3];
				date = orderAttributes[4];
				chest = orderAttributes[5];
				waist = orderAttributes[6];
				hip = orderAttributes[7];
				size = orderAttributes[8];
				status = orderAttributes[9];
//				status = "Incomplete";
				
				measurement = new Measurements(chest,waist,hip,size);
				order = new Order(id,name,measurement,date,num,status,assignedTo);
				orderArraylist.add(order);
			}
			
			return orderArraylist;

		} catch (IOException f) {
			System.out.println("An error occurred.");
			f.printStackTrace();
			Arraylist<Order> orderList= new Arraylist<>();
			return orderList;
		}
	}

	public String readFile(String address) throws IOException {
		String data = "";
//		ID\tNAME\tCONTACT\tDEADLINE\tCHEST\tWAIST\tHIP\tSIZE\t\tSTATUS
		Path path = Paths.get(address);
		Scanner scanner = new Scanner(path);
//		System.out.println("readtextfileusingscanner");
		//read line by line
		while(scanner.hasNextLine()){
		    //process each line
		    data += scanner.nextLine() + "\n";
		}
		scanner.close();
		
		return data;
		
	}
	public String readFileLine(int orderId) {
		try {
			String orderIdData = "";
			BufferedReader alphaReader = new BufferedReader(new FileReader("data.txt"));
			for (int i = 0; i < orderId; i++) {
				orderIdData = alphaReader.readLine();
			}
			alphaReader.close();
			return orderIdData;

		} catch (IOException f) {
			System.out.println("An error occurred.");
			f.printStackTrace();
			return null;
		}

	}
	public void writeOrder(Order order,String fileName) {
		text = "";
		text += order.getAssignedEmployeeID() + "\t";
		text += order.getCurrentId() + "\t";
		text += order.getCustomerName() + "\t";
		text += order.getCustomerNumber() + "\t";
		text += order.getDeadline() + "\t";
		text += order.getMeasurements().getChestSize() + "\t";
		text += order.getMeasurements().getWaistSize() + "\t";
		text += order.getMeasurements().getHipSize() + "\t";
		text += order.getMeasurements().getSize() + "\t";
		text += order.getStatus() + "\n";
		try {
			File file = new File(fileName);
			FileWriter myWriter = new FileWriter(file,true);
			BufferedWriter alphaWriter = new BufferedWriter(myWriter);
			alphaWriter.write(text);
			alphaWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
	public String readUserFile() throws IOException {
		String data = "";
//		ID\tUSERNAME\tPASSWORD
		Path path = Paths.get("userData.txt");
		Scanner scanner = new Scanner(path);
		System.out.println("readusertextfileusingscanner");
		//read line by line
		while(scanner.hasNextLine()){
		    //process each line
		    data += scanner.nextLine() + "\n";
		}
		scanner.close();
		
		return data;
		
	}

	public Arraylist<User> readUsers(){
		Arraylist<User> userArraylist = new Arraylist<>();
		try {
//			String orderIdData = "";
			List<String> myFile = Files.readAllLines(Paths.get("userData.txt"));
			
			for(String line:myFile){
				String[] orderAttributes = line.split("\t");
				String id,userName,password;
				id = orderAttributes[0];
				userName = orderAttributes[1];
				password = orderAttributes[2];
				
				User user = new User(id,userName,password);
				userArraylist.add(user);
			}
			
			return userArraylist;

		} catch (IOException f) {
			System.out.println("An error occurred.");
			f.printStackTrace();
			Arraylist<User> userList= new Arraylist<>();
			return userList;
		}
	}
	
	static public Arraylist<String> readUsername(){
		Arraylist<String> userArraylist = new Arraylist<>();
		try {
//			String orderIdData = "";
			List<String> myFile = Files.readAllLines(Paths.get("userData.txt"));
			
			for(String line:myFile){
				String[] orderAttributes = line.split("\t");
				String userName;
				userName = orderAttributes[1];
				
				userArraylist.add(userName);
			}
			
			return userArraylist;

		} catch (IOException f) {
			System.out.println("An error occurred.");
			f.printStackTrace();
			Arraylist<String> userList= new Arraylist<>();
			return userList;
		}
	}
}
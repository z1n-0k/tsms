package application;

public class Order {
	    private static int orderId = 1;
	    private String currentId;
	    private String assignedTo;
	    private String customerName;
	    private String customerNumber;
	    private Measurements measurements = new Measurements();
	    private String deadline;
	    private double price;
	    private String status;
	    
	    //getters
	    public String getDeadline() {
			return deadline;
		}
	    
	    public int getOrderId() {
	        return orderId;
	    }
	    public Measurements getMeasurements() {
			return measurements;
		}

		public String getCurrentId() {
			return currentId;
		}

		public String getCustomerName() {
			return customerName;
		}

		public double getPrice() {
			return price;
		}
		public String getCustomerNumber() {
			return this.customerNumber;
		}
		public String getStatus() {
	        return this.status;
	    }
		public String getAssignedEmployeeID() {
			return assignedTo;
		}
		public void setMeasurements(Measurements measurements) {
			this.measurements = measurements;
		}
		
	    public Order() {	
	    	
	    }

	    public Order(String customerName, Measurements measurements, String deadline,String phoneNumber, String assignedTo) {
	        this.currentId = Integer.toString(Order.orderId);
	        orderId ++;
	        this.customerName = customerName;
	        this.measurements = measurements;
	        this.deadline = deadline;
	        this.customerNumber = phoneNumber;
	        if(measurements.getSize() == "small") {
	        	this.price = 1500;
	        }
	        else if (measurements.getSize() == "medium") {
	        	this.price = 1750;
	        }
	        else {
	        	this.price = 2000;
	        }
	        
	        this.status = "Incomplete";
	        this.assignedTo = assignedTo;
	    }
	    
	    public Order(String id,String customerName, Measurements measurements, String deadline,String phoneNumber, String status, String assignedTo) {
	        this.currentId = id;
	        orderId = Integer.parseInt(id) + 1;	
	        this.customerName = customerName;
	        this.measurements = measurements;
	        this.deadline = deadline;
	        this.customerNumber = phoneNumber;
	        if(measurements.getSize() == "small") {
	        	this.price = 1500;
	        }
	        else if (measurements.getSize() == "medium") {
	        	this.price = 1750;
	        }
	        else {
	        	this.price = 2000;
	        }
	        
	        this.status = status;
	        this.assignedTo = assignedTo;
	    }
	    
	    public void setData(String[] updatedOrder) {
	    	this.customerName = updatedOrder[1];
	    	this.customerNumber = updatedOrder[2];
	    	this.deadline = updatedOrder[3];
	    	this.assignedTo = updatedOrder[4];
	    	Measurements measurement = new Measurements(updatedOrder[4],updatedOrder[5],updatedOrder[6],updatedOrder[7]);
	    	this.status = updatedOrder[8];
	    	this.assignedTo = updatedOrder[9];
	    	this.measurements = measurement;
	    }

	    public void setStatus(String complete) {
	        this.status = complete;
	    }

	    @Override
	    public String toString() {
	        return "Order ID: " + currentId +
	                "\nCustomer: " + customerName +
	                "\nMeasurements: " + measurements +
	                "\nDeadline: " + deadline +
	                "\nStatus: " + (status) +
	                "\nPrice: "+ price +
	                "\nAssigned To "+ assignedTo +
	                "\n";
	    }
	    
	    public String displayOrder() {
	    	return "Order ID: " + orderId +
	                "\nCustomer: " + customerName +
	                "\nDeadline: " + deadline +
	                
	                "\n";
	    			
	    }
	}
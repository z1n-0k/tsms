package application;
public class OrderManagementSystem {
    private Arraylist<Order> orders;
    
    public OrderManagementSystem() {
        this.orders = new Arraylist<>();
        
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        System.out.println("Order added successfully.");
    }

    public void removeOrder(Order order) {
    	System.out.println("Order removed successfully.");
    	this.orders.remove(order);
    }

    public void markOrderAsComplete(Order order) {
        order.setStatus("Completed");
        System.out.println("Order marked as complete.");
    }

    public void displayOrders() {
        System.out.println("Orders:");
        for (Order order : this.orders) {
            System.out.println(order.toString());
        }
    }
    public void displayOrder(int index) {
        System.out.println("Order: "+ this.orders.get(index).toString());
        
    }
    public int getTotalOrders() {
    	return this.orders.size();
    }
    public Arraylist<Order> getOrderList(){
    	return this.orders;
    }
    public void removeOrder(String id){
    	for (Order order: this.orders) {
    		if (order.getCurrentId().equals(id)) {
//    			orders.remove(orders.indexOf(order);
    			orders.remove(order);
    			break;
    		}
    	}
    }
    
    public void checkOrderDeadlines() {
        System.out.println("Order Deadlines:");

        for (Order order : this.orders) {
            if (!(order.getStatus().equals("Completed"))) {
                String deadline = order.getDeadline();
                // Compare the deadline with the current date and take appropriate action
                // For demonstration purposes, let's assume the deadline is in the format "YYYY-MM-DD"
                if (deadline.compareTo(getCurrentDate()) < 0) {
                    System.out.println("Order ID: " + order.getOrderId() + " - Overdue");
                } else if (deadline.equals(getCurrentDate())) {
                    System.out.println("Order ID: " + order.getOrderId() + " - Due Today");
                } else {
                    System.out.println("Order ID: " + order.getOrderId() + " - Due on " + deadline);
                }
            }
        }
    }

    private String getCurrentDate() {
        // Get the current date in the format "YYYY/MM/DD"
        // Implement the logic to get the current date here or use a library like java.time.LocalDate
        return ""; // For demonstration purposes, return a hard coded date
    }
    
    public void addOrderList(Arraylist<Order> orderList) {
    	for (Order order: orderList) {
    		this.orders.add(order);
    		
    	}
    }
    
    public void updateOrderStatus(String id,String status) {
//    	this.orders.get(id).setStatus(status);
    	for (Order order: this.orders) {
    		if (order.getCurrentId().equals(id)) {
    			order.setStatus(status);	
    		}
    	}
    }
}
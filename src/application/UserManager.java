package application;

//import java.util.Arraylist;
//import java.util.List;

public class UserManager {

	    private Arraylist<User> users;
	    public String currentUser = "monke";

	    public UserManager() {
	        this.users = new Arraylist<>();
	        
	        // Initialize with some default admin and employee users
//	        users.add(new User("admin", "admin123", "admin"));
//	        users.add(new User("employee", "employee123", "employee"));
//	        users.add(new User("employee1","employee123","employee"));
	    }

	    public User login(String username, String password) {
	        for (User user : users) {
	            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
	                return user;
	            }
	        }
	        return null; // Login failed, user not found or incorrect credentials
	    }
	    
	    public void addUserList(Arraylist<User> userList) {
	    	this.users.addAll(userList);
	    }
	    
	    public Arraylist<User> getUsers(){
	    	return this.users;
	    }
	    public void setUser(String userId) {
	    	this.currentUser = userId;
	    }
}
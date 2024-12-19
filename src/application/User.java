package application;

public class User extends UserManager{

	    private String username;
	    private String password;
	    private String id;

	    public User(String id, String username, String password) {
	        this.id = id;
	    	this.username = username;
	        this.password = password;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public String getPassword() {
	        return password;
	    }
	    
	    public String getId() {
	    	return id;
	    }

}
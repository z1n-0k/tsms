package application;

public class Measurements {

	    private String chestSize;
	    private String waistSize;
	    private String hipSize;
	    private String size;
	    
	    public Measurements() {
	    	//default
	    	
	    }

	    public Measurements(String chestSize, String waistSize, String hipSize, String size) {
	        this.chestSize = chestSize;
	        this.waistSize = waistSize;
	        this.hipSize = hipSize;
	        this.size = size;
	    }

	    // Getters and Setters
	    public String getSize() {
	    	return this.size;
	    }
	    public String getChestSize() {
	    	return this.chestSize;
	    }
	    public String getWaistSize() {
	    	return this.waistSize;
	    }
	    public String getHipSize() {
	    	return this.hipSize;
	    }
	    
	    @Override
	    public String toString() {
	        return "Chest: " + chestSize +
	                ", Waist: " + waistSize +
	                ", Hips: " + hipSize;
	    }
	}
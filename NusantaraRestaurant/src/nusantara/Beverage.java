package nusantara;

class Beverage extends MenuItem {
	
	private String type;
	
    public Beverage(String name, String type, int price) {
    	super(name, price);
    	this.type = type;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

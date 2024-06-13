package nusantara;

class OrderItem {
    private MenuItem menuItem;
    private int qty;

    public OrderItem(MenuItem menuItem, int qty) {
        this.menuItem = menuItem;
        this.qty = qty;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return menuItem.getPrice() * qty;
    }
    
    public String itemName() {
    	
    	return menuItem.getName();
    }
    
    public int itemPrice() {
    	
    	return menuItem.getPrice();
    }
}

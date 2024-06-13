package nusantara;

import java.util.*;

public class Main {
	
	static int choice;
	
    private static ArrayList<CustomerOrder> customerOrders = new ArrayList<>();
    private static ArrayList<MenuItem> menu = new ArrayList<>();

    public static void main(String[] args) {
        initializeMenu();

        Scanner scanner = new Scanner(System.in);

        while (true) {
        	
        	System.out.println("\n╔═════════════════════════════════════════════════╗");
        	System.out.println("║╔═╦╗          ╔╗       ╔══╗     ╔╗            ╔╗ ║");
        	System.out.println("║║║║╠╗╔╦══╦═╦═╦╣╠╦═╦═╦═╗║░░╠═╦══╦╣╠╦═╦╗╔╦═╦═╦═╦╣╠╗║");
        	System.out.println("║║║║║╚╝╠╗╚╬╝║║╠╗╔╬╝║╠╬╝║║╔╗╣╩╬╗╚╬╗╔╬╝║╚╝║╠╬╝║║╠╗╔╣║");
        	System.out.println("║╚╩═╩══╩══╩═╩╩╝╚═╩═╩╝╚═╝╚╝╚╩═╩══╝╚═╩═╩══╩╝╚═╩╩╝╚═╝║");
        	System.out.println("╚═════════════════════════════════════════════════╝\n");
        		
        	
            System.out.println("1. Add Customer\n2. Add Order\n3. View Bill\n4. Delete Order\n5. View Menu\n6. Exit");
            System.out.print("Enter your choice: ");
            	
            	try {

					System.out.print("Choice [1-6]: ");
					choice = scanner.nextInt();
					scanner.nextLine();

				} catch (Exception e) {

					System.out.println("Input tidak valid. Harap masukkan angka.");
					scanner.nextLine(); 
				}


			switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    addOrder(scanner);
                    break;
                case 3:
                    viewBill(scanner);
                    break;
                case 4:
                    deleteOrder(scanner);
                    break;
                case 5:
                    viewMenu();
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;
                
            }
        }
    }

    private static void initializeMenu() {

    	menu.add(new Food("Nasi Goreng Jawa", "Jawa" , 30000));
    	menu.add(new Food("Mie Jawa", "Jawa", 25000));
    	menu.add(new Food("Soto Medan", "Medan", 35000));
    	menu.add(new Food("Pempek", "Palembang",32000));
    	menu.add(new Food("Babi Guling", "Bali", 43000));
    	menu.add(new Food("Ayam Betutu", "Bali", 29000));
    	menu.add(new Food("Kepiting Soka", "Kalimantan", 89000));
    	menu.add(new Food("Mie Aceh", "Sumatra", 28000));
    	menu.add(new Food("Sei Sapi", "NTT", 38000));
    	
    	menu.add(new Beverage("Es Jeruk", "Non-Alcohol", 8000));
    	menu.add(new Beverage("Es Teh", "Non-Alcohol",6000));
    	menu.add(new Beverage("Kopi Luwak", "Non-Alcohol", 15000));
    	menu.add(new Beverage("Soda Gembira", "Non-Alcohol",12000));
    	menu.add(new Beverage("Air Mineral", "Non-Alcohol", 4500));
    	menu.add(new Beverage("Anggur Merah", "Alcohol", 28000));
    	menu.add(new Beverage("Bir Bintang", "Alcohol", 32000));
    	
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        char firstLetter = customerName.charAt(0);
        String customerId = generateCustomerId(firstLetter);

        CustomerOrder customerOrder = new CustomerOrder(customerId, customerName);
        customerOrders.add(customerOrder);

        System.out.println("Customer added successfully! Customer ID: " + customerId + "\n");
    }

    private static String generateCustomerId(char firstLetter) {
        int count = 1;
        for (CustomerOrder order : customerOrders) {
            if (order.getCustomerId().charAt(0) == firstLetter) {
                count++;
            }
        }
        return Character.toUpperCase(firstLetter) + String.format("%03d", count);
    }
    
    //method baru utk duplicate code
    private static CustomerOrder findOrder(Scanner scanner) {
    	
    	if (customerOrders.isEmpty()) {
            System.out.println("No customers. Please add a customer first.");
            return null;
        }
        
        viewCust();
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();

        return findCustomerOrder(customerId);
    }

    private static void addOrder(Scanner scanner) {
    	
    	//panggil di sini
    	CustomerOrder currentOrder = findOrder(scanner);

        if (currentOrder != null) {
            char addMore = 'y';
            while (addMore == 'y') {
                System.out.println("Choose from the menu:");
                viewMenu();
                System.out.print("Enter the name of the food or beverage: ");
                String itemName = scanner.nextLine();
                
                int idx = searchidx(itemName);
                if (idx == -1) {
                    System.out.println("Invalid menu item. Please choose from the existing menu.\n");
                    continue;
                }
                
                System.out.println("Enter the quantity: ");
                int qty = scanner.nextInt();
                
                currentOrder.addOrderItem(new OrderItem(menu.get(idx), qty));
                
                System.out.println("Order added successfully!");

                System.out.print("Do you want to add more items? (press 'y' to continue | press other key to go back): ");
                addMore = scanner.next().charAt(0);
            }
        } else {
            System.out.println("Customer not found. Please add a customer first.\n");
        }
    }
    
    private static int searchidx(String itemName) {
    	for (int i=0 ; i<menu.size() ; i++) {
    		MenuItem item = menu.get(i);
    		if(item.getName().equalsIgnoreCase(itemName)) {
    			System.out.println(item.getName());
    			return i;
    		}
    	}
    	return -1;
    }

    private static void viewBill(Scanner scanner) {
    	
    	//panggil di sini
    	CustomerOrder currentOrder = findOrder(scanner);

        if (currentOrder != null) {
            currentOrder.printBill();
        } else {
            System.out.println("Customer not found. Please add a customer first.");
        }
    }

    private static void deleteOrder(Scanner scanner) {
    	
    	//panggil di sini
    	CustomerOrder currentOrder = findOrder(scanner);

        if (currentOrder != null) {
            currentOrder.deleteOrderItem(scanner);
        } else {
            System.out.println("Customer not found. Please add a customer first.");
        }
    }
    
    private static void viewCust() {
    	
    	if (customerOrders.isEmpty()) {
    		
    		System.out.println("there is no customer please add customer first");
    	}
    	else {
    		  System.out.println("Customer List : ");
    		  System.out.println("+=============================+");
    	      System.out.printf("|%-15s | %-10s | \n", "Customer ID", "Name");
    	      System.out.println("+=============================+");
    	      
    	      for (CustomerOrder customerOrder : customerOrders) {
    	    	  
    	    	  System.out.printf("|%-15s | %-10s |\n" , customerOrder.getCustomerId(), customerOrder.getCustomerName() );
				
			}
    	      System.out.println("+=============================+");
    	}
    }

    private static void viewMenu() {
    	
    	System.out.println("\n+=================================================+");
    	System.out.println("|                   FOOD MENU                     |");
        System.out.println("+=================================================+");
        System.out.printf("| %-16s | %-15s | %-10s | %n", "Menu", "Type", "Harga");
        System.out.println("+=================================================+");
        
        
        for (MenuItem entry : menu) {
        	if(entry instanceof Food) {
        		Food item = (Food)entry;
        		System.out.printf("| %-16s | %-15s | %-10d | \n", item.getName(), item.getType(), item.getPrice());
        	}
        }
        
        System.out.println("+=================================================+\n");
        
        System.out.println("+=================================================+");
        System.out.println("|                  BEVERAGE MENU                  |");
        System.out.println("+=================================================+");
        System.out.printf("| %-16s | %-15s | %-10s | %n", "Menu", "Type", "Harga");
        System.out.println("+=================================================+");
        
        for (MenuItem entry : menu) {
        	if(entry instanceof Beverage) {
        		Beverage item = (Beverage)entry;
        		System.out.printf("| %-16s | %-15s | %-10d | \n", item.getName(), item.getType(), item.getPrice());
        	}
        }
        
        System.out.println("+=================================================+");
    }


    private static CustomerOrder findCustomerOrder(String customerId) {
        for (CustomerOrder order : customerOrders) {
            if (order.getCustomerId().equalsIgnoreCase(customerId)) {
                return order;
            }
        }
        return null;
    }
}

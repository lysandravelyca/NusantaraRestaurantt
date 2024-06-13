package nusantara;

import java.util.*;

class CustomerOrder {
        private String customerId;
        private String customerName;
        private String workerID;
        private ArrayList<OrderItem> orderItems = new ArrayList<>();
        
       Random random = new Random();

        public CustomerOrder(String customerId, String customerName) {
            this.customerId = customerId;
            this.customerName = customerName;
            this.workerID = String.valueOf("W") + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
        }

        public String getCustomerId() {
            return customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void addOrderItem(OrderItem orderItem) {
            orderItems.add(orderItem);
        }

        public void printBill() {
            System.out.println("\nCUSTOMER ID : " + customerId);
            System.out.println("WORKER ID   : " + workerID);

            displayOrder();

        }

        public void deleteOrderItem(Scanner scanner) {
            if (orderItems.isEmpty()) {
                System.out.println("No items to delete. The order is empty.");
                return;
            }

            System.out.println("Current Order:");
            displayOrder();

            System.out.print("Enter the item number you want to modify: ");
            int itemToModify = scanner.nextInt();

            if (itemToModify > 0 && itemToModify <= orderItems.size()) {
                OrderItem targetItem = orderItems.get(itemToModify - 1);

                
                System.out.println("Current quantity of " + targetItem.itemName() + ": " + targetItem.getQty());
                System.out.print("Enter the quantity to delete: ");
                int qtyToDelete = scanner.nextInt();

                if (qtyToDelete > 0 && qtyToDelete <= targetItem.getQty()) {
                    targetItem.setQty(targetItem.getQty() - qtyToDelete);
                    System.out.println("Quantity deleted successfully!");
                    if (targetItem.getQty() == 0) {
                        orderItems.remove(itemToModify - 1);
                        System.out.println("Item removed from the order.");
                    }
                } else {
                    System.out.println("Invalid quantity. Please enter a valid quantity.");
                }
            } else {
                System.out.println("Invalid item number. Please enter a valid item number.");
            }
        }

        private void displayOrder() {

        	System.out.println("+===============================================================+");
            System.out.println("|                              BILL                             |");
            System.out.println("+===============================================================+");
            System.out.printf("|%-4s | %-16s | %-10s | %-10s |%-10s | %n", "No.", "Menu", "Harga", "Jumlah","Total Harga");
            System.out.println("+===============================================================+");
            int itemNo = 1;
            for (OrderItem item : orderItems) {
            	
            	//message chain
            	System.out.printf("|%-5d| %-16s | %-10d | %-10d | %-10d |\n", itemNo++, item.itemName(), item.itemPrice(), item.getQty(),item.getTotal());
            	
            }
            
            System.out.println("+===============================================================+");

            double totalBill = calculateTotalBill();
            double discount = calculateDiscount(totalBill);
            double priceToPay = totalBill - (totalBill * discount);

            System.out.printf("|Total Bill    : %-47.2f|\n", totalBill);
            System.out.printf("|Discount (%%)  : %-47.0f|\n", (discount * 100));
            System.out.printf("|Payment Price : %-47.2f|\n", priceToPay);
            System.out.println("+===============================================================+\n");
        }

        private double calculateTotalBill() {
            double total = 0;
            for (OrderItem item : orderItems) {
                total += item.getTotal();
            }
            return total;
        }

        private double calculateDiscount(double totalBill) {
            if (totalBill > 50000 && totalBill <= 100000) {
                return 0.05;
            } else if (totalBill > 100000 && totalBill <= 150000) {
                return 0.15;
            } else if (totalBill > 150000) {
                return 0.20;
            } else {
                return 0;
            }
        }
    }
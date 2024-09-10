import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Item class to hold product information
class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}

// ReceiptCalculator class to handle the receipt operations
public class ReceiptCalculator {
    private ArrayList<Item> items;
    private double taxRate;
    private double discountRate;

    public ReceiptCalculator(double taxRate, double discountRate) {
        items = new ArrayList<>();
        this.taxRate = taxRate;
        this.discountRate = discountRate;
    }

    public void addItem(String name, double price, int quantity) {
        items.add(new Item(name, price, quantity));
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (Item item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public double calculateTax(double subtotal) {
        return subtotal * (taxRate / 100);
    }

    public double calculateDiscount(double subtotal) {
        return subtotal * (discountRate / 100);
    }

    public double calculateTotal(double subtotal, double tax, double discount) {
        return subtotal + tax - discount;
    }

    public void displayReceipt() {
        System.out.println("\n--- Receipt ---");
        for (Item item : items) {
            System.out.printf("%s: %.2f x %d = %.2f\n",
                item.getName(), item.getPrice(), item.getQuantity(), item.getTotalPrice());
        }
        double subtotal = calculateSubtotal();
        double tax = calculateTax(subtotal);
        double discount = calculateDiscount(subtotal);
        double total = calculateTotal(subtotal, tax, discount);

        System.out.printf("Subtotal: %.2f\n", subtotal);
        System.out.printf("Tax: %.2f\n", tax);
        System.out.printf("Discount: %.2f\n", discount);
        System.out.printf("Total: %.2f\n", total);
    }

    public void saveReceiptAsText(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("--- Receipt ---\n");
            for (Item item : items) {
                writer.write(item.getName() + ": " + item.getPrice() + " x " + item.getQuantity() + " = " + item.getTotalPrice() + "\n");
            }
            double subtotal = calculateSubtotal();
            double tax = calculateTax(subtotal);
            double discount = calculateDiscount(subtotal);
            double total = calculateTotal(subtotal, tax, discount);

            writer.write("Subtotal: " + subtotal + "\n");
            writer.write("Tax: " + tax + "\n");
            writer.write("Discount: " + discount + "\n");
            writer.write("Total: " + total + "\n");

            System.out.println("Receipt saved as " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReceiptCalculator calculator = new ReceiptCalculator(10, 5); // 10% tax, 5% discount

        System.out.println("Enter item details (name price quantity), or 'done' to finish:");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("done")) {
                break;
            }
            String[] parts = input.split(" ");
            String name = parts[0];
            double price = Double.parseDouble(parts[1]);
            int quantity = Integer.parseInt(parts[2]);

            calculator.addItem(name, price, quantity);
        }

        calculator.displayReceipt();
        System.out.println("Enter a filename to save the receipt (e.g., 'receipt.txt'):");
        String fileName = scanner.nextLine();
        calculator.saveReceiptAsText(fileName);
    }
}

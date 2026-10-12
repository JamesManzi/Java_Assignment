
import java.util.Scanner;

// Product class
class Product {
    int productId;
    String productName;
    double price;
    int availableQuantity;

    // Constructor
    public Product(int productId, String productName,
                   double price, int availableQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    // Display product details
    public void displayProduct() {
        System.out.printf("%-10d %-20s %12.2f %10d%n",
                productId, productName, price, availableQuantity);
    }
}

// Main class
public class ProductShoppingSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numberOfProducts;

        // Validate number of products
        do {
            System.out.print("How many products do you want to register? ");

            while (!input.hasNextInt()) {
                System.out.print("Enter a valid whole number: ");
                input.next();
            }

            numberOfProducts = input.nextInt();

            if (numberOfProducts <= 0) {
                System.out.println("Register at least one product.");
            }

        } while (numberOfProducts <= 0);

        input.nextLine();

        // Create array of Product objects
        Product[] products = new Product[numberOfProducts];

        // Enter product information
        for (int i = 0; i < numberOfProducts; i++) {
            System.out.println("\nEnter details for Product " + (i + 1));

            System.out.print("Product ID: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.print("Product Name: ");
            String name = input.nextLine();

            double price;

            // Validate price
            do {
                System.out.print("Product Price: ");

                while (!input.hasNextDouble()) {
                    System.out.print("Enter a valid price: ");
                    input.next();
                }

                price = input.nextDouble();

                if (price <= 0) {
                    System.out.println("Price must be greater than zero.");
                }

            } while (price <= 0);

            int quantity;

            // Validate initial stock
            do {
                System.out.print("Available Quantity: ");

                while (!input.hasNextInt()) {
                    System.out.print("Enter a valid quantity: ");
                    input.next();
                }

                quantity = input.nextInt();

                if (quantity < 0) {
                    System.out.println("Stock cannot be negative.");
                }

            } while (quantity < 0);

            input.nextLine();

            // Create object using constructor
            products[i] = new Product(id, name, price, quantity);
        }

        // Display all products
        System.out.println("\n========== AVAILABLE PRODUCTS ==========");
        System.out.printf("%-10s %-20s %12s %10s%n",
                "ID", "Name", "Price", "Stock");

        for (int i = 0; i < products.length; i++) {
            products[i].displayProduct();
        }

        double totalPaid = 0;
        String choice;

        // Allow customer to purchase repeatedly
        do {
            int productId;
            int productIndex = -1;

            // Find a valid product ID
            while (productIndex == -1) {
                System.out.print("\nEnter the ID of the product to buy: ");

                while (!input.hasNextInt()) {
                    System.out.print("Enter a valid product ID: ");
                    input.next();
                }

                productId = input.nextInt();

                for (int i = 0; i < products.length; i++) {
                    if (products[i].productId == productId) {
                        productIndex = i;
                        break;
                    }
                }

                if (productIndex == -1) {
                    System.out.println(
                        "Product ID not found. Please try again."
                    );
                }
            }

            Product selectedProduct = products[productIndex];

            // Check that stock is available
            if (selectedProduct.availableQuantity == 0) {
                System.out.println("This product is out of stock.");
            } else {
                int requestedQuantity;

                // Validate purchase quantity
                do {
                    System.out.print("Enter quantity to purchase: ");

                    while (!input.hasNextInt()) {
                        System.out.print("Enter a valid whole quantity: ");
                        input.next();
                    }

                    requestedQuantity = input.nextInt();

                    if (requestedQuantity <= 0) {
                        System.out.println(
                            "Quantity must be greater than zero."
                        );
                    } else if (
                        requestedQuantity >
                        selectedProduct.availableQuantity
                    ) {
                        System.out.println(
                            "Insufficient stock. Available: "
                            + selectedProduct.availableQuantity
                        );
                    }

                } while (requestedQuantity <= 0 ||
                         requestedQuantity >
                         selectedProduct.availableQuantity);

                // Calculate purchase amount
                double purchaseAmount =
                    selectedProduct.price * requestedQuantity;

                double discountRate;

                // Determine discount based on purchase amount
                if (purchaseAmount >= 200000) {
                    discountRate = 0.15;
                } else if (purchaseAmount >= 100000) {
                    discountRate = 0.10;
                } else if (purchaseAmount >= 50000) {
                    discountRate = 0.05;
                } else {
                    discountRate = 0.0;
                }

                double discount = purchaseAmount * discountRate;
                double finalAmount = purchaseAmount - discount;

                // Reduce stock after a successful purchase
                selectedProduct.availableQuantity -= requestedQuantity;

                // Add to customer's total
                totalPaid += finalAmount;

                // Display purchase receipt
                System.out.println("\n========== PURCHASE RECEIPT ==========");
                System.out.println("Product: " + selectedProduct.productName);
                System.out.println("Quantity: " + requestedQuantity);
                System.out.printf("Price per item: %.2f%n",
                        selectedProduct.price);
                System.out.printf("Original amount: %.2f%n",
                        purchaseAmount);
                System.out.printf("Discount: %.2f%n", discount);
                System.out.printf("Amount to pay: %.2f%n", finalAmount);
                System.out.println("Remaining stock: "
                        + selectedProduct.availableQuantity);
            }

            input.nextLine();

            // Ask whether the customer wants to continue
            do {
                System.out.print(
                    "\nDo you want to buy another product? (yes/no): "
                );
                choice = input.nextLine().trim();

                if (!choice.equalsIgnoreCase("yes") &&
                    !choice.equalsIgnoreCase("no")) {
                    System.out.println("Please enter yes or no.");
                }

            } while (!choice.equalsIgnoreCase("yes") &&
                     !choice.equalsIgnoreCase("no"));

        } while (choice.equalsIgnoreCase("yes"));

        // Final shopping summary
        System.out.println("\n========== FINAL SHOPPING SUMMARY ==========");
        System.out.printf("Total amount paid: %.2f%n", totalPaid);

        System.out.println("\n========== REMAINING STOCK ==========");
        System.out.printf("%-10s %-20s %10s%n",
                "ID", "Product", "Stock");

        for (int i = 0; i < products.length; i++) {
            System.out.printf("%-10d %-20s %10d%n",
                    products[i].productId,
                    products[i].productName,
                    products[i].availableQuantity);
        }

        System.out.println("\nThank you for shopping!");
        input.close();
    }
}

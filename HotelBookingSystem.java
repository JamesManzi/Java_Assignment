
import java.util.Scanner;

// HotelBooking class
class HotelBooking {
    int bookingId;
    String customerName;
    String roomType;
    int numberOfNights;
    int numberOfGuests;

    double roomPrice;
    double originalAmount;
    double discount;
    double finalAmount;

    // Constructor
    public HotelBooking(int bookingId, String customerName,
                        String roomType, int numberOfNights,
                        int numberOfGuests) {

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.numberOfNights = numberOfNights;
        this.numberOfGuests = numberOfGuests;

        // Determine room price
        if (roomType.equalsIgnoreCase("Standard")) {
            roomPrice = 30000;
        } else if (roomType.equalsIgnoreCase("Deluxe")) {
            roomPrice = 50000;
        } else if (roomType.equalsIgnoreCase("Executive")) {
            roomPrice = 80000;
        } else {
            roomPrice = 0;
        }

        // Calculate original amount
        originalAmount = roomPrice * numberOfNights;

        // Calculate discount based on number of nights
        if (numberOfNights >= 15) {
            discount = originalAmount * 0.20;
        } else if (numberOfNights >= 7) {
            discount = originalAmount * 0.10;
        } else if (numberOfNights >= 3) {
            discount = originalAmount * 0.05;
        } else {
            discount = 0;
        }

        // Calculate final amount
        finalAmount = originalAmount - discount;
    }

    // Display booking details
    public void displayBooking() {
        System.out.println("\n==================================");
        System.out.println("       HOTEL BOOKING DETAILS      ");
        System.out.println("==================================");

        System.out.println("Booking ID: " + bookingId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Number of Nights: " + numberOfNights);
        System.out.println("Number of Guests: " + numberOfGuests);

        System.out.printf("Price Per Night: %,.2f RWF%n", roomPrice);
        System.out.printf("Original Amount: %,.2f RWF%n", originalAmount);
        System.out.printf("Discount: %,.2f RWF%n", discount);
        System.out.printf("Final Amount: %,.2f RWF%n", finalAmount);

        System.out.println("==================================");
    }
}

// Main class
public class HotelBookingSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numberOfBookings;

        // Validate number of bookings
        do {
            System.out.print("How many customers will make bookings? ");

            while (!input.hasNextInt()) {
                System.out.print("Enter a valid whole number: ");
                input.next();
            }

            numberOfBookings = input.nextInt();

            if (numberOfBookings <= 0) {
                System.out.println(
                    "Number of bookings must be greater than zero."
                );
            }

        } while (numberOfBookings <= 0);

        input.nextLine();

        // Array to store booking objects
        HotelBooking[] bookings = new HotelBooking[numberOfBookings];

        // Register bookings
        for (int i = 0; i < numberOfBookings; i++) {

            System.out.println("\nBooking " + (i + 1));

            System.out.print("Enter Booking ID: ");
            int bookingId = input.nextInt();
            input.nextLine();

            System.out.print("Enter Customer Name: ");
            String customerName = input.nextLine();

            String roomType;

            // Validate room type using do...while
            do {
                System.out.println("\nAvailable Room Types:");
                System.out.println("1. Standard - 30,000 RWF per night");
                System.out.println("2. Deluxe - 50,000 RWF per night");
                System.out.println("3. Executive - 80,000 RWF per night");

                System.out.print("Select room type (Standard/Deluxe/Executive): ");
                roomType = input.nextLine().trim();

                if (!roomType.equalsIgnoreCase("Standard")
                        && !roomType.equalsIgnoreCase("Deluxe")
                        && !roomType.equalsIgnoreCase("Executive")) {
                    System.out.println(
                        "Invalid room type. Please try again."
                    );
                }

            } while (!roomType.equalsIgnoreCase("Standard")
                    && !roomType.equalsIgnoreCase("Deluxe")
                    && !roomType.equalsIgnoreCase("Executive"));

            int nights;

            // Validate number of nights using while
            System.out.print("Enter number of nights (1-30): ");
            nights = input.nextInt();

            while (nights < 1 || nights > 30) {
                System.out.print(
                    "Invalid number of nights. Enter 1-30: "
                );
                nights = input.nextInt();
            }

            int guests;

            // Validate number of guests
            System.out.print("Enter number of guests (1-4): ");
            guests = input.nextInt();

            while (guests < 1 || guests > 4) {
                System.out.print(
                    "Invalid number of guests. Enter 1-4: "
                );
                guests = input.nextInt();
            }

            input.nextLine();

            // Create booking object using constructor
            bookings[i] = new HotelBooking(
                bookingId,
                customerName,
                roomType,
                nights,
                guests
            );
        }

        // Display all booking details
        System.out.println("\n******** ALL HOTEL BOOKINGS ********");

        for (int i = 0; i < bookings.length; i++) {
            bookings[i].displayBooking();
        }

        input.close();
    }
}

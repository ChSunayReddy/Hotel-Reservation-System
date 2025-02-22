import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public void bookRoom() {
        this.isAvailable = false;
    }

    public void releaseRoom() {
        this.isAvailable = true;
    }
}

class Booking {
    String guestName;
    Room room;
    String paymentStatus;

    public Booking(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
        this.paymentStatus = "Pending";
        room.bookRoom();
    }

    public void makePayment() {
        this.paymentStatus = "Paid";
    }
}

class Hotel {
    List<Room> rooms = new ArrayList<>();
    List<Booking> bookings = new ArrayList<>();

    public void addRoom(int roomNumber, String category, double price) {
        rooms.add(new Room(roomNumber, category, price));
    }

    public void showAvailableRooms() {
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room: " + room.roomNumber + " | Category: " + room.category + " | Price: " + room.price);
            }
        }
    }

    public void bookRoom(String guestName, int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                Booking booking = new Booking(guestName, room);
                bookings.add(booking);
                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room not available!");
    }

    public void showBookings() {
        for (Booking booking : bookings) {
            System.out.println("Guest: " + booking.guestName + " | Room: " + booking.room.roomNumber + " | Status: " + booking.paymentStatus);
        }
    }

    public void processPayment(String guestName) {
        for (Booking booking : bookings) {
            if (booking.guestName.equals(guestName)) {
                booking.makePayment();
                System.out.println("Payment successful for " + guestName);
                return;
            }
        }
        System.out.println("Booking not found!");
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        hotel.addRoom(101, "Single", 100.0);
        hotel.addRoom(102, "Double", 150.0);
        hotel.addRoom(103, "Suite", 250.0);
        hotel.addRoom(201, "Single", 100.0);
        hotel.addRoom(202, "Double", 150.0);
        hotel.addRoom(203, "Suite", 250.0);
        hotel.addRoom(301, "Single", 100.0);
        hotel.addRoom(302, "Double", 150.0);
        hotel.addRoom(303, "Suite", 250.0);

        while (true) {
            System.out.println("\n1. Show Available Rooms\n2. Book Room\n3. View Bookings\n4. Make Payment\n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    hotel.showAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    hotel.bookRoom(name, roomNumber);
                    break;
                case 3:
                    hotel.showBookings();
                    break;
                case 4:
                    System.out.print("Enter your name: ");
                    String guest = scanner.nextLine();
                    hotel.processPayment(guest);
                    break;
                case 5:
                    System.out.println("Thank you for using Hotel Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

import java.util.LinkedList;

public class Movie {
    private String name;
    private double price;
    private LinkedList<Seat> seats;
    private static final int TOTAL_SEATS = 50; // Total seats per movie

    public Movie(String name, double price) {
        this.name = name;
        this.price = price;
        this.seats = new LinkedList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        for (int i = 1; i <= TOTAL_SEATS; i++) {
            seats.add(new Seat(i));
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean bookSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
            return false;
        }

        Seat seat = seats.get(seatNumber - 1);
        if (!seat.isBooked()) {
            seat.book();
            return true;
        }
        return false;
    }

    public void displaySeatArrangement() {
        System.out.println("\nSeat Arrangement for " + name);
        System.out.println("(O: Available, X: Booked)\n");

        for (int i = 0; i < TOTAL_SEATS; i++) {
            Seat seat = seats.get(i);
            System.out.printf("%3d[%s] ", (i + 1), (seat.isBooked() ? "X" : "O"));
            

            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
} 
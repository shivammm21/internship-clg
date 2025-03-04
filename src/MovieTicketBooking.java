import java.util.LinkedList;
import java.util.Scanner;

public class MovieTicketBooking {
    private LinkedList<Movie> movies;
    private Scanner scanner;

    public MovieTicketBooking() {
        this.movies = new LinkedList<>();
        this.scanner = new Scanner(System.in);
        initializeMovies();
    }

    private void initializeMovies() {
        Movie movie1 = new Movie("Inception", 300.0);
        Movie movie2 = new Movie("The Dark Knight", 250.0);
        Movie movie3 = new Movie("Interstellar", 280.0);
        
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== Movie Ticket Booking System ===");
            System.out.println("1. Show Available Movies");
            System.out.println("2. Book Tickets");
            System.out.println("3. View Seat Arrangement");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAvailableMovies();
                    break;
                case 2:
                    bookTickets();
                    break;
                case 3:
                    viewSeatArrangement();
                    break;
                case 4:
                    System.out.println("Thank you for using our system!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void showAvailableMovies() {
        System.out.println("\nAvailable Movies:");
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            System.out.printf("%d. %s (₹%.2f per ticket)%n", 
                (i + 1), movie.getName(), movie.getPrice());
        }
    }

    private void bookTickets() {
        showAvailableMovies();
        System.out.print("\nSelect movie number: ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine();

        if (movieChoice < 1 || movieChoice > movies.size()) {
            System.out.println("Invalid movie selection!");
            return;
        }

        Movie selectedMovie = movies.get(movieChoice - 1);
        selectedMovie.displaySeatArrangement();

        System.out.print("Enter number of tickets to book: ");
        int numTickets = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numTickets; i++) {
            System.out.printf("\nEnter seat number for ticket %d: ", (i + 1));
            int seatNumber = scanner.nextInt();
            scanner.nextLine();

            if (selectedMovie.bookSeat(seatNumber)) {
                System.out.printf("Seat %d booked successfully!%n", seatNumber);
            } else {
                System.out.printf("Failed to book seat %d. It might be already booked or invalid.%n", seatNumber);
                i--;
            }
        }

        double totalCost = numTickets * selectedMovie.getPrice();
        System.out.printf("\nTotal cost: ₹%.2f%n", totalCost);
    }

    private void viewSeatArrangement() {
        showAvailableMovies();
        System.out.print("\nSelect movie number to view seats: ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine();

        if (movieChoice < 1 || movieChoice > movies.size()) {
            System.out.println("Invalid movie selection!");
            return;
        }

        Movie selectedMovie = movies.get(movieChoice - 1);
        selectedMovie.displaySeatArrangement();
    }

} 
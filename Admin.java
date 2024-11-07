
public class Admin extends User {

    private ConcertDetails concertDetails;

    public Admin(ConcertDetails concertDetails){
        this.concertDetails = concertDetails;
    }
    
    /**
     * Displays the main menu for the admin and handles user input for different admin actions.
     * This menu allows the admin to perform several operations related to concert management.
     * @param concertDetails the details of the concerts being managed
     * @param row the number of rows in the venue
     * @param left the number of seats in the left section of each row
     * @param mid the number of seats in the middle section of each row
     * @param right the number of seats in the right section of each row
     */
    @Override
    public void mainMenu(ConcertDetails concertDetails, int row, int left, int mid, int right) {
        
        int option;
        do {
            System.out.println("Select an option to get started!");
            System.out.println("Press 1 to look at the concert timings");
            System.out.println("Press 2 to add a concert");
            System.out.println("Press 3 to remove a concert");
            System.out.println("Press 4 to look at the ticket costs");
            System.out.println("Press 5 to update the ticket costs for a concert");
            System.out.println("Press 6 to view the layout of the concert");
            System.out.println("Press 7 to exit");
            System.out.print("> ");
            
            // scanner.next();
            option = InputManager.SCANNER.nextInt();
            // scanner.next();
            // System.out.println("Next: "+scanner.next());

            switch (option) {
                case 1:
                    concertDetails.displayConcert();
                    break;
                case 2:
                    addConcert(row, left, mid, right);
                    break;
                case 3:
                    removeConcert();
                    // System.out.print("optionL: "+option);
                    break;
                case 4:
                    displaySelectedSeatPrices(concertDetails);
                    // System.out.print("optionL: "+option);
                    break;
                case 5:
                    updateTicketPrice();
                    // System.out.print("optionL: "+option);
                    break;
                case 6:
                    System.out.println("Select a concert.");
                    concertDetails.displayConcert();
                    int concertIndex = InputManager.SCANNER.nextInt() - 1;
                    
                    concertDetails.getConcerts()[concertIndex].getVenue().displaySeat();
                    break;
                case 7:
                    System.out.println("Exiting Admin Mode");
                    return; // Return to main menu instead of exiting program
                    
                default:
                    System.out.println("Invalid Input.");
                    break;
            }
        } while (option != 7);
        // scanner.close();
    }

    /**
     * Displays seat prices for a selected concert.
     * @param concertDetails the details of concerts which include pricing information.
     */
    private void displaySelectedSeatPrices(ConcertDetails concertDetails) {
        // Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the concert number to see the prices for the seats");
        concertDetails.displayConcert();
        int concertNumber = InputManager.SCANNER.nextInt();
        Concert[] concerts = concertDetails.getConcerts();
        if (concertNumber < 1 || concertNumber > concerts.length) {
            System.out.println("Invalid concert number");
            // scanner.close();
            return;
        }
        Concert concert = concerts[concertNumber - 1];
        System.out.println("> Seat Costs");
        System.out.println("-----------------------");
        System.out.println("Left Zone:   AUD " + concert.getLeftSectionPrice());
        System.out.println("Right Zone:  AUD " + concert.getRightSectionPrice());
        System.out.println("Middle Zone: AUD " + concert.getMiddleSectionPrice());
        System.out.println("-----------------------");

        // scanner.close();
    }

    /**
     * Displays the layout of a selected concert.
     * @param concertDetails contains details of all concerts including venue layouts.
     * @param row number of rows in the venue.
     * @param left number of seats in the left section per row.
     * @param mid number of seats in the middle section per row.
     * @param right number of seats in the right section per row.
     */
    private void displayConcertLayout(ConcertDetails concertDetails, int row, int left, int mid, int right) {
        // Implement this method
        // Scanner scanner = new Scanner(System.in);
        System.out.println("Select the concert number to view its seating layout:");

        Concert [] concerts = concertDetails.getConcerts();
        for (int i = 0; i < concertDetails.getNumberOfConcerts(); i++) {
            System.out.println((i + 1) + ". " + concerts[i].getArtistName() + " at " +
                               concerts[i].getVenue().getName() + " on " + concerts[i].getDate());
        }

        System.out.print("> ");
        int concertChoice  = InputManager.SCANNER.nextInt() -1;
        if (concertChoice >= 0 && concertChoice < concertDetails.getNumberOfConcerts()) {
            printSeatingLayout(concerts[concertChoice], row, left, mid, right);
        } else {
            System.out.println("Invalid concert selection. Please try again.");
        }
        // scanner.close();
    }

    private void printSeatingLayout(Concert selectedConcert, int row, int left, int mid, int right) {
        System.out.println("Seating Layout:");
        // boolean [][] bookedSeat = selectedConcert.getSeatsBooked();
        Venue venue = new Venue(row, left, mid, right);
        venue.seatLayout(row, left, mid, right);

    }

    /**
     * Adds a new concert based on user input.
     * @param row number of rows in the concert venue.
     * @param left number of left section seats per row.
     * @param mid number of middle section seats per row.
     * @param right number of right section seats per row.
     */
    private void addConcert(int row,int left,int mid,int right){
        System.out.println("To add a new concert please enter the details:");
        System.out.print("Date: ");
        String date = InputManager.SCANNER.next();
        System.out.print("Timing: ");
        String timing = InputManager.SCANNER.next();
        InputManager.SCANNER.nextLine();
        System.out.print("Artist Name: ");
        String artistName = InputManager.SCANNER.nextLine();
        System.out.print("Venue: ");
        String venueName = InputManager.SCANNER.nextLine();
        System.out.print("Left zone price: ");
        double leftPrice = InputManager.SCANNER.nextDouble();
        System.out.print("Middle zone price: ");
        double middlePrice = InputManager.SCANNER.nextDouble();
        System.out.print("Right zone price: ");
        double rightPrice = InputManager.SCANNER.nextDouble();
        int totalSeats = row*(left+mid+right);

        concertDetails.addConcert(new Concert(date, artistName, timing, new Venue(venueName, row, left, mid, right, totalSeats), leftPrice, middlePrice, rightPrice), row, left, mid, right);
        System.out.println("New concert added successfully.");
    }

    /**
     * Removes a concert from the list if no tickets have been sold.
     */
    private void removeConcert() {
        System.out.println("Select a concert that you want to remove.");
        concertDetails.displayConcert();
        System.out.print("> ");

        int concertIndex = InputManager.SCANNER.nextInt() - 1; // Adjust index for 0-based array

        if (concertIndex < 0 || concertIndex >= concertDetails.getNumberOfConcerts()) {
            System.out.println("Invalid concert number.");
            return;
        }

        if (!concertDetails.isSeatsBooked(concertIndex)) {
            concertDetails.removeConcert(concertIndex);
            System.out.println("Concert removed successfully.");
        } else {
            System.out.println("Concert cannot be removed as tickets are booked for this concert.");
        }
    }

    /**
     * Updates the ticket prices for a specific concert.
     */
    private void updateTicketPrice(){
        System.out.println("Select a concert that's price needs to be updated");
        concertDetails.displayConcert();
        int concertIndex = InputManager.SCANNER.nextInt()-1;
        System.out.println("> Enter the price details");
        System.out.print("Left zone price: ");
        double leftPrice = InputManager.SCANNER.nextDouble();
        System.out.print("Middle zone price: ");
        double middlePrice = InputManager.SCANNER.nextDouble();
        System.out.print("Right zone price: ");
        double rightPrice = InputManager.SCANNER.nextDouble();

        concertDetails.updateTicketPrice(concertIndex, leftPrice, middlePrice, rightPrice);
        System.out.println("Price updated successfully.");
        
    }

    

}



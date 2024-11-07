/**
 * Represents a Customer user with the capability to interact with the concert booking system.
 * This class provides functionalities such as viewing show timings, seat costs, seat layouts, and booking seats.
 */
public class Customer extends User {
    /**
     * Displays the main menu for the customer and processes user input to navigate through the menu options.
     * The method provides options to view concert timings, ticket costs, seat layouts, book seats, view prices for selected seats, and exit.
     * @param concertDetails Details of all concerts available for viewing and booking.
     * @param row Number of rows in the seating layout for the concerts.
     * @param left Number of seats in the left section of each row.
     * @param mid Number of seats in the middle section of each row.
     * @param right Number of seats in the right section of each row.
     */
    @Override
    
    public void mainMenu(ConcertDetails concertDetails, int row, int left, int mid, int right) {
        
        int option;
        do {
            System.out.println("Select an option to get started!");
            System.out.println("Press 1 to look at the show timings");
            System.out.println("Press 2 to look at the ticket costs");
            System.out.println("Press 3 to view the layout of the concert");
            System.out.println("Press 4 to book seats");
            System.out.println("Press 5 to see the price for selected seats");
            System.out.println("Press 6 to exit");
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
                    displaySelectedSeatPrices(concertDetails);
                    break;
                case 3:
                    System.out.println("Select a concert that's price needs to be updated");
                    concertDetails.displayConcert();
                    int concertIndex = InputManager.SCANNER.nextInt() - 1;
                    System.out.print("> ");
                    concertDetails.getConcerts()[concertIndex].getVenue().displaySeat();
                    break;
                case 4:
                    System.out.println("Select a concert that's price needs to be updated");
                    concertDetails.displayConcert();
                    concertIndex = InputManager.SCANNER.nextInt() - 1;

                    concertDetails.getConcerts()[concertIndex].getVenue().TicketBooking();

                    char move;
                    // System.out.println();
                    System.out.println("> Continue to the seat selection! ");
                    System.out.println("You can select the seat that are empty and marked by _");
                    do {
                        concertDetails.getConcerts()[concertIndex].getVenue().displaySeat();
                        System.out.println("Press W/S/A/D to move up/down/left/right. Press Z to select, Q to quit.");
                        move = InputManager.SCANNER.next().charAt(0);
                        if (move == 'q' || move == 'Q') {
                            System.out.println("> Your seat selection is saved.");
                            System.out.print("");
                            concertDetails.getConcerts()[concertIndex].getVenue().resetSelection();
                            // concertDetails.getConcerts()[concertIndex].getVenue().displaySeat();
                            break;
                        } else {
                            System.out.print("> ");
                            concertDetails.getConcerts()[concertIndex].getVenue().moveSeat(move);
                        }
                    } while (true);

                    // System.out.println("Select a concert that's price needs to be updated");
                    // concertDetails.displayConcert();
                    // concertIndex = InputManager.SCANNER.nextInt() - 1;
                    
                    // concertDetails.getConcerts()[concertIndex].getVenue().displaySeat();
                    // break;
                case 5:
                    // displaySelectedSeatPrices();
                    break;
                case 6:
                    System.out.println("Exiting Customer Mode");
                    return; // Return to main menu instead of exiting program
                default:
                    System.out.println("Invalid Input.");
                    break;
            }
        } while (option != 7);
        // scanner.close();
    }

    /**
     * Displays prices for selected seats within a specific concert.
     * @param concertDetails The details of concerts available.
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
     * Allows the customer to view the seating layout for a selected concert.
     * @param concertDetails The details of all concerts available.
     */
    private void displayConcertLayout(ConcertDetails concertDetails, int row, int left, int mid, int right) {
        // Implement this method
        // Scanner scanner = new Scanner(System.in);
        System.out.println("Select a concert that's price needs to be updated");
        concertDetails.displayConcert();

        Concert [] concerts = concertDetails.getConcerts();
        // for (int i = 0; i < concertDetails.getNumberOfConcerts(); i++) {
        //     System.out.println((i + 1) + ". " + concerts[i].getArtistName() + " at " +
        //                        concerts[i].getVenue().getName() + " on " + concerts[i].getDate());
        // }

        System.out.print("> ");
        int concertChoice  = InputManager.SCANNER.nextInt() -1;
        if (concertChoice >= 0 && concertChoice < concertDetails.getNumberOfConcerts()) {
            printSeatingLayout(concerts[concertChoice]);
        } else {
            System.out.println("Invalid concert selection. Please try again.");
        }
        // scanner.close();
    }

    private void printSeatingLayout(Concert selectedConcert) {;
        for(int i=0; i<selectedConcert.getVenue().getRow(); i++){
            for(int j=0; j<selectedConcert.getVenue().getTotalSeats()+2; j++){
                System.out.print(selectedConcert.getVenue().getSeats()[i][j]);
                System.out.println();
            }
        System.out.println(selectedConcert.getVenue().getSeats()[0][0]);

        // for(int i=0; i<selectedConcert.getVenue().getRow(); i++){
        //     for(int j=0; j<selectedConcert.getVenue().getTotalSeats()+2; j++){
        //         if (j == 0){
        //             System.out.print("[");
        //             System.out.print(selectedConcert.getVenue().getSeats()[i][j]);
        //         }else if (j == selectedConcert.getVenue().getTotalSeats()+2){
        //             System.out.print(selectedConcert.getVenue().getSeats()[i][j]);
        //             System.out.print("]");
        //         }else{
        //             System.out.print(selectedConcert.getVenue().getSeats()[i][j]);
        //         }
        //     }
        System.out.println(); 
        // System.out.println();
        }

    }



    

}




/**
 * The TicketManagementEngine class is responsible for managing the entire ticket management system
 * for concerts. It includes functionalities such as checking input arguments, pre-loading concert details,
 * and providing a main menu interface for user interactions.
 */


import java.util.Scanner;





public class TicketManagementEngine {

    /**
     * The main method serves as the entry point for the application.
     * It initializes the system, checks layout arguments, and handles the main user interaction loop.
     * 
     * @param args Command line arguments that specify the seating configuration for the concerts.
     */
    public static void main(String[] args){
        TicketManagementEngine tme = new TicketManagementEngine();
        if(tme.checkLayoutArgs(args)){
            System.out.println("Invalid Inputs to set layout. Exiting the program now.");
        }else {
            int row = Integer.parseInt(args[0]);
            int left = Integer.parseInt(args[1]);
            int mid = Integer.parseInt(args[2]);
            int right = Integer.parseInt(args[3]);

            ConcertDetails concertDetails = tme.preLoadConcertDetails(args);
            // System.out.println("concert1111111111111111111111111111: "+ concertDetails.getNumberOfConcerts());
            tme.displayMessage();

            
            tme.mainMenu(concertDetails, row, left, mid, right);

            //write code for customer /admin menu
        }
    }

    /**
     * Preloads concert details using command line arguments to set up the venue and concert information.
     *
     * @param args Command line arguments for row, left, mid, and right seat counts.
     * @return A new instance of ConcertDetails filled with predefined concerts.
     */
    private  ConcertDetails preLoadConcertDetails(String[] args) {

        ConcertDetails concertDetails = new ConcertDetails(3);
        int totalSeats = Integer.parseInt(args[0]) * (Integer.parseInt(args[1])+Integer.parseInt(args[2])+Integer.parseInt(args[3]));
        
        Venue venue1 = new Venue(
            "Melbourne Cricket Ground", 
            Integer.parseInt(args[0]),
            Integer.parseInt(args[1]),
            Integer.parseInt(args[2]),
            Integer.parseInt(args[3]),
            totalSeats);
        Venue venue2 = new Venue(
            "Marvel Stadium",
            Integer.parseInt(args[0]),
            Integer.parseInt(args[1]),
            Integer.parseInt(args[2]),
            Integer.parseInt(args[3]),
            totalSeats);

        Concert concert1 = new Concert("2023-07-01", "Taylor Swift", "1700-1900", venue1, 200.0, 500.0, 250.0);
        Concert concert2 = new Concert("2023-07-02", "Taylor Swift", "1900-2100", venue2, 100.0, 300.0, 150.0);


        concertDetails.addConcert(concert1, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        concertDetails.addConcert(concert2, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        /* concert 1   
        * venue 1 :  venue using the command line args
        * create concert with following details 
        * "2023-07-01" , "1700-1900","Taylor Swift", "Melbourne Cricket Ground", totalSeats,
        * 200.0f, 500.0f, 250.0f,venue1
        */
       

        /*concert 2
        * concert 2
        * venue 2 :  venue using the command line args
        * create concert with following details 
        * "2023-07-02" , "1900-2100","Taylor Swift", "Marvel Stadium", totalSeats,
        * 100.0f, 300.0f, 150.0f,venue2
        */
        
        return concertDetails;
    }


    /**
     * Checks if the layout arguments provided in the command line are valid.
     *
     * @param args Array of strings representing command line arguments.
     * @return true if arguments are invalid, false otherwise.
     */
    private boolean checkLayoutArgs(String[] args) {
        if (args.length!=4){
            return true;
        }
        for (String arg : args){
            try{
                if (Integer.parseInt(arg) <=0){
                    return true;
                }}catch (NumberFormatException e){
                    return true;
                }
            }
            return false;
        // write code to return appropriate boolean if your command line arguments doesnt match 
        // as per specs.
    }

    /**
     * Provides a main menu interface for navigating different modes of the system.
     * Allows switching between customer and admin modes or exiting the application.
     *
     * @param concertDetails Details of concerts used throughout the system.
     * @param row Number of rows in the concert venue.
     * @param left Number of left seats per row.
     * @param mid Number of middle seats per row.
     * @param right Number of right seats per row.
     */
    public void mainMenu(ConcertDetails concertDetails, int row, int left, int mid, int right){
        // Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        // System.out.println();
        while(!exit){
            // System.out.println();
            System.out.println("Select an option to get started!");
            System.out.println("Press 1 to enter Customer mode");
            System.out.println("Press 2 to enter Admin mode");
            System.out.println("Press 3 to exit");
            System.out.print("> ");
            int choice = InputManager.SCANNER.nextInt();
            // System.out.println("lelelle: "+ choice);
            switch (choice){
                
                case 1:
                    //customer menu
                    User customer = new Customer();
                    customer.mainMenu(concertDetails, row, left, mid, right);
                    break;
                case 2:
                    //admin menu
                    User admin = new Admin(concertDetails);
                    admin.mainMenu(concertDetails, row, left, mid, right);
                    break;
                case 3:
                System.out.println("Goodbye from the Ticket Management System! See you next time!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Input.");
                    break;
            }
        }
        // scanner.close();
    }

    /**
     * Displays a welcome message to the user at the start of the application.
     */
    public void displayMessage(){
        System.out.println("Welcome to a revised version of Taylor Swift's Eras tour.");
    }
}


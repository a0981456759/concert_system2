/**
 * Represents the venue where concerts are held. This class manages the seating arrangement,
 * including tracking which seats are booked and allowing for seat selection.
 */


public class Venue {

    // define what data fields will be used to define the venue layout


    // define your constructors

    // write some methods to show the venue layout


    // write some methods to select seats and move up/down/left/right

    private String name;
    private int totalSeats;

    private int row;
    private int left;
    private int mid;
    private int right;

    private static final char EMPTY_SEAT = '_';
    private static final char SELECTED_SEAT = 'X';
    private static final char BOOKED_SEAT = 'B';
    private static final char AISLE = ' ';

    private char[][] seats;
    private int currentRow = 0;
    private int currentSeat = 0;

    // Constructor

    /**
     * Constructs a Venue with specified seating divisions and total seat count.
     *
     * @param name the name of the venue
     * @param row the number of rows in the venue
     * @param left the number of seats in the left section of each row
     * @param mid the number of seats in the middle section of each row
     * @param right the number of seats in the right section of each row
     * @param totalSeats the total number of seats in the venue
     */

    public Venue(String name, int row, int left, int mid, int right, int totalSeats){
        this.name= name;
        this.row = row;
        this.left = left;
        this.mid = mid;
        this.right = right;
        this.totalSeats = totalSeats;
    }
    // public Venue(String name, int totalSeats) {
    //     this.name = name;
    //     this.totalSeats = totalSeats;
    // }
    public Venue(int row, int left, int mid, int right){
        this.row = row;
        this.left = left;
        this.mid = mid;
        this.right = right;
        this.totalSeats = (left + mid + right) * row;
    }

    // Operation

    public void seatLayout(int row, int left, int mid, int right){
        for (int i=1; i<=row; i++){
            if (i ==1){
                System.out.println("["+"_".repeat(left)+" " + "_".repeat(mid) + " " + "_".repeat(right)+"]");
            }else{
                System.out.println("["+"_".repeat(left)+" " + "_".repeat(mid) + " " + "_".repeat(right)+"]");
            }
        }
    }

    /**
     * Initializes the seating layout for the venue with aisles.
     *
     * @param row the number of rows in the venue
     * @param left the number of seats in the left section of each row
     * @param mid the number of seats in the middle section of each row
     * @param right the number of seats in the right section of each row
     */
    public void initLayout(int row, int left, int mid, int right){
        seats = new char[row][left+mid+right+2];
        for (int i = 0; i < row; i++){
            for (int j =0; j<seats[i].length;j++){
                if (j==left || j==left+mid+1){
                    seats[i][j] = AISLE;
                }else{
                    seats[i][j] = EMPTY_SEAT;
                }
            }
        }
    }

    public int getSeatsBooked() {
        int count = 0;
        for (int i = 0; i < row; i++){
            for (int j =0; j < seats[i].length; j++){
                if (seats[i][j] == BOOKED_SEAT) {
                    count++;
                }
            }
        }
        return count;
    }

    public void TicketBooking() {
        for (int i = 0; i < row; i++){
            for (int j =0; j < seats[i].length; j++){
                if (seats[i][j] == SELECTED_SEAT) {
                    return;
                }
            }
        }

        for (int i = 0; i < row; i++){
            for (int j =0; j < seats[i].length; j++){
                if (seats[i][j] == EMPTY_SEAT) {
                    seats[i][j] = SELECTED_SEAT;
                    currentRow = i;
                    currentSeat = j;
                    return;
                }
            }
        }
    }

    /**
     * Displays the seating layout, showing available, selected, and booked seats.
     */
    public void displaySeat(){
        for(char[] row:seats){
            System.out.print("[");
            for(char seat: row){
                System.out.print(seat);
            }
            System.out.println("]");
        }
    }

     /**
     * Moves the seat selection cursor in the specified direction within the seating layout.
     * The method ensures the cursor does not move into an aisle or out of bounds.
     *
     * @param dir the direction to move ('W', 'A', 'S', 'D')
     */
    public void moveSeat(char dir){
        if (seats[currentRow][currentSeat] == SELECTED_SEAT) {
            seats[currentRow][currentSeat] = EMPTY_SEAT;
        }
        int maxRow = row;
        int maxSeat = left + mid + right + 2;
        // System.out.println("Current Row: "+currentRow);
        // System.out.println("Current Seat: "+currentSeat);
        // System.out.println("seat row length "+seats[currentRow].length);
        // System.out.println("seat length "+seats.length);
        // System.out.println("seat row length "+seats[currentRow].length);

        switch(dir){
            case 'w':
            case 'W':
                if (currentRow >0 && currentRow!=0){
                    currentRow--;
                    break;
                }else if (currentRow == 0){
                    System.out.println("Invalid move.");
                    break;
                }
            case 's':
            case 'S':
                if (currentRow<seats.length -1) {
                    currentRow++;
                    break;
                }else if (currentRow == seats.length-1){
                    System.out.println("Invalid move.");
                    break;
                }
            case 'a':
            case 'A':
                if (currentSeat>0 && currentSeat!=0){
                    currentSeat--;
                    break;
                }else if (currentSeat == 0){
                    System.out.println("Invalid move.");
                    break;
                }
            case 'd':
            case 'D':
                if (currentSeat < seats[currentRow].length-1 && currentSeat!=seats[currentRow].length-1){
                    currentSeat++;
                    break;
                }else if (currentSeat == seats[currentRow].length-1){
                    System.out.println("Invalid move.");
                    break;
                }
            case 'z':
            case 'Z':
                seats[currentRow][currentSeat] = BOOKED_SEAT;
                TicketBooking();
                return;
            default:
                System.out.println("Invalid Input.");
                break;
        }

        if (seats[currentRow][currentSeat] == AISLE){
            if (dir=='d'|| dir=='D'){
                currentSeat++;
            }else if (dir=='a' ||dir=='A'){
                currentSeat--;
            }
        }

        if (seats[currentRow][currentSeat] == BOOKED_SEAT){
            if (dir == 'w' || dir == 'W') {
                if (currentRow-1 <= 0) {
                    System.out.println("Invalid move.");
                } else {
                    currentRow--;
                }
            } else if (dir == 's'|| dir == 'S') {
                if (currentRow+1 >= maxRow) {
                    System.out.println("Invalid move.");
                } else {
                    currentRow++;
                }
            } else if (dir == 'd' || dir == 'D') {
                if (currentSeat+1 >= maxSeat) {
                    System.out.println("Invalid move.");
                } else {
                    currentSeat++;
                }
            } else if (dir == 'a' || dir == 'A') {
                if (currentSeat-1 <= maxSeat) {
                    System.out.println("Invalid move.");
                } else {
                    currentSeat--;
                }
            }
        }
        seats[currentRow][currentSeat] = SELECTED_SEAT;
    }

    /**
     * Resets the seat selection cursor to the default position.
     */
    public void resetSelection() {
        seats[currentRow][currentSeat] = EMPTY_SEAT;
        currentRow = 0;
        currentSeat = 0;
    }


    // Getters
    /**
     * Returns the name of the venue.
     * @return the name of the venue
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the total number of seats in the venue.
     * @return the total number of seats
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    public int getRow(){
        return row;
    }

    public int getTotalSeatPreRow(){
        return (left+mid+right);
    }

    public char[][] getSeats(){
        return seats;
    }

    // Setters
    /**
     * Sets the name of the venue.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }



    // Additional methods, like updating seating capacities or prices
    // public void updateSeatingCapacity(int rows, int leftSeats, int middleSeats, int rightSeats) {
    //     this.totalSeats = (leftSeats + middleSeats + rightSeats) * rows;
    // }

    // Method to display venue details
    // public void displayDetails() {
    //     System.out.println("Venue: " + name);
    //     System.out.println("Total Seats: " + totalSeats);
    //     System.out.println("Prices - Left: $" + leftSectionPrice + ", Middle: $" + middleSectionPrice + ", Right: $" + rightSectionPrice);
    // }
}


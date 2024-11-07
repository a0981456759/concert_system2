/**
 * Manages a collection of concerts, including adding, removing, and updating concerts.
 * This class also handles displaying concert details and updating ticket prices.
 */
public class ConcertDetails{

    // write some instance variables, construtors, methods to represent concert details

    // think about how will you manage multiple concerts using this class.

    private Concert[] concerts;
    private int numberOfConcerts;

    /**
     * Constructs a ConcertDetails object with a specified maximum number of concerts.
     * @param size the maximum number of concerts that can be managed.
     */
    public ConcertDetails(int size){
        concerts = new Concert[size];
        numberOfConcerts = 0;
    }

    /**
     * Adds a new concert to the collection if there is space available.
     * Also initializes the layout for the concert venue.
     * @param concert the concert to add.
     * @param row the number of rows in the concert venue.
     * @param left the number of seats in the left section per row.
     * @param mid the number of seats in the middle section per row.
     * @param right the number of seats in the right section per row.
     */
    public void addConcert(Concert concert, int row, int left, int mid, int right){
        if (numberOfConcerts < concerts.length){
            concert.getVenue().initLayout(row, left, mid, right);
            concerts[numberOfConcerts++] = concert;
        }else{
            System.out.println("Concerts are full");
        }
    }

    /**
     * Displays details of all concerts managed by this instance.
     */
    public void displayConcert(){
        
        System.out.println("Show Timings");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-15s%-15s%-30s%-15s%-15s%-15s\n", "#", "Date", "Artist Name", "Timing", "Venue Name", "Total Seats", "Seats Booked", "Seats Left");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < numberOfConcerts; i++) {
            Concert concert = concerts[i];
            System.out.printf("%-5d%-15s%-15s%-15s%-30s%-15d%-15d%-15d\n",
                (i + 1),
                concert.getDate(),
                concert.getArtistName(),
                concert.getTiming(),
                concert.getVenue().getName(),
                concert.getVenue().getTotalSeats(),
                concert.getVenue().getSeatsBooked(),
                concert.getVenue().getTotalSeats() - concert.getVenue().getSeatsBooked());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Gets the array of concerts.
     * @return the array of Concert objects.
     */
    public Concert[] getConcerts(){
        return concerts;
    }

    /**
     * Sets the array of concerts.
     * @param concerts the array of Concert objects to set.
     */
    public void setConcerts(Concert[] concerts){
        this.concerts = concerts;
    }

    /**
     * Gets the number of concerts currently managed.
     * @return the number of concerts.
     */
    public int getNumberOfConcerts(){
        return numberOfConcerts;
    }

    // public boolean isSeatsBooked(int concertIndex){
    //     Concert concert = this.concerts[concertIndex];
    //     return concert.getSeatsBooked()>0;
    // }

    /**
     * Checks if seats are booked for a specified concert.
     * @param concertIndex the index of the concert in the array.
     * @return true if any seats are booked, false otherwise.
     */
    public boolean isSeatsBooked(int concertIndex) {
    if (concertIndex >= 0 && concertIndex < numberOfConcerts) {
        Concert concert = this.concerts[concertIndex];
        return concert.getVenue().getSeatsBooked() > 0;
    } else {
        throw new IllegalArgumentException("Invalid concert index.");
    }
}

    /**
     * Removes a concert from the collection based on the index.
     * @param concertIndex the index of the concert to remove.
     */
    public void removeConcert(int concertIndex){
        if(concertIndex < 0 || concertIndex >= numberOfConcerts){
            throw new IllegalArgumentException("Invalid concert index.");
        }

        for (int i = concertIndex; i<numberOfConcerts-1;i++){
            concerts[i] = concerts[i+1];
        }

        concerts[numberOfConcerts-1] = null;

        numberOfConcerts--;
    }

    public void updateTicketPrice(int concertIndex, double leftPrice, double middlePrice, double rightPrice){
        if (concertIndex >= 0 && concertIndex < concerts.length){
            concerts[concertIndex].setLeftSectionPrice(leftPrice);
            concerts[concertIndex].setMiddleSectionPrice(middlePrice);
            concerts[concertIndex].setRightSectionPrice(rightPrice);
        }else{
            System.out.println("Invalid concert index. No price update");
        }
    }
}

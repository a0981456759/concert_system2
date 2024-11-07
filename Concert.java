/**
 * Represents a concert event, including details about the artist, timing, and associated venue.
 * This class manages the booking and pricing for different sections of seats at the venue.
 */
public class Concert {

    // create some instance variables here that represents a concert
    // write some constructors and getters/setters

    private String date;
    private String artistName;
    private String timing;
    private Venue venue;
    // private int seatBooked;
    private boolean[][] seatsBooked;
    private double leftSectionPrice;
    private double middleSectionPrice;
    private double rightSectionPrice;
    
    /**
     * Constructs a Concert object with specified details and seat pricing.
     *
     * @param date The date of the concert.
     * @param artistName The name of the artist performing.
     * @param timing The timing of the concert.
     * @param venue The venue where the concert will be held.
     * @param leftSectionPrice The price for seats in the left section.
     * @param middleSectionPrice The price for seats in the middle section.
     * @param rightSectionPrice The price for seats in the right section.
     */
    public Concert(String date, String artistName, String timing, Venue venue, double leftSectionPrice, double middleSectionPrice, double rightSectionPrice){
        this.date = date;
        this.artistName = artistName;
        this.timing = timing;
        this.venue = venue;
        this.leftSectionPrice = leftSectionPrice;
        this.middleSectionPrice = middleSectionPrice;
        this.rightSectionPrice = rightSectionPrice;
        this.seatsBooked = new boolean[venue.getRow()][venue.getTotalSeatPreRow()];
    }

    /**
     * Attempts to book a seat at a specified row and seat number.
     *
     * @param row The row number of the seat to book.
     * @param seat The seat number within the row to book.
     * @return true if the seat was successfully booked, false if the seat was already booked.
     */
    public boolean bookSeat(int row, int seat){
        if(!seatsBooked[row][seat]){
            seatsBooked[row][seat] = true;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Checks if a specific seat is booked.
     *
     * @param row The row number of the seat.
     * @param seat The seat number within the row.
     * @return true if the seat is booked, false otherwise.
     */
    public boolean isSeatsBooked(int row, int seat){
        return seatsBooked[row][seat];
    }

     /**
     * Calculates the total number of seats that have been booked for this concert.
     *
     * @return The total number of booked seats.
     */
    public int getTotalSeatsBooked(){
        int count = 0;
        for (int i=0;i < seatsBooked.length; i++){
            for (int j = 0; j< seatsBooked[i].length;i++){
                if (seatsBooked[i][j]){
                    count++;
                }
            }
        }
        return count;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public int getSeatsBooked() {
        return getTotalSeatsBooked();
        // return count;
    }

    // public void setSeatsBooked(int seatBooked) {
    //     this.seatBooked = seatBooked;
    // }

    public double getLeftSectionPrice() {
        return leftSectionPrice;
    }

    public void setLeftSectionPrice(double leftSectionPrice) {
        this.leftSectionPrice = leftSectionPrice;
    }

    public double getMiddleSectionPrice() {
        return middleSectionPrice;
    }

    public void setMiddleSectionPrice(double middleSectionPrice) {
        this.middleSectionPrice = middleSectionPrice;
    }

    public double getRightSectionPrice() {
        return rightSectionPrice;
    }

    public void setRightSectionPrice(double rightSectionPrice) {
        this.rightSectionPrice = rightSectionPrice;
    }
    

}

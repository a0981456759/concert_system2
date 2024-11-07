/**
 * Parents Class of Admin and Customer
 */

public abstract class User{

    /** This method represents the mainMenu that is implemented uniquely for customer and admin
     * you cannot change the name of the method but can add/remove parameters if you want to
     * A customer and admin both shares the concertdetails, thus concerts added by admin is visible to customer
     *
     * @param ConcertDetails store concert detail
     * @param row how many venue's row.
     * @param left how many seats of venue's left zone.
     * @param mid how many seats of venue's middle zone.
     * @param right how many seats of venue's right zone.
     * 
     */
     public abstract  void  mainMenu( ConcertDetails concertDetails,int row,int left,int mid,int right );

     /*
     * Can you think of any common methods between customer and admin?
     * Add them here
     */
}


package skipass.card;

import skipass.date.Date;
import skipass.card.WorkdayCard;


public class WorkdayCardForTripsNumber extends WorkdayCard{
    private NumberCard.TripCounts trips;
    private int tripCount;
    
    public WorkdayCardForTripsNumber(int id,NumberCard.TripCounts trips){
       super(id);
       this.tripCount = trips.getTripCount();
    }
    
    @Override
    public boolean checkCard(Date current){
        return super.checkCard(current) && this.checkTripCounts();
    }
    

    public boolean checkTripCounts(){
        if (tripCount <= 0) {
            return false;
        } else {
            tripCount--;
            return true;
        }
    }    
}

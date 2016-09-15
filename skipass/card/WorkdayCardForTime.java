package skipass.card;

import skipass.date.Date;
import skipass.card.WorkdayCard;


public class WorkdayCardForTime extends WorkdayCard{
    private TimeCard.Time time;
    private Date establishDate;
    
    public WorkdayCardForTime(int id,TimeCard.Time time,Date date){
        super(id);
        this.time = time;
        this.establishDate = date;
    }
    
    
    
    @Override
    public boolean checkCard(Date current) {
       return super.checkCard(current) && checkTime(Date.getCurrentDate());
    }
    
   
    public boolean checkTime(Date date){
        int delta = date.compareTo(establishDate);
        if (delta < 0 || delta >= this.time.getTime()){
            return false;
        }
        else {
            return true;
        }
    }
    
}

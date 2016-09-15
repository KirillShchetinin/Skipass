package skipass.card;

import skipass.date.Date;


public class WeekendCardForTime extends WeekendCard {
    private TimeCard.Time time;
    private Date establishDate;
    
    public WeekendCardForTime(int id,TimeCard.Time time,Date date){
        super(id);
        if (time.equals(TimeCard.Time.FiveDays)) {
            time = TimeCard.Time.TwoDays;
        }
        this.time = time;
        this.establishDate = date;
    }
    
    
    
    @Override
    public boolean checkCard(Date current) {
       return super.checkCard(current) && checkTime(Date.getCurrentDate());
    }
    
   
    public boolean checkTime(Date date){
        int delta = date.compareTo(establishDate);
        if (this.time.equals(TimeCard.Time.SecondHalfDay)) {
            delta += 4;
        }
        if (delta < 0 || delta >= this.time.getTime()){
            return false;
        }
        else {
            return true;
        }
    }
}

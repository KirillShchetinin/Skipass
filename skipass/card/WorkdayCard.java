package skipass.card;

import skipass.date.Date;


abstract public class WorkdayCard extends Card {
    
    public WorkdayCard(int id){
        super(id);
    }
    
    @Override
    public boolean checkCard(Date current) {
        return !current.isWeekend();
    };
}

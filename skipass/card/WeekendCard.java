
package skipass.card;


import skipass.date.Date;

abstract public class WeekendCard extends Card {

    public WeekendCard(int id) {
        super(id);
    }
    
    @Override
    public boolean checkCard(Date current){
        return current.isWeekend();
    };
    
}

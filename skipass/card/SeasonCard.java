
package skipass.card;

import skipass.date.Date;


public class SeasonCard extends Card{
    
    public SeasonCard(int id) {
        super(id);
    }
    
    @Override
    public boolean checkCard(Date current){
        return true;
    }
    
}

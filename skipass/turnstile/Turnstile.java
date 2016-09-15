package skipass.turnstile;
import skipass.systema.Systema;
import skipass.card.*;

public class Turnstile {
    Systema systema;
    public boolean checkCard(Card card){
        return this.systema.checkCard(card);
    }
    
    public Turnstile(Systema systema){
        this.systema = systema;
    }
}

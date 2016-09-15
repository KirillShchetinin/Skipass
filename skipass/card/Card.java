/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skipass.card;

import skipass.date.Date;

/**
 *
 * @author Asus
 */
abstract public class Card {
    protected int id;
    
    protected Card(int id) {
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    abstract public boolean checkCard(Date current);
    


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}

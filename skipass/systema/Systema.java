package skipass.systema;

import skipass.card.SeasonCard;
import skipass.date.Date;
import skipass.card.WorkdayCardForTripsNumber;
import skipass.card.WorkdayCardForTime;
import skipass.card.WeekendCardForTripsNumber;
import skipass.card.WeekendCardForTime;
import skipass.card.Card;
import java.util.Objects;
import skipass.card.TimeCard;
import skipass.card.NumberCard;



public class Systema {
    
    private class ExtendedCard{
        int cardID;
        boolean isBlocked;
        
        ExtendedCard(int id){
            this.cardID = id;
            this.isBlocked = false;
        }
           
        void block(){
            this.isBlocked = true;
        }
        
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + Objects.hashCode(this.cardID);
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
            final ExtendedCard other = (ExtendedCard) obj;
            if (!Objects.equals(this.cardID, other.cardID)) {
                return false;
            }
            return true;
        }
        
       
    }
    
    private MyArrayList events;
    private MyArrayList database;
    private int[] statCards;
    private int blockedCardsNumber;
    private int cardCount;
    
    private Card CardFactory(CardType.cardType cardtype, int id, Date date , String ... timeNumber){
        Card result = null;
        
        switch (cardtype){
            case WeekendTime: result = new WeekendCardForTime(id,
                    TimeCard.Time.valueOf(timeNumber[0]),date);break;
            case WeekendTrips: result = new WeekendCardForTripsNumber(id,
                    NumberCard.TripCounts.valueOf(timeNumber[0]));break;
            case WorkdayTime: result = new WorkdayCardForTime(id,
                    TimeCard.Time.valueOf(timeNumber[0]),date);break;
            case WorkdayTrips: result = new WorkdayCardForTripsNumber(id,
                    NumberCard.TripCounts.valueOf(timeNumber[0]));break;
            case Season: result = new SeasonCard(id);break;
            default: System.out.println("No such type of card"); break;
        }
        statCards[cardtype.ordinal()]++;
        if (result != null) {
            if (!cardtype.equals(CardType.cardType.Season)) {
            events.add("New card created: id " + result.getId() + "; type :" + cardtype +
                    "; feature :" + timeNumber[0] + "; date: " + date.toString());
            } else {
            events.add("New card created: id " + result.getId() + "; type :" + cardtype +
                    "; date: " + date.toString());  
            }
        } else {
            events.add("No such type");
        }
        return result;
    }
    
    public boolean blockCard(Card card){
        ExtendedCard temp = getExtendedCard(card);
        if (temp == null) {
            return false;
        }
        temp.block();
        this.blockedCardsNumber++;
        events.add("Card ID: " + card.getId() + " blocked");
        return true;
    }
    
    public  Card addCard(CardType.cardType cardtype,Date date, NumberCard.TripCounts number) {
        ExtendedCard current = new ExtendedCard(++cardCount);
        Card tempCard = null;
        if (cardtype.equals(CardType.cardType.WeekendTrips) || 
                cardtype.equals(CardType.cardType.WorkdayTrips)){
            tempCard = CardFactory(cardtype,cardCount,date,number.name());
        }
        if (tempCard != null) {
            this.database.add(current);
            return tempCard;
        } else {
            ErrorInvalidOptions();
            cardCount--;
            return null;
        }
    }
    
    public  Card addCard(CardType.cardType cardtype,Date date, TimeCard.Time time) {
        ExtendedCard current = new ExtendedCard(++cardCount);
        Card tempCard = null;
        if (cardtype.equals(CardType.cardType.WeekendTime) || 
                cardtype.equals(CardType.cardType.WorkdayTime)){
            tempCard = CardFactory(cardtype,cardCount,date,time.name());
        }
        if (tempCard != null) {
            this.database.add(current);
            return tempCard;
        } else {
            ErrorInvalidOptions();
            cardCount--;
            return null;
        }
    }
    
    private void ErrorInvalidOptions(){
        System.out.println("Invalid options of card");
    }
    
    public  Card addCard(CardType.cardType cardtype,Date date) {
        ExtendedCard current = new ExtendedCard(++cardCount);
        Card tempCard = null;
        if (cardtype.equals(CardType.cardType.Season)) {
            tempCard = CardFactory(cardtype,cardCount,date);
        }
        if (tempCard != null) {
            this.database.add(current);
            return tempCard;
        } else {
            ErrorInvalidOptions();
            cardCount--;
            return null;
        }
    }
    
    public Systema(){
        this.database = new MyArrayList();
        this.events = new MyArrayList();
        this.statCards = new int[5];
        this.blockedCardsNumber = 0;
        this.cardCount = 0;
    }
    
    public boolean checkCard(Card card){
        boolean result = card.checkCard(Date.getCurrentDate());
        if (result && !isBlocked(card)) {
            events.add("Card ID: " + card.getId() + " passed");
        } else {
            events.add("Card ID:" + card.getId() + " not passed");
        }
        return result;
    }
    
    private boolean isBlocked(Card card) {
        ExtendedCard temp = (ExtendedCard)database.get(card.getId() - 1);
        return temp.isBlocked;
    }
    
    public void eventsOut(){
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i));
        }
    }
    
    private ExtendedCard  getExtendedCard(Card card){
        ExtendedCard temp = new ExtendedCard(card.getId());
        int index = database.indexOf(temp);
        if (index == -1) {
            events.add("Unavailuable card");
            return null;
        }
        return (ExtendedCard)database.get(index);
    }
    
    public void getStatistics(){
        System.out.println("All Cards: " + database.size());
        for (CardType.cardType i: CardType.cardType.values() ) {
            System.out.println(i.toString() + " " + statCards[i.ordinal()]);
        }
    }
    
}

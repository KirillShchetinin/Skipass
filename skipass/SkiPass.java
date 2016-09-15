
package skipass;
import skipass.systema.Systema;
import skipass.turnstile.Turnstile;
import skipass.date.Date;
import skipass.card.*;
import skipass.systema.CardType;


public class SkiPass {

  
    public static void main(String[] args) {
        Systema systema = new Systema();
        Turnstile turnstile = new Turnstile(systema);
        Card testWeekendForNumber1 = systema.addCard(CardType.cardType.WeekendTrips,
                Date.getCurrentDate(),NumberCard.TripCounts.Ten);
        Card testWeekendForNumber2 = systema.addCard(CardType.cardType.WeekendTrips,
                Date.getCurrentDate(),NumberCard.TripCounts.Ten);
        for (int i = 0; i < 11; i++) {
            if (i == 5) {
                systema.blockCard(testWeekendForNumber2);
            }
            turnstile.checkCard(testWeekendForNumber1);
            turnstile.checkCard(testWeekendForNumber2);
        }
        
        Card testSeason = systema.addCard(CardType.cardType.Season,
                Date.getCurrentDate());
        for (int i = 0; i < 25; i++) {
            turnstile.checkCard(testSeason);
        }
        
        Card testWeekendForTime = systema.addCard(CardType.cardType.WeekendTime,
                Date.getCurrentDate(), TimeCard.Time.FirstHalfDay);
        for (int i = 0; i < 13; i++) {
            turnstile.checkCard(testWeekendForTime);
        }
        Card testWorkdayForTime = systema.addCard(CardType.cardType.WorkdayTime,
                Date.getCurrentDate(), TimeCard.Time.OneDay);
        for (int i = 0; i < 5; i++) {
            turnstile.checkCard(testWorkdayForTime);
        }
        Card testWeekendForTimeSecondHalfDay = systema.addCard(CardType.cardType.WeekendTime,
                Date.getCurrentDate(), TimeCard.Time.SecondHalfDay);
        turnstile.checkCard(testWeekendForTimeSecondHalfDay);
        Card testWorkdayForTrips = systema.addCard(CardType.cardType.WorkdayTrips,
                Date.getCurrentDate(), NumberCard.TripCounts.Twenty);
        for (int i = 0; i < 23; i++) {
            turnstile.checkCard(testWorkdayForTrips);
        }
        systema.eventsOut();
        systema.getStatistics();
    }
    
    
}

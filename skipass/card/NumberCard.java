package skipass.card;

public class NumberCard {
    public enum TripCounts{
        Ten(10), Twenty(20), Fifty(50), OneHundred(100);
        
        private final int count;
        
        private TripCounts(int trips){
            this.count = trips;
        }
        
        public int getTripCount(){
            return this.count;
        }
        
    }
}

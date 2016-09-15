
package skipass.card;


public class TimeCard {
    public enum Time{
        FirstHalfDay(4) , SecondHalfDay(4), OneDay(8), TwoDays(16), FiveDays(40);
        
        private final int hours;
        
        private Time(int hours){
            this.hours = hours;
        }
        
        public int getTime(){
            return hours;
        }        
        
        public Time parse(String str){
            return Time.valueOf(str);
        }
    }
}

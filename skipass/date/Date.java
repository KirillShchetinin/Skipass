package skipass.date;


public class Date {

    
    Integer year;
    Integer month;
    Integer day;
    Integer hour;
    
    public Date(int year, int month, int day, int hour) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
    }
    
    public int compareTo(Date o) {
        int deltaHours = this.hour - o.hour;
        int deltaDays = (this.day - o.day)*8;
        int deltaMonthes = (this.month - o.month)*8*30;
        int deltaYears = (this.year - o.year)*8*30*12;
        return deltaHours + deltaDays + deltaMonthes + deltaYears;
    }
    
    static public  Date getCurrentDate(){
        return new Date(0,0,6,0);
    }
    
    public boolean isWeekend(){
        int dayOfWeek = ((this.compareTo(new Date(0,0,0,0))+ 7) / 8) % 7;
        if (dayOfWeek < 5) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public String toString(){
        return this.year.toString() + "." + this.month.toString() + "." + 
                this.day.toString() + "." + this.hour.toString();
    }
    
    public void addHours(int k){
        this.hour += k;
    }
    
    public void decHours(int k){
        this.hour-=k;
    }
    
    public void addDay(){
        this.day++;
        if (this.day > 30) {
            this.day -= 30;
            this.month++;
        }
    }
    
}

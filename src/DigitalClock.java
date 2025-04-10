import java.util.Date;

public class DigitalClock extends Clock {



    public DigitalClock(int hour, int minute, int second, City city, ClockType clockType) {
        super(hour, minute, second, city);
    }


    public int getHour() {
        return hour;
    }

    public String set12HClock(int hour, int minute, int second) {
        int pmHour = hour;

        if (hour < 0 || hour > 12) {
            if (pmHour > 12) {
                pmHour = hour - 12;
                return String.format("%d:%02d:%02d PM", pmHour, minute, second);
            }
            throw new IllegalArgumentException("Godzina ma byc w zakresie 0-12, podana wartosc:" + hour);
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minuta musi byc w przedziale 0-59, podana wartosc:" + minute);
        }
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Sekunda musi byc w przedziale 0-59, podana wartosc:" + second);
        }
        if (hour == 0) {
            hour += 12;
            return String.format("%d:%02d:%02d PM", hour, minute, second);
        }
        if (getHour() == 12) {
            return String.format("%d:%02d:%02d PM", hour, minute, second);
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;

        return String.format("%d:%02d:%02d AM", hour, minute, second);

    }

    public String toString(ClockType clockType){
        if (clockType == ClockType.TWENTY_FOUR_HOUR){
            return super.toString();
        }

        String suffix = "AM";
        int tempHour = hour;
        if (tempHour > 12) {
            suffix = "PM";
            tempHour -= 12;

        }
        if (tempHour == 0){
            tempHour = 12;
        }
        return String.format("%d:%02d:%02d %s", tempHour, minute, second, suffix);
    }

    public enum ClockType{
        TWELVE_HOUR,
        TWENTY_FOUR_HOUR
    }

}

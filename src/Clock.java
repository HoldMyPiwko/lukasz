import java.text.SimpleDateFormat;

import java.time.LocalTime;
import java.util.Calendar;

public abstract class Clock {
    protected int hour;
    protected int minute;
    protected int second;
    private final City city;

    public Clock(int hour, int minute, int second, City city) {
        int summerZoneTime = Integer.parseInt(city.getSummerZoneTime());
        setHourWithZoneTime(summerZoneTime);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.city = city;
    }

    public void setTime(LocalTime time) {
        this.hour = time.getHour();
        this.minute = time.getMinute();
        this.second = time.getSecond();

    }
    public static String setCurrentTimeHour(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        return hour.format(calendar.getTime());
    }
    public static String setCurrentTimeMinute(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat minute= new SimpleDateFormat("mm");
        return minute.format(calendar.getTime());
    }
    public static String setCurrentTimeSecond(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat second = new SimpleDateFormat("ss");
        return second.format(calendar.getTime());
    }

    private void setHourWithZoneTime(int summerZoneTime) {
        this.hour = hour + summerZoneTime;
        if (hour < 0){
            hour = 24 + hour;
        }else if (this.hour >= 24) {
            hour = hour - 24;
        }
    }

    public void setCity(City city){

        int oldZoneTime = Integer.parseInt(this.city.getSummerZoneTime());
        int newZoneTime = Integer.parseInt(city.summerZoneTime);

        newZoneTime -= oldZoneTime;

        setHourWithZoneTime(newZoneTime);

    }


    @Override
    public String toString(){
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
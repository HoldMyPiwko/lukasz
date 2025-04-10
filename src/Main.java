import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Map<String, City> cities = City.parseFile();


        City warsaw = cities.get("Warszawa");
        City kijow = cities.get("Kijów");
        City losAngeles = cities.get("Los Angeles");
        City tokio = cities.get("Tokio");
        City wellington = cities.get("Wellington");

        Clock clock = new DigitalClock(12,4,2,warsaw, DigitalClock.ClockType.TWENTY_FOUR_HOUR);
        LocalTime localTime = LocalTime.of(12,0,0);


        System.out.println(warsaw.localMeanTime(localTime));
        System.out.println("zone time: " + localTime);
    }
}
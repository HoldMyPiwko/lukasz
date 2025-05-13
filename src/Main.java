import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Map<String, City> cities = City.parseFile();
        List<City> cityList = new ArrayList<>(cities.values());

        City baseCity = cities.get("Lublin");
        AnalogClock baseClock = new AnalogClock(10, 10, 55, baseCity);

        City.generateAnalogClocksSvg(cityList, baseClock);


    }
}
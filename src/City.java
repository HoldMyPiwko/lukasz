import java.io.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City {
    String capital;
    String summerZoneTime;
    String latitude;
    String longitude;

    public String getCapital() {
        return capital;
    }

    public String getSummerZoneTime() {
        return summerZoneTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public City(String capital, String summerZoneTime, String latitude, String longitude) {
        this.capital = capital;
        this.summerZoneTime = summerZoneTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    static File file = new File("C:\\Users\\koszo\\IntellijProject\\" +
                            "java-course\\out\\production\\zadaniaChat\\" +
                             "Kolokwium2024\\out\\production\\Kolokwium2024\\strefy.txt");

    private City parseLine() {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    return new City(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Map<String, City> parseFile() {
        Map<String, City> cityStringHashMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                City city = new City(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                cityStringHashMap.put(parts[0].trim(), city);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cityStringHashMap;
    }

        public LocalTime localMeanTime(LocalTime localTime) {
            int zoneOffset = Integer.parseInt(summerZoneTime);
            LocalTime utc = localTime.minusHours(zoneOffset);

            String[] coord = longitude.trim().split("\\s+");
            double deg = Double.parseDouble(coord[0]);
            String dir = coord.length > 1 ? coord[1] : "";

            double signedDeg = deg * (dir.equalsIgnoreCase("W") ? -1 : 1);

            long offsetSec = Math.round(signedDeg * 240.0);

            return utc.plusSeconds(offsetSec);
    }

    public int timeZoneFit(){
        LocalTime referenceTime = LocalTime.of(12,0);
        LocalTime localMean = localMeanTime(referenceTime);

        return Math.abs(localMean.toSecondOfDay() - referenceTime.toSecondOfDay());
    }

    public static int worstTimeZoneFIx(City a, City b){
        return Integer.compare(b.timeZoneFit(), a.timeZoneFit());
    }
    public static void generateAnalogClocksSvg(List<City> cities, AnalogClock baseClock) {
        String folderName = baseClock.toString().replace(":", "_"); // np. "14_27_45"
        File dir = new File(folderName);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (City city : cities) {
            baseClock.setCity(city);

            LocalTime now = LocalTime.now();
            LocalTime localTime = city.localMeanTime(now);
            baseClock.setTime(localTime);

            String fileName = city.getCapital().replaceAll("\\s+", "_") + ".svg";
            baseClock.toSvg(new File(dir, fileName).getPath());
        }
    }
}

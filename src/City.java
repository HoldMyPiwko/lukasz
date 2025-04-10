import java.io.*;
import java.time.LocalTime;
import java.util.HashMap;
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
        double longitude = 0;
        String direction = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {

                String[] longitudeParts = line.split(",");
                String coord = longitudeParts[3].trim();
                String[] coordParts = coord.split(" ");
                longitude = Double.parseDouble(coordParts[0]);
                direction = coordParts[1];
            }
            longitude = (int) Math.round(longitude / 15.0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (direction.equals("E")) {
            return localTime.plusHours((long) longitude);
    } else  {
            return localTime.minusHours((long) longitude);
        }
    }

    public void displayInfo () {
        System.out.println("Summer zone time: " + summerZoneTime);
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
    }

}

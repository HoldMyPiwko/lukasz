import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SvgClockGenerator extends Clock {

    public SvgClockGenerator(int hour, int minute, int second, City city) {
        super(hour, minute, second, city);
    }

    public void generateSvgClock(String filename) {
        int width = 400;
        int height = 400;
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = 180;

        StringBuilder svg = new StringBuilder();

        svg.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        svg.append("<svg width=\"" + width + "\" height=\"" + height + "\" xmlns=\"http://www.w3.org/2000/svg\">\n");
        svg.append("  <circle cx=\"" + centerX + "\" cy=\"" + centerY + "\" r=\"" + radius + "\" stroke=\"black\" stroke-width=\"4\" fill=\"white\" />\n");

        for (int hour = 0; hour < 12; hour++) {
            double angle = Math.toRadians((hour * 30) - 90);
            int x1 = (int) (centerX + Math.cos(angle) * (radius - 10));
            int y1 = (int) (centerY + Math.sin(angle) * (radius - 10));
            int x2 = (int) (centerX + Math.cos(angle) * (radius - 30));
            int y2 = (int) (centerY + Math.sin(angle) * (radius - 30));

            svg.append("  <line x1=\"").append(x1).append("\" y1=\"").append(y1).append("\" x2=\"").append(x2).append("\" y2=\"").append(y2).append("\" stroke=\"black\" stroke-width=\"3\" />\n");
        }

        svg.append("</svg>");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(svg.toString());
            System.out.println("✅ Zegar SVG zapisany do pliku: " + filename);
        } catch (IOException e) {
            System.err.println("💥 Błąd zapisu: " + e.getMessage());
        }
    }
}

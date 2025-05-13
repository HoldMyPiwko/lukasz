import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

    public class AnalogClock extends Clock {

        private final List<ClockHand> hands;

        public AnalogClock(int hour, int minute, int second, City city) {
            super(hour, minute, second, city);
            int centerX = 150;
            int centerY = 150;

            this.hands = Arrays.asList(
                    new HourHand(centerX, centerY, 60),
                    new MinuteHand(centerX, centerY, 90),
                    new SecondHand(centerX, centerY, 110)
            );

            updateHands();
        }

        private void updateHands() {
            LocalTime currentTime = LocalTime.of(hour, minute, second);
            for (ClockHand hand : hands) {
                hand.setTime(currentTime);
            }
        }

        @Override
        public String toString() {
            updateHands();
            return super.toString();
        }

        public void toSvg(String path) {
            int size = 300;
            int centerX = size / 2;
            int centerY = size / 2;
            int radius = size / 2;

            StringBuilder svg = new StringBuilder();
            svg.append(String.format("""
            <svg width="%d" height="%d" xmlns="http://www.w3.org/2000/svg">
                <circle cx="%d" cy="%d" r="%d" stroke="black" stroke-width="4" fill="white"/>
            """, size, size, centerX, centerY, radius - 5));

            for (int i = 0; i < 12; i++) {
                double angle = Math.toRadians(i * 30);
                int x1 = (int) (centerX + (radius - 10) * Math.sin(angle));
                int y1 = (int) (centerY - (radius - 10) * Math.cos(angle));
                int x2 = (int) (centerX + (radius - 20) * Math.sin(angle));
                int y2 = (int) (centerY - (radius - 20) * Math.cos(angle));

                svg.append(String.format("""
                <line x1="%d" y1="%d" x2="%d" y2="%d" stroke="black" stroke-width="2" />
                """, x1, y1, x2, y2));
            }

            for (ClockHand hand : hands) {
                svg.append(hand.toSvg());
            }

            svg.append("</svg>");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                writer.write(svg.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

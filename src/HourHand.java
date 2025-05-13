import java.time.LocalTime;

public class HourHand extends ClockHand {
    private double angle = 0;

    public HourHand(int centerX, int centerY, int length) {
        super(centerX, centerY, length);
    }

    @Override
    public void setTime(LocalTime time) {
        int hour = time.getHour() % 12;
        int minute = time.getMinute();
        int second = time.getSecond();
        this.angle = (hour + minute / 60.0 + second / 3600.0) * 30.0;
    }

    @Override
    public String toSvg() {
        int x2 = centerX;
        int y2 = centerY - length;

        return String.format("""
                <line x1="%d" y1="%d" x2="%d" y2="%d"
                      stroke="black" stroke-width="5"
                      transform="rotate(%.2f %d %d)" />
                """, centerX, centerY, x2, y2, angle, centerX, centerY);
    }
}

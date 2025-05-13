import java.time.LocalTime;

public class MinuteHand extends ClockHand{
    private double angle = 0;

    public MinuteHand(int centerX, int centerY, int length) {
        super(centerX, centerY, length);
    }

    @Override
    public void setTime(LocalTime time) {
        int minute = time.getMinute();
        int second = time.getSecond();
        this.angle = (minute + second / 60.0) * 6.0;
    }

    @Override
    public String toSvg() {
        int x2 = centerX;
        int y2 = centerY - length;

        return String.format("""
            <line x1="%d" y1="%d" x2="%d" y2="%d"
                  stroke="blue" stroke-width="3"
                  transform="rotate(%.2f %d %d)" />
            """, centerX, centerY, x2, y2, angle, centerX, centerY);
    }
}

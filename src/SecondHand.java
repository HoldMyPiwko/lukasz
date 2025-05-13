import java.time.LocalTime;

public class SecondHand extends ClockHand {
    private int angle = 0;

    public SecondHand(int centerX, int centerY, int length) {
        super(centerX, centerY, length);
    }

    @Override
    public void setTime(LocalTime time) {
        int second = time.getSecond();
        this.angle = second * 6;
    }

    @Override
    public String toSvg() {
        int x2 = centerX;
        int y2 = centerY - length;

        return String.format("""
                <line x1="%d" y1="%d" x2="%d" y2="%d"
                      stroke="red" stroke-width="2"
                      transform="rotate(%d %d %d)" />
                """, centerX, centerY, x2, y2, angle, centerX, centerY);
    }
}
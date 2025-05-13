import java.time.LocalTime;

public abstract class ClockHand {

        protected int centerX;
        protected int centerY;
        protected int length;

        public ClockHand(int centerX, int centerY, int length) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.length = length;
        }
        public abstract void setTime(LocalTime time);

        public abstract String toSvg();
}

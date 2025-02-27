import static java.lang.Math.pow;

public class Segment {
    public Point a;
    public Point b;

    public double length(){
        return Math.sqrt(pow(a.x-b.x, 2) + pow(a.y-b.y, 2));
    }
}
Segment[] tab = new Segment[2];
tab[0] = seg;

tab[1] = new Segment();
tab[1].a


public static Segment maxSegment(Segment[] arr) {
    if (arr.length == 0)
        return null;

    Segment max = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i].length() > max.length()) {
            max = arr[i];
        }
    }
}
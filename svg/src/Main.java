
public class Main {
    public static void main(String[] args) {

        Point p = new Point();
        p.x = 5.0;
        p.y = 3.0;
        System.out.println("P=" + p);

        p.translate(5, -5);
        System.out.println();


        Segment seg = new Segment();

        seg.a = new Point();
        seg.b = new Point();
        seg.a.x = 1.0;
        seg.a.y = 1.0;
        seg.b.x = 5;
        seg.b.y = 4;
        System.out.println("seg len: " + seg.length());

    }

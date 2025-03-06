public class Point {
    public double x;
    public double y;

    public String toString(){
        return  "("+x+", "+x+")";
    }

    public void translate(double dx, double dy){
        x += dx;
        this.y += dy;
    }


}

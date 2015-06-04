package square;

public class Triangle extends FigureParent {
    double p, h;
    public Triangle (double _p, double _h)
    {
        p = _p;
        h = _h;
    }
    public double square ()
    {
        return p * h * 0.5;
    }
    public String type ()
    {
        return "TRIANGLE";
    }
}
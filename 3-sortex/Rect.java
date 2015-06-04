package square;

public class Rect extends FigureParent {
    double a, b;
    public Rect (double _a, double _b)
    {
        a = _a;
        b = _b;
    }
    public double square ()
    {
        return a * b;
    }
    public String type ()
    {
        return "RECT";
    }
}
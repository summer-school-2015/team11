package square;

public class Rect extends Figure {
    double a, b;
   // String type = "RECT";
    public Rect (double _a, double _b, String _type)
    {
        super(_type);
        a = _a;
        b = _b;
    }
    public double square ()
    {
        return a * b;
    }
}
package square;

public class Triangle extends Figure {
    double p, h;
   // String type = "TRIANGLE";
    public Triangle (double _p, double _h, String _type)
    {
        super(_type);
        p = _p;
        h = _h;
    }
    public double square ()
    {
        return p * h * 0.5;
    }
}
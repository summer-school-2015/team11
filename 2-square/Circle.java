package square;

public class Circle extends Figure {
    double r;
    double pi = 3.1415926;
  //  String type = "CIRCLE";
    public Circle (double _r, String _type)
    {
        super(_type);
        r = _r;
    }
    public double square ()
    {
        return pi * r * r;
    }
}

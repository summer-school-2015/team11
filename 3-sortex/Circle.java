package square;

public class Circle extends FigureParent{
    double r;
    double pi = 3.1415926;
    public Circle (double _r)
    {
        r = _r;
    }
    public double square ()
    {
        return pi * r * r;
    }
    public String type ()
    {
        return "CIRCLE";
    }
}

package square;

import java.util.Comparator;

public class Figure {
    String type;
    public Figure (String s)
    {
        type = s;
    }
    public double square ()
    {
        return 0;
    }
    public static Comparator<Figure> MyComparator = new Comparator<Figure>() {
        public int compare(Figure f1, Figure f2) {
            return (int) (f1.square() - f2.square());
        }
    };
}


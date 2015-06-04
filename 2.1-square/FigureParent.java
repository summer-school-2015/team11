package square;

import java.util.Comparator;

public class FigureParent implements Figure{
    public double square()
    {
        return 1;
    }
    public String type()
    {
        return "";
    }
    public static Comparator<FigureParent> MyComparator = new Comparator<FigureParent>() {
        public int compare(FigureParent f1, FigureParent f2) {
            return (int) (f1.square() - f2.square());
        }
    };
}

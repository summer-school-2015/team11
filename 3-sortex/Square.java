package square;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Square {
    public static void main (String[] args) throws IOException {
        try {
            Scanner in = new Scanner(new File("in.txt"));

            String fig;
            int n = in.nextInt();
            ArrayList<FigureParent> figures = new ArrayList<FigureParent>();
            for (int i = 0; i < n; i++) {
                fig = in.next();
                if (fig.charAt(0) == 'C')
                    figures.add(new Circle(in.nextDouble()));
                if (fig.charAt(0) == 'R')
                    figures.add(new Rect(in.nextDouble(), in.nextDouble()));
                if (fig.charAt(0) == 'T')
                    figures.add(new Triangle(in.nextDouble(), in.nextDouble()));
            }

            figures.sort(FigureParent.MyComparator);

            for (int i = 0; i < n; i++)
                System.out.println(figures.get(i).type()+ " " + figures.get(i).square());
            in.close();
        }
        catch (FileNotFoundException | NoSuchElementException error)
        {
            System.out.print(error);
        }
    }
}

package square;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Square {
    public static void main (String[] args) throws IOException {
        try {
            Scanner in = new Scanner(new File("in.txt"));

            String fig;
            int n = in.nextInt();
            Figure figures[] = new Figure[n];
            for (int i = 0; i < n; i++) {
                fig = in.next();
                if (fig.charAt(0) == 'C')
                    figures[i] = new Circle(in.nextDouble(), "CIRCLE");
                if (fig.charAt(0) == 'R')
                    figures[i] = new Rect(in.nextDouble(), in.nextDouble(), "RECT");
                if (fig.charAt(0) == 'T')
                    figures[i] = new Triangle(in.nextDouble(), in.nextDouble(), "TRIANGLE");
            }
            Arrays.sort(figures, Figure.MyComparator);
            for (int i = 0; i < n; i++)
                System.out.println(figures[i].type + " " + figures[i].square());
            in.close();
        }
        catch (FileNotFoundException | NoSuchElementException error)
        {
            System.out.print(error);
        }
    }
}

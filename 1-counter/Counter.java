package team11.counter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Counter {
    static int strcnt = -1;
    static int wrdcnt = 0;
    static int smbcnt = 0;
    static String inFile, outFile;
    public static void main (String[] args) throws IOException {

        if (args.length == 0)
        {
            ReadConsole();
            PrintConsole();
        }
        int cnt = args.length;
        if (cnt != 0 && args[0].charAt(0) == 'i')
        {
            inFile = args[0].substring(3);
            ReadFile();
            if (cnt == 2 && args[1].charAt(0) == 'o') {
                outFile = args[1].substring(3);
                ReadFile();
            }
        }
        if (cnt == 1 && args[0].charAt(0) == 'o')
        {
            outFile = args[0].substring(3);
            ReadFile();
        }
    }
    static void ReadFile () throws IOException {
        String s = "start";
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        StringBuilder sb = new StringBuilder();

        while (!s.equals("")) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            s = sb.toString();
            strcnt++;
            smbcnt += s.length();
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) == ' ')
                    wrdcnt++;
        }

    }
    static void ReadConsole() throws IOException {
        String s = "start";
        String m = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!s.equals("")) {
            s = br.readLine();
            m += s + " ";
                strcnt++;
                smbcnt += s.length();
                for (int i = 0; i < s.length(); i++)
                    if (s.charAt(i) == ' ')
                        wrdcnt++;
            }
        StringTokenizer st = new StringTokenizer(m);
        wrdcnt = st.countTokens();

    }
    static void PrintConsole() throws IOException {
        System.out.println(strcnt + " " + wrdcnt + " " + smbcnt);
    }
    static void PrintFile() throws IOException {
        PrintWriter out = new PrintWriter(outFile);
        out.println(strcnt + " " + wrdcnt + " " + smbcnt);
    }
}

package counter;
import java.io.*;
import java.util.StringTokenizer;

public class Counter {
    static int strcnt = -1;
    static int wrdcnt = 0;
    static int smbcnt = 0;
    static FileInputStream inFile;
    static String outFile;
    public static void main (String[] args) throws IOException {
        int argcnt = args.length;
        if (argcnt == 0)
        {
            ReadConsole();
            PrintConsole();
        }
        if (argcnt == 2)
        {
            inFile = new FileInputStream(args[0].substring(3));
            ReadFile();
            outFile = args[1].substring(3);
            PrintFile();
        }
        if (argcnt == 1)
            if (args[0].charAt(0) == 'o')
            {
                outFile = args[0].substring(3);
                ReadConsole();
                PrintFile();
            }
            else
            {
                inFile = new FileInputStream(args[0].substring(3));
                ReadFile();
                PrintConsole();
            }
    }
    static void ReadFile () throws IOException {
        String s = "start";
        String all = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inFile));
        while (!s.equals("")) {
            s = br.readLine();
            all += s + " ";
            strcnt++;
            smbcnt += s.length();
        }
        StringTokenizer st = new StringTokenizer(all);
        wrdcnt = st.countTokens();
    }
    static void ReadConsole() throws IOException {
        String s = "start";
        String all = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!s.equals("")) {
            s = br.readLine();
            all += s + " ";
            strcnt++;
            smbcnt += s.length();
        }
        StringTokenizer st = new StringTokenizer(all);
        wrdcnt = st.countTokens();
    }
    static void PrintConsole() throws IOException {
        System.out.println(strcnt + " " + wrdcnt + " " + smbcnt);
    }
    static void PrintFile() throws IOException {
        PrintWriter out = new PrintWriter(outFile);
        out.println(strcnt + " " + wrdcnt + " " + smbcnt);
        out.close();
    }
}


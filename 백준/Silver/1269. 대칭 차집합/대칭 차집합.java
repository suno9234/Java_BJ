
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        HashSet<Integer> a = new HashSet<Integer>();
        HashSet<Integer> a2 = new HashSet<Integer>();
        HashSet<Integer> b = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            int now = scanner.nextInt();
            a.add(now);
            a2.add(now);
        }
        
        for (int i = 0; i < m; i++) {
            b.add(scanner.nextInt());
        }
        a.addAll(b);
   
        int answer =a.size();
        //System.out.println(a.toString());
        a2.retainAll(b);
        answer -= a2.size();
        //System.out.println(a2.toString());

        System.out.println(answer);
    }
}


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        // b < 10 0000 0000  10억
        int count = 1;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(a);
        while(!queue.isEmpty()){
            count+=1;
            Queue<Integer> nextQueue = new LinkedList<Integer>();
            while(!queue.isEmpty()) {
                int now = queue.poll();
                //System.out.println(now);
                if (now * 2L <= b ) {
                    nextQueue.add(now * 2);
                    if (now * 2L == b) {
                        System.out.println(count);
                        return;
                    }
                }
                if (now * 10L + 1 <= b ) {
                    nextQueue.add(now * 10 + 1);
                    if (now * 10L + 1 == b) {
                        System.out.println(count);
                        return;
                    }
                }
            }
            queue = nextQueue;
        }
        System.out.println(-1);
    }
}

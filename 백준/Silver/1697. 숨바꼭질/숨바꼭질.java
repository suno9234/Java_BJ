import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int [] time = new int[100001];
        Arrays.fill(time,999999);
        time[n] = 0;
        Queue <Integer> queue = new LinkedList<Integer>();
        queue.add(n);
        while(!queue.isEmpty()){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int i : queue){
                if(i+1<100001 && time[i+1] == 999999 && !temp.contains(i+1)) {
                    time[i+1] = time[i]+1;
                    temp.add(i + 1);
                }
                if(i-1 >=0 && time[i-1] == 999999 && !temp.contains(i-1)) {
                    time[i-1] = time[i] +1;
                    temp.add(i - 1);
                }
                if(i*2 <100001 && time[i*2] == 999999 && !temp.contains(i*2)) {
                    time[i*2] = time[i]+1;
                    temp.add(i * 2);
                }
            }
            queue.clear();
            queue.addAll(temp);
        }
        System.out.println(time[k]);
    }
}

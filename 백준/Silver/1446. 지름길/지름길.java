import java.util.*;
import java.io.*;
public class Main {
    static int n, d,answer;
    static int [] minDist;
    static boolean [] v;
    static List<int[]>[] graph;
    static final int MAX_VALUE = 99999;
    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        d = Integer.parseInt(tokens[1]);
        minDist = new int[d+1];
        graph = new List[d+1];
        v = new boolean[d+1];
        Arrays.fill(minDist,MAX_VALUE);
        for(int i = 0 ; i < d+1; i++){
            graph[i] = new ArrayList<>();
            graph[i].add(new int[]{i+1,1});
        }
        for(int i = 0 ; i < n ; i++){
            tokens = bufferedReader.readLine().split(" ");
            int s = Integer.parseInt(tokens[0]);
            int e = Integer.parseInt(tokens[1]);
            int w = Integer.parseInt(tokens[2]);
            if(e <= d)  graph[s].add(new int[]{e,w});
        }
        // 다익스트라
        PriorityQueue<int [] >priorityQueue = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1[1],o2[1])));
        priorityQueue.add(new int[]{0,0});
        // 목적지 , dist
        while(!priorityQueue.isEmpty()){
            int [] now = priorityQueue.poll();
            int pos = now[0];
            int dist = now[1];
            if(v[pos])continue;
            v[pos] = true;
            minDist[pos] = dist;
            if(pos == d ){
                System.out.println(dist);
                break;
            }
            for(int [] next: graph[pos]){
                int np = next[0];
                int nd = next[1];
                if(!v[np] && minDist[np] > dist+nd){
                    priorityQueue.add(new int[]{np,dist+nd});
                }
            }
        }
    }

}

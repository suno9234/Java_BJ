import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static int p, w, c, v;
    // p개의 지점과 w개의 길
    // 양방향, 각 길마다 너비 존재
    // 경로 상에 있는 길 중 너비가 가장 좁은 길의 너비를 최대화
    static List[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        p = Integer.parseInt(tokens[0]);
        w = Integer.parseInt(tokens[1]);
        tokens = br.readLine().split(" ");
        c = Integer.parseInt(tokens[0]);
        v = Integer.parseInt(tokens[1]);
        graph = new List[p + 1];
        for (int i = 0; i <= p; i++) {
            graph[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < w; i++) {
            tokens = br.readLine().split(" ");
            int start = Integer.parseInt(tokens[0]);
            int end = Integer.parseInt(tokens[1]);
            int width = Integer.parseInt(tokens[2]);
            graph[start].add(new int[]{end,width});
            graph[end].add(new int[]{start,width});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2)->-Integer.compare(o1[1],o2[1]));
        boolean [] visited = new boolean[p+1];
        visited[p] = true;
        int answer = Integer.MAX_VALUE;
        List<int[]> nextNodes = graph[c];
        for(int [] n : nextNodes) {
            pq.add(n);
        }
        while(!pq.isEmpty()){
            int [] cur = pq.poll();
            int next = cur[0];
            int width = cur[1];
            answer = Math.min(answer,width);
            visited[next] = true;
            if(next == v){
                break;
            }
            nextNodes = graph[next];
            for(int [] n : nextNodes) {
                if(!visited[n[0]]){
                    pq.add(n);
                }
            }
        }
        System.out.println(answer);
    }
}

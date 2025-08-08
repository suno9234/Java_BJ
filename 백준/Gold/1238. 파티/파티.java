import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main{
    static int n, m, x, answer;
    static int[] dist;
    static int[] dist_reverse;
    static List<int[]>[] graph;
    static List<int[]>[] graph_reverse;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        x = Integer.parseInt(tokens[2]) - 1;
        dist = new int[n];
        dist_reverse = new int[n];
        graph = new List[n];
        graph_reverse = new List[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<int[]>();
            graph_reverse[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE / 2;
            dist_reverse[i] = Integer.MAX_VALUE / 2;
        }

        for (int i = 0; i < m; i++) {
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]) - 1;
            int b = Integer.parseInt(tokens[1]) - 1;
            int t = Integer.parseInt(tokens[2]);
            graph[a].add(new int[]{b, t});
            graph_reverse[b].add(new int[]{a, t});
        }

        dijkstra(x,graph,dist);
        dijkstra(x,graph_reverse,dist_reverse);

        for(int i = 0; i < n; i++){
            if(i == x) continue;
            answer = Math.max(answer,dist[i]+dist_reverse[i]);
        }

        System.out.println(answer);
    }

    static void dijkstra(int start, List<int[]>[] graph,int [] dist) {
        // o1 = [ 번호, 거리 ]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        });
        pq.add(new int[]{start, 0});
        int cnt = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now_number = cur[0];
            int now_dist = cur[1];
            if (cnt == n ) {
                return;
            }
            if (dist[now_number] != Integer.MAX_VALUE/2) {
                continue;
            }
            dist[now_number] = now_dist;
            List<int[]> nexts = graph[now_number];
            for (int[] next : nexts) {
                int next_number = next[0];
                int next_dist = next[1];
                if (dist[next_number] > now_dist + next_dist) {
                    pq.add(new int[]{next_number, now_dist + next_dist});
                }
            }
            cnt++;
        }

    }
}

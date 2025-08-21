import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static List[] graph;
    static int[] in;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new List[n + 1];
        in = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int last = 0;
            for (int j = 0; j < num; j++) {
                int now = Integer.parseInt(st.nextToken());
                if (last != 0) {
                    graph[last].add(now);
                    in[now]++;
                }
                last = now;
            }
        }

        StringBuilder sb = new StringBuilder();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {
            int now = queue.poll();
            count++;
            sb.append(now).append("\n");
            List<Integer> nexts = graph[now];
            for(int next : nexts){
                in[next]--;
                if (in[next] == 0) {
                    queue.add(next);
                }
            }
        }
        if(count < n){
            System.out.println(0);
            return;
        }

        System.out.println(sb);
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int answer = 0;
    static int m,n;
    static int [] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            String[] tokens = br.readLine().split(" ");
            m = Integer.parseInt(tokens[0]);
            n = Integer.parseInt(tokens[1]);
            if(m == 0){
                break;
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
            int sum = 0;
            for (int i = 0; i < n; i++) {
                tokens = br.readLine().split(" ");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                int z = Integer.parseInt(tokens[2]);
                pq.add(new int[]{x, y, z});
                sum += z;
            }
            int cnt = 0;
            int val = 0;
            parent = new int[m];
            Arrays.setAll(parent, x -> x);
            while (!pq.isEmpty()) {
                int[] p = pq.poll();
                int x = p[0];
                int y = p[1];
                int z = p[2];
                if (union(x, y)) {
                    sum -= z;
                    val += z;
                    if (cnt++ == m - 1) {
                        break;
                    }
                }

            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb.toString());
    }
    static boolean union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa < pb){
            parent[pb] = pa;
            return true;
        }else if(pa > pb){
            parent[pa] = pb;
            return true;
        }
        return false;
    }
    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}

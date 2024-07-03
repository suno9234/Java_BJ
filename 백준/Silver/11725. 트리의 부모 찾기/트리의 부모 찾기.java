import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int answer;
    static int n;
    static List<Integer>[] graph;
    static int [] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        graph = new List[n+1];
        for(int i = 1 ; i < n+1 ; i++){
            graph[i] = new ArrayList<>();
        }
        parent = new int[n+1];
        parent[0] = -1;
        parent[1] = -1;
        for(int i = 0 ; i < n-1; i++){
            String [] tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            graph[a].add(b);
            graph[b].add(a);
        }
        ArrayDeque<int []> q = new ArrayDeque<>();
        for(int next : graph[1]){
            q.add(new int[]{1,next});
        }
        while(!q.isEmpty()){
            int [] now = q.poll();
            int p = now[0];
            int c = now[1];
            parent[c] = p;
            // p->c->next
            for(int next : graph[c]){
                if(next!= p){
                    q.add(new int[]{c,next});
                }
            }

        }
        for(int i = 2 ; i < n+1 ; i++){
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

}

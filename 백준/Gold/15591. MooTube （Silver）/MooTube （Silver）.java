import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,q;
    static List[] graph;
    static StringBuilder sb;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        // n <= 5000 , q <= 5000
        // q 개의 질문에 대해 k일때 각각 v에서 추천되는 동영상의 개수는?
        graph = new List[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
        }
        for(int i = 0 ; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            graph[p].add(new int[]{q,r});
            graph[q].add(new int[]{p,r});
        }
        for(int i = 0 ; i < q ; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            solve(k,v);
        }
        System.out.println(sb);
    }
    static void solve(int k , int v){
        boolean [] visited = new boolean[n+1];
        visited[v] = true;
        ArrayDeque<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{v,9999999});
        int answer = -1;
        while(!queue.isEmpty()){
            int  [] now = queue.poll();
            int nowV = now[0];
            int nowK = now[1];
            if(nowK >= k){
                answer++;
            }
            List<int[]> nexts = graph[nowV];
            for(int[] next : nexts){
                int nv = next[0];
                int nk = next[1];
                if(!visited[nv]){
                    visited[nv] = true;
                    nk = Math.min(nowK,nk);
                    queue.add(new int[]{nv,nk});
                }
            }
        }
        sb.append(answer).append("\n");
    }
}

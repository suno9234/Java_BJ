import java.util.*;
import java.io.*;

public class Main {

    static int n,answer;
    static int []  population;
    static List<Integer> [] graph;
    static boolean [] v ;
    public static void main(String[] args) throws Exception{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        n = Integer.parseInt(bufferedReader.readLine());
        population = new int[n+1];
        tokens = bufferedReader.readLine().split(" ");
        for(int i = 0 ; i < n ; i++){
            population[i+1] = Integer.parseInt(tokens[i]);
        }
        graph = new List[n+1];
        v = new boolean[n+1];
        answer = Integer.MAX_VALUE;
        for(int i = 0 ; i < n; i++){
            graph[i+1] = new LinkedList<>();
        }
        for(int i = 0 ; i < n ; i++){
            tokens = bufferedReader.readLine().split(" ");
            int c = Integer.parseInt(tokens[0]);
            for (int j = 0; j < c; j++) {
                graph[i + 1].add(Integer.parseInt(tokens[j + 1]));
            }
        }

        subs(1);
        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }

    }

    static int aCnt, bCnt , aSum , bSum;
    static int [] a,b;
    static boolean []  tempV;
    static void subs(int cnt) {
        if(cnt == n+1){
            return;
        }
        a = new int[n+1];
        b = new int[n+1];
        aSum = 0;
        bSum = 0;
        tempV = new boolean[n+1];
        aCnt = 0;
        bCnt = 0;
        for(int i = 1 ; i < n+1; i++){
            if(v[i]){
                a[aCnt++] = i;
            }else{
                b[bCnt++] = i;
            }
        }
        if(a[0]!= 0 && b[0]!= 0){
            aSum = dfs(a[0],a,aCnt,0);
            bSum = dfs(b[0],b,bCnt,0);
            int flag = 0;
            for(int i = 1 ; i < n+1 ; i ++){
                if(!tempV[i]){
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                answer = Math.min(answer ,Math.abs(aSum-bSum));
            }
        }


        v[cnt] = true;
        subs(cnt + 1);
        v[cnt] = false;
        subs(cnt + 1);
    }
    static int dfs(int now ,int [] arr , int aCnt , int pop){
        tempV[now] = true;
        pop = population[now];
        for(int next : graph[now]){
            if(tempV[next]) continue;
            int flag = 0;
            for(int i = 0 ; i < aCnt; i++){
                if(arr[i] == next){
                    flag = 1 ;
                    break;
                }
            }
            if(flag == 1){
                pop += dfs(next,arr,aCnt,pop);
            }
        }
        return pop;
    }
}

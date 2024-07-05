import java.io.*;
import java.util.*;
class Solution {
    static Map<Integer, Integer> map = new HashMap<>();
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static boolean [] v;
    static int [] answer;
    public int[] solution(int[][] edges) throws Exception{
        int max = 0;
        answer = new int[4];
        for(int [] edge : edges){
            int a = edge[0];
            int b = edge[1];
            max = Math.max(max,b);
            List<Integer> now = graph.get(a);
            if(now == null){
                now = new ArrayList<>();
                graph.put(a,now);
            }
            now.add(b);
            graph.put(a,now);
            if(map.get(a) == null){
                map.put(a,-1);
            }else{
                map.put(a,map.get(a)-1);
            }
            if(map.get(b) == null){
                map.put(b,1);
            }else{
                map.put(b,map.get(b)+1);
            }
        }
        for(int key : map.keySet()){
            if(map.get(key) < -1){
                answer[0] = key;
                break;
            }
        }
        v = new boolean[max+1];
        for(int node : graph.get(answer[0])){
            check(node);
        }
        return answer;
    }
    static void check(int node){
        v[node] = true;
        List <Integer> ls = graph.get(node);
        if(ls == null){
            answer[2]++;
            return;
        }else if(ls.size() == 1){
            int next = ls.get(0);
            if(v[next]){
                answer[1]++;
                return;
            }
            check(ls.get(0));
        }else{
            answer[3]++;
            return;
        }
    }
}
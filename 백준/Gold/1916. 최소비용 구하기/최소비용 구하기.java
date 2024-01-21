import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());
        ArrayList<ArrayList<Node>> arrayLists = new ArrayList<ArrayList<Node>>();
        for(int i = 0 ; i < n+1 ; i++){
            arrayLists.add(new ArrayList<Node>());
        }
        int [] _distance = new int[n+1];
        String [] tokens;
        Arrays.fill(_distance,Integer.MAX_VALUE);
        for(int i = 0 ; i < m ; i++){
            tokens = bufferedReader.readLine().split(" ");
            int a,b,cost ;
            a = Integer.parseInt(tokens[0]);
            b = Integer.parseInt(tokens[1]);
            cost = Integer.parseInt(tokens[2]);
            arrayLists.get(a).add(new Node(b,cost));
        }
        tokens = bufferedReader.readLine().split(" ");
        int start = Integer.parseInt(tokens[0]);
        int end = Integer.parseInt(tokens[1]);
        _distance[start] = 0;
        PriorityQueue<Node> hq = new PriorityQueue<>();
        hq.add(new Node(start,0));
        while(!hq.isEmpty()){
            Node now = hq.poll();
            //System.out.println(now.target);
            int _min_cost = Integer.MAX_VALUE;
            if(now.cost > _distance[now.target]){
                continue;
            }
            for(Node node : arrayLists.get(now.target)){
                //System.out.printf("%d %d\n",node.target,node.cost);
                if(_distance[node.target] > _distance[now.target]+node.cost){
                    _distance[node.target] = _distance[now.target]+node.cost;
                    hq.offer(new Node(node.target,_distance[node.target]));
                }
            }
            //System.out.println(Arrays.toString(_distance));
        }
        System.out.println(_distance[end]);
    }
}
class Node implements Comparable<Node>{
    int target,cost;
    public Node(int target,int cost){
        this.target = target;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node n){
        return Integer.compare(this.cost,n.cost);
    }
}
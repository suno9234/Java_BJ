import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Node head = new Node("START");
        for(int i = 0 ; i < n ; i++){
            String [] tokens = br.readLine().split(" ");
            insert(head, tokens);
        }
        dfs(head,-1);
        System.out.println(sb.toString());

    }

    static void dfs(Node curr,int layer){
        for(int i = 0 ; i < layer; i++){
            sb.append("--");
        }
        if(layer>= 0) {
            sb.append(curr.s).append("\n");
        }
        for(int i = 0 ; i < curr.list.size(); i++){
            dfs(curr.list.get(i),layer+1);
        }

    }
    static void insert(Node curr, String [] tokens){
        int len = tokens.length-1;
        int depth = 0;
        while(depth < len){
            Node next = new Node(tokens[depth+1]);
            int flag = 0;
            for(int i = 0 ; i < curr.list.size(); i++){
                if(curr.list.get(i).s.equals(tokens[depth+1])){
                    // 내가 넣으려는게 있으면
                    curr = curr.list.get(i);
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                // 내가 넣으려는게 없었던 경우
                curr.list.add(next);
                curr.list.sort((o1,o2)->o1.s.compareTo(o2.s));
                curr = next;
            }

            depth++;
        }
    }
}
class Node{
    String s;
    boolean visited;
    ArrayList<Node> list;
    public Node(String s){
        this.s = s;
        this.visited = false;
        list = new ArrayList<Node>();
    }
}

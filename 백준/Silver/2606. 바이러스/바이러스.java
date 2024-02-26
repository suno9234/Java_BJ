
import java.util.*;

public class Main {
    static int ret = 1;
    public static void main(String args[]) {
        int n, m;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        Graph2 g = new Graph2(n);
        for (int i = 0; i < m; i++) {
            g.addEdge(scan.nextInt(), scan.nextInt());
        }

        g.bfs(1);
        System.out.println(g.getVisitedLenth());

    }
}
class Graph2 {

    int V;
    ArrayList<LinkedList<Integer>> adjlist = new ArrayList<LinkedList<Integer>>();
    boolean visited[];
    boolean visited2[];

    public Graph2(int v) {
        V = v;
        visited = new boolean[V + 1];
        adjlist.add(0, new LinkedList<Integer>());
        for (int i = 0; i < v; i++) {
            adjlist.add(i + 1, new LinkedList<Integer>());
        }
    }

    void addEdge(int v1, int v2) {
        adjlist.get(v1).add(v2);
        adjlist.get(v2).add(v1);
    }

    void adjsort() {
        for (int i = 1; i < V + 1; i++) {
            Collections.sort(adjlist.get(i));
        }
    }

    int getVisitedLenth(){
        int ret = -1;
        for(int i =1;i<V+1;i++){
            if(visited[i]){
                ret++;
            }
        }
        return ret;
    }

    void bfs(int v) {
        Queue<Integer> q = new LinkedList<Integer>();
        visited[v] = true;
        q.add(v);

        while (q.size() != 0) {
            v = q.poll();
            //System.out.print(v + " ");

            Iterator<Integer> iter = adjlist.get(v).listIterator();
            while (iter.hasNext()) {
                int w = iter.next();
                if (!visited[w]) {
                    visited[w] = true;
                    q.add(w);
                }
            }
        }

    }

    void visitedN(){
        visited = new boolean[V+1];
    }

}

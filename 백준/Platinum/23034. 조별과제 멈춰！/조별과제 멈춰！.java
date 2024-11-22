import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static int answer = 0;
  static int[] parent;
  static int n, m;
  static int[] minDist;
  static boolean[] visited;
  static boolean[] v;
  static int totalDistance;
  static List<int[]>[] graph;
  static PriorityQueue<Edge> originalEdges;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    minDist = new int[n + 1];
    visited = new boolean[n + 1];
    graph = new List[n + 1];
    parent = new int[n + 1];
    Arrays.setAll(parent, x -> x);
    for (int i = 1; i < n + 1; i++) {
      graph[i] = new ArrayList<>();
    }
    originalEdges = new PriorityQueue<Edge>((e1, e2) -> Integer.compare(e1.cost, e2.cost));
    Arrays.fill(minDist, Integer.MAX_VALUE);
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      originalEdges.add(new Edge(a, b, c));
    }
    int q = Integer.parseInt(br.readLine());

    while (!originalEdges.isEmpty()) {
      Edge e = originalEdges.poll();
      if (find(e.pos) != find(e.dest)) {
        union(e.pos, e.dest);
        totalDistance += e.cost;
        graph[e.pos].add(new int[]{e.dest, e.cost});
        graph[e.dest].add(new int[]{e.pos, e.cost});
      }
    }
    // O (q * n) 1,0000,0000
    for (int i = 0; i < q; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      sb.append(solve(x, y)).append("\n");
    }
    System.out.println(sb);
  }

  static int solve(int x, int y) {
    // O(n)
    int result = totalDistance;
    v = new boolean[n + 1];
    v[x] = true;
    result -= findMaxEdge(x, y, 0);
    return result;
  }

  static int findMaxEdge(int x, int y, int maxValue) {
    // O(n)
    if (x == y) {
      return maxValue;
    }
    int result = -1;
    for (int[] edge : graph[x]) {
      int dest = edge[0];
      int cost = edge[1];
      if (v[dest]) {
        continue;
      }
      v[dest] = true;
      int res = findMaxEdge(dest, y, Math.max(maxValue, cost));
      if (res != -1) {
        result = res;
      }
    }
    return result;
  }

  static int find(int x) {
    if (parent[x] != x) {
      parent[x] = find(parent[x]); // 경로 압축
    }
    return parent[x];
  }

  static void union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);
    if (rootA != rootB) {
      parent[rootA] = rootB;
    }
  }

}

class Edge {

  int pos;
  int dest;
  int cost;

  public Edge(int pos, int dest, int cost) {
    this.pos = pos;
    this.dest = dest;
    this.cost = cost;
  }

  public String toString() {
    return "POS : " + pos + " DEST : " + dest + " COST : " + cost;
  }
}
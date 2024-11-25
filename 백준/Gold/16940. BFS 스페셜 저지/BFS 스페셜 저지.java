import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

  static int answer = 0;
  static int n;
  static List<Integer>[] graph;
  static int[] orders;
  static int[] answers;
  static boolean[] v;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    n = Integer.parseInt(br.readLine());
    graph = new List[n + 1];
    orders = new int[n + 1];
    v = new boolean[n + 1];
    answers = new int[n];
    for (int i = 1; i <= n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int i = 1; i < n; i++) {
      String[] tokens = br.readLine().split(" ");
      int a = Integer.parseInt(tokens[0]);
      int b = Integer.parseInt(tokens[1]);
      graph[a].add(b);
      graph[b].add(a);
    }
    String[] tokens = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      answers[i] = Integer.parseInt(tokens[i]);
    }
    if (answers[0] != 1) {
      System.out.println("0");
      return;
    }
    int last = -1;
    v = new boolean[n + 1];
    v[answers[0]] = true;
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(answers[0], 0);
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      int now = answers[i];
      if (map.get(now) == null) {
        System.out.println("0");
        return;
      } else if (map.get(now) < last) {
        System.out.println("0");
        return;
      } else {
        last = map.get(now);
        for (int next : graph[now]) {
          if (v[next]) {
            continue;
          }
          v[next] = true;
          map.put(next, cnt);
        }
        cnt++;
      }
    }
    System.out.println("1");
  }


}

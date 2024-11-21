import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

  static int answer = 0;
  static int n;
  static int[][] map;
  static int[] dx = {0, 0, 1, -1};
  static int[] dy = {1, -1, 0, 0};
  static int[][] times;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    n = Integer.parseInt(br.readLine());
    map = new int[n][n];
    times = new int[n][n];
    for (int i = 0; i < n; i++) {
      String l = br.readLine();
      for (int j = 0; j < n; j++) {
        map[i][j] = l.charAt(j) - '0';
        times[i][j] = Integer.MAX_VALUE;
      }
    }

    PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1[2], p2[2]));
    pq.add(new int[]{0, 0, 0});
    while (!pq.isEmpty()) {
      int[] now = pq.poll();
      int x = now[0];
      int y = now[1];
      int time = now[2];
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
          if (times[nx][ny] == Integer.MAX_VALUE) {
            if (map[nx][ny] == 1) {
              pq.add(new int[]{nx, ny, time});
              times[nx][ny] = time;
            } else {
              pq.add(new int[]{nx, ny, time + 1});
              times[nx][ny] = time + 1;
            }
          }
        }
      }
    }
    System.out.println(times[n - 1][n - 1]);


  }

  static void dfs(int x, int y, int cnt) {

  }

}

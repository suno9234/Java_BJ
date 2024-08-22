import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

  static int answer, n, k;
  static int[][] board;
  static int[] dirs;
  static int[][] parent;
  static int[] pos;
  static int[] dx = {0, 0, -1, 1};
  static int[] dy = {1, -1, 0, 0};
  static List<Integer>[][] nodes;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String[] tokens = br.readLine().split(" ");
    n = Integer.parseInt(tokens[0]);
    k = Integer.parseInt(tokens[1]);
    board = new int[n][n];
    parent = new int[n][n];
    dirs = new int[k + 1];
    pos = new int[k + 1];
    nodes = new List[n][n];
    for (int i = 0; i < n; i++) {
      tokens = br.readLine().split(" ");
      for (int j = 0; j < n; j++) {
        board[i][j] = Integer.parseInt(tokens[j]);
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        nodes[i][j] = new ArrayList<>();
      }
    }
    for (int i = 0; i < k; i++) {
      tokens = br.readLine().split(" ");
      int row = Integer.parseInt(tokens[0]) - 1;
      int col = Integer.parseInt(tokens[1]) - 1;
      int dir = Integer.parseInt(tokens[2]);
      dirs[i + 1] = dir - 1;
      pos[i + 1] = row * n + col;
      nodes[row][col].add(i + 1);
    }
    int turn = 1;
    while (turn <= 1000) {
      // 움직인다
      for (int i = 0; i < k; i++) {
        int num = i + 1;
        int dir = dirs[num];
        int x = pos[num] / n;
        int y = pos[num] % n;
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        int mySize = nodes[x][y].size();
        if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
          // 범위 밖으로 나감
          if (dir == 0) {
            dir = 1;
          } else if (dir == 1) {
            dir = 0;
          } else if (dir == 2) {
            dir = 3;
          } else {
            dir = 2;
          }
          dirs[num] = dir;
          nx = x + dx[dir];
          ny = y + dy[dir];
          if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
            // 다음 칸이 빨강이거나 흰색일때만 이동
            if (board[nx][ny] == 1) {
              // 빨
              for (int l = mySize - 1; l >= 0; l--) {
                int now = nodes[x][y].get(l);
                nodes[nx][ny].add(now);
                nodes[x][y].remove(l);
                pos[now] = nx * n + ny;
                if (now == num) {
                  break;
                }
              }
              if (nodes[nx][ny].size() > 3) {
                System.out.println(turn);
                return;
              }
            } else if (board[nx][ny] == 0) {
              // 흰
              for (int j = 0; j < mySize; j++) {
                int now = nodes[x][y].get(j);
                if (now == num) {
                  while (j < nodes[x][y].size()) {
                    now = nodes[x][y].get(j);
                    nodes[nx][ny].add(now);
                    nodes[x][y].remove(j);
                    pos[now] = nx * n + ny;
                  }
                  break;
                }
              }
              if (nodes[nx][ny].size() > 3) {
                System.out.println(turn);
                return;
              }
            }
          }

        } else if (board[nx][ny] == 2) {
          // 파란색
          if (dir == 0) {
            dir = 1;
          } else if (dir == 1) {
            dir = 0;
          } else if (dir == 2) {
            dir = 3;
          } else {
            dir = 2;
          }
          dirs[num] = dir;
          nx = x + dx[dir];
          ny = y + dy[dir];
          if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
            // 다음 칸이 빨강이거나 흰색일때만 이동
            if (board[nx][ny] == 1) {
              // 빨
              for (int l = mySize - 1; l >= 0; l--) {
                int now = nodes[x][y].get(l);
                nodes[nx][ny].add(now);
                nodes[x][y].remove(l);
                pos[now] = nx * n + ny;
                if (now == num) {
                  break;
                }
              }
              if (nodes[nx][ny].size() > 3) {
                System.out.println(turn);
                return;
              }
            } else if (board[nx][ny] == 0) {
              // 흰
              for (int j = 0; j < mySize; j++) {
                int now = nodes[x][y].get(j);
                if (now == num) {
                  while (j < nodes[x][y].size()) {
                    now = nodes[x][y].get(j);
                    nodes[nx][ny].add(now);
                    nodes[x][y].remove(j);
                    pos[now] = nx * n + ny;
                  }
                  break;
                }
              }
              if (nodes[nx][ny].size() > 3) {
                System.out.println(turn);
                return;
              }
            }
          }
        } else if (board[nx][ny] == 1) {
          // 빨간색
          for (int l = mySize - 1; l >= 0; l--) {
            int now = nodes[x][y].get(l);
            nodes[nx][ny].add(now);
            nodes[x][y].remove(l);
            pos[now] = nx * n + ny;
            if (now == num) {
              break;
            }
          }
          if (nodes[nx][ny].size() > 3) {
            System.out.println(turn);
            return;
          }
        } else {
          // 흰색
          for (int j = 0; j < mySize; j++) {
            int now = nodes[x][y].get(j);
            if (now == num) {
              while (j < nodes[x][y].size()) {
                now = nodes[x][y].get(j);
                nodes[nx][ny].add(now);
                nodes[x][y].remove(j);
                pos[now] = nx * n + ny;
              }
              break;
            }
          }
          if (nodes[nx][ny].size() > 3) {
            System.out.println(turn);
            return;
          }
        }
      }
//      for (int i = 0; i < n; i++) {
//        for (int j = 0; j < n; j++) {
//          System.out.printf(nodes[i][j].toString() + " ");
//        }
//        System.out.println();
//      }
//      System.out.println();
      turn++;

    }
    System.out.println(-1);
  }

}

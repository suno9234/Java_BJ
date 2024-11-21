import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{

  static int answer = 0;
  static int n, m, k;
  static int[] values;
  static int[] parents;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String[] tokens = br.readLine().split(" ");
    n = Integer.parseInt(tokens[0]);
    m = Integer.parseInt(tokens[1]);
    k = Integer.parseInt(tokens[2]);
    values = new int[m + 1];
    parents = new int[m + 1];
    tokens = br.readLine().split(" ");
    for (int i = 1; i <= m; i++) {
      values[i] = Integer.parseInt(tokens[i - 1]);
    }
    Arrays.sort(values);
    Arrays.setAll(parents, x -> x);
    tokens = br.readLine().split(" ");
    for (int i = 0; i < k; i++) {
      int now = Integer.parseInt(tokens[i]);
      int result = bs(now);
      sb.append(result).append("\n");
    }
    System.out.println(sb);
  }

  static int bs(int x) {
    // x보다 같거나 큰 최소값을 이분탐색으로 서치
    int start = 1;
    int end = m;
    int mid = 0;
    int result = -1;
    while (start <= end) {
      mid = (start + end) / 2;
      int parent = getParent(mid);
      // parent = 현재 선택한 노드보다 작은 최대값의 좌표
      if (values[parent] > x) {
        // 현재 고른 카드가 내가 준 값보다 크면
        result = parent;
        end = parent - 1;
        // 작은 값 검사
      } else {
        // 현재 고른 카드가 내가 준 값보다 작으면
        start = mid + 1;
      }
    }
    parents[result] = getParent(result - 1);
    return values[result];
  }

  static int getParent(int x) {
    if (parents[x] == x) {
      return x;
    }
    return parents[x] = getParent(parents[x]);
  }
}

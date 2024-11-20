import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

  static int[] answer;
  static int n, m, k;
  static int[] ks;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tokens = br.readLine().split(" ");
    n = Integer.parseInt(tokens[0]);
    m = Integer.parseInt(tokens[1]);
    k = Integer.parseInt(tokens[2]);
    tokens = br.readLine().split(" ");
    ks = new int[k];
    for (int i = 0; i < k; i++) {
      ks[i] = Integer.parseInt(tokens[i]);
    }
    int start = 0;
    int end = n;
    while (start <= end) {
      int mid = (end + start) / 2;
      if (isPossible(mid)) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < k; i++) {
      sb.append(answer[i]);
    }
    System.out.println(sb);
  }

  static boolean isPossible(int dist) {
    int cnt = 1;
    int last = ks[0];
    int idx = 1;
    int[] result = new int[k];
    result[0] = 1;
    while (idx < k) {
      if (ks[idx] - last >= dist) {
        result[idx] = 1;
        last = ks[idx];
        cnt++;
        if (cnt == m) {
          break;
        }
      }
      idx++;
    }
    if (cnt < m) {
      return false;
    }
    answer = result;
    return true;
  }
}

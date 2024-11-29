import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  static int answer = 0;
  static int n, m, l;
  static int[] rest;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String[] tokens = br.readLine().split(" ");
    n = Integer.parseInt(tokens[0]);
    m = Integer.parseInt(tokens[1]);
    l = Integer.parseInt(tokens[2]);
    rest = new int[n + 1];
    tokens = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      rest[i] = Integer.parseInt(tokens[i]);
    }
    rest[n] = l;
    Arrays.sort(rest);
    int start = 0;
    int end = l;
    while (start <= end) {
      int mid = (start + end) / 2;
      if (isPossible(mid, m)) {
        answer = mid;
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    System.out.println(answer);
  }

  static boolean isPossible(int mid, int cnt) {
    int last = 0;
    for (int i = 0; i < n + 1; i++) {
      int next = rest[i];
      int nCopy = next;
      while (nCopy - last > mid) {
        cnt--;
        nCopy -= mid;
        if (cnt < 0) {
          return false;
        }
      }
      last = next;
    }
    return true;
  }
}

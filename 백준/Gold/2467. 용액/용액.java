import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

  static int n;
  static long[] features;
  static long min = Long.MAX_VALUE;
  static long left, right;
  static int lp;
  static int rp;
  static int pl = -1, mr = -1;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    n = Integer.parseInt(br.readLine());
    features = new long[n];
    String[] tokens = br.readLine().split(" ");
    int flag = 0;
    for (int i = 0; i < n; i++) {
      features[i] = Long.parseLong(tokens[i]);
      if (flag == 0 && features[i] > 0) {
        pl = i;
        mr = i - 1;
        flag = 1;
      }
    }
    if (flag == 0) {
      mr = n - 1;
      pl = n;
    }
    if (mr - 1 >= 0) {
      // mr = 음수의 가장 오른쪽 인덱스
      long sum = features[mr - 1] + features[mr];
      if (Math.abs(sum) < min) {
        min = Math.abs(sum);
        left = features[mr - 1];
        right = features[mr];
      }
    }

    if (pl + 1 < n) {
      // pl = 양수의 가장 왼쪽 인덱스
      long sum = features[pl] + features[pl + 1];
      if (Math.abs(sum) < min) {
        min = Math.abs(sum);
        left = features[pl];
        right = features[pl + 1];
      }
    }
    lp = mr;
    rp = pl;
    while (lp >= 0 && rp < n) {
      long l = features[lp];
      long r = features[rp];
      long sum = l + r;
      if (Math.abs(sum) < min) {
        min = Math.abs(sum);
        left = l;
        right = r;
      }
      if (sum == 0) {
        break;
      } else if (sum > 0) {
        lp--;
      } else {
        rp++;
      }
    }
    System.out.println(Math.min(left, right) + " " + Math.max(left, right));
  }

}

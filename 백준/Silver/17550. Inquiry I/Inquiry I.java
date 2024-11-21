import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{

  static long answer = 0;
  static int n;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    n = Integer.parseInt(br.readLine());
    long[] squareSums = new long[n + 1];
    long[] sums = new long[n + 1];
    for (int i = 0; i < n; i++) {
      long a = Long.parseLong(br.readLine());
      sums[i + 1] = sums[i] + a;
      squareSums[i + 1] = squareSums[i] + a * a;
    }
    for (int k = 1; k <= n; k++) {
      answer = Math.max(answer, squareSums[k] * (sums[n] - sums[k]));
    }
    System.out.println(answer);

  }

}

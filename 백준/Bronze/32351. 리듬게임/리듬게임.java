import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{

  static double answer = 0;
  static int n, k;
  static double bps;


  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String[] tokens = br.readLine().split(" ");
    n = Integer.parseInt(tokens[0]);
    bps = Double.parseDouble(tokens[1]) / 60;
    k = Integer.parseInt(tokens[2]);
    int lastTime = 1;
    for (int i = 0; i < k; i++) {
      tokens = br.readLine().split(" ");
      int m = Integer.parseInt(tokens[0]);
      double nBpm = Double.parseDouble(tokens[1]);
      // lastTime~m-1 마디에 걸리는 시간
      int diff = (m - lastTime) * 4; // diff마디 진행
      lastTime = m;
      answer += diff / bps;
      bps = nBpm / 60;
    }
    int diff = (n - lastTime + 1) * 4;
    answer += diff / bps;
    System.out.printf("%.12f\n", answer);
  }

}

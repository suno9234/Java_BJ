import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{

  static int answer = 0;
  static int g, p;
  static int[] planes;
  static int[] closeGate;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    g = Integer.parseInt(br.readLine());
    p = Integer.parseInt(br.readLine());
    planes = new int[p];
    closeGate = new int[g + 1];
    Arrays.setAll(closeGate, x -> x);
    for (int i = 0; i < p; i++) {
      planes[i] = Integer.parseInt(br.readLine());
    }
    for (int i = 0; i < p; i++) {
      int gNow = planes[i];
      // 1~ gNow 사이의 게이트에 넣어야함
      int closeGateNow = getClose(gNow);
      // 내가 다음에 둘 놈
      if (closeGateNow == 0) {
        System.out.println(i);
        return;
      } else {
        closeGate[closeGateNow] = getClose(closeGateNow - 1);
        closeGate[gNow] = getClose(closeGateNow - 1);
      }
    }
    System.out.println(p);
  }

  static int getClose(int gate) {
    if (closeGate[gate] == gate) {
      return gate;
    }
    return closeGate[gate] = getClose(closeGate[gate]);
  }
}

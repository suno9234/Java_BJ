import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

  static int answer = 0;
  static char[] c1, c2;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String s1 = br.readLine();
    c1 = s1.toCharArray();
    String s2 = br.readLine();
    c2 = s2.toCharArray();
    int idx = 0;
    while (idx < c1.length) {
      //System.out.println("IDX :" + idx);
      idx = check(idx);
      //System.out.println(res);
    }
    System.out.println(answer);
  }

  static int check(int idx) {
    // idx 자리에서 cnt(2번문자열) 길이번쨰 비교
    int cnt = 0;
    while (cnt < c2.length && idx + cnt < c1.length) {
      if (idx + cnt < c1.length && c1[idx + cnt] == c2[cnt]) {
        cnt++;
      } else {
        idx++;
        cnt = 0;
      }
    }
    if (cnt == c2.length) {
      answer++;
    }
    return idx + cnt;
  }

}

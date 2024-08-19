import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

  static int answer;
  static int n;
  static Map<String, String> parent;
  static Map<String, Integer> map;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    n = Integer.parseInt(br.readLine());
    for (int i = 0; i < n; i++) {
      int f = Integer.parseInt(br.readLine());
      map = new HashMap<>();
      parent = new HashMap<>();
      for (int j = 0; j < f; j++) {
        String[] tokens = br.readLine().split(" ");
        String user0 = tokens[0];
        String user1 = tokens[1];
        if (map.get(user0) == null) {
          parent.put(user0, user0);
          map.put(user0, 1);
        }
        if (map.get(user1) == null) {
          parent.put(user1, user1);
          map.put(user1, 1);
        }
        // user0과 user1이 같은 유니온이 아니면 유니온 등록 후 map update 후 출력
        // 같은 유니온이면 스킵 후 map.get(user1) 출력
        union(user0, user1);
        sb.append(map.get(getParent(user1))).append("\n");
      }
    }
    System.out.println(sb);
  }

  static void union(String user0, String user1) {
    String pa = getParent(user0);
    String pb = getParent(user1);
    if (!pa.equals(pb)) {
      map.put(pb, map.get(pa) + map.get(pb));
      parent.put(pa, pb);
    }
  }

  static String getParent(String user) {
    if (parent.get(user).equals(user)) {
      return user;
    }
    String result = getParent(parent.get(user));
    parent.put(user, result);
    return result;
  }

}

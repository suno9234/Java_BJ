import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

  static int n, answer = 0;

  static char[][] tree;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    n = Integer.parseInt(br.readLine());
    tree = new char[26][2];
    for (int i = 0; i < n; i++) {
      String[] tokens = br.readLine().split(" ");
      char parent = tokens[0].charAt(0);
      char childL = tokens[1].charAt(0);
      char childR = tokens[2].charAt(0);
      tree[parent - 'A'][0] = childL;
      tree[parent - 'A'][1] = childR;
    }
    sb.append(preOrder('A', "")).append("\n").append(inOrder('A', "")).append("\n")
        .append(postOrder('A', ""));
    System.out.println(sb);
  }

  static String preOrder(char idx, String result) {
    char Left = tree[idx - 'A'][0];
    char Right = tree[idx - 'A'][1];
    result += idx;
    if (Left != '.') {
      result = preOrder(Left, result);
    }
    if (Right != '.') {
      result = preOrder(Right, result);
    }
    return result;
  }

  static String postOrder(char idx, String result) {
    char Left = tree[idx - 'A'][0];
    char Right = tree[idx - 'A'][1];
    if (Left != '.') {
      result = postOrder(Left, result);
    }
    if (Right != '.') {
      result = postOrder(Right, result);
    }
    result += idx;
    return result;
  }

  static String inOrder(char idx, String result) {
    char Left = tree[idx - 'A'][0];
    char Right = tree[idx - 'A'][1];

    if (Left != '.') {
      result = inOrder(Left, result);
    }
    result += idx;
    if (Right != '.') {
      result = inOrder(Right, result);
    }
    return result;
  }
}

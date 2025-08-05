import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int n, answer, expire;
    static List<Integer>[] childs;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        childs = new List[n + 1];
        parents = new int[n + 1];
        String[] tokens = br.readLine().split(" ");
        for (int i = 0; i < n + 1; i++) {
            childs[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            parents[i + 1] = Integer.parseInt(tokens[i]) + 1;
            childs[Integer.parseInt(tokens[i]) + 1].add(i + 1);
        }
        expire = Integer.parseInt(br.readLine()) + 1;
        int expireParent = parents[expire];
        List<Integer> expireParentChilds = childs[expireParent];
        List<Integer> newChilds = new ArrayList<>();
        for (int i = 0; i < expireParentChilds.size(); i++) {
            if (expireParentChilds.get(i) != expire) {
                newChilds.add(expireParentChilds.get(i));
            }
        }
        childs[expireParent] = newChilds;

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            List<Integer> children = childs[now];
            if (now!= 0 && children.size() == 0) {
                answer++;
            } else {
                for (int c : children) {
                    queue.add(c);
                }

            }
        }
        System.out.println(answer);
    }
}


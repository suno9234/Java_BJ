import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int[][] dp;
    static int[][] _map;
    static final int INF = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        _map = new int[n][n];
        dp = new int[n][(1 << n)];

        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                _map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        // 모든 dp 값을 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (1 << n); j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(tsp(0, 1));
    }

    static int tsp(int idx, int visited) {
        // 모든 도시를 방문한 경우, 현재 위치에서 출발 도시로 돌아가는 비용 반환
        if (visited == (1 << n) - 1) {
            if (_map[idx][0] == 0) return INF; // 출발 도시로 갈 수 없는 경우
            return _map[idx][0];
        }

        // 이미 계산한 적이 있는 경우, 저장된 값 반환
        if (dp[idx][visited] != -1) return dp[idx][visited];

        dp[idx][visited] = INF;

        for (int i = 0; i < n; i++) {
            // 아직 방문하지 않은 도시라면
            if ((_map[idx][i] != 0) && ((visited & (1 << i)) == 0)) {
                dp[idx][visited] = Math.min(dp[idx][visited], tsp(i, visited | (1 << i)) + _map[idx][i]);
            }
        }

        return dp[idx][visited];
    }
}
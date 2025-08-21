import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static long minValue;
    static long[] liquids, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        liquids = new long[n];
        answer = new long[3];
        minValue = Long.MAX_VALUE;
        String[] tokens = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            liquids[i] = Integer.parseInt(tokens[i]);
        }

        Arrays.sort(liquids);

        for (int i = 1; i < n - 1; i++) {
            // 기준점 = i
            // i를 가운데로 포함하는 3개의 용액 중 가장 0에 근접한것
            int left = i - 1;
            int right = i + 1;
            // left가 왼쪽으로 갈수록 값은 작아지고, right가 오른쪽으로 갈수록 값은 커진다.
            while (left >= 0 && right < n) {
                long sum = liquids[left] + liquids[i] + liquids[right];
                if (Math.abs(sum) < minValue) {
                    answer[0] = liquids[left];
                    answer[1] = liquids[i];
                    answer[2] = liquids[right];
                    minValue = Math.abs(sum);
                }
                if (sum < 0) {
                    right++;
                } else if (sum > 0) {
                    left--;
                } else {
                    System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
                    return;
                }
            }
        }
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n,m;
        String [] tokens = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        int [] nums;
        StringBuilder stringBuilder = new StringBuilder();
        nums = Arrays.stream(bufferedReader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(s->(int)s).
                toArray();
        int [] sums = new int[n+1];
        sums[0] = 0;
        for(int i = 1 ; i < n+1; i++){
            sums[i] = sums[i-1]+nums[i-1];
        }
        for(int i = 0 ; i < m; i++){
            int s, e;
            tokens = bufferedReader.readLine().split(" ");
            s = Integer.parseInt(tokens[0]);
            e = Integer.parseInt(tokens[1]);
            int answer = sums[e]-sums[s-1];
            stringBuilder.append(Integer.toString(answer));
            stringBuilder.append("\n");
        }
    System.out.println(stringBuilder.toString());
    }
}

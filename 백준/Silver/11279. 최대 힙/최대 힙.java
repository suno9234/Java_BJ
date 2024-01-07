import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.PriorityQueue;

public class Main {
    public static void main(String [] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Integer> h = new PriorityQueue<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i< n ; i++){
            int x = Integer.parseInt(bufferedReader.readLine());
            if(x == 0){
                Integer answer = h.poll();
                if(answer == null){
                    stringBuilder.append("0\n");
                }else{
                    stringBuilder.append(-answer);
                    stringBuilder.append("\n");
                }
            }else{
                h.add(-x);
            }
        }
        System.out.println(stringBuilder.toString());
    }
}

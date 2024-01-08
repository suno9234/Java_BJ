import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> h = new PriorityQueue<Integer>();
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        for(int i =0 ; i< n ; i++){
            int x = Integer.parseInt(bufferedReader.readLine());
            if (x == 0){
                if(!h.isEmpty()){
                    int now = h.poll();
                    if(now% 2 == 0){
                        stringBuilder.append(now/2);
                        // 2-> 1
                    }else{
                        stringBuilder.append(-(now+1)/2);
                        // 1-> -1
                    }
                }else{
                    stringBuilder.append("0");
                }
                stringBuilder.append("\n");
            }else{
                if(x > 0){
                    h.add(x*2);
                }else{
                    h.add(-(x*2)-1);
                }
                // 1->2
                // -1->1
            }

        }
        System.out.println(stringBuilder.toString());
    }
}

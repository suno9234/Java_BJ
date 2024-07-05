import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int n,h;
    static Map<Integer, Integer> suck,jong;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String [] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        h = Integer.parseInt(tokens[1]);
        suck = new HashMap<>();
        jong = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
            int height = Integer.parseInt(br.readLine());
            if(i%2 == 0){
                if(suck.get(height) == null){
                    suck.put(height,1);
                }else{
                    suck.put(height,suck.get(height)+1);
                }
            }else{
                if(jong.get(height) == null){
                    jong.put(height,1);
                }else{
                    jong.put(height,jong.get(height)+1);
                }
            }
        }
        int cnt = n/2;
        int min = cnt;
        int gu = 1;
        for(int i = 1; i < h ; i++) {
            int s = suck.getOrDefault(i,0);
            int j  = jong.getOrDefault(h-i,0);
            cnt = cnt-s+j;
            //System.out.println(i+" "+cnt+" "+s+" "+j);
            if(min > cnt){
                min = cnt;
                gu = 1;
            }else if(min == cnt){
                gu++;
            }
        }
        sb.append(min).append(" ").append(gu);
        System.out.println(sb.toString());
    }


}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i<t; i++){
            int n = Integer.parseInt(bufferedReader.readLine());
            HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
            for(int j = 0 ; j < n ; j++){
                String type = bufferedReader.readLine().split(" ")[1];
                if(hashMap.get(type) == null){
                    hashMap.put(type,1);
                }else{
                    hashMap.replace(type,hashMap.get(type)+1);
                }
            }
            int answer = 1;
            for(String type : hashMap.keySet()){
                answer *= hashMap.get(type)+1;
            }
            System.out.println(answer-1);

        }
    }
}
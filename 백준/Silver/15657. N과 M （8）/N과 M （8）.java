import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int m;
    static StringBuilder stringBuilder;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens =  bufferedReader.readLine().split(" ");
        stringBuilder = new StringBuilder();
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        arr = new int[n];
        tokens = bufferedReader.readLine().split(" ");
        for(int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(tokens[i]);
        }
        Arrays.sort(arr);
        comb(new int[m],0,m);
        System.out.println(stringBuilder.toString());
    }
    public static void comb(int [] _arr ,int start, int count){
        //System.out.println(Arrays.toString(_arr));
        //System.out.printf("%d %d\n",start,count);
        if(count == 0){
            for(int i = 0 ; i < m ; i++){
                stringBuilder.append(arr[_arr[i]]).append(" ");
            }
            stringBuilder.append("\n");
            //System.out.println(stringBuilder.toString());
            return;
        }
        for(int i = start; i < n ; i++){
            _arr[m - count] = i;   
            count -= 1;
            comb(_arr,i, count);
            count += 1;
        }
    }
}

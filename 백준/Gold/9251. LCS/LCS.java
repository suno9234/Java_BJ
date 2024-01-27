import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = bufferedReader.readLine();
        String s2 = bufferedReader.readLine();
        int [][] lcs = new int[s1.length()+1][s2.length()+1];
        for(int i = 1 ; i < s1.length()+1;i++){
            for(int j = 1 ; j < s2.length()+1;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    lcs[i][j] = lcs[i-1][j-1]+1;
                }else{
                    lcs[i][j] = Math.max(lcs[i-1][j],lcs[i][j-1]);
                }
            }
        }
        System.out.println(lcs[s1.length()][s2.length()]);
    }
}

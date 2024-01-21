import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int [] leftB = new int[n];
        int [] middleB = new int[n];
        int [] rightB = new int[n];
        int [] leftS = new int[n];
        int [] middleS = new int[n];
        int [] rightS = new int[n];
        String [] tokens ;
        for(int i = 0 ; i < n ; i++){
            tokens = bufferedReader.readLine().split(" ");
            int l,m,r;
            l = Integer.parseInt(tokens[0]);
            m = Integer.parseInt(tokens[1]);
            r = Integer.parseInt(tokens[2]);
            if(i == 0){
                leftB[0] = l;
                middleB[0] = m;
                rightB[0] = r;
                leftS[0] = l;
                middleS[0] = m;
                rightS[0] = r;
                continue;
            }
            leftB[i] = (int)Math.max(leftB[i-1]+l , middleB[i-1]+l);
            middleB[i] = (int)Math.max((int)Math.max(leftB[i-1]+m , middleB[i-1]+m),rightB[i-1]+m) ;
            rightB[i] = (int)Math.max(middleB[i-1]+r,rightB[i-1]+r);

            leftS[i] = (int)Math.min(leftS[i-1]+l , middleS[i-1]+l);
            middleS[i] = (int)Math.min((int)Math.min(leftS[i-1]+m , middleS[i-1]+m),rightS[i-1]+m) ;
            rightS[i] = (int)Math.min(middleS[i-1]+r,rightS[i-1]+r);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((int)Math.max((int)Math.max(leftB[n-1],middleB[n-1]),rightB[n-1])).append(" ")
                     .append((int)Math.min((int)Math.min(leftS[n-1],middleS[n-1]),rightS[n-1]));
        System.out.println(stringBuilder.toString());
    }
}

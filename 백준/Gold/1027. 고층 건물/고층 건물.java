import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int n,answer,maxCount;
    static int [] buildings;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        buildings = new int[n];
        String [] tokens = br.readLine().split(" ");
        for(int i = 0 ; i < n ; i++){
            buildings[i] = Integer.parseInt(tokens[i]);
        }
        for(int i = 0 ; i < n ; i++){
            int temp = 0;
            // 왼쪽
            if(i > 0) {
                double minA = buildings[i] - buildings[i - 1] ;
                temp++;
                for(int j = 1 ; i-j >= 0 ; j++){
                    // minA = 기울기
                    // minA = 양수 = 나보다 작음  0 = 똑같음 음수 = 나보다 큼
                    double a = (double) (buildings[i] - buildings[i-j]) / j;
                    // 기울기가 나보다 크거나
                    if(a < minA ){
                        temp++;
                        minA = a;
                    }
                }
            }
            // 오른쪽
            if(i < n-1) {
                double minA = buildings[i + 1] - buildings[i];
                temp++;
                for(int j = 1 ; i+j < n ; j++){
                    // minA = 기울기
                    // minA = 음수 = 나보다 작음  0 = 똑같음 양수 = 나보다 큼
                    double a = (double) (buildings[i + j] - buildings[i]) / j;
                    if(a > minA){
                        temp++;
                        minA = a;
                    }
                }
            }
            answer = Math.max(answer,temp);
        }
        System.out.println(answer);
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int [] party ;
    static int [] party2;
    static int answer = Integer.MAX_VALUE;
    static int [][] _map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        party = new int[n/2];
        party2 = new int[n/2];
        _map = new int[n][n];
        for(int i = 0 ; i < n;i++){
            String [] tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < n ; j++){
                _map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        back(0,n/2);
        System.out.println(answer);
    }
    static void back(int start ,int cnt){
        if(cnt == 0){
            // party에 팀인 사람들 번호가 배열로 들어가있음.
            int idx = 0;
            for(int i = 0 ; i < n ; i++){
                int flag = 0;
                for(int j = 0 ; j < n/2;j++){
                    if(party[j] == i){
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0){
                    party2[idx++] = i;
                }
            }
            //System.out.println(Arrays.toString(party));
            int temp = 0;
            int temp2 = 0;
            for(int i = 0 ; i < n/2;i++){
                for(int j = i+1 ; j < n/2;j++){
                    temp+= _map[party[i]][party[j]] + _map[party[j]][party[i]];
                }
            }
            for(int i = 0 ; i < n/2;i++){
                for(int j = i+1 ; j < n/2;j++){
                    temp2+= _map[party2[i]][party2[j]] + _map[party2[j]][party2[i]];
                }
            }
            if(answer > (int)Math.abs(temp-temp2)){
                answer = (int)Math.abs(temp-temp2);
            }

            return;
        }
        for(int i = start ;i < n ; i++){
            cnt--;
            party[cnt] = i;
            back(i+1,cnt);
            cnt++;
        }
    }

}

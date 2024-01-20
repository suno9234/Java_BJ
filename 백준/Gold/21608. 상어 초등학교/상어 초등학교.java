import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int [] dx = {1,-1,0,0};
    static int [] dy = {0,0,1,-1};

    static int [][] answer;
    static int [][] love ;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        love = new int[n*n+1][]; // 1~ n*n까지
        answer = new int[n][n];
        for(int i = 0 ; i < n*n;i++){
            String [] tokens = bufferedReader.readLine().split(" ");
            int now = Integer.parseInt(tokens[0]);
            int [] love_now = new int[]{Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]),};
            love[now] = love_now;
            // 좋아하는 학생이 인접한 칸에 가장 많은 칸 찾기
            int [][][] score = new int[n][n][2];
            // 각 칸의 점수와 빈칸 점수 입력
            for(int j  = 0 ; j < n ; j++){
                for(int k = 0 ; k < n ; k++){
                    //모든 칸에 대하여
                    if(answer[j][k] == 0){
                    // 현재 검사하는 칸에 사람이 없으면
                        for(int l = 0 ; l < 4 ; l++){
                            int nx = j+dx[l];
                            int ny = k+dy[l];
                            if(nx >= 0 && ny >=0 && nx < n && ny <n ){
                                // 주위 4방향에 대하여 탐색이 가능할 때
                                for(int m = 0 ; m <4; m++) {
                                    if (answer[nx][ny] == love_now[m]){
                                        // 만약 탐색하는 칸의 값이 내가 좋아하는 친구면
                                        score[j][k][0] += 1;
                                        //sroce [j][k] 점수 증가
                                        break;
                                    }else if(answer[nx][ny] == 0){
                                        score[j][k][1] +=1;
                                        break;
                                    }
                                }

                            }
                        }
                    }
                }
            }

            int posX = -1;
            int posY = -1;
            int _max_score = 0;
            int _max_blank = 0;

            //점수와 빈칸 점수를 통하여 가장 좋은 자리 찾기
            for(int j = n-1 ; j >= 0; j--) {
                for (int k = n-1; k >= 0; k--) {
                    if (answer[j][k] == 0) {
                        if(posX == -1){
                            posX = j;
                            posY = k;
                            _max_score = score[j][k][0];
                            _max_blank = score[j][k][1];
                        }
                        if (score[j][k][0] > _max_score) {
                            posX = j;
                            posY = k;
                            _max_score = score[j][k][0];
                            _max_blank = score[j][k][1];
                        } else if (score[j][k][0] == _max_score) {
                            if (score[j][k][1] > _max_blank) {
                                posX = j;
                                posY = k;
                                _max_blank = score[j][k][1];
                            } else if (score[j][k][1] == _max_blank) {
                                if (posX > j) {
                                    posX = j;
                                    posY = k;
                                } else if (posX == j) {
                                    if (posY > k) {
                                        posY = k;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            answer[posX][posY] = now;
        }


        int answerI = 0;
        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j < n ; j++){
                int now = answer[i][j];
                int temp = 0;
                for(int k = 0 ; k < 4 ; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx >=0 && ny >= 0 && nx< n && ny < n ){
                        for(int l = 0 ; l < 4 ; l++){
                            if(love[now][l] == answer[nx][ny]){
                                temp+=1;
                                break;
                            }
                        }
                    }
                }
                if(temp == 1){
                    answerI+=1;
                }else if(temp == 2){
                    answerI+=10;
                }else if(temp == 3){
                    answerI+=100;
                }else if(temp == 4){
                    answerI+=1000;
                }
            }
        }
        System.out.println(answerI);
    }
}

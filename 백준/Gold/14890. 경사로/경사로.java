import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int l = Integer.parseInt(tokens[1]);
        int [][] _map = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < n ; j++){
                _map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        int answer = 0;
        for(int i = 0 ; i < n ; i++){
            // 오른쪽 방향 탐색
            int height = _map[i][0];
            int idx = 0;
            boolean [] _visited = new boolean[n];
            while( idx+1 < n){
                if(_map[i][idx+1] > height){
                    // 가려는 곳이 현재보다 높은 경우
                    if(_map[i][idx+1]-height > 1){
                        //높이차가 2 이상이면 break
                        break;
                    }
                    if(idx-l+1< 0){
                        // 경사로가 맵 밖으로 나가는 경우 break
                        break;
                    }
                    int flag = 0 ;
                    for(int j = idx ; j >idx-l; j--){
                        if(_map[i][j] != height || _visited[j]){
                            //경사로를 놓을 자리가 평평하지 않거나 이미 경사로를 놓은 경우
                            flag = 1;
                            break;
                        }else{
                            _visited[j] = true;
                        }
                    }
                    if(flag ==1 ){
                        break;
                    }
                    // 오른쪽으로 경사로를 하나 놓고 이동
                    height = _map[i][idx+1];
                    idx+=1;
                }else if(_map[i][idx+1] < height){
                    // 경사로를 놓고 내려가는 경우
                    if(height-_map[i][idx+1] > 1){
                        //높이차가 2 이상이면 break
                        break;
                    }
                    if(idx+l >= n){
                        //경사로가 맵 밖으로 나가는 경우
                        break;
                    }
                    int flag = 0 ;
                    for(int j = idx+1 ; j <idx+l+1; j++){
                        if(_map[i][j] != height-1 || _visited[j]){
                            //경사로를 놓을 자리가 평평하지 않거나 이미 경사로를 놓은 경우
                            flag = 1;
                            break;
                        }else{
                            _visited[j] = true;
                        }
                    }
                    if(flag ==1 ){
                        break;
                    }
                    height -=1;
                    idx += l;
                }else{
                    idx++;
                }

            }
            if(idx >= n-1){
                answer++;
            }
        }
        for(int i = 0 ; i < n ; i++){
            // 아래쪽 방향 탐색
            int height = _map[0][i];
            int idx = 0;
            boolean [] _visited = new boolean[n];
            while( idx+1 < n){
                if(_map[idx+1][i] > height){
                    // 가려는 곳이 현재보다 높은 경우
                    if(_map[idx+1][i]-height > 1){
                        //높이차가 2 이상이면 break
                        break;
                    }
                    if(idx-l+1< 0){
                        // 경사로가 맵 밖으로 나가는 경우 break
                        break;
                    }
                    int flag = 0 ;
                    for(int j = idx ; j >idx-l; j--){
                        if(_map[j][i] != height || _visited[j]){
                            //경사로를 놓을 자리가 평평하지 않거나 이미 경사로를 놓은 경우
                            flag = 1;
                            break;
                        }else{
                            _visited[j] = true;
                        }
                    }
                    if(flag ==1 ){
                        break;
                    }
                    // 오른쪽으로 경사로를 하나 놓고 이동
                    height = _map[idx+1][i];
                    idx+=1;
                }else if(_map[idx+1][i] < height){
                    // 경사로를 놓고 내려가는 경우
                    if(height-_map[idx+1][i] > 1){
                        //높이차가 2 이상이면 break
                        break;
                    }
                    if(idx+l >= n){
                        //경사로가 맵 밖으로 나가는 경우
                        break;
                    }
                    int flag = 0 ;
                    for(int j = idx+1 ; j <idx+l+1; j++){
                        if(_map[j][i] != height-1 || _visited[j]){
                            //경사로를 놓을 자리가 평평하지 않거나 이미 경사로를 놓은 경우
                            flag = 1;
                            break;
                        }else{
                            _visited[j] = true;
                        }
                    }
                    if(flag ==1 ){
                        break;
                    }
                    height -=1;
                    idx += l;
                }else{
                    idx++;
                }

            }
            if(idx >= n-1){
                answer++;
            }
        }
        System.out.println(answer);
    }
}

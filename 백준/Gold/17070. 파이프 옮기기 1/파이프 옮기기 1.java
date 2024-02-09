import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int answer = 0;
        int [][] _map = new int[n][n];
        int [] dx = {0,1,1};
        int [] dy = {1,0,1};
        for(int i = 0 ; i < n ; i++){
            String [] tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < n ; j++){
                _map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        int x = 0 ,y = 0 , dir = 0;
        ArrayDeque<int[]> arrayDeque = new ArrayDeque<>();
        arrayDeque .add(new int[]{x,y,dir});
        while(!arrayDeque.isEmpty()){
            int [] now = arrayDeque.poll();
            x = now[0];
            y = now[1];
            dir = now[2];
            int nx = x+dx[dir];
            int ny = y+dy[dir];
            if(nx<n && ny <n){
                switch(dir){
                    case 0 :
                        if(ny+1 < n && _map[nx][ny+1] < 1) {
                            // 오른쪽이 비었을때
                            if(nx == n-1 && ny+1 == n-1){
                                answer++;
                            }else {
                                arrayDeque.add(new int[]{x, y + 1, 0});
                            }
                            if(nx+1 < n && _map[nx+1][ny] <1 && _map[nx+1][ny+1]<1){
                                // 오른쪽 대각선이 비었을 때
                                if(nx+1 == n-1 && ny+1 == n-1){
                                    answer++;
                                }else {
                                    arrayDeque.add(new int[]{x, y + 1, 2});
                                }
                            }
                        }
                        break;
                    case 1:
                        if(nx+1 < n && _map[nx+1][ny] < 1){
                            // 아래 비었을때
                            if(nx+1 == n-1 && ny == n-1){
                                answer++;
                            }else{
                                arrayDeque.add(new int[]{x+1,y,1});
                            }
                            if(ny+1 < n && _map[nx][ny+1]<1 && _map[nx+1][ny+1] < 1 ){
                                // 대각선 비었을때
                                if(nx+1 == n-1 && ny+1 == n-1){
                                    answer++;
                                }else{
                                    arrayDeque.add(new int[]{x+1,y,2});
                                }
                            }
                        }
                        break;
                    case 2:
                        // 대각선
                        if(ny +1 < n &&  _map[nx][ny+1] < 1){
                            // 오른쪽이 비었을 때
                            if(ny+1 == n-1 && nx == n-1){
                                answer++;
                            }else{
                                arrayDeque.add(new int[]{x+1,y+1,0});
                            }
                            if(nx+1 < n && _map[nx+1][ny]<1 && _map[nx+1][ny+1] < 1){
                                // 아래도 비고 오른쪽 아래도 비었을
                                if(nx+1 == n-1 && ny+1 == n-1){
                                    answer++;
                                }else{
                                    arrayDeque.add(new int[]{x+1,y+1,2});
                                }
                            }
                        }
                        if(nx+1 < n  && _map[nx+1][ny]<1){
                            if(nx+1 == n-1 && ny == n-1){
                                answer++;
                            }else{
                                arrayDeque.add(new int[]{x+1,y+1,1});
                            }
                        }
                        break;
                }
            }

        }
        System.out.println(answer);
    }
}

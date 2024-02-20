import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        int [][] _map  = new int [n][n];

        int sizeOfShark = 2;
        int food = 0;
        int posX = 0;
        int posY = 0;
        int [] dx = {-1,0,0,1};
        int [] dy = {0,-1,1,0};
        int answer = 0;

        for(int i = 0 ; i < n ; i++){
            String [] tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < n; j++){
                _map[i][j] = Integer.parseInt(tokens[j]);
                if(_map[i][j] == 9){
                    posX = i;
                    posY = j;
                    _map[i][j] = 0;
                }
            }
        }
        boolean isNext = true;
        while(isNext){
            boolean [][] _visited = new boolean[n][n];
            PriorityQueue <int []> pq = new PriorityQueue<>(new Comparator<int[]>() {
            	public int compare(int [] ia1, int [] ia2) {
            		return ia1[0]-ia2[0]==0 ? ia1[1]-ia2[1] : ia1[0]-ia2[0];
            	}
            });
            pq.add(new int[] {posX,posY});
            int flag = 0;
            int distance = 0;
            while (flag == 0 && !pq.isEmpty()) {
                // 더이상 갈곳이 없을 때 종료
                PriorityQueue<int[]> nextPq = new PriorityQueue<>(new Comparator<int[]>() {
                	public int compare(int [] ia1, int [] ia2) {
                		return ia1[0]-ia2[0]==0 ? ia1[1]-ia2[1] : ia1[0]-ia2[0];
                	}
                });
                //현재 위치에서 탐색
                while(!pq.isEmpty()){
                    int [] now = pq.poll();
                    int x = now[0];
                    int y = now[1];
                    if(_map[x][y] > 0 && _map[x][y] < sizeOfShark){
                        // queue 에서 뽑은게 내가 먹을 수 있는 물고기라면
                        posX = x;
                        posY = y;
                        _map[x][y] = 0;
                        food ++;
                        if (food == sizeOfShark) {
                            food = 0;
                            sizeOfShark++;
                        }
                        flag = 1;
                        answer+=distance;
                        break;
                    }
                    for (int i = 0; i < 4; i++) {
                        // 상하좌우에 대해서
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (nx >= 0 && ny >= 0 && nx < n && ny < n && _map[nx][ny] <= sizeOfShark && !_visited[nx][ny]) {
                            _visited[nx][ny] = true;
                            nextPq.add(new int[] {nx, ny});
                        }
                    }

                }
                pq = nextPq;
                distance++;
            }
            if(flag == 0){
                isNext = false;
            }
        }
        System.out.println(answer);
    }
 
}

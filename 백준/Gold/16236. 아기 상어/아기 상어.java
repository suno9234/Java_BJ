import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
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
            Queue<Coor> queue = new LinkedList<Coor>();
            //System.out.println("Coordinate: "+posX+" "+posY+" SIZE : "+sizeOfShark+" DIST : "+answer);
            queue.add(new Coor(posX, posY));
            int flag = 0;
            int distance = 0;
            while (flag == 0 && !queue.isEmpty()) {
                // 더이상 갈곳이 없을 때 종료
                LinkedList<Coor> _nextQueue = new LinkedList<>();
                //현재 위치에서 탐색
                while(!queue.isEmpty()){
                    Coor now = queue.poll();
                    if(_map[now.x][now.y] > 0 && _map[now.x][now.y] < sizeOfShark){
                        // queue 에서 뽑은게 내가 먹을 수 있는 물고기라면
                        posX = now.x;
                        posY = now.y;
                        _map[now.x][now.y] = 0;
                        food +=1;
                        if (food == sizeOfShark) {
                            food = 0;
                            sizeOfShark += 1;
                        }
                        flag = 1;
                        answer+=distance;
                        break;
                    }
                    for (int i = 0; i < 4; i++) {
                        // 상하좌우에 대해서
                        int nx = now.x + dx[i];
                        int ny = now.y + dy[i];
                        if (nx >= 0 && ny >= 0 && nx < n && ny < n && _map[nx][ny] <= sizeOfShark && !_visited[nx][ny]) {
                            _visited[nx][ny] = true;
                            _nextQueue.add(new Coor(nx, ny));
                        }
                    }

                }
                //System.out.println();
                _nextQueue.sort(new Comparator<Coor>() {
                    @Override
                    public int compare(Coor a, Coor b) {
                        if (a.x == b.x) {
                            return a.y - b.y;
                        }
                        return a.x - b.x;

                    }
                });

                queue.addAll((Queue<Coor>)_nextQueue);
                distance+=1;
            }
            if(flag == 0){
                isNext = false;
            }
        }
        System.out.println(answer);
    }
    static class Coor{
        int x;
        int y;
        public Coor(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

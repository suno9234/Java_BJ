import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int [][] A;
    static ArrayDeque<int [] > cloud = new ArrayDeque<>();
    static int [] dx = {0,-1,-1,-1,0,1,1,1};
    static int [] dy = {-1,-1,0,1,1,1,0,-1};
    static int [] xDx = {1,1,-1,-1};
    static int [] xDy = {1,-1,1,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        A = new int[n][n];
        cloud.add(new int[]{n-1,0});
        cloud.add(new int[]{n-1,1});
        cloud.add(new int[]{n-2,0});
        cloud.add(new int[]{n-2,1});
        for(int i = 0 ; i < n ; i++) {
            tokens = br.readLine().split(" ");
            for(int j = 0 ; j < n ; j++) {
                A[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        for(int magic = 0 ; magic < m ; magic++) {
            tokens = br.readLine().split(" ");
            int d = Integer.parseInt(tokens[0])-1;
            int s = Integer.parseInt(tokens[1]);
            ArrayDeque <int[]> newCloud = new ArrayDeque<>();
            boolean [][] v = new boolean[n][n];
            while(!cloud.isEmpty()) {
                int [] cl = cloud.poll();
                int x = cl[0];
                int y = cl[1];
                int nx = (x+dx[d]*s + n*100)%n;
                int ny = (y+dy[d]*s + n*100)%n;
                newCloud.add(new int[]{nx,ny});
                A[nx][ny]++;
                v[nx][ny] = true;
            }
            while(!newCloud.isEmpty()){
                int [] now = newCloud.poll();
                int x = now[0];
                int y = now[1];
                for(int i = 0 ; i< 4 ; i++) {
                    int nx = x+xDx[i];
                    int ny = y+xDy[i];
                    if(nx >=0 && ny >= 0 && nx < n && ny <n && A[nx][ny]>0) {
                        A[x][y] ++;
                    }
                }
            }
            ArrayDeque <int []> returnCloud = new ArrayDeque<>();
            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    if(A[i][j] >= 2 && !v[i][j] ) {
                        A[i][j] -= 2;
                        returnCloud.add(new int[]{i,j});
                    }
                }
            }
            cloud = returnCloud;
        }
        int answer = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                answer+= A[i][j];
            }
        }
        System.out.println(answer);

    }

}

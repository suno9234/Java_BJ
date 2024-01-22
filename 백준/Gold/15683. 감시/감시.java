
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    static Tup [] cctvs ;
    static int answer = Integer.MAX_VALUE;
    static int [][][] dir;
    static int count;
    static int [][] _map ;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        _map = new int[n][m];
        cctvs = new Tup[8];
        count = 0;
        for(int i = 0 ; i < n ; i++){
            tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < m ; j++){
                _map[i][j] = Integer.parseInt(tokens[j]);
                if(_map[i][j] >0 && _map[i][j] < 6){
                    cctvs[count++] = new Tup(i,j,_map[i][j]);
                }
            }
        }
        dir = new int[n][m][];
        foo(0);
        System.out.println(answer);

    }
    public static void foo(int idx){
        if (idx == count ){
            int [][] _map_copy = new int[n][m];
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < m ; j++){
                    _map_copy[i][j] = _map[i][j];
                }
            }

            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < m ; j++){
                    if(_map_copy[i][j] ==1 || _map_copy[i][j] ==2 || _map_copy[i][j] == 3||
                    _map_copy[i][j] == 4 || _map_copy[i][j] == 5){
                        for(int d : dir[i][j]){
                            if(d == 0){
                                for(int k = i; k>=0;k--){
                                    if(_map_copy[k][j] == 6){
                                        break;
                                    }
                                    if(_map_copy[k][j] > 0 && _map_copy[k][j] < 6){
                                        continue;
                                    }
                                    _map_copy[k][j] = -1;
                                }
                            }
                            if(d == 1){
                                for(int k = j; k <m ; k++){
                                    if(_map_copy[i][k] == 6){
                                        break;
                                    }
                                    if(_map_copy[i][k] > 0 && _map_copy[i][k] < 6){
                                        continue;
                                    }
                                    _map_copy[i][k] = -1;
                                }
                            }
                            if(d == 2){
                                for(int k = i; k < n; k++){
                                    if(_map_copy[k][j] == 6){
                                        break;
                                    }
                                    if(_map_copy[k][j] > 0 && _map_copy[k][j] < 6){
                                        continue;
                                    }
                                    _map_copy[k][j] = -1;
                                }
                            }
                            if(d == 3){
                                for(int k = j ; k >=0 ; k--){
                                    if(_map_copy[i][k] == 6){
                                        break;
                                    }
                                    if(_map_copy[i][k] > 0 && _map_copy[i][k] < 6){
                                        continue;
                                    }
                                    _map_copy[i][k] = -1;
                                }
                            }
                        }
                    }
                }
            }
            int temp = 0;
            //System.out.println("====================");
            for(int i = 0 ; i < n ; i++){
                //System.out.println(Arrays.toString(_map_copy[i]));
                for(int j = 0 ; j < m ; j++){
                    if(_map_copy[i][j] == 0){
                        temp+=1;
                    }
                }
            }
            answer = Math.min(answer,temp);
            return;
        }
        Tup now = cctvs[idx];
        int type = now.type;
        if(type == 1) {
            for (int i = 0; i < 4; i++) {
                dir[now.x][now.y] = new int[]{i};
                foo(idx + 1);
            }
        } else if (type == 2) {
            for(int i = 0 ; i < 2 ; i++){
                dir[now.x][now.y] = new int[]{i,i+2};
                foo(idx+1);
            }
        } else if(type == 3){
            for(int i = 0 ; i < 4 ; i++){
                dir[now.x][now.y] = new int[]{i%4,(i+1)%4};
                foo(idx+1);
            }
        }else if(type== 4){
            for(int i = 0 ; i < 4; i++){
                dir[now.x][now.y] = new int[]{i%4,(i+1)%4, (i+2)%4};
                foo(idx+1);
            }
        }else {
            dir[now.x][now.y] = new int[]{0,1,2,3};
            foo(idx+1);
        }

    }
}


class Tup{
    int x , y , type;
    public Tup(int x , int y,int type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
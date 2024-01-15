
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int r = 0;
    static int c = 0;
    static int m = 0;
    static Shark [][] _map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        r = Integer.parseInt(tokens[0]);
        c = Integer.parseInt(tokens[1]);
        m = Integer.parseInt(tokens[2]);
        _map = new Shark[r][c];
        int answer = 0;

        int king = -1;
        for(int i = 0 ; i < m ; i++){
            tokens = bufferedReader.readLine().split(" ");
            int nowR = Integer.parseInt(tokens[0]);
            int nowC = Integer.parseInt(tokens[1]);
            int nowS = Integer.parseInt(tokens[2]);
            int nowD = Integer.parseInt(tokens[3]);
            int nowZ = Integer.parseInt(tokens[4]);
            _map[nowR-1][nowC-1] = new Shark(nowS,nowD,nowZ);
        }
        while(true){
            // 오른쪽으로 한 칸 이동
            king++;
            if(king >= c){
                break;
            }
            // 가장 위에 있는 상어 잡음
            for(int i = 0 ; i < r ; i++){
                if(_map[i][king] != null){
                    answer+= _map[i][king].z;
                    _map[i][king] = null;
                    //System.out.println("SHARK : "+i+" "+king+" DEAD");
                    break;
                }
            }
            // 상어 이동
            Shark [][] _newMap = new Shark[r][c];
            for(int i = 0 ; i < r ; i++){
                for(int j = 0 ; j < c;j++){
                    if(_map[i][j] != null){
                        Shark now = _map[i][j];
                        //System.out.println("SHARK : "+i +" "+j);
                        int _direction = now.d;
                        int _speed = now.s;
                        int _size = now.z;
                        int x = i;
                        int y = j;
                        if(_direction == 1){
                            //위
                            int _distance = _speed % ((r-1) * 2);
                            int adder = -1;
                            while(_distance > 0){
                                if(x == 0 && adder <0){
                                    adder = -adder;
                                }
                                if(x == r-1 && adder > 0){
                                    adder = -adder;
                                }
                                x += adder;
                                _distance-=1;
                            }
                            //System.out.println(x+" "+y);
                            if(_newMap[x][y] == null){
                                if(adder < 0) {
                                    _newMap[x][y] = new Shark(_speed,1, _size);
                                }else{
                                    _newMap[x][y] = new Shark(_speed,2,_size);
                                }
                            }else if (_newMap[x][y].z < _size){
                                if(adder < 0) {
                                    _newMap[x][y] = new Shark(_speed,1, _size);
                                }else{
                                    _newMap[x][y] = new Shark(_speed,2,_size);
                                }
                            }
                        }
                        if(_direction == 2){
                            //아래
                            int _distance = _speed % ((r-1) * 2);
                            int adder = 1;
                            while(_distance > 0){
                                if(x == 0 && adder <0){
                                    adder = -adder;
                                }
                                if(x == r-1 && adder > 0){
                                    adder = -adder;
                                }
                                x += adder;
                                _distance-=1;
                            }
                            //System.out.println(x+" "+y);
                            if(_newMap[x][y] == null){
                                if(adder < 0) {
                                    _newMap[x][y] = new Shark(_speed,1, _size);
                                }else{
                                    _newMap[x][y] = new Shark(_speed,2,_size);
                                }
                            }else if (_newMap[x][y].z < _size){
                                if(adder < 0) {
                                    _newMap[x][y] = new Shark(_speed,1, _size);
                                }else{
                                    _newMap[x][y] = new Shark(_speed,2,_size);
                                }
                            }
                        }
                        if(_direction == 3){
                            //왼쪽
                            int _distance = _speed % ((c -1) * 2);
                            int adder = 1;
                            while(_distance > 0){
                                if(y == 0 && adder <0){
                                    adder = -adder;
                                }
                                if(y == c-1 && adder > 0){
                                    adder = -adder;
                                }
                                y += adder;
                                _distance-=1;
                            }
                            //System.out.println(x+" "+y);
                            if(_newMap[x][y] == null){
                                if(adder < 0) {
                                    _newMap[x][y] = new Shark(_speed,4, _size);
                                }else{
                                    _newMap[x][y] = new Shark(_speed,3,_size);
                                }
                            }else if (_newMap[x][y].z < _size){
                                if(adder < 0) {
                                    _newMap[x][y] = new Shark(_speed,4, _size);
                                }else{
                                    _newMap[x][y] = new Shark(_speed,3,_size);
                                }
                            }
                        }
                        if(_direction == 4){
                            //오른쪽
                            int _distance = _speed % ((c-1) * 2);
                            int adder = -1;
                            while(_distance > 0){
                                if(y == 0 && adder <0){
                                    adder = -adder;
                                }
                                if(y == c-1 && adder > 0){
                                    adder = -adder;
                                }
                                y += adder;
                                _distance-=1;
                            }

                            //System.out.println(x+" "+y);
                            if(_newMap[x][y] == null){
                                if(adder < 0) {
                                    _newMap[x][y] = new Shark(_speed,4, _size);
                                }else{
                                    _newMap[x][y] = new Shark(_speed,3,_size);
                                }
                            }else if (_newMap[x][y].z < _size){
                                if(adder < 0) {
                                    _newMap[x][y] = new Shark(_speed,4, _size);
                                }else{
                                    _newMap[x][y] = new Shark(_speed,3,_size);
                                }
                            }
                        }
                    }
                }
            }
            _map = _newMap;
        }
        System.out.println(answer);
    }
    public static class Shark{
        int s;
        int d;
        int z;
        public Shark(int s, int d, int z){
            this.s = s;
            this.d  = d;
            this.z = z;
        }
    }
}

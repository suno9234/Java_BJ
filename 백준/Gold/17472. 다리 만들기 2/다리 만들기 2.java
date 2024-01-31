import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int n,m;
    static int[][] _map;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,-1,0,1};
    static ArrayList<ArrayList<MyTup>> _graph = new ArrayList<>();
    static int answer;
    static int islands;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("C:\\Users\\신순호\\Desktop\\싸피\\PrevStudy\\src\\org\\baekjoon\\gold\\res\\sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        _map = new int[n][m];


        for(int i = 0 ; i < n ; i++) {
            tokens = br.readLine().split(" ");
            for(int j = 0 ; j <m;j++) {
                _map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        int idx = 2;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ;j < m ; j++) {
                if(_map[i][j] == 1) {
                    _map[i][j] = idx;
                    Queue<Integer> queue = new ArrayDeque<>();
                    queue.add(i*m+j);
                    while(!queue.isEmpty()) {
                        int now = queue.poll();
                        int x = now/m;
                        int y = now%m;
                        for(int k = 0 ; k < 4; k++) {
                            int nx = x+dx[k];
                            int ny = y+dy[k];
                            if(nx>=0 && ny>=0 && nx<n && ny<m && _map[nx][ny]==1) {
                                _map[nx][ny] = idx;
                                queue.add(nx*m+ny);
                            }
                        }
                    }
                    _graph.add(new ArrayList<>());
                    islands++;
                    idx+=1;
                }
            }
        }
        for(int i = 0 ; i < n;i++) {
            for(int j = 0 ; j < m; j++) {
                if(_map[i][j] == 0) {
                    for(int k = 0 ; k < 4 ; k++) {
                        int nx = i+dx[k];
                        int ny = j+dy[k];
                        if(nx>=0 && ny>=0 && nx<n && ny<m && _map[nx][ny]>1) {
                            int start = _map[nx][ny];
                            int dir = (k+2)%4;
                            int dist = 0;
                            int dest = -1;

                            while(true) {
                                if(nx<0 || ny <0 || nx >=n || ny>=m || ( dist >= 1  && _map[nx][ny] == start)) {
                                    // 범위 밖으로 나가면 종료
                                    break;
                                }else if(_map[nx][ny]>1 && _map[nx][ny]!=start) {
                                    // 다른 섬을 만난 경우만 dest > 0
                                    dest = _map[nx][ny];
                                    dist--;
                                    break;
                                }
                                nx +=dx[dir];
                                ny +=dy[dir];
                                dist++;
                            }
                            if(dist >= 2 && dest>1) {
                                _graph.get(start-2).add(new MyTup(dist,dest-2));
                            }

                        }
                    }
                }

            }
        }
        boolean [] _visited = new boolean[islands];
        _visited[0] = true;
        PriorityQueue<MyTup> pq = new PriorityQueue<>();
        ArrayList<MyTup> _nexts = _graph.get(0);
        for(MyTup e : _nexts) {
            pq.add(e);
        }


        while(!pq.isEmpty()) {
            MyTup now = pq.poll();
            if(!_visited[now.dest]) {
                //System.out.println(now.dest);
                answer+=now.dist;
                _visited[now.dest]=true;
                _nexts = _graph.get(now.dest);
                for(MyTup e : _nexts) {
                    if(!_visited[e.dest]) {
                        pq.add(e);
                    }
                }
            }
        }
        for(int i = 0 ; i < islands; i++) {
            if(!_visited[i]) {
                System.out.println("-1");
                return;
            }
        }
        System.out.println(answer);
    }
}

class MyTup implements Comparable<MyTup>{
    int dist,dest;
    public MyTup(int dist,int dest) {
        this.dist = dist;
        this.dest = dest;
    }

    @Override
    public int compareTo(MyTup o) {
        return this.dist-o.dist;
    }
}

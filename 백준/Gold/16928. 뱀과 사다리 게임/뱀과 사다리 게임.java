
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine()," ");
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int [] _map = new int[101];
        int [] dist = new int[101];
        Arrays.fill(dist,99999);
        dist[1] = 0;
        boolean [] _visited = new boolean[101];
        Arrays.fill(_visited,false);
        for (int i = 0 ; i < n+m ; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine()," ");
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            _map[x] = y;
        }
//        for (int i = 0 ; i < m ; i++){
//            stringTokenizer = new StringTokenizer(bufferedReader.readLine()," ");
//            int x = Integer.parseInt(stringTokenizer.nextToken());
//            int y = Integer.parseInt(stringTokenizer.nextToken());
//            _max[x] = y;
//        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int i = 1; i<7; i++){
                if(now+i < 101 && !_visited[now+i]){
                    if(_map[now+i] == 0){
                        _visited[now+i] = true;
                        dist[now+i] = dist[now]+1;
                        queue.add(now+i);
                    }else{
                        if(!_visited[_map[now+i]]){
                            _visited[_map[now+i]] = true;
                            dist[_map[now+i]] = dist[now]+1;
                            queue.add(_map[now+i]);
                        }
                    }
                }
            }
            // i = 1일때 dist[6] = 1
            // i = 6일때 i = 6이면 _map[12] = 98
        }
        //System.out.println(Arrays.toString(dist));
        //System.out.printf("%d %d %d %d\n",dist[1],dist[6],dist[98],dist[100]);
        System.out.println(dist[100]);
    }
}

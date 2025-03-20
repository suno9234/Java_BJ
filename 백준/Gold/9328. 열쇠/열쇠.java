import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int t,h,w,answer;
    static char [][] map;
    static boolean [] keys;
    static boolean [][] visited;
    static int [] dx = {1,-1,0,0};
    static int [] dy = {0,0,1,-1};
    static List[] cord;
    static StringBuilder sb ;
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());
        for(int tc = 0;tc<t;tc++){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            keys = new boolean[26];
            visited = new boolean[h][w];
            answer = 0;
            cord = new List[26];
            for(int i = 0; i < 26; i++){
                cord[i] = new ArrayList();
            }
            for(int i = 0; i < h; i++){
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                for(int j = 0; j < w; j++){
                    map[i][j] = s.charAt(j);
                }
            }
            st = new StringTokenizer(br.readLine());
            String keyString = st.nextToken();
            for(int i = 0; i < keyString.length(); i++){
                if(keyString.charAt(i)>='a' &&  keyString.charAt(i)<='z'){
                    keys[keyString.charAt(i)-'a'] = true;
                }
            }
            for(int i = 0 ; i < h ; i++){
                if(!visited[i][0]) {
                    if (map[i][0] == '$' || map[i][0] == '.' || (map[i][0] >= 'a' && map[i][0] <= 'z')) {
                        bfs(i, 0);
                    } else if (map[i][0] >= 'A' && map[i][0] <= 'Z'){
                        if(keys[map[i][0] + 32 - 'a']) {
                            bfs(i, 0);
                        }else{
                            cord[map[i][0]+32-'a'].add(new int[]{i,0});
                        }
                    }
                }
                if(!visited[i][w-1]) {
                    if (map[i][w - 1] == '$' || map[i][w - 1] == '.' || map[i][w - 1] >= 'a' && map[i][w - 1] <= 'z') {
                        bfs(i, w - 1);
                    } else if (map[i][w - 1] >= 'A' && map[i][w - 1] <= 'Z'){
                        if (keys[map[i][w - 1] + 32 - 'a']) {
                            bfs(i, w - 1);
                        }else{
                            cord[map[i][w-1]+32-'a'].add(new int[]{i,w-1});
                        }
                    }
                }
            }
            for(int i = 1 ; i < w-1 ; i++){
                if(!visited[0][i]){
                    if(map[0][i]=='$' || map[0][i] == '.' || (map[0][i] >='a' && map[0][i] <='z')){
                        bfs(0, i);
                    }else if(map[0][i] >='A' && map[0][i] <='Z'){
                        if(keys[map[0][i]+32-'a']) {
                            bfs(0, i);
                        }else{
                            cord[map[0][i]+32-'a'].add(new int[]{0,i});
                        }
                    }
                }
                if(!visited[h-1][i]) {
                    if (map[h - 1][i] == '$' || map[h - 1][i] == '.' || (map[h - 1][i] >= 'a' && map[h - 1][i] <= 'z')) {
                        bfs(h - 1, i);
                    } else if (map[h - 1][i] >= 'A' && map[h - 1][i] <= 'Z') {
                        if (keys[map[h - 1][i] + 32 - 'a']) {
                            bfs(h - 1, i);
                        } else {
                            cord[map[h - 1][i] + 32 - 'a'].add(new int[]{h - 1, i});
                        }
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static void bfs(int x, int y){
        ArrayDeque<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{x,y});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            x = now[0];
            y = now[1];
            visited[x][y] = true;
            if(map[x][y] == '$'){
                answer++;
            }else if(map[x][y] >='a' && map[x][y] <='z'){
                keys[map[x][y] - 'a'] = true;
                List<int []> nexts = cord[map[x][y]-'a'];
                for(int[] next : nexts){
                    if(!visited[next[0]][next[1]]){
                        visited[x][y] = true;
                        queue.add(new int[]{next[0],next[1]});
                    }
                }
            }
            for(int i = 0 ; i < 4 ; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx >= 0 && nx < h && ny >= 0 && ny < w && !visited[nx][ny] && map[nx][ny]!='*'){
                    if(map[nx][ny] >= 'A' && map[nx][ny] <= 'Z'){
                        // 문이 있는 경우
                        if(keys[map[nx][ny]+32-'a']){
                            // 열쇠를 가지고 있다면
                            visited[nx][ny] = true;
                            queue.add(new int[]{nx,ny});
                        }else{
                            // 나중에 열쇠를 먹었을 때 방문하라고 저장
                            cord[map[nx][ny]+32-'a'].add(new int[]{nx,ny});
                        }
                    }else if(map[nx][ny] >='a' && map[nx][ny] <= 'z'){
                        // 열쇠가 있는 경우
                        keys[map[nx][ny]-'a'] = true;
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx,ny});
                        List<int []> nexts = cord[map[nx][ny]-'a'];
                        for(int[] next : nexts){
                            if(!visited[next[0]][next[1]]){
                                visited[nx][ny] = true;
                                queue.add(new int[]{next[0],next[1]});
                            }
                        }
                    }else {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx,ny});
                    }
                }
            }
        }
    }
}

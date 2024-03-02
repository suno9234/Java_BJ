import java.io.*;
import java.util.Arrays;

public class Main {
	static int N ;
	static int [][] _map;
	static int posX,posY,dir;
	static int [] dx = {0,1,0,-1};
	static int [] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		_map = new int[N+4][N+4];
		for(int i = 0 ; i < N;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j = 0 ; j < N ; j++) {
				_map[i+2][j+2] = Integer.parseInt(tokens[j]);
			}
		}
		posX = 2+N/2;
		posY = 2+N/2;
		dir = 2;
		for(int i = 0 ; i < N;i++) {
			if(i == N-1) {
				for(int j = 0; j < i; j++) {
					int nx = posX + dx[dir];
					int ny = posY + dy[dir];
					int total = _map[nx][ny];
					int _sum = 0;
					_map[nx+dx[dir]*2][ny+dy[dir]*2] += (int)(total*0.05);
					_sum+=(int)(total*0.05);
					_map[posX+dx[(dir+1)%4]][posY+dy[(dir+1)%4]] += (int)(total*0.01);
					_sum+=(int)(total*0.01);
					_map[posX+dx[(dir+3)%4]][posY+dy[(dir+3)%4]] += (int)(total*0.01);
					_sum+=(int)(total*0.01);
					_map[nx+dx[(dir+1)%4]][ny+dy[(dir+1)%4]] += (int)(total*0.07);
					_sum+=(int)(total*0.07);
					_map[nx+dx[(dir+3)%4]][ny+dy[(dir+3)%4]] += (int)(total*0.07);
					_sum+=(int)(total*0.07);
					_map[nx+dx[(dir+1)%4]*2][ny+dy[(dir+1)%4]*2] += (int)(total*0.02);
					_sum+=(int)(total*0.02);
					_map[nx+dx[(dir+3)%4]*2][ny+dy[(dir+3)%4]*2] += (int)(total*0.02);
					_sum+=(int)(total*0.02);
					_map[nx+dx[(dir+1)%4]+dx[dir]][ny+dy[(dir+1)%4]+dy[dir]] += (int)(total*0.1);
					_sum+=(int)(total*0.1);
					_map[nx+dx[(dir+3)%4]+dx[dir]][ny+dy[(dir+3)%4]+dy[dir]] += (int)(total*0.1);
					_sum+=(int)(total*0.1);
					_map[nx][ny] = 0;
					_map[nx+dx[dir]][ny+dy[dir]] += total-_sum;
					posX = nx;
					posY = ny;
				}
				break;
			}
			
			for(int j = 0; j < i+1; j++) {
				int nx = posX + dx[dir];
				int ny = posY + dy[dir];
				int total = _map[nx][ny];
				int _sum = 0;
				_map[nx+dx[dir]*2][ny+dy[dir]*2] += (int)(total*0.05);
				_sum+=(int)(total*0.05);
				_map[posX+dx[(dir+1)%4]][posY+dy[(dir+1)%4]] += (int)(total*0.01);
				_sum+=(int)(total*0.01);
				_map[posX+dx[(dir+3)%4]][posY+dy[(dir+3)%4]] += (int)(total*0.01);
				_sum+=(int)(total*0.01);
				_map[nx+dx[(dir+1)%4]][ny+dy[(dir+1)%4]] += (int)(total*0.07);
				_sum+=(int)(total*0.07);
				_map[nx+dx[(dir+3)%4]][ny+dy[(dir+3)%4]] += (int)(total*0.07);
				_sum+=(int)(total*0.07);
				_map[nx+dx[(dir+1)%4]*2][ny+dy[(dir+1)%4]*2] += (int)(total*0.02);
				_sum+=(int)(total*0.02);
				_map[nx+dx[(dir+3)%4]*2][ny+dy[(dir+3)%4]*2] += (int)(total*0.02);
				_sum+=(int)(total*0.02);
				_map[nx+dx[(dir+1)%4]+dx[dir]][ny+dy[(dir+1)%4]+dy[dir]] += (int)(total*0.1);
				_sum+=(int)(total*0.1);
				_map[nx+dx[(dir+3)%4]+dx[dir]][ny+dy[(dir+3)%4]+dy[dir]] += (int)(total*0.1);
				_sum+=(int)(total*0.1);
				_map[nx][ny] = 0;
				_map[nx+dx[dir]][ny+dy[dir]] += total-_sum;
				posX = nx;
				posY = ny;
				
			}
			dir = (dir+3) % 4;
			for(int j = 0; j < i+1; j++) {
				int nx = posX + dx[dir];
				int ny = posY + dy[dir];
				int total = _map[nx][ny];
				int _sum = 0;
				_map[nx+dx[dir]*2][ny+dy[dir]*2] += (int)(total*0.05);
				_sum+=(int)(total*0.05);
				_map[posX+dx[(dir+1)%4]][posY+dy[(dir+1)%4]] += (int)(total*0.01);
				_sum+=(int)(total*0.01);
				_map[posX+dx[(dir+3)%4]][posY+dy[(dir+3)%4]] += (int)(total*0.01);
				_sum+=(int)(total*0.01);
				_map[nx+dx[(dir+1)%4]][ny+dy[(dir+1)%4]] += (int)(total*0.07);
				_sum+=(int)(total*0.07);
				_map[nx+dx[(dir+3)%4]][ny+dy[(dir+3)%4]] += (int)(total*0.07);
				_sum+=(int)(total*0.07);
				_map[nx+dx[(dir+1)%4]*2][ny+dy[(dir+1)%4]*2] += (int)(total*0.02);
				_sum+=(int)(total*0.02);
				_map[nx+dx[(dir+3)%4]*2][ny+dy[(dir+3)%4]*2] += (int)(total*0.02);
				_sum+=(int)(total*0.02);
				_map[nx+dx[(dir+1)%4]+dx[dir]][ny+dy[(dir+1)%4]+dy[dir]] += (int)(total*0.1);
				_sum+=(int)(total*0.1);
				_map[nx+dx[(dir+3)%4]+dx[dir]][ny+dy[(dir+3)%4]+dy[dir]] += (int)(total*0.1);
				_sum+=(int)(total*0.1);
				_map[nx][ny] = 0;
				_map[nx+dx[dir]][ny+dy[dir]] += total-_sum;
				posX = nx;
				posY = ny;
				
			}
			dir = (dir+3) % 4;
		}
		int answer = 0;
		for(int i = 0 ; i < N+4; i++) {
			for(int j = 0 ; j < N+4 ; j++) {
				if(i < 2 || j <2 || i >=N+2 || j >=N+2) {
					answer+=_map[i][j];
				}
			}
		}
		System.out.println(answer);
	}

}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{

	static int N,M,K;
	static int [] dx = {-1,-1,0,1,1,1,0,-1};
	static int [] dy = {0,1,1,1,0,-1,-1,-1};
	static ArrayList<FireBall> fireBalls ;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		K = Integer.parseInt(tokens[2]);
		fireBalls = new ArrayList<>();
		
		for(int p = 0 ; p < M ; p++) {
			tokens = br.readLine().split(" ");
			int  r,c,m,s,d;
			r = Integer.parseInt(tokens[0])-1;
			c = Integer.parseInt(tokens[1])-1;
			m = Integer.parseInt(tokens[2]);
			s = Integer.parseInt(tokens[3]);
			d = Integer.parseInt(tokens[4]);
			fireBalls.add(new FireBall(r,c,m,s,d,1));
		}
		for(int i = 0; i < K; i++) {
			FireBall [][] newMap = new FireBall[N][N];
			ArrayList<Integer> isFireBall = new ArrayList<>();
			ArrayList<FireBall> newFireBall = new ArrayList<>();
			for(FireBall fb : fireBalls) {
				//파이어볼 이동 + 합체
				FireBall fireBallA = newMap[(fb.r+dx[fb.d]*fb.s+N*1000)%N][(fb.c+dy[fb.d]*fb.s+N*1000)%N];
				if(fireBallA == null) {
					newMap[(fb.r+dx[fb.d]*fb.s+N*1000)%N][(fb.c+dy[fb.d]*fb.s+N*1000)%N] = new FireBall(
							(fb.r+dx[fb.d]*fb.s+N*1000)%N,(fb.c+dy[fb.d]*fb.s+N*1000)%N,
							fb.m,
							fb.s,
							fb.d,
							fb.cnt
							);
				}else {
					if(fireBallA.d%2 ==fb.d%2){
						// 방향이 모두 짝수거나 모두 홀수인 경우
						newMap[(fb.r+dx[fb.d]*fb.s+N*1000)%N][(fb.c+dy[fb.d]*fb.s+N*1000)%N]=new FireBall(
								(fb.r+dx[fb.d]*fb.s+N*1000)%N,(fb.c+dy[fb.d]*fb.s+N*1000)%N,
								fireBallA.m+fb.m,
								fireBallA.s+fb.s,
								fireBallA.d,
								fireBallA.cnt+1
								);
					}else {
						// 방향이 다른 경우
						newMap[(fb.r+dx[fb.d]*fb.s+N*1000)%N][(fb.c+dy[fb.d]*fb.s+N*1000)%N]=new FireBall(
								(fb.r+dx[fb.d]*fb.s+N*1000)%N,(fb.c+dy[fb.d]*fb.s+N*1000)%N,
								fireBallA.m+fb.m,
								fireBallA.s+fb.s,
								-1,
								fireBallA.cnt+1
								);
					}
				}
				if(!isFireBall.contains(((fb.r+dx[fb.d]*fb.s+N*1000)%N)*N+(fb.c+dy[fb.d]*fb.s+N*1000)%N)) {
					isFireBall.add(((fb.r+dx[fb.d]*fb.s+N*1000)%N)*N+(fb.c+dy[fb.d]*fb.s+N*1000)%N);
				}
			}
			
			for(Integer fbcoor : isFireBall) {
				FireBall fb = newMap[fbcoor/N][fbcoor%N];
				if(fb.cnt == 1) {
					newFireBall.add(fb);
				}
				else if(fb.m >= 5) {
					if(fb.d >= 0) {
						for(int j = 0 ; j < 4 ; j++) {
							newFireBall.add(new FireBall(fb.r,fb.c,fb.m/5,fb.s/fb.cnt,j*2,1));
						}
					}else {
						for(int j = 0 ; j < 4 ; j++) {
							newFireBall.add(new FireBall(fb.r,fb.c,fb.m/5,fb.s/fb.cnt,j*2+1,1));
						}
					}
				}
			}
			
			fireBalls = newFireBall;
		}
		int answer = 0;
		for(FireBall fb : fireBalls) {
			answer+=fb.m;
		}
		System.out.println(answer);
	}
	
	static class FireBall{
		int r,c,m,s,d,cnt;
		public FireBall(int r, int c ,int m,int s, int d,int cnt) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
			this.cnt = cnt;
		}
	}
}

import java.io.*;
import java.util.*;

public class Main{
	static int n;
	static double [][] _map;
	static List<double[]>[] graph;
	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		_map = new double[n][2];
		for (int i = 0; i < n; i++) {
			tokens = br.readLine().split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			_map[i] = new double[] { x, y };
		}
		graph = new List[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < n; i++) {
			double x = _map[i][0];
			double y = _map[i][1];
			for (int j = i + 1; j < n; j++) {
				double nx = _map[j][0];
				double ny = _map[j][1];
				double dist = Math.sqrt(Math.pow(x - nx,2)+Math.pow((y - ny),2));
				graph[i].add(new double[] { j, dist });
				graph[j].add(new double[] { i, dist });
			}
		}
		double answer = 0;
		int cnt = 0;
		boolean[] v = new boolean[n];
		PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1[1], o2[1]));
		pq.add(new double[] { 0, 0 });
		while (!pq.isEmpty()) {
			double[] now = pq.poll();
			int pos =(int)now[0];
			double dist = now[1];
			if(v[pos] )continue;
			v[pos] = true;
			cnt++;
			answer+= dist;
			if(cnt == n) {
				System.out.printf("%.2f",answer);
				return;
			}
			for(double [] next : graph[pos]) {
				int np = (int)next[0];
				double nd = next[1];
				if(!v[np]) pq.add(new double[] {np,nd});
			}
		}
		System.out.println(answer);
		br.close();
	}
}

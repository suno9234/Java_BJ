import java.util.*;
import java.io.*;

public class Main{

	static int n,m,r;
	static int [][] _map ;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		r = Integer.parseInt(tokens[2]);
		_map = new int[n][m];
		for(int i = 0 ; i < n ; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0 ; j < m ; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		
		tokens = br.readLine().split(" ");
		int [] orders = new int[r];
		for(int i = 0 ; i < r ; i++) {
			orders[i] = Integer.parseInt(tokens[i]);
		}
		for(int o : orders) {
			switch(o) {
			case 1:
				oper1();
				break;
			case 2:
				oper2();
				break;
			case 3:
				oper3();
				break;
			case 4:
				oper4();
				break;
			case 5:
				oper5();
				break;
			case 6:
				oper6();
				break;
			}
		}
		for(int i = 0 ; i < _map.length; i++) {
			for(int j = 0 ; j < _map[0].length;j++) {
				sb.append(_map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		
	}
	static void oper1() {
		int [][] newMap = new int[_map.length][_map[0].length];
		for(int i = 0 ; i < _map.length ; i++) {
			for(int j = 0 ; j < _map[0].length ; j++) {
				newMap[_map.length-i-1][j] = _map[i][j];
			}
		}
		_map = newMap;
	}
	static void oper2() {
		int [][] newMap = new int[_map.length][_map[0].length];
		for(int i = 0 ; i <_map.length ; i++) {
			for(int j = 0 ; j <_map[0].length ; j++) {
				newMap[i][_map[0].length-j-1] = _map[i][j];
			}
		}
		_map = newMap;
	}
	static void oper3() {
		int [][] newMap = new int[_map[0].length][_map.length];
		for(int i = 0 ; i < _map[0].length ; i++) {
			for(int j = 0 ; j < _map.length ; j++) {
				newMap[i][j] = _map[_map.length-j-1][i];
			}
		}
		_map = newMap;
	}
	static void oper4() {
		int [][] newMap = new int[_map[0].length][_map.length];
		for(int i = 0 ; i < _map[0].length ; i++) {
			for(int j = 0 ; j < _map.length ; j++) {
				newMap[i][j] = _map[j][_map[0].length-1-i];
			}
		}
		_map = newMap;
	}
	static void oper6() {
		int [][] newMap = new int[_map.length][_map[0].length];
		for(int i = 0 ; i < _map.length/2; i++) {
			for(int j = 0 ; j < _map[0].length/2;j++) {
				newMap[i][j] = _map[i][j+_map[0].length/2] ;
				newMap[i][j+_map[0].length/2] =_map[i+_map.length/2][j+_map[0].length/2]; 
				newMap[i+_map.length/2][j+_map[0].length/2] = _map[i+_map.length/2][j];
				newMap[i+_map.length/2][j] = _map[i][j];
			}
		}
		_map  =newMap;
	}

	static void oper5() {
		int [][] newMap = new int[_map.length][_map[0].length];
		for(int i = 0 ; i < _map.length/2; i++) {
			for(int j = 0 ; j < _map[0].length/2;j++) {
				newMap[i][j] = _map[i+_map.length/2][j];
				newMap[i+_map.length/2][j] = _map[i+_map.length/2][j+_map[0].length/2];
				newMap[i+_map.length/2][j+_map[0].length/2] = _map[i][j+_map[0].length/2];
				newMap[i][j+_map[0].length/2] = _map[i][j];
			}
		}
		
		_map  =newMap;
	}
	
}

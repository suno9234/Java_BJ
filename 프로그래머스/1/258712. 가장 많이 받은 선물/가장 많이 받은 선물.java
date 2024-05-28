import java.util.*;
import java.io.*;

class Solution {
    static Map<String, Integer> hm = new HashMap<>();
    static Map<Integer,String> hm2 = new HashMap<>();
    static int [][] history;
    static int [] result;
    public int solution(String[] friends, String[] gifts) throws Exception {
        int answer = 0;
        int idx = 0;
        int len = friends.length;
        history = new int[len][len];
        result = new int[len];
        for(String friend : friends){
            hm.put(friend,idx);
            hm2.put(idx++,friend);
        }
        for(String gift : gifts){
            String [] tokens = gift.split(" ");
            int a = hm.get(tokens[0]);
            int b = hm.get(tokens[1]);
            history[a][b]++;
        }
        for(int i = 0 ; i < len ; i++){
            for(int j = i+1 ; j < len; j++){
                // i <=> j 간 선물
                if(history[i][j] > history[j][i]){
                    // i가 j한테 선물을 더 많이 줬으면
                    result[i]++;
                }else if(history[i][j] < history[j][i]){
                    result[j]++;
                }else{
                    int pzI = 0;
                    int pzJ = 0;
                    for(int k = 0 ; k < len; k++){
                        pzI += history[i][k];
                        pzI -= history[k][i];
                        pzJ += history[j][k];
                        pzJ -= history[k][j];
                    }
                    if(pzI > pzJ){
                        result[i]++;
                    }else if(pzJ > pzI){
                        result[j]++;
                    }
                }
            }
        }
        for(int i = 0 ; i < len ; i++){
            answer  = Math.max(answer,result[i]);
        }
        return answer;
    }
}
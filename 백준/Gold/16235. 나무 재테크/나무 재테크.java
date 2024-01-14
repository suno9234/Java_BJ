import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        int n,m,k;
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        k = Integer.parseInt(tokens[2]);

        int [][] A = new int[n][n];
        int [][] energy = new int[n][n];
        for(int [] e : energy){
            Arrays.fill(e,5);
        }
        ArrayList<Integer>[][] trees = new ArrayList[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0; j< n ; j++){
                trees[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0 ; i < n ; i++){
            String [] nl = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < n ; j++){
                A[i][j] = Integer.parseInt(nl[j]);
            }
        }
        for(int i = 0 ; i < m ; i++){
            int x,y,z;
            tokens = bufferedReader.readLine().split(" ");
            x = Integer.parseInt(tokens[0]);
            y = Integer.parseInt(tokens[1]);
            z = Integer.parseInt(tokens[2]);
            trees[x-1][y-1].add(z);
        }

        for(int i = 0 ; i < k ; i++){
            // 봄
            int [][] newEnergy  = new int[n][n];

            for(int j = 0 ; j < n ; j++){
                for(int l = 0 ; l < n ; l++){
                    ArrayList<Integer> nextTree = new ArrayList<Integer>();
                    for( int tree : trees[j][l]){
                        if(energy[j][l] >= tree){
                            energy[j][l] -= tree;
                            nextTree.add(tree+1);
                        }else{
                            newEnergy[j][l]+=tree/2;
                        }
                    }
                    trees[j][l]=nextTree;
                    energy[j][l] += newEnergy[j][l];
                }
            }

            // 가을
            ArrayList<Integer> [][] newTrees = new ArrayList[n][n];
            for(int l = 0 ; l < n ; l++){
                for(int j = 0; j< n ; j++){
                    newTrees[l][j] = new ArrayList<>();
                }
            }
            for(int j = 0 ; j < n ; j++){
                for(int l = 0 ; l < n ; l++) {
                    energy[j][l] += A[j][l];
                    for(int t : trees[j][l]){
                        if(t % 5 == 0){
                            if(j+1 < n && l+1 < n){
                                newTrees[j+1][l+1].add(1);
                            }
                            if(j-1 >= 0 && l+1 < n){
                                newTrees[j-1][l+1].add(1);
                            }
                            if(j+1 < n && l-1 >= 0){
                                newTrees[j+1][l-1].add(1);
                            }
                            if(j-1 >= 0  && l-1 >= 0){
                                newTrees[j-1][l-1].add(1);
                            }

                            if(j+1 < n){
                                newTrees[j+1][l].add(1);
                            }
                            if( l+1 < n){
                                newTrees[j][l+1].add(1);
                            }
                            if(j-1 >=0){
                                newTrees[j-1][l].add(1);
                            }
                            if( l-1>=0 ){
                                newTrees[j][l-1].add(1);
                            }
                        }
                    }
                }
            }
            for(int j = 0 ; j < n ; j++){
                for(int l = 0 ; l < n ; l++){
                    newTrees[j][l].addAll(trees[j][l]);
                    trees[j][l] = newTrees[j][l];
                }
            }

        }
        int answer = 0;
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                answer+=trees[i][j].size();
            }
        }
        System.out.println(answer);
    }
}

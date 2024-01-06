import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int answer = 0;
        int [] arr = new int[50001];
        HashSet<Integer> h1 = new HashSet<Integer>();
        HashSet<Integer> h2 = new HashSet<Integer>();
        HashSet<Integer> h3 = new HashSet<Integer>();
        for(int i = 1; i <(int)Math.sqrt(50001);i++){
            arr[i*i] = 1;
            h1.add(i*i);
        }
        for(int n1 : h1){
            for (int n2 : h1){
                if (n1+n2<50001 && !h1.contains(n1+n2) ){
                    h2.add(n1+n2);
                    arr[n1+n2] = 2;
                }
            }
        }

        for(int n1: h1){
            for(int n2: h2){
                if(n1+n2<50001 && arr[n1+n2] == 0  ){
                    h3.add(n1+n2);
                    arr[n1+n2] = 3;
                }
            }
        }
        for(int i = 4; i<50001;i++){
            if(arr[i] == 0){
                arr[i] = 4;
            }
        }
        System.out.println(arr[n]);
    }
}

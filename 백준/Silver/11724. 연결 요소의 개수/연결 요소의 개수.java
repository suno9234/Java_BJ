import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int [] parent = new int[1001];
        for (int i = 0 ; i <m;i++){
            int a = scanner.nextInt();
            int b= scanner.nextInt();
            if(a >= b ){
                makeParent(parent,b,a);
            }else{
                makeParent(parent,a,b);
            }
        }
        int answer = 0;
        for(int i = 1 ; i <n+1;i++ ){
            if (parent[i] == 0){
                answer+=1;
            }
        }

        System.out.println(answer);
    }
    public static void makeParent(int [] parent , int smaller, int bigger){
        int smallerP =  parent[smaller];
        if (smallerP == 0 ){
            smallerP = smaller;
        }
        int biggerP = parent[bigger];
        if(biggerP == 0){
            biggerP = bigger;
        }
        if(smallerP < biggerP){
            parent[bigger] = smallerP;
            parent[biggerP] = smallerP;
            for(int i = 1 ; i < 1001;i++){
                if (parent[i] == biggerP){
                    parent[i] = smallerP;
                }
            }
        }else if(biggerP < smallerP){
            parent[smaller] = biggerP;
            parent[smallerP] = biggerP;
            for(int i = 1 ; i < 1001;i++){
                if(parent[i] == smallerP){
                    parent[i] = biggerP;
                }
            }
        }
    }
}

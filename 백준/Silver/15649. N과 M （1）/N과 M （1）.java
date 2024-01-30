import java.util.Scanner;

public class Main{
    public static void main(String args[]){

        int n,m;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        int arr[] = new int[n];
        boolean visited[] = new boolean [n];


        func(0,n,m,arr,visited);
        
    }
    static void func(int count,int n,int m,int[] arr,boolean[] visited){
        if(count == m){
            for(int i = 0;i<m;i++){
                System.out.print(arr[i]);
                System.out.print(" ");
            }
            System.out.println("");
            return;
        }
        for(int i = 0;i<n;i++){ //0~n-1까지
            if(!visited[i]){ //방문 안했으면
                visited[i] = true; //방문 체크
                arr[count] = i+1;  //arr[count] = i+1 (1~n) 
                func(count+1,n,m,arr,visited);  // count = visited[true] 개수 
                visited[i] = false;
            }
        }
    }

   
}
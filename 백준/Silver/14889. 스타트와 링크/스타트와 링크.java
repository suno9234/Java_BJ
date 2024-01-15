import java.util.Scanner;

public class Main {
    static int n;
    static int arr[][];
    static int nums[];
    static int nums2[];
    static int firstC=0;
    static int secondC=0;
    static boolean visited[];
    static int min = 2100000000;

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        arr = new int[n][n];
        nums = new int[n / 2];
        nums2 = new int[n/2];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
        func(0,0);
        System.out.println(min);
    }

    static void func(int start, int counter) {
        if (counter == n / 2) {
            int calculated = calc();
            if (calculated < min) {
                min = calculated;
            }
            return;
        }
            for (int i = start; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    nums[counter] = i;
                    func(i+1,counter + 1);
                    visited[i] = false;
                }
            }

        

    }
    //nums[n/2] 에 내가 뽑은 숫자들이 들어있음
    static int calc(){

        int c=0;
        for(int i =0;i<n;i++){
            int idx = 0;
            for(idx=0;idx<n/2;idx++){
                if(nums[idx]==i){
                    idx=0;
                    break;
                }
            }
            if(idx ==n/2){
                nums2[c]=i;
                c++;
            }
            

        }
        
        
        firstC=0;
        for(int i=0;i<n/2-1;i++){
            for(int j=i+1;j<n/2;j++){
                firstC+=arr[nums[i]][nums[j]];
                firstC+=arr[nums[j]][nums[i]];
            }
        }
        secondC=0;
        for(int i=0;i<n/2-1;i++){
            for(int j=i+1;j<n/2;j++){
                secondC+=arr[nums2[i]][nums2[j]];
                secondC+=arr[nums2[j]][nums2[i]];
            }
        }
        if(firstC-secondC<0){
            return secondC-firstC;
        }else{
            return firstC-secondC;
        }

        
    }    
}
    

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        String s = scanner.nextLine();
        stringBuilder.append("I");
        for(int i = 0 ; i < n; i++){
            stringBuilder.append("OI");
        }
        String p = stringBuilder.toString();
        String now = "";
        stringBuilder.setLength(0);
        stringBuilder.append(s.substring(0,2*n+1));
        int answer = 0;
        for(int i = 0; i < m-(2*n+1);i++){
            now = stringBuilder.toString();
            if(now.equals(p)){
                answer+=1;
            }
            stringBuilder.deleteCharAt(0);
            stringBuilder.append(s.charAt(2*n+1+i));
        }
        now = stringBuilder.toString();
        if(now.equals(p)){
            answer+=1;
        }
        System.out.println(answer);
    }
}

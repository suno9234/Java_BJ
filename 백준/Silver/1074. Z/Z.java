import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n,r,c;

        n = scanner.nextInt();
        r = scanner.nextInt();
        c = scanner.nextInt();

        int answer = 0;
        int divider = (int)Math.pow(2,15);
        // r행 c열
        while (true){
            if(divider <= r) {
                answer += 2 * (divider) * (divider);
            }
            if (r/divider > 0){
                r = r %divider;
            }
            if (divider == 1){
                break;
            }
            divider /= 2;

        }
        divider = (int)Math.pow(2,15);
        while(true){
            if(divider<= c){
                answer+= (divider)*(divider);
            }
            if(c/divider > 0){
                c = c % divider;
            }
            if(divider == 1){
                break;
            }
            divider /= 2;

        }
        System.out.println(answer);


    }
}
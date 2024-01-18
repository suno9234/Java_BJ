
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for(int tc = 0 ; tc< t ; tc++ ) {
            int n = Integer.parseInt(br.readLine());
            Cube cube = new Cube();
            String [] tokens = br.readLine().split(" ");
            for(String token : tokens){
                cube.turn(token.charAt(0),token.charAt(1));
            }
            for(int i = 0 ; i <3;i++){
                for(int j = 0 ; j < 3 ; j++){
                    stringBuilder.append(cube.U[i][j]);
                }
                stringBuilder.append("\n");
            }
        }
        System.out.println(stringBuilder.toString());
    }

}
class Cube{
    char [][] U = {{'w','w','w'},{'w','w','w'},{'w','w','w'}};  //cube[0] = 윗면
    char [][] F = {{'r','r','r'},{'r','r','r'},{'r','r','r'}};  // cube[1] =
    char [][] D = {{'y','y','y'},{'y','y','y'},{'y','y','y'}};
    char [][] B = {{'o','o','o'},{'o','o','o'},{'o','o','o'}};
    char [][] L = {{'g','g','g'},{'g','g','g'},{'g','g','g'}};
    char [][] R = {{'b','b','b'},{'b','b','b'},{'b','b','b'}};

    public void turn(char surface , char direction) {
        char [] temp ;
        char [] sideTemp ;
        switch(surface) {
            case 'U':
                temp = new char[]{U[0][0],U[0][1]};
                sideTemp = new char[]{B[2][0],B[2][1],B[2][2]};
                if(direction == '+') {
                    U[0][1] = U[1][0];
                    U[0][0] = U[2][0];
                    U[1][0] = U[2][1];
                    U[2][0] = U[2][2];
                    U[2][1] = U[1][2];
                    U[2][2] = U[0][2];
                    U[1][2] = temp[1];
                    U[0][2] = temp[0];

                    B[2][2] = L[0][2];
                    B[2][1] = L[1][2];
                    B[2][0] = L[2][2];

                    L[0][2] = F[0][0];
                    L[1][2] = F[0][1];
                    L[2][2] = F[0][2];

                    F[0][0] = R[2][0];
                    F[0][1] = R[1][0];
                    F[0][2] = R[0][0];

                    R[2][0] = sideTemp[2];
                    R[1][0] = sideTemp[1];
                    R[0][0] = sideTemp[0];

                }else{
                    U[0][0] = U[0][2];
                    U[0][1] = U[1][2];
                    U[0][2] = U[2][2];
                    U[1][2] = U[2][1];
                    U[2][2] = U[2][0];
                    U[2][1] = U[1][0];
                    U[2][0] = temp[0];
                    U[1][0] = temp[1];

                    B[2][0] = R[0][0];
                    B[2][1] = R[1][0];
                    B[2][2] = R[2][0];

                    R[0][0] = F[0][2];
                    R[1][0] = F[0][1];
                    R[2][0] = F[0][0];

                    F[0][2] = L[2][2];
                    F[0][1] = L[1][2];
                    F[0][0] = L[0][2];

                    L[2][2] = sideTemp[0];
                    L[1][2] = sideTemp[1];
                    L[0][2] = sideTemp[2];

                }
                break;
            case 'L':
                temp = new char[]{L[0][0],L[0][1]};
                sideTemp = new char[]{D[0][0],D[1][0],D[2][0]};
                if(direction == '+'){
                    L[0][1] = L[1][0];
                    L[0][0] = L[2][0];
                    L[1][0] = L[2][1];
                    L[2][0] = L[2][2];
                    L[2][1] = L[1][2];
                    L[2][2] = L[0][2];
                    L[1][2] = temp[1];
                    L[0][2] = temp[0];

                    D[0][0] = F[0][0];
                    D[1][0] = F[1][0];
                    D[2][0] = F[2][0];

                    F[0][0] = U[0][0];
                    F[1][0] = U[1][0];
                    F[2][0] = U[2][0];

                    U[0][0] = B[0][0];
                    U[1][0] = B[1][0];
                    U[2][0] = B[2][0];

                    B[0][0] = sideTemp[0];
                    B[1][0] = sideTemp[1];
                    B[2][0] = sideTemp[2];

                }else{
                    L[0][0] = L[0][2];
                    L[0][1] = L[1][2];
                    L[0][2] = L[2][2];
                    L[1][2] = L[2][1];
                    L[2][2] = L[2][0];
                    L[2][1] = L[1][0];
                    L[2][0] = temp[0];
                    L[1][0] = temp[1];

                    D[0][0] = B[0][0];
                    D[1][0] = B[1][0];
                    D[2][0] = B[2][0];

                    B[0][0] = U[0][0];
                    B[1][0] = U[1][0];
                    B[2][0] = U[2][0];

                    U[0][0] = F[0][0];
                    U[1][0] = F[1][0];
                    U[2][0] = F[2][0];

                    F[0][0] = sideTemp[0];
                    F[1][0] = sideTemp[1];
                    F[2][0] = sideTemp[2];

                }
                break;
            case 'R':
                temp = new char[]{R[0][0],R[0][1]};
                sideTemp = new char[]{D[0][2],D[1][2],D[2][2]};
                if(direction == '+'){
                    R[0][1] = R[1][0];
                    R[0][0] = R[2][0];
                    R[1][0] = R[2][1];
                    R[2][0] = R[2][2];
                    R[2][1] = R[1][2];
                    R[2][2] = R[0][2];
                    R[1][2] = temp[1];
                    R[0][2] = temp[0];

                    D[0][2] = B[0][2];
                    D[1][2] = B[1][2];
                    D[2][2] = B[2][2];

                    B[0][2] = U[0][2];
                    B[1][2] = U[1][2];
                    B[2][2] = U[2][2];

                    U[0][2] = F[0][2];
                    U[1][2] = F[1][2];
                    U[2][2] = F[2][2];

                    F[0][2] = sideTemp[0];
                    F[1][2] = sideTemp[1];
                    F[2][2] = sideTemp[2];

                }else{
                    R[0][0] = R[0][2];
                    R[0][1] = R[1][2];
                    R[0][2] = R[2][2];
                    R[1][2] = R[2][1];
                    R[2][2] = R[2][0];
                    R[2][1] = R[1][0];
                    R[2][0] = temp[0];
                    R[1][0] = temp[1];

                    D[0][2] = F[0][2];
                    D[1][2] = F[1][2];
                    D[2][2] = F[2][2];

                    F[0][2] = U[0][2];
                    F[1][2] = U[1][2];
                    F[2][2] = U[2][2];

                    U[0][2] = B[0][2];
                    U[1][2] = B[1][2];
                    U[2][2] = B[2][2];

                    B[0][2] = sideTemp[0];
                    B[1][2] = sideTemp[1];
                    B[2][2] = sideTemp[2];
                }
                break;
            case 'F':
                temp = new char[]{F[0][0],F[0][1]};
                sideTemp = new char[]{D[0][0],D[0][1],D[0][2]};
                if(direction == '+'){
                    F[0][1] = F[1][0];
                    F[0][0] = F[2][0];
                    F[1][0] = F[2][1];
                    F[2][0] = F[2][2];
                    F[2][1] = F[1][2];
                    F[2][2] = F[0][2];
                    F[1][2] = temp[1];
                    F[0][2] = temp[0];

                    D[0][0] = R[2][2];
                    D[0][1] = R[2][1];
                    D[0][2] = R[2][0];

                    R[2][2] = U[2][2];
                    R[2][1] = U[2][1];
                    R[2][0] = U[2][0];

                    U[2][2] = L[2][2];
                    U[2][1] = L[2][1];
                    U[2][0] = L[2][0];

                    L[2][2] = sideTemp[0];
                    L[2][1] = sideTemp[1];
                    L[2][0] = sideTemp[2];

                }else{
                    F[0][0] = F[0][2];
                    F[0][1] = F[1][2];
                    F[0][2] = F[2][2];
                    F[1][2] = F[2][1];
                    F[2][2] = F[2][0];
                    F[2][1] = F[1][0];
                    F[2][0] = temp[0];
                    F[1][0] = temp[1];

                    D[0][2] = L[2][0];
                    D[0][1] = L[2][1];
                    D[0][0] = L[2][2];

                    L[2][0] = U[2][0];
                    L[2][1] = U[2][1];
                    L[2][2] = U[2][2];

                    U[2][0] = R[2][0];
                    U[2][1] = R[2][1];
                    U[2][2] = R[2][2];

                    R[2][0] = sideTemp[2];
                    R[2][1] = sideTemp[1];
                    R[2][2] = sideTemp[0];
                }
                break;
            case 'B':
                temp = new char[]{B[0][0],B[0][1]};
                sideTemp = new char[]{D[2][2],D[2][1],D[2][0]};
                if(direction == '+'){
                    B[0][1] = B[1][0];
                    B[0][0] = B[2][0];
                    B[1][0] = B[2][1];
                    B[2][0] = B[2][2];
                    B[2][1] = B[1][2];
                    B[2][2] = B[0][2];
                    B[1][2] = temp[1];
                    B[0][2] = temp[0];

                    D[2][2] = L[0][0];
                    D[2][1] = L[0][1];
                    D[2][0] = L[0][2];

                    L[0][0] = U[0][0];
                    L[0][1] = U[0][1];
                    L[0][2] = U[0][2];

                    U[0][0] = R[0][0];
                    U[0][1] = R[0][1];
                    U[0][2] = R[0][2];

                    R[0][0] = sideTemp[0];
                    R[0][1] = sideTemp[1];
                    R[0][2] = sideTemp[2];

                }else{
                    B[0][0] = B[0][2];
                    B[0][1] = B[1][2];
                    B[0][2] = B[2][2];
                    B[1][2] = B[2][1];
                    B[2][2] = B[2][0];
                    B[2][1] = B[1][0];
                    B[2][0] = temp[0];
                    B[1][0] = temp[1];

                    D[2][0] = R[0][2];
                    D[2][1] = R[0][1];
                    D[2][2] = R[0][0];

                    R[0][2] = U[0][2];
                    R[0][1] = U[0][1];
                    R[0][0] = U[0][0];

                    U[0][2] = L[0][2];
                    U[0][1] = L[0][1];
                    U[0][0] = L[0][0];

                    L[0][2] = sideTemp[2];
                    L[0][1] = sideTemp[1];
                    L[0][0] = sideTemp[0];
                }
                break;
            case 'D':
                temp = new char[]{D[0][0],D[0][1]};
                sideTemp = new char[]{B[0][0],B[0][1],B[0][2]};
                if(direction == '+'){
                    D[0][1] = D[1][0];
                    D[0][0] = D[2][0];
                    D[1][0] = D[2][1];
                    D[2][0] = D[2][2];
                    D[2][1] = D[1][2];
                    D[2][2] = D[0][2];
                    D[1][2] = temp[1];
                    D[0][2] = temp[0];

                    B[0][0] = R[0][2];
                    B[0][1] = R[1][2];
                    B[0][2] = R[2][2];

                    R[0][2] = F[2][2];
                    R[1][2] = F[2][1];
                    R[2][2] = F[2][0];

                    F[2][2] = L[2][0];
                    F[2][1] = L[1][0];
                    F[2][0] = L[0][0];

                    L[2][0] = sideTemp[0];
                    L[1][0] = sideTemp[1];
                    L[0][0] = sideTemp[2];

                }else{
                    D[0][0] = D[0][2];
                    D[0][1] = D[1][2];
                    D[0][2] = D[2][2];
                    D[1][2] = D[2][1];
                    D[2][2] = D[2][0];
                    D[2][1] = D[1][0];
                    D[2][0] = temp[0];
                    D[1][0] = temp[1];

                    B[0][2] = L[0][0];
                    B[0][1] = L[1][0];
                    B[0][0] = L[2][0];

                    L[0][0] = F[2][0];
                    L[1][0] = F[2][1];
                    L[2][0] = F[2][2];

                    F[2][0] = R[2][2];
                    F[2][1] = R[1][2];
                    F[2][2] = R[0][2];

                    R[2][2] = sideTemp[2];
                    R[1][2] = sideTemp[1];
                    R[0][2] = sideTemp[0];

                }
                break;
        }
    }

}
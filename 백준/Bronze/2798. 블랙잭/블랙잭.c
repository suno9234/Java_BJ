#include<stdio.h>
int main(){
    int i,j,k,N,M;
    int arr[100];
    int max=0;
    scanf("%d",&N);//개수
    scanf("%d",&M);//최대값
    for(i=0;i<N;i++){
        scanf("%d",&arr[i]);
    }
    for(i=0;i<N-2;i++){
        for(j=i+1;j<N-1;j++){
            for(k=j+1;k<N;k++){
                if(max<(arr[i]+arr[j]+arr[k])&&(arr[i]+arr[j]+arr[k])<=M)
                    max=arr[i]+arr[j]+arr[k];
            }
        }
    }
    printf("%d",max);
}
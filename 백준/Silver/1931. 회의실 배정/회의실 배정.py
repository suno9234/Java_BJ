# 백준 1931
n = int(input())
arr = []
for i in range(n):
  s, e = map(int,input().split())
  arr.append([s,e])
arr.sort(key= lambda x:(x[1],x[0]))
#print(arr)

le = 0
answer = 0
for i in range(0,n):
  s,e = arr[i]
  if le <= s :
    le = e 
    answer+=1

print(answer)
cup = []

n = int(input())

for i in range(n):
  cup.append(int(input()))

if n  == 1:
  print(cup[0])
elif n == 2 :
  print(sum(cup))
else:
  dp= [0] * n
  dp[0] = cup[0]
  dp[1] = dp[0] + cup[1]
  dp[2] = max(dp[1] , dp[0]+cup[2] , cup[1]+cup[2])

  for i in range(3, n):
    dp[i] = max(
      dp[i-3] + cup[i-1] + cup[i], # 쉬 먹 먹
      dp[i-2] + cup[i] , # 먹 쉬 먹
      dp[i-1]  
      )
  print(max(dp))
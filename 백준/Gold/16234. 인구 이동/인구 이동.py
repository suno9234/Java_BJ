# 03:49
n,l,r = map(int,input().split())
_graph = []
_visited = [[-1]*(n) for _ in range(n)]
for i in range(n):
    _graph.append(list(map(int,input().split())))

dx = [0,0,-1,1]
dy = [1,-1,0,0]

answer = 0
def _union(start, unionNum):
    x,y = start
    _unionlist = [(x,y)]
    _unionSum = _graph[x][y]
    _visited[x][y] = unionNum
    #print(_visited)
    for i in range(4):
        nextX = x+dx[i]
        nextY = y+dy[i]
        if 0<= nextX < n and 0<=nextY <n:
            if _visited[nextX][nextY] == -1 and l<= abs(_graph[x][y]-_graph[nextX][nextY]) <=r:
                nextlist , nextSum = _union((nextX,nextY),unionNum)
                _unionlist+=nextlist
                _unionSum+=nextSum
    return _unionlist , _unionSum

while True:
    _visited = [[-1]*n for _ in range(n)]
    unionNumber = 0
    flag = 0
    for i in range(n):
        for j in range(n):
            if _visited[i][j] == -1:
                ul, us = _union((i,j),unionNumber)
                #print(ul, us)
                if len(ul) > 1 and flag == 0:
                    flag = 1
                    answer+=1
                for u in ul:
                    _graph[u[0]][u[1]] = us//len(ul)
                unionNumber+=1
    #print(_graph)
    if unionNumber  == 1 or unionNumber == n*n:
        break
    
print(answer)

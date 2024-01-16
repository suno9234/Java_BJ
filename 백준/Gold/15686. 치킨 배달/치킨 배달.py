from itertools import combinations
n,m = map(int,input().split())
_house = []
_chick = []
for i in range(1,n+1):
    # 0 = 빈칸 1 = 집 2 = 치킨집
    # n<=50 M<=13
    _info = list(map(int,input().split()))
    for j in range(n):
        if _info[j] == 1:
            _house.append([i,j+1])
        elif _info[j] == 2:
            _chick.append([i,j+1])

comb = list(combinations(_chick,m))
answer = 999999

for c in comb:
    _sum = 0
    for h in _house:
        _chickdist = 999999
        for c2 in c:
            _now = abs(c2[0]-h[0])+abs(c2[1]-h[1])
            if _now < _chickdist:
                _chickdist = _now
        _sum+=_chickdist
    if _sum < answer :
        answer = _sum


print(answer)
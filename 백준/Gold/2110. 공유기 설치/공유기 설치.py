# 08 22 
n,c = map(int,input().split())
h = []
for _ in range(n):
    h.append(int(input()))
h.sort()
# 2<= n <= 200000
# 2 <= c <= n

answer = -1
start = 0
end = h[-1]
# 1 2 4 8 9
def possible(distance):
    # c개의 공유기를 설치해야 됨
    idx = 1
    last = h[0]
    global c
    _c = c-1
    while idx<n and _c >0:
        now = h[idx]
        if now - last >=  distance :
            #print(idx)
            last = now
            _c-=1
        idx+=1
        
    if _c == 0 :
        #print("possible")
        return True
    #print("impossible")
    return False
while start <= end:
    #print(start,end)
    mid = (start+end)//2
    #print(mid)
    if possible(mid):
        answer = mid
        start = mid+1
    else :
        end = mid-1

print(answer)


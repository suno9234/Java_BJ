from itertools import permutations
def solution(user_id, banned_id):
    answer = 0
    ps = list(permutations(user_id,len(banned_id)))
    banned_id.sort()
    ps.sort()
    result =set()
    for p in ps:
        flag =0
        for i in range(len(banned_id)):
            s1 = p[i]
            s2 = banned_id[i]
            if len(s1) ==len(s2):
                
                for j in range(len(s1)):
                    if s2[j]=='*':
                        continue
                    elif s1[j]!=s2[j]:
                        flag =1
                        break
            else:
                break
        else:
            if flag ==0:
                p = list(p)
                p = sorted(p)
                result.add(tuple(p))
    answer = len(result)
    print(result)
    print(answer)
    return answer
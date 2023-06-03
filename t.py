# 틀림
def getDistance(a: tuple[int, int], b: tuple[int, int, int]) -> int:
    return abs(a[0] - b[0]) + abs(a[1] - b[1])


total = 0


def check(current: tuple[int, int], mint: list[tuple[int, int, int]], visited: list[bool], M: int, H: int, eat: int):
    global total
    for i in range(len(mint)):
        if visited[i]:
            continue
        distance = getDistance(current, mint[i])
        if mint[i][2] == 2:  # 민초
            if M >= distance:   # 초기체력 >= 거리
                visited[i] = True
                M += H
                M -= distance
                eat += 1
                check(current, mint, visited, M, H, eat)
                visited[i] = False
                M -= H
                M += distance
                eat -= 1
        elif mint[i][2] == 1:   # 홈
            if M >= distance:
                if total < eat:
                    total = eat


def solution():
    N, M, H = map(int, input().split())
    mint = []
    home = (-1, -1)
    for i in range(N):
        temp = list(map(int, input().split()))
        for j in range(N):
            if temp[j] == 1:
                home = (i, j)
                mint.append((i, j, 1))
            elif temp[j] == 2:
                mint.append((i, j, 2))
    visited = [False] * len(mint)
    check(home, mint, visited, M, H, 0)
    print(total)


solution()

N = int(input())

sumA = 0
sumB = 0

leadA = 0
leadB = 0

for _ in range(N):
    tmpA, tmpB = map(int,input().split())
    sumA += tmpA
    sumB += tmpB

    if sumA > sumB:
        if leadA < abs(sumA-sumB):
            leadA = abs(sumA-sumB)
    else:
        if leadB < abs(sumA-sumB):
            leadA = abs(sumA-sumB)

if leadA > leadB:
    print(1+" "+leadA)
else:
    print(2+" "+leadB)

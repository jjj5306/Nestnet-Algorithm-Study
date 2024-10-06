# 7576번 문제와 거의 같은 코드인데 이건 시간 초과고 7576번은 정답인 이유를 잘 모르겠어요.

import sys
from queue import Queue

m, n, h = list(map(int, sys.stdin.readline().split()))

arr = [[[0 for y in range(m)] for x in range(n)] for z in range(h)]
dist = [[[0 for y in range(m)] for x in range(n)] for z in range(h)]

q = Queue()

for z in range(h):
    for x in range(n):
        arr[z][x] = list(map(int, sys.stdin.readline().split()))
        for y in range(m):
            if arr[z][x][y] == 1:
                q.put([x, y, z])
            if arr[z][x][y] == 0:
                dist[z][x][y] = -1

mx = [1, -1, 0, 0, 0, 0]
my = [0, 0, 1, -1, 0, 0]
mz = [0, 0, 0, 0, 1, -1]

while not q.empty():
    cursor = q.get()
    for dir in range(6):
        nx = cursor[0] + mx[dir]
        ny = cursor[1] + my[dir]
        nz = cursor[2] + mz[dir]

        if nx < 0 or nx >= n or ny < 0 or ny >= m or nz < 0 or nz >= h:
            continue
        if dist[nz][nx][ny] >= 0:
            continue

        q.put([nx, ny, nz])
        dist[nz][nx][ny] = dist[cursor[2]][cursor[0]][cursor[1]] + 1

def isDone():
    ans = 0
    for z in range(h):
        for x in range(n):
            for y in range(m):
                if dist[z][x][y] == -1:
                    return -1
                ans = max(ans, dist[z][x][y])
    return ans

print(isDone())
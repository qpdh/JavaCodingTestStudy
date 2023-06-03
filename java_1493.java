import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class java_1493 {
    static int boxLength, boxWidth, boxHeight;
    static int n;
    static List<Cube> cubeList;
    static int widthList[];
    static boolean canMake = true;

    static class Cube {
        int size;
        int amount;

        public Cube(int size, int amount) {
            this.size = size;
            this.amount = amount;
        }
    }

    // length, width, height에 들어갈 수 있는 존재하는 큐브의 인덱스
    // 2^10 10
    // 2^9 20
    //
    // 1*1*1
    // -> 2번큐브 -> 
    static int findAppropriateCube(int width, int length, int height, int maxIndex) {
        if (cubeList.size() == 0) {
            canMake = false;
            return -1;
        }

        for (int i = maxIndex; i >= 0; i--) {
            Cube cube = cubeList.get(i);
            int cubeSize = cube.size;

            if (cubeList.get(i).amount == 0) {
                continue;
            }

            if ((cubeSize <= width && cubeSize <= length && cubeSize <= height)) {
                return i;
            }
        }

        return -1;
    }

    static long fillCube(int width, int length, int height, int maxIndex) {
        // 셋 중 하나가 0이면 필요없음
        if (width == 0 || length == 0 || height == 0) {
            return 0;
        }

        long result = 1;

        // 최대한 큰 것 부터 넣는것이 이득인가?
        // -> 이득이다. 큐브가 항상 2의 제곱 꼴이므로..

        // 큐브 선택하기
        // 박스를 여러 조각으로 나누기
        int cubeIndex = findAppropriateCube(width, length, height, maxIndex);

        // 적절한 큐브를 찾기 못하면
        if (cubeIndex == -1) {
            canMake = false;
            return -1;
        }

        Cube cube = cubeList.get(cubeIndex);

        // 큐브 제거하기
        cube.amount -= 1;

        long tmp = fillCube(cube.size, length - cube.size, height, cubeIndex);
        result += tmp;

        tmp = fillCube(width - cube.size, length, height, cubeIndex);
        result += tmp;

        tmp = fillCube(cube.size, cube.size, height - cube.size, cubeIndex);
        result += tmp;

        return result;
    }

    // 한 변에 놓을 수 있는 큐브의 최댓값
    static long solution() {
        long result = fillCube(boxWidth, boxLength, boxHeight, cubeList.size() - 1);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        boxLength = Integer.parseInt(st.nextToken());
        boxWidth = Integer.parseInt(st.nextToken());
        boxHeight = Integer.parseInt(st.nextToken());

        n = Integer.parseInt(br.readLine());
        cubeList = new ArrayList<>();

        widthList = new int[n];
        widthList[0] = 1;
        for (int i = 1; i < n; i++) {
            widthList[i] = widthList[i - 1] * 2;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int size = widthList[Integer.parseInt(st.nextToken())];
            int amount = Integer.parseInt(st.nextToken());

            if (amount == 0) {
                continue;
            }

            Cube cube = new Cube(size, amount);
            cubeList.add(cube);
        }

        long result = solution();
        result = canMake ? result : -1;

        System.out.println(result);

        br.close();
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class java_2437 {
    public static int findMin(PriorityQueue<Integer> sinkerList) {
        // sum 까지 만들 수 있음
        int sum = 0;

        while (!sinkerList.isEmpty()) {
            int tmp = sinkerList.poll();
            if (sum + 1 >= tmp) {
                sum += tmp;
            } else {
                return sum + 1;
            }
        }
        return sum + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> sinkerList = new PriorityQueue<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            sinkerList.add(Integer.parseInt(st.nextToken()));
        }

        int ret = findMin(sinkerList);

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.util.Iterator;
// import java.util.StringTokenizer;
// import java.util.TreeSet;

// public class java_2437 {
// static TreeSet<Integer> result = new TreeSet<>();

// public static void canMake(int sinkerList[]) {
// // 1개 써보기
// // ... N개 써보기

// for (int i = 0; i < sinkerList.length; i++) {
// useSinker(sinkerList, i, 0);
// }
// }

// public static void useSinker(int sinkerList[], int nowIndex, int num) {
// num += sinkerList[nowIndex];
// result.add(num);

// // if (num > weight)
// // return -1;

// // // 만들 수 있음!
// // if (num == weight) {
// // return weight;
// // }

// for (int i = 1; nowIndex + i < sinkerList.length; i++) {
// useSinker(sinkerList, nowIndex + i, num);
// }

// // return -1;
// }

// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

// // 1번째 방법
// // 1부터 만들 수 있는지 확인해보기
// // .. k 수가 만들어 질 수 없으면 k 반환. 끝.
// // 몇까지 만들어 볼 것인가?
// // k가 못만드는 수 까지 만들어 보기 -> 무한반복
// int N = Integer.parseInt(br.readLine());

// // N개의 추 입력받기
// int sinkerList[] = new int[N];
// StringTokenizer st = new StringTokenizer(br.readLine());
// for (int i = 0; i < N; i++) {
// sinkerList[i] = Integer.parseInt(st.nextToken());
// }

// // 추 정렬
// // Arrays.sort(sinkerList);

// // 1부터 시작.
// // 만들 수 있으면 1 늘리면서 만들어보기
// canMake(sinkerList);

// Iterator it = result.iterator();
// int pre = (int)it.next();
// int now=0;
// while(it.hasNext()){
// now = (int)it.next();
// if(now-pre != 1){
// break;
// }
// pre = now;
// }

// bw.write(pre+1 + "\n");

// bw.close();
// br.close();
// }
// // 2 번째 방법
// // 1개의 추를 쓰기, 2개의 추를 쓰기... n개의 추를 쓰기
// // => N^2임
// }

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.util.Arrays;
// import java.util.StringTokenizer;

// import jdk.tools.jlink.internal.SymLinkResourcePoolEntry;

// public class java_2437 {
// static boolean isSelected[];

// public static boolean canMake(int sinkerList[], int weight) {
// // 1개 써보기
// // ... N개 써보기

// for (int i = 0; i < sinkerList.length; i++) {
// if (useSinker(sinkerList, weight, i, 0) == weight) {
// // System.out.println(weight + "만들 수 있음");
// return true;
// }
// }

// return false;
// }

// public static int useSinker(int sinkerList[], int weight, int nowIndex, int
// num) {
// // 현재 선택된 추 더하기
// num += sinkerList[nowIndex];

// // 무게가 확인하려는 값보다 커지면..
// if (num > weight)
// return -1;

// // 만들 수 있음!
// if (num == weight) {
// return weight;
// }

// // 다음 추 선택
// for (int i = nowIndex + 1; i < sinkerList.length; i++) {
// if (useSinker(sinkerList, weight, i, num) == weight) {
// return weight;
// }
// }

// return -1;
// }

// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

// // 1번째 방법
// // 1부터 만들 수 있는지 확인해보기
// // .. k 수가 만들어 질 수 없으면 k 반환. 끝.
// // 몇까지 만들어 볼 것인가?
// // k가 못만드는 수 까지 만들어 보기 -> 무한반복
// int N = Integer.parseInt(br.readLine());

// // N개의 추 입력받기
// int sinkerList[] = new int[N];
// isSelected = new boolean[N];
// StringTokenizer st = new StringTokenizer(br.readLine());
// for (int i = 0; i < N; i++) {
// sinkerList[i] = Integer.parseInt(st.nextToken());
// }

// // // 추 정렬
// // Arrays.sort(sinkerList);

// // 1부터 시작.
// // 만들 수 있으면 1 늘리면서 만들어보기
// int ret = 1;
// while (true) {
// if (!canMake(sinkerList, ret)) {
// break;
// }
// //System.out.println(ret+"만들 수 있음");
// ret++;
// }

// bw.write(ret + "\n");

// bw.close();
// br.close();
// }
// // 2 번째 방법
// // 1개의 추를 쓰기, 2개의 추를 쓰기... n개의 추를 쓰기
// // => N^2임
// }

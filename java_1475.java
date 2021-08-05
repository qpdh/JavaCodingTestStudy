import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class java_1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 6은 9를 뒤집어서 사용 가능
        // 반대도 가능
        // 0~8 까지 배열을 만든 다음에 추가하는 방식?
        // 0~8까지 값 중에서 최대값을 찾기
        // 6이나 9가 가장 많이 필요한 경우는 / 2 세트 사면 됨
        String roomNumber = br.readLine();

        // 0 ~ 9까지
        int numberList[][] = new int[9][2];
        // 0 빈도수
        // 1 빈도수
        // 2 빈도수
        // 3 
        // 0 ~ 8 

        // 배열의 0 번째 열 초기화
        for (int i = 0; i < numberList.length; i++) {
            numberList[i][0] = i;
        }

        // 방 번호 집어넣기
        for (int i = 0; i < roomNumber.length(); i++) {
            int number = roomNumber.charAt(i) - '0';
            if (number == 9)
                number = 6;
            numberList[number][1] += 1;
        }

        // 6 사용 횟수 / 2 -> 올림처리
        numberList[6][1] = (int) Math.ceil(numberList[6][1] / 2.0);

        // 정렬
        // 사용한 숫자 수 오름차순으로
        Arrays.sort(numberList, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                // TODO Auto-generated method stub
                return o2[1] - o1[1];
            }

        });

        // 배열이 정렬이 잘 됐는지 출력해보기
        // for (int i = 0; i < numberList.length; i++) {
        // System.out.println(numberList[i][0] + " " + numberList[i][1]);
        // }

        bw.write(numberList[0][1] + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}

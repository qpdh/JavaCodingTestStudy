import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_2064 {

    static void printIPAddress(BufferedWriter bw, StringBuffer sb) throws IOException {
        bw.write(Integer.parseInt(sb.subSequence(0, 8).toString(), 2) + "");
        for (int i = 1; i < 4; i++) {
            bw.write("." + Integer.parseInt(sb.subSequence(i * 8, i * 8 + 8).toString(), 2));
        }
        bw.write("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        /*
         * 네트워크 주소 : 기본 :: IP주소 달라지기 시작했을 때 나머지 다 0으로 바꾸기
         * 네트워크 마스크 : 기본 :: 1 달라지기 시작했을 때 나머지 다 0으로 바꾸기
         */

        // 1. IP 10진수를 2진수로 바꾸기
        // System.out.printf("%032s", Integer.toBinaryString(123));

        // n개의 컴퓨터 IP 주소 입력받기
        int n = Integer.parseInt(br.readLine());

        // 4개씩 끊기
        String ipList[][] = new String[n][4];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ".");
            for (int j = 0; j < 4; j++) {
                int ipNumber = Integer.parseInt(st.nextToken());
                int ipPadding = Integer.parseInt(Integer.toBinaryString(ipNumber));
                ipList[i][j] = String.format("%08d", ipPadding);
            }
        }

        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < 4; j++) {
        // System.out.print(ipList[i][j] + ".");
        // }
        // System.out.println();
        // }

        StringBuffer networkAddress = new StringBuffer();
        StringBuffer networkMask = new StringBuffer();

        boolean isDone = false;
        // 비트 단위로 돌면서 언제 달라지는지 체크
        for (int i = 0; i < 4; i++) {
            // 8비트 씩
            for (int j = 0; j < 8; j++) {
                char bit = ipList[0][i].charAt(j);
                for (int k = 1; k < n; k++) {
                    char targetBit = ipList[k][i].charAt(j);
                    // 비트가 다르다면... 종료
                    if (bit != targetBit) {
                        isDone = true;
                        break;
                    }
                }
                if (isDone) {
                    break;
                } else {
                    networkAddress.append(bit + "");
                    networkMask.append("1");
                }
            }
            if (isDone) {
                break;
            }
        }

        // 나머지 비트 채우기
        for (int i = networkAddress.length() - 1; i < 32; i++) {
            networkAddress.append("0");
            networkMask.append("0");
        }

        // 네트워크 주소 출력
        printIPAddress(bw, networkAddress);

        // 네트워크 마스크 출력
        printIPAddress(bw, networkMask);

        // System.out.println(networkAddress.toString());

        br.close();
        bw.close();
    }
}

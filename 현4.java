import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 현4 {
    static int publicNumber;
    static int n;

    // 비밀번호 맵
    static HashMap<Integer, Integer> passwordMap;

    static String userInput;

    // 초기 값
    static ArrayList<Integer> numberList;

    static boolean isTimeToInputAddressPassword;
    static int address = 0;

    static int getNumber() {
        int result = 0;

        int exp = (int) Math.pow(10, numberList.size() - 1);

        while (!numberList.isEmpty()) {
            int number = numberList.remove(0);
            result += number * exp;
            exp /= 10;
        }

        return result;
    }

    static void checkCall() {
        // 이전에 누른 값이 없다면 -> 경비실 호출
        if (numberList.isEmpty()) {
            System.out.println("0000 SECURITY");
            return;
        }

        // 이전에 누른 값들이 존재한다면 -> 세대 호출
        // 없는 세대 호출 입력 체크
        // 4자리 이하여야 함
        if (numberList.size() > 4) {
            // 0000으로 초기화
            numberList.clear();
        }
        // 세대 값 추출
        int number = getNumber();
        // number가 실제로 있는 세대인가?
        if (passwordMap.get(number) != null) {
            System.out.printf("%04d CALL\n", number);
        }

    }

    // true를 반환하면 세대 비밀번호를 입력할 차례임
    static boolean checkEnter() {
        int number = getNumber();

        // 세대 비밀번호를 입력할 차례인가?
        if (isTimeToInputAddressPassword) {
            //
            int addressPassword = passwordMap.get(number);
            int passwordLength = Integer.toString(addressPassword).length();

            int inputNumberLength = Integer.toString(number).length();

            // 비밀번호가 일치하지 않는 경우
            // 입력 비밀번호가 더 작은 경우
            if (inputNumberLength < passwordLength) {
                return false;
            }
            while (Integer.toString(addressPassword).length() != Integer.toString(number).length()) {
                number /= 10;
            }

            if (number == addressPassword) {
                System.out.printf("%04d OPEN\n", number % 10000);
            }
        }

        // 입력한 것이 공동 비밀번호인가?
        if (publicNumber == number) {
            System.out.printf("%04d OPEN\n", publicNumber % 10000);
        }

        // 아니라면 세대 번호인가?
        else if (passwordMap.get(number) != null) {
            System.out.println("****");
            address = number;
            return true;
        }

        return false;
    }

    static void solution() {
        numberList = new ArrayList<>();

        isTimeToInputAddressPassword = false;

        for (int i = 0; i < userInput.length(); i++) {
            // 숫자를 누른 경우
            if ('0' <= userInput.charAt(i) && userInput.charAt(i) <= '9') {
                numberList.add(userInput.charAt(i) - '0');
            }

            // 호출 버튼을 누른 경우
            else if (userInput.charAt(i) == '!') {
                if (isTimeToInputAddressPassword) {
                    address = 0;
                    isTimeToInputAddressPassword = false;
                    numberList.clear();
                    continue;
                }
                checkCall();
            }

            // 확인 버튼을 누른 경우
            else if (userInput.charAt(i) == '*') {
                isTimeToInputAddressPassword = checkEnter();
            }

            // 취소 버튼을 누른 경우
            else if (userInput.charAt(i) == '#') {
                address = 0;
                isTimeToInputAddressPassword = false;
                numberList.clear();
            }
        }

        // numberList의 값 중 마지막 4자리 추출
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        passwordMap = new HashMap<>();

        publicNumber = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        // 데이터 삽입
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int address = Integer.parseInt(st.nextToken());
            int password = Integer.parseInt(st.nextToken());
            passwordMap.put(address, password);
        }

        userInput = br.readLine();

        solution();

        br.close();
    }
}

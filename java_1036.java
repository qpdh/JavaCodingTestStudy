import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

public class java_1036 {
    static int N;
    static int K;
    static String wordList[];
    static int result[];

    final static String alpha36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static HashMap<Character, Boolean> selectedAlphabet = new HashMap<>();
    static HashMap<Character, BigInteger> alphaSum = new HashMap<>();
    // a
    // b

    static void sumAlpha() {
        for (int i = 0; i < wordList.length; i++) {
            for (int j = 0; j < wordList[i].length(); j++) {
                // 현재 자릿수를 36진수화
                // 1234
                // 4자릿수 36^3 -> 36^(길이 - 현재 자릿수 - 1)
                BigInteger digit = new BigInteger("36").pow(wordList[i].length() - j - 1);

                // Z에서 뺀 값 저장
                int thisNumber = 35;
                // 문자인 경우
                if ('A' <= wordList[i].charAt(j) && wordList[i].charAt(j) <= 'Z') {
                    thisNumber -= (wordList[i].charAt(j) - 'A' + 10);
                }
                // 숫자인 경우
                else {
                    thisNumber -= (wordList[i].charAt(j) - '0');
                }

                alphaSum.put(wordList[i].charAt(j), alphaSum.getOrDefault(wordList[i].charAt(j), BigInteger.ZERO)
                        .add(digit.multiply(BigInteger.valueOf(thisNumber))));
            }
        }
    }

    static BigInteger sum() {
        BigInteger result = new BigInteger("0");

        for (int i = 0; i < wordList.length; i++) {
            for (int j = 0; j < wordList[i].length(); j++) {
                // 자릿수
                BigInteger digit = new BigInteger("36").pow(wordList[i].length() - j - 1);

                // 선택된 알파벳이라면...
                if (selectedAlphabet.getOrDefault(wordList[i].charAt(j), false)) {
                    // 35 곱해서 더하기
                    result = result.add(digit.multiply(BigInteger.valueOf(35)));
                }
                // 선택되지 않았다면..
                else {
                    int number = 0;
                    if ('A' <= wordList[i].charAt(j) && wordList[i].charAt(j) <= 'Z') {
                        number = wordList[i].charAt(j) - 'A' + 10;
                    } else {
                        number = wordList[i].charAt(j) - '0';
                    }
                    // 그냥 더하기
                    result = result.add(digit.multiply(BigInteger.valueOf(number)));
                }
            }
        }

        return result;
    }

    static String to36Digit(BigInteger bigInteger) {
        StringBuffer result = new StringBuffer();

        if (bigInteger.equals(BigInteger.ZERO)) {
            result.append("0");
        } else {
            // 0이 아닐 때 까지 반복
            while (bigInteger.compareTo(BigInteger.ZERO) != 0) {
                result.append(alpha36.charAt(bigInteger.remainder(BigInteger.valueOf(36)).intValue()));
                bigInteger = bigInteger.divide(BigInteger.valueOf(36));
            }
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        // 1. 최고 자릿수의 알파벳은 무조건 Z로 바꾼다.
        // 2. 최고 자릿수의 문자열이 많으면 오름차순으로 선택한다.
        // 3. 다음 자릿수의 알파벳을 무조건 Z로 바꾼다.
        // 4. 다음 자릿수의 문자열이 많으면 오름차순으로 선택한다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        wordList = new String[N];

        for (int i = 0; i < wordList.length; i++) {
            wordList[i] = br.readLine();
        }

        // 각 알파벳끼리 Z로 바꾸었을 때 차이 합을 저장하자
        sumAlpha();

        // 차이 내림차순 정렬
        ArrayList<HashMap.Entry<Character, BigInteger>> arrayList = new ArrayList<>(alphaSum.entrySet());

        Collections.sort(arrayList, new Comparator<HashMap.Entry<Character, BigInteger>>() {

            @Override
            public int compare(Entry<Character, BigInteger> o1, Entry<Character, BigInteger> o2) {
                // TODO Auto-generated method stub
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // K 입력 받기
        K = Integer.parseInt(br.readLine());
        int selectCount = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (selectCount == K) {
                break;
            } else {
                selectCount++;
                selectedAlphabet.put(arrayList.get(i).getKey(), true);
            }
        }

        // 더하기
        BigInteger bigIntegerSum = sum();

        // 36진수 변환
        String result = to36Digit(bigIntegerSum);

        System.out.println(result);

        br.close();
    }
}

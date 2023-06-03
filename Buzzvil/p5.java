package Buzzvil;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class p5 {

    public static String getOptimalString(String s) {
        // Write your code here

        // s의 길이가 홀수라면 "1"0"1"0"1"0"1" 형태로..
        // 만약 1이 많다면..
        // 1111000
        // 0001111
        // 000111 - 1
        // 111000 - 1
        // 11100 - 01
        // 00111 - 01
        // 0011 - 101
        // 1100 - 101
        // 110 - 0101
        // 011 - 0101
        // 01 - 10101
        // 10 - 10101
        // 1 - 010101
        // - 1010101

        // s의 0의 갯수, 1의 갯수 세기
        int zeroCount = 0, oneCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
                continue;
            }
            if (s.charAt(i) == '1') {
                oneCount++;
                continue;
            }
        }

        // 결과 b 만들기
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < oneCount; i++) {
            b.append('1');
        }
        for (int i = 0; i < zeroCount; i++) {
            b.append('0');
        }

        //
        StringBuffer result = new StringBuffer();

        while (b.length() > 0) {
            // 1. 역방향으로 뒤집기
            b = b.reverse();

            // 2. b의 맨 뒤에 있는 문자를 result 맨 앞에 두기
            StringBuffer tmp = new StringBuffer(b.charAt(b.length() - 1)).append(result);
            result = tmp;
            b.deleteCharAt(b.length() - 1);
        }

        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();

        String result = getOptimalString(s);

        System.out.println(result);

        bufferedReader.close();

    }
}

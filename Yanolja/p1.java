package Yanolja;

import java.util.Scanner;

public class p1 {

    public static int solution(String S) {
        // Implement your solution here
        // B의 갯수
        // A의 갯수
        // N의 갯수

        int bCount = 0, aCount = 0, nCount = 0;

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'B') {
                bCount++;
                continue;
            }
            if (S.charAt(i) == 'A') {
                aCount++;
                continue;
            }
            if (S.charAt(i) == 'N') {
                nCount++;
                continue;
            }
        }

        int result = 0;

        int bRemove = bCount / 1;
        int aRemove = aCount / 3;
        int nRemove = nCount / 2;

        result = Math.min(bRemove, aRemove);
        result = Math.min(result, nRemove);
        // while (bCount >= 1 && aCount >= 3 && nCount >= 2) {
        //     bCount -= 1;
        //     aCount -= 3;
        //     nCount -= 2;

        //     result++;
        // }

        return result;
    }

    public static void main(String[] args) {
        int result = solution("NAABXXAN");

        System.out.println(result);
    }
}

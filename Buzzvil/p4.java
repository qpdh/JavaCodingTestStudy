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

public class p4 {
    final static int MOD = 1000000007;

    public static int getMaxSumOfArray(List<Integer> arr1, List<Integer> arr2) {
        // Write your code here

        int result = 0;

        // arr1 내림차
        Collections.sort(arr1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // arr2 오름차
        Collections.sort(arr2);

        for (int i = 0; i < arr1.size(); i++) {
            int tmpSum = ((((arr2.get(i) - arr1.get(i)) % MOD) % MOD) * (i + 1)) % MOD;
            result = (result + tmpSum) % MOD;
        }

        return result;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int arr1Count = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr1 = IntStream.range(0, arr1Count).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int arr2Count = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr2 = IntStream.range(0, arr2Count).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = getMaxSumOfArray(arr1, arr2);

        System.out.println(result);

        bufferedReader.close();
    }
}

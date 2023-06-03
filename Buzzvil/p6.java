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

public class p6 {

    public static int bestSumAnyTreePath(List<Integer> parent, List<Integer> values) {
        

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int parentCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> parent = IntStream.range(0, parentCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int valuesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> values = IntStream.range(0, valuesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = bestSumAnyTreePath(parent, values);

        System.out.println(result);

        bufferedReader.close();

    }
}

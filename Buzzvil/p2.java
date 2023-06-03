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

public class p2 {
    public static List<Integer> findPoint(List<List<Integer>> lines) {
        // Write your code here
        // 점과 직선 사이의 거리 구하기

        // 한 직선의 x 범위 안에 속하는가?
        // y차이

        // 한 직선의 y 범위 안에 속하는가?
        // x차이

        return new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int linesRows = Integer.parseInt(bufferedReader.readLine().trim());
        int linesColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> lines = new ArrayList<>();

        IntStream.range(0, linesRows).forEach(i -> {
            try {
                lines.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = findPoint(lines);

        bufferedReader.close();
    }
}

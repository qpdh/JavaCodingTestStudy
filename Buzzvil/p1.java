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

public class p1 {
    public static int getMaxBarrier(List<Integer> initialEnergy, long th) {
        // Write your code here
        // 전체합 - barrier*사이즈 >= 0

        // barrier의 크기를 찾아보자 -> 정렬
        Collections.sort(initialEnergy);

        int low = 0;
        int lowIndex = 0;
        // 배열의 원소에 맞는 베리어 크기가 존재하는가?
        for (int i = initialEnergy.size() - 2; i >= 0; i--) {
            // 베리어의 크기
            int barrierSize = initialEnergy.get(i);

            // 에너지 계산해보기
            int totalEnergy = 0;
            for (int j = i + 1; j < initialEnergy.size(); j++) {
                totalEnergy += initialEnergy.get(j) - barrierSize;
            }

            if (totalEnergy >= th) {
                low = barrierSize;
                lowIndex = i;
                break;
            }
        }

        List<Integer> totalSumEnergy = new ArrayList<>();
        totalSumEnergy.add(initialEnergy.get(0));

        for (int i = 1; i < initialEnergy.size(); i++) {
            totalSumEnergy.add(totalSumEnergy.get(i - 1) + initialEnergy.get(i));
        }

        int high = initialEnergy.get(initialEnergy.size() - 1);

        int mid = (low + high) / 2;

        while (!(high < low)) {
            // th보다 큰지 체크하기
            mid = (low + high) / 2;

            // 0이하가 되는 것은 빼지 않는다.
            int initialSum = 0;
            for (int i = lowIndex; i < initialEnergy.size(); i++) {
                if (initialEnergy.get(i) - mid > 0) {
                    if (i > 0) {
                        initialSum = (totalSumEnergy.get(totalSumEnergy.size() - 1) - totalSumEnergy.get(i - 1))
                                - (mid * (totalSumEnergy.size() - i));
                    } else {
                        initialSum += (totalSumEnergy.get(totalSumEnergy.size() - 1))
                                - (mid * (totalSumEnergy.size()));
                    }
                    break;
                }
                lowIndex = Math.max(lowIndex, i);
            }

            // 임계값 이상이라면..베리어 늘려보기
            if (initialSum >= th) {
                low = mid + 1;
                continue;
            }

            // 임계값 미만이라면.. 베리어 줄여보기
            if (initialSum < th) {
                high = mid - 1;
                continue;
            }
        }

        return high;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int initialEnergyCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> initialEnergy = IntStream.range(0, initialEnergyCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim).map(Integer::parseInt)
                .collect(toList());

        long th = Long.parseLong(bufferedReader.readLine().trim());

        int result = getMaxBarrier(initialEnergy, th);

        System.out.println(result);

        bufferedReader.close();

    }
}

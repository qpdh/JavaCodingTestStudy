package Yanolja;

import java.util.*;

public class p3 {

    static int maxHeight = 0;

    static class Tree {
        public int x;
        public Tree l;
        public Tree r;
    }

    public static int countPerfectTreeHeight(Tree T) {
        if (T == null) {
            return 0;
        }

        int count = 1;

        int leftHeight = countPerfectTreeHeight(T.l);
        int rightHeight = countPerfectTreeHeight(T.r);

        count += Math.min(leftHeight, rightHeight);

        if (maxHeight < count) {
            maxHeight = count;
        }

        return count;
    }

    public static int pow(int exp) {
        if (exp == 0) {
            return 1;
        }

        int tmp = pow(exp / 2);

        if (exp % 2 == 0) {
            return tmp * tmp;
        }

        return tmp * tmp * 2;
    }

    public static int solution(Tree T) {
        // Implement your solution here

        // 현재 노드에서 완전 이진트리의 최대 높이

        // 한쪽 노드가 존재한다면 -> 한쪽 노드에서 완전 이진트리의 최대 높이

        // 양쪽 노드가 전부 존재한다면
        // 왼쪽 노드에서 완전 이진트리의 최대 높이
        // 오른쪽 노드에서 완전 이진트리의 최대 높이
        // 1 + Min(왼쪽 최대 높이, 오른쪽 최대 높이)
        // 왼쪽 최대 높이
        // 오른쪽 최대 높이

        countPerfectTreeHeight(T);

        //
        int maxNodeCount = pow(maxHeight);

        return maxNodeCount;
    }

    public static void main(String[] args) {

    }
}

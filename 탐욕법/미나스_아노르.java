package 탐욕법;

public class 미나스_아노르 {
    static int n;
    static double y[], x[], r[];
    static Pair ranges[];

    static class Pair implements Comparable<Pair> {
        double begin, end;

        public Pair(double begin, double end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {
            if (begin <= o.begin) {
                return -1;
            }
            return 1;
        }
    }

    static double fmod(double a, double b) {
        int result = (int) Math.floor(a / b);
        return a - result * b;
    }

    static void convertToRange() {
        for (int i = 0; i < n; i++) {
            double loc = fmod(2 * Math.PI + Math.atan2(y[i], x[i]), 2 * Math.PI);
            double range = 2.0 * Math.asin(r[i] / 2.0 / 8.0);
            ranges[i] = new Pair(loc - range, loc + range);
        }
    }

    static int solveCircle() {
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (ranges[i].begin <= 0 || ranges[i].end >= 2 * Math.PI) {
                double begin = fmod(ranges[i].end, 2 * Math.PI);
                double end = fmod(ranges[i].begin + 2 * Math.PI, 2 * Math.PI);

                result = Math.min(result, 1 + solveLine(begin, end));
            }
        }
        return result;
    }

    static int solveLine(double begin, double end) {
        int used = 0, index = 0;

        while (begin < end) {
            double maxCover = -1;
            while (index < n && ranges[index].begin <= begin) {
                maxCover = Math.max(maxCover, ranges[index].end);
                index++;
            }

            if (maxCover <= begin) {
                return Integer.MAX_VALUE;
            }

            begin = maxCover;
            used++;
        }

        return used;
    }

    public static void main(String[] args) {

    }
}

/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

public class Recursion {

    private static int z;

    public static void main(String[] args) {

    }

    public static void countdown(int n) {
        System.out.println(n);
        if (n > 0) {
            countdown(n - 1);
        }
    }

    public static void countup(int n) {
        if (n > 0) {
            countup(n - 1);
        }
        System.out.println(n);
    }

    public static long gNaive(int i) {
        if (i > 1) {
            return 2 * gNaive(i - 1) + 5 * gNaive(i - 2);
        } else if (i == 1) {
            return 1;
        } else if (i == 0) {
            return 0;
        }
        return i;
    }

    public static long gTail(int i) {
        return gTailHelper(i, 1, 0);
    }

    public static long gTailHelper(int i, long giMinusOne, long giMinusTwo) {
        if (i == 0) return giMinusTwo;
        return gTailHelper(i - 1, (2 * giMinusOne) + (5 * giMinusTwo), giMinusOne);
    }

    public static long gIter(int i) {
        long l = 0;
        long g = 1;
        for (int j = 1; j < i; j++) {
            long tmp = g;
            g = 2 * g + 5 * l;
            l = tmp;
        }

        return g;
    }

    public static int hornerRecursive(int[] digits, int base) {
        int n = digits.length - 1;
        return hornerRecursiveHelper(digits, base, n);
    }

    public static int hornerRecursiveHelper(int[] digits, int base, int n) {
        if (n > -1) {
            hornerRecursiveHelper(digits, base, n - 1);
            z = base * z;
            z = z + digits[n];
        }
        return z;
    }


}

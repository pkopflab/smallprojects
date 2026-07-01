/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

public class Arrays {


    public static void printArray(int[] array) {
        System.out.print("[");

        for (int i = 0; i < array.length; i++) {
            if (i < (array.length - 1)) {
                System.out.print(array[i] + ", ");
            } else {
                System.out.print(array[i]);
            }
        }
        System.out.print("]");
        System.out.println();
    }

    public static int sum(int[] array) {
        int res = 0;
        for (int value : array) {
            res += value;
        }
        return res;
    }

    public static double mean(int[] array) {
        return (double) sum(array) / array.length;
    }

    public static int[] sumArrays(int[] array1, int[] array2) {
        int[] array3 = new int[Math.min(array1.length, array2.length)];

        for (int i = 0; i < array3.length; i++) {
            array3[i] = array1[i] + array2[i];
        }
        return array3;
    }

    public static int maximum(int[] array) {
        int max = 0;

        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    public static int[] tail(int[] array) {

        if (array.length == 0) {
            return array;
        }

        int[] newArray = new int[array.length - 1];

        for (int i = 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }

        return newArray;
    }

    public static boolean checkSorting(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                if (!(array[i] < array[i + 1])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean[] evenNumbers(int[] array) {
        boolean[] evenNumbers = new boolean[array.length];

        for (int i = 0; i < array.length; i++) {
            int rest = array[i] % 2;
            evenNumbers[i] = rest == 0;
        }

        return evenNumbers;
    }

    public static void printBooleanArray(boolean[] array) {
        System.out.print("[");

        for (int i = 0; i < array.length; i++) {
            if (i < (array.length - 1)) {
                System.out.print(array[i] + ", ");
            } else {
                System.out.print(array[i]);
            }
        }
        System.out.print("]");
        System.out.println();
    }


    public static void main(String[] args) {
        int[] array1 = {};
        int[] array2 = {};

        printArray(array1);
        printArray(array2);

        System.out.println("array1: sum = " + sum(array1) + ", mean = " + mean(array1));
        System.out.println("array2: sum = " + sum(array2) + ", mean = " + mean(array2));

        printArray(sumArrays(array1, array2));

        System.out.println("Maximum array1: " + maximum(array1));
        System.out.println("Maximum array2: " + maximum(array2));

        System.out.println("array1: tail = " + getArrayAsString(tail(array1)));
        System.out.println("array2: tail = " + getArrayAsString(tail(array2)));

        System.out.println("array1: sorted = " + checkSorting(array1));
        System.out.println("array2: sorted = " + checkSorting(array2));

        printBooleanArray(evenNumbers(array1));
        printBooleanArray(evenNumbers(array2));


        Double.toHexString(double a)



    }

    private static String getArrayAsString(int[] array) {
        StringBuilder s = new StringBuilder("[");

        for (int i = 0; i < array.length; i++) {
            if (i < (array.length - 1)) {
                s.append(array[i]).append(", ");
            } else {
                s.append(array[i]);
            }
        }
        s.append("]");
        return s.toString();
    }
}

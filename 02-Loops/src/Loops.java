/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

public class Loops {

    public static void main(String[] args) {

        //Aufgabe 2.2.2

        for (int i = 0; i < 11; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i <= 10; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 6; i < 43; i += 6) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 23; i > 10; i -= 2) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 8; i < 18; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();


        for (int i = 4; i < 10; i++) {
            System.out.print((int) Math.pow(2, i) + " ");
        }
        System.out.print("711"); //?????

        System.out.println();


        //Aufgabe 2.2.3

        int i = 0;
        int m = 7;

        while (i <= m) {
            System.out.print(i + " ");
            i++;
        }
        System.out.println();

        int i2 = 42;

        while ((2 * i2) > m) {
            System.out.print(i2 + " ");
            i2 -= 6;

        }

        System.out.println();


        //Aufgabe 2.2.4

        int i3 = 0;
        int m2 = 7;

        while (true) {
            if (!(i3 <= m2)) {
                break;
            }
            System.out.print(i3 + " ");
            i3++;
        }

        System.out.println();

        int i4 = 42;

        while (true) {
            if (!((2 * i4) > m2)) {
                break;
            }
            System.out.print(i4 + " ");
            i4 -= 6;

        }
        System.out.println();


        //Aufgabe 2.2.5

        int nMax = 5;
        double x = 2;
        double a = 2;

        for (int n = 0; n <= nMax; n++) {
            x = 0.5 * (x + a / x);
            System.out.println("Approximation at step " + n + ": " + x);
        }
    }
}

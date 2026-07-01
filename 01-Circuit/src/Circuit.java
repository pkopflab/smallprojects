import java.util.Scanner;

/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

public class Circuit {

    static double protectiveResistor = 0.0;
    static double voltage = 5.0;
    static double d1 = 0.03;
    static double d2 = 0.04;
    static double a1 = 0.00097;
    static double a2 = 0.00051;
    static double resistor1 = 1000.0;
    static double resistor2 = 472.0;
    static Scanner scRes;
    static Scanner scMod;
    static final double EPSILON_0 = 8.854 * Math.pow(10, -12);


    public static void main(String[] args) {

        //init Scanner
        scRes = new Scanner(System.in);
        scMod = new Scanner(System.in);


        System.out.println("Enter protective resistor...");

        protectiveResistor = scRes.nextDouble();

        if (isInputValid(protectiveResistor)) {

            double current = voltage / protectiveResistor;

            double power = Math.pow(current, 2) * protectiveResistor;

            System.out.println("Current: " + current + " Ampere; Power: " + power + " Watt");
            System.out.println("Enter module...");

            String module = scMod.nextLine();

            switch (module) {
                case "cap":
                    runWithCap();
                    break;
                case "res":
                    runWithRes();
                    break;
                case "short":
                    runWithShort();
                    break;
                default:
                    System.err.println("Error: This module does not exist.");
                    break;
            }

        } else {
            System.err.println("Error: Input has to be > 0.");

        }

        scRes.close();

        scMod.close();
    }

    private static boolean isInputValid(double input) {
        return input > 0;
    }

    private static void runWithCap() {
        double capacity1 = (EPSILON_0 * 1 * a1) / d1;

        double capacity2 = (EPSILON_0 * 1 * a2) / d2;

        System.out.println("Capacity1: " + capacity1 + " Farad");
        System.out.println("Capacity2: " + capacity2 + " Farad");

        double totalCapacity = capacity1 + capacity2;
        System.out.println("The total capacity is: " + totalCapacity + " Farad.");
    }

    private static void runWithRes() {
        double totalResistance = (resistor1 * resistor2) / (resistor1 + resistor2);
        System.out.println("The total resistance is: " + totalResistance + " Ohm.");
    }

    private static void runWithShort() {

        System.out.println("Board shorted - No modules installed!");
    }
}

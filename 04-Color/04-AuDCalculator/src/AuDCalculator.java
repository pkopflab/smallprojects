/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

import java.util.ArrayList;
import java.util.List;

public class AuDCalculator {

    public static void main(String[] args) {
        new CalculatorGUI();
    }

    public static boolean isKnownNumberSystem(int inputSystem) {
        switch (inputSystem) {
            case 10:
            case 8:
            case 16:
            case 2:
                return true;
            default:
                return false;
        }

    }

    public static boolean isInNumberSystem(char number, int inputSystem) {
        switch (inputSystem) {
            case 2:
                switch (number) {
                    case '1':
                    case '0':
                        return true;
                    default:
                        return false;
                }
            case 8:
                switch (number) {
                    case '8':
                    case '9':
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                        return false;
                    default:
                        return true;
                }
            case 10:
                switch (number) {
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                        return false;
                    default:
                        return true;
                }
            case 16:
                switch (number) {
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        return true;
                    default:
                        return false;
                }
            default:
                return false;
        }

    }

    public static int convertToInt(String inputNumber, int inputSystem) {

        char[] hex = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] hexValue = {10, 11, 12, 13, 14, 15};


        if (!isKnownNumberSystem(inputSystem)) {
            return -1;
        }

        for (int i = 0; i < inputNumber.length(); i++) {
            if (!isInNumberSystem(inputNumber.charAt(i), inputSystem)) {
                return -1;
            }
        }

        int add = 0;
        int converted = 0;

        for (int i = 0; i < inputNumber.length(); i++) {
            int number;

            if (!isHexAlph(inputNumber.charAt(i))) {
                number = inputNumber.charAt(i) - '0';
            } else {
                number = getHexVal(inputNumber.charAt(i));
            }

            int result = number + add;
            add = result * inputSystem;
            converted = result;
        }
        return converted;
    }

    public static String convertToNumberSystem(int number, int outputSystem) {
        List<Integer> converted = new ArrayList<>();

        if (!isKnownNumberSystem(outputSystem)) {
            return "error";
        }

        String result = "";

        int div = number;
        int rest = number % outputSystem;

        while (div != 0) {
            converted.add(rest);
            div = div / outputSystem;
            rest = div % outputSystem;
        }

        StringBuilder builder = new StringBuilder();

        switch (outputSystem) {
            case 2:
                builder.append("b");
                break;
            case 8:
                builder.append("0");
                break;
            case 16:
                builder.append("0x");
        }

        for (int i = converted.size() - 1; i >= 0; i--) {
            if (outputSystem == 16) {
                if (converted.get(i) > 9) {
                    builder.append(replaceWithHex(converted.get(i)));
                } else {
                    builder.append(converted.get(i));
                }
            } else {
                builder.append(converted.get(i));
            }
        }


        result = builder.toString();
        return result;

    }

    public static String computeArithmetic(int firstNumber, int secondNumber, String operator, int outputSystem) {
        int result = 0;
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber == 0)
                    return "Calculation Error";

                result = firstNumber / secondNumber;
                break;
        }

        if (result < 0) {
            return "Calculation Error";
        }

        return "" + convertToNumberSystem(result, outputSystem);
    }

    private static char replaceWithHex(int rest) {
        switch (rest) {
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C';
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
        }
        return '0';
    }

    //Hilfsmethoden
    public static boolean isHexAlph(char c) {
        switch (c) {
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
                return true;
            default:
                return false;
        }
    }

    public static int getHexVal(char c) {
        char[] hex = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] hexValue = {10, 11, 12, 13, 14, 15};

        for (int i = 0; i < hex.length; i++) {
            if (hex[i] == c) {
                return hexValue[i];
            }
        }
        return -1;
    }
}

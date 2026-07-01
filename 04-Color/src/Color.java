/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

import java.util.ArrayList;
import java.util.List;

public class Color {

    private int rgb;
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color GRAY = new Color(128, 128, 128);

    public static void main(String[] args) {

        Color color1 = new Color("#7FB17F");
        new ColorVisualizer(color1);

        Color color2 = new Color("#003200");
        new ColorVisualizer(color2);

        Color color3 = color1.mixColor(color2);
        new ColorVisualizer(color3);
    }

    public Color(int rgb) {
        this.rgb = rgb;
    }

    public Color(int red, int green, int blue) {
        if (!(red >= 0 && red <= 255)) {
            System.err.println("Farbkanal Rot nicht gueltig!");
        }
        if (!(green >= 0 && green <= 255)) {
            System.err.println("Farbkanal Gruen nicht gueltig!");

        }
        if (!(blue >= 0 && blue <= 255)) {
            System.err.println("Farbkanal Blau nicht gueltig!");
        }
    }

    public Color() {
        this.rgb = 0;
    }

    public Color(String hex) {
        rgb = convertToInt(hex.substring(1), 16);
    }

    public int getRgb() {
        return rgb;
    }

    public int getRed() {
        int red = rgb;
        red = red >> 16;
        return red;

    }

    public int getGreen() {
        int green = rgb;
        green = green >> 8;
        green = green & 255;
        return green;
    }

    public int getBlue() {
        int blue = rgb;
        blue = blue & 255;
        return blue;
    }

    public String getHex() {
        return convertToHex(getRgb());
    }


    private String convertToHex(int number) {
        List<Integer> converted = new ArrayList<>();


        String result = "";

        int div = number;
        int rest = number % 16;

        while (div != 0) {
            converted.add(rest);
            div = div / 16;
            rest = div % 16;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("#");

        for (int i = converted.size() - 1; i >= 0; i--) {
            if (converted.get(i) > 9) {
                builder.append(replaceWithHex(converted.get(i)));
            } else {
                builder.append(converted.get(i));
            }

        }

        result = builder.toString();
        return result;
    }

    @Override
    public String toString() {
        return convertToHex(rgb);
    }

    public Color complementaryColor() {
        return new Color(getRed() - 255, getGreen() - 255, getBlue() - 255);
    }

    public Color mixColor(Color color) {
        int rNew = (color.getRed() + getRed()) / 2;
        int gNew = (color.getGreen() + getGreen()) / 2;
        int bNew = (color.getBlue() + getBlue()) / 2;
        return new Color(rNew, gNew, bNew);
    }


    //Nicht in Aufgabe

    private char replaceWithHex(int rest) {
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

    public int convertToInt(String inputNumber, int inputSystem) {

        char[] hex = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] hexValue = {10, 11, 12, 13, 14, 15};


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

    private boolean isHexAlph(char c) {
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

    private int getHexVal(char c) {
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

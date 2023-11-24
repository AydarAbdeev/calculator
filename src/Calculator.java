import java.util.Arrays;
import java.util.Scanner;

public class Calculator {

    public static String[] roman = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
            "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
            "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
            "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII",
            "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII",
            "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
            "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
    public static int[] arabian = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
            24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
            51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77,
            78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100};
    public static String example;
    public static int number1;
    public static int number2;
    public static String romanNumeral1;
    public static String romanNumeral2;


    public static void main(String[] args) throws Exception {
        System.out.println("Введите пример, используя арабские цифры от 1 до 10, либо римские от I до X,  в формате [1+2]:");
        while (true){
            Scanner scanner = new Scanner(System.in);
            example = scanner.nextLine().replaceAll("\\s", "").toUpperCase();
            String[] exampleArray = example.split("[+\\-*/]");
            if (exampleArray.length > 2) {
                throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            if (exampleArray.length < 2) {
                throw new Exception("Строка не является математической операцией");
            }
            boolean isArabian = exampleArray[0].matches("\\d+") && exampleArray[1].matches("\\d+");
            boolean isRoman = Arrays.asList(roman).contains(exampleArray[0]) && Arrays.asList(roman).contains(exampleArray[1]);
            if (isArabian) {
                number1 = Integer.parseInt(exampleArray[0]);
                number2 = Integer.parseInt(exampleArray[1]);
                System.out.println(calculate(number1, number2));
            } else if (isRoman) {
                romanNumeral1 = exampleArray[0];
                romanNumeral2 = exampleArray[1];
                number1 = converterToArabian(romanNumeral1);
                number2 = converterToArabian(romanNumeral2);
                int result = calculate(number1, number2);
                System.out.println(converterToRoman(result));
            } else {
                throw new Exception("Используются одновременно разные системы счисления");
            }
        }
    }



    static int converterToArabian(String romanNumeral) {
        int converter = switch (romanNumeral) {
            case "I": yield 1;
            case "II": yield 2;
            case "III": yield 3;
            case "IV": yield 4;
            case "V": yield 5;
            case "VI": yield 6;
            case "VII": yield 7;
            case "VIII": yield 8;
            case "IX": yield 9;
            case "X": yield 10;
            default: yield -1;
        };
        return converter;
    }

    public static String converterToRoman(int number) throws Exception {
        if (number < 1) {
            throw new Exception("В римской системе нет отрицательных чисел");
        }
        String converter = roman[number];
        return converter;

    }
    public static int calculate (int number1, int number2) throws Exception {
        int result = 0;
        if (example.contains("+")) {
            result = number1 + number2;
        } else if (example.contains("-")) {
            result = number1 - number2;
        } else if (example.contains("*")) {
            result = number1 * number2;
        } else if (example.contains("/")) {
            if (number2 > 0) {
                result = number1 / number2;
            } else {
               throw new Exception("Неверный делитель");
            }
        }
        return result;
    }
}
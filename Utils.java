import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int getIntegerInput(String prompt) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                scanner.nextLine();
                if (value > 0) {
                    valid = true;
                } else {
                    System.out.println("Value must be greater than 0.");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.println("Invalid input. Please enter a whole number.");
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
            }
        }

        return value;
    }

    public static int getValidIndex(String prompt, int maxValue) {
        int value = -1;
        boolean valid = false;

        while (!valid) {
            value = getIntegerInput(prompt);

            if (value > 0 && value <= maxValue) {
                valid = true;
            } else {
                System.out.println("Input value not allowed. It must be between 1 and " + maxValue + ".");
            }
        }

        return value;
    }

    public static String getValidatedString(String prompt, int minLength, int maxLength) {
        String input = "";
        boolean valid = false;

        while (!valid) {
            input = getStringInput(prompt);

            if (StringUtils.isBlank(input) || input.length() < minLength) {
                System.out.println("String cannot be empty or shorter than " + minLength + " characters.");
            } else if (input.length() > maxLength) {
                System.out.println("String cannot be more than " + maxLength + " characters.");
            } else {
                valid = true;
            }
        }

        return input;
    }

    public static boolean terminate() {
        scanner.close();
        return true;
    }
}

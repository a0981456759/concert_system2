import java.util.Scanner;

/**
 * Public Scanner
 */
public class InputManager {
    // Public static scanner for the entire application
    public static final Scanner SCANNER = new Scanner(System.in);

    private InputManager() {
        // Private constructor to prevent instantiation
    }

    // Optionally, you can add a method to close the scanner if you need to ensure clean up
    public static void closeScanner() {
        SCANNER.close();
    }
}

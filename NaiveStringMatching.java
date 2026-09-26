import java.util.*;

public class NaiveStringMatching {
    public static void run(Scanner sc) {
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter pattern: ");
        String pattern = sc.nextLine();

        if (pattern.length() == 0) {
            System.out.println("Empty pattern.");
            return;
        }

        boolean found = false;
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            int j = 0;
            while (j < pattern.length() && text.charAt(i + j) == pattern.charAt(j))
                j++;
            if (j == pattern.length()) {
                System.out.println("Pattern found at index: " + i);
                found = true;
            }
        }
        if (!found) System.out.println("Pattern not found.");
    }
}

import java.util.*;

public class ZAlgorithm {
    public static void run(Scanner sc) {
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter pattern: ");
        String p = sc.nextLine();

        if (p.length() == 0) {
            System.out.println("Empty pattern.");
            return;
        }

        String s = p + '\0' + text;
        int[] z = new int[s.length()];
        int l = 0, r = 0;
        boolean found = false;

        for (int i = 1; i < s.length(); i++) {
            if (i <= r) z[i] = Math.min(r - i + 1, z[i - l]);

            while (i + z[i] < s.length() &&
                   s.charAt(z[i]) == s.charAt(i + z[i]))
                z[i]++;

            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }

            if (z[i] == p.length()) {
                System.out.println("Pattern found at index: " + (i - p.length() - 1));
                found = true;
            }
        }

        if (!found) System.out.println("Pattern not found.");
    }
}

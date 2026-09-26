import java.util.*;

public class KMP {
    public static void run(Scanner sc) {
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter pattern: ");
        String p = sc.nextLine();

        if (p.length() == 0) {
            System.out.println("Empty pattern.");
            return;
        }

        int[] lps = new int[p.length()];
        for (int i = 1, len = 0; i < p.length();) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }

        boolean found = false;
        for (int i = 0, j = 0; i < text.length();) {
            if (text.charAt(i) == p.charAt(j)) {
                i++; j++;
                if (j == p.length()) {
                    System.out.println("Pattern found at index: " + (i - j));
                    found = true;
                    j = lps[j - 1];
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        if (!found) System.out.println("Pattern not found.");
    }
}

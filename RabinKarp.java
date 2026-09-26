import java.util.*;

public class RabinKarp {
    static final long BASE = 256;
    static final long MOD = 1000000007L;

    public static void run(Scanner sc) {
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter pattern: ");
        String p = sc.nextLine();

        if (p.length() == 0) {
            System.out.println("Empty pattern.");
            return;
        }
        if (p.length() > text.length()) {
            System.out.println("Pattern not found.");
            return;
        }

        int m = p.length();
        long ph = 0, th = 0, high = 1;

        for (int i = 0; i < m - 1; i++)
            high = (high * BASE) % MOD;

        for (int i = 0; i < m; i++) {
            ph = (ph * BASE + p.charAt(i)) % MOD;
            th = (th * BASE + text.charAt(i)) % MOD;
        }

        boolean found = false;

        for (int i = 0; i <= text.length() - m; i++) {
            if (ph == th && text.regionMatches(i, p, 0, m)) {
                System.out.println("Pattern found at index: " + i);
                found = true;
            }

            if (i < text.length() - m) {
                th = (BASE * (th - text.charAt(i) * high % MOD + MOD)
                        + text.charAt(i + m)) % MOD;
            }
        }

        if (!found) System.out.println("Pattern not found.");
    }
}

import java.util.*;

public class WeightedEditDistance {
    public static void run(Scanner sc) {
        System.out.print("Enter first string: ");
        String a = sc.nextLine();

        System.out.print("Enter second string: ");
        String b = sc.nextLine();

        System.out.print("Insertion cost: ");
        int ins = Integer.parseInt(sc.nextLine());

        System.out.print("Deletion cost: ");
        int del = Integer.parseInt(sc.nextLine());

        System.out.print("Substitution cost: ");
        int sub = Integer.parseInt(sc.nextLine());

        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 1; i <= a.length(); i++)
            dp[i][0] = dp[i - 1][0] + del;

        for (int j = 1; j <= b.length(); j++)
            dp[0][j] = dp[0][j - 1] + ins;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int replace = dp[i - 1][j - 1]
                        + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : sub);

                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + del,
                                 dp[i][j - 1] + ins),
                        replace);
            }
        }

        System.out.println("Weighted Edit Distance = " +
                dp[a.length()][b.length()]);
    }
}

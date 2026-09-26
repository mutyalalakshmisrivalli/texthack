import java.util.*;

public class SuffixArray {
    public static int[] build(String s) {
        Integer[] a = new Integer[s.length()];
        for (int i = 0; i < s.length(); i++) a[i] = i;
        Arrays.sort(a, (x, y) -> s.substring(x).compareTo(s.substring(y)));

        int[] sa = new int[a.length];
        for (int i = 0; i < a.length; i++) sa[i] = a[i];
        return sa;
    }

    public static void run(Scanner sc) {
        System.out.print("Enter text: ");
        String s = sc.nextLine();
        int[] sa = build(s);

        System.out.println("Suffix Array:");
        for (int x : sa)
            System.out.println(x + " : " + s.substring(x));
    }
}

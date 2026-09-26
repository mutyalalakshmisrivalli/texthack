import java.util.*;

public class SuffixTree {
    static class Node {
        Map<Character, Node> children = new TreeMap<>();
        String label = "";
    }

    static Node root;

    static void insertSuffix(String suffix) {
        Node cur = root;
        String rem = suffix;

        while (!rem.isEmpty()) {
            Node next = cur.children.get(rem.charAt(0));

            if (next == null) {
                Node n = new Node();
                n.label = rem;
                cur.children.put(rem.charAt(0), n);
                return;
            }

            int k = 0;
            while (k < next.label.length() && k < rem.length()
                    && next.label.charAt(k) == rem.charAt(k))
                k++;

            if (k == next.label.length()) {
                cur = next;
                rem = rem.substring(k);
            } else {
                Node split = new Node();
                split.label = next.label.substring(0, k);

                next.label = next.label.substring(k);
                split.children.put(next.label.charAt(0), next);

                cur.children.put(rem.charAt(0), split);

                if (k < rem.length()) {
                    Node leaf = new Node();
                    leaf.label = rem.substring(k);
                    split.children.put(leaf.label.charAt(0), leaf);
                }

                return;
            }
        }
    }

    static void display(Node node, String prefix) {
        String current = prefix + node.label;

        if (!node.children.isEmpty())
            for (Node child : node.children.values())
                display(child, current);
        else
            System.out.println(current);
    }

    public static void run(Scanner sc) {
        System.out.print("Enter text: ");
        String s = sc.nextLine();

        root = new Node();

        for (int i = 0; i < s.length(); i++)
            insertSuffix(s.substring(i));

        System.out.println("Suffix Tree:");
        display(root, "");
    }
}

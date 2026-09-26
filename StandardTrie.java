import java.util.*;

public class StandardTrie {
    static class Node {
        Map<Character, Node> child = new TreeMap<>();
        boolean end;
    }

    static Node root = new Node();

    static void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray())
            cur = cur.child.computeIfAbsent(c, k -> new Node());
        cur.end = true;
    }

    static boolean search(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            cur = cur.child.get(c);
            if (cur == null) return false;
        }
        return cur.end;
    }

    static void display(Node node, String prefix) {
        if (node.end) System.out.println(prefix);
        for (Map.Entry<Character, Node> e : node.child.entrySet())
            display(e.getValue(), prefix + e.getKey());
    }

    public static void run(Scanner sc) {
        root = new Node();

        System.out.print("Number of words: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Word " + (i + 1) + ": ");
            insert(sc.nextLine());
        }

        System.out.println("Trie contents:");
        display(root, "");

        System.out.print("Search word: ");
        String word = sc.nextLine();
        System.out.println(search(word) ? "Found" : "Not Found");
    }
}

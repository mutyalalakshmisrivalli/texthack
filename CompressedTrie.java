import java.util.*;

public class CompressedTrie {
    static class Node {
        String label;
        boolean end;
        List<Node> children = new ArrayList<>();

        Node(String label) {
            this.label = label;
        }
    }

    static Node root = new Node("");

    static int common(String a, String b) {
        int i = 0;
        while (i < a.length() && i < b.length() &&
               a.charAt(i) == b.charAt(i))
            i++;
        return i;
    }

    static void insert(String word) {
        Node cur = root;
        String remaining = word;

        while (!remaining.isEmpty()) {
            boolean matched = false;

            for (Node child : cur.children) {
                int k = common(remaining, child.label);

                if (k == 0) continue;

                matched = true;

                if (k == child.label.length()) {
                    cur = child;
                    remaining = remaining.substring(k);
                } else {
                    Node split = new Node(child.label.substring(0, k));
                    child.label = child.label.substring(k);
                    split.children.add(child);

                    cur.children.remove(child);
                    cur.children.add(split);

                    cur = split;
                    remaining = remaining.substring(k);
                }
                break;
            }

            if (!matched) {
                Node x = new Node(remaining);
                x.end = true;
                cur.children.add(x);
                return;
            }
        }

        cur.end = true;
    }

    static boolean search(String word) {
        Node cur = root;
        String remaining = word;

        while (!remaining.isEmpty()) {
            boolean found = false;

            for (Node child : cur.children) {
                if (remaining.startsWith(child.label)) {
                    remaining = remaining.substring(child.label.length());
                    cur = child;
                    found = true;
                    break;
                }
            }

            if (!found) return false;
        }

        return cur.end;
    }

    static void display(Node node, String prefix) {
        String current = prefix + node.label;

        if (node.end)
            System.out.println(current);

        for (Node child : node.children)
            display(child, current);
    }

    public static void run(Scanner sc) {
        root = new Node("");

        System.out.print("Number of words: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Word " + (i + 1) + ": ");
            insert(sc.nextLine());
        }

        System.out.println("Compressed Trie:");
        display(root, "");

        System.out.print("Search word: ");
        System.out.println(search(sc.nextLine()) ? "Found" : "Not Found");
    }
}

import java.util.*;

public class TextHack {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==================== TEXT HACK ====================");
            System.out.println("1.  Naive String Matching");
            System.out.println("2.  Rabin-Karp");
            System.out.println("3.  KMP");
            System.out.println("4.  Z Algorithm");
            System.out.println("5.  Suffix Array");
            System.out.println("6.  SA-IS (LMS)");
            System.out.println("7.  LCP Array");
            System.out.println("8.  Kasai Algorithm");
            System.out.println("9.  Standard Trie");
            System.out.println("10. Compressed Trie");
            System.out.println("11. Suffix Tree");
            System.out.println("12. Levenshtein / Wagner-Fischer");
            System.out.println("13. Weighted Edit Distance");
            System.out.println("14. Damerau-Levenshtein");
            System.out.println("15. Global Sequence Alignment");
            System.out.println("16. Local Sequence Alignment");
            System.out.println("17. Matrix Chain Multiplication");
            System.out.println("18. OBST - Successful Searches");
            System.out.println("19. Bitmask DP - TSP");
            System.out.println("20. Bitmask DP - Hamiltonian Path");
            System.out.println("21. Ford-Fulkerson");
            System.out.println("22. Edmonds-Karp");
            System.out.println("23. Dinic's Algorithm");
            System.out.println("24. Max-Flow Min-Cut");
            System.out.println("25. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: NaiveStringMatching.run(sc); break;
                case 2: RabinKarp.run(sc); break;
                case 3: KMP.run(sc); break;
                case 4: ZAlgorithm.run(sc); break;
                case 5: SuffixArray.run(sc); break;
                case 6: SAIS.run(sc); break;
                case 7: LCPArray.run(sc); break;
                case 8: KasaiAlgorithm.run(sc); break;
                case 9: StandardTrie.run(sc); break;
                case 10: CompressedTrie.run(sc); break;
                case 11: SuffixTree.run(sc); break;
                case 12: Levenshtein.run(sc); break;
                case 13: WeightedEditDistance.run(sc); break;
                case 14: DamerauLevenshtein.run(sc); break;
                case 15: GlobalSequenceAlignment.run(sc); break;
                case 16: LocalSequenceAlignment.run(sc); break;
                case 17: MatrixChainMultiplication.run(sc); break;
                case 18: OBST.run(sc); break;
                case 19: TSP.run(sc); break;
                case 20: HamiltonianPath.run(sc); break;
                case 21: FordFulkerson.run(sc); break;
                case 22: EdmondsKarp.run(sc); break;
                case 23: Dinic.run(sc); break;
                case 24: MaxFlowMinCut.run(sc); break;
                case 25:
                    System.out.println("Program exited.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

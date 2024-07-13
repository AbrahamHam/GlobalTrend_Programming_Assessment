import java.util.*;
class Tree {
    private TreeNode root;

    public Tree() {
        root = new TreeNode();
    }

    public void insert(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.containsKey(c)) {
                node.put(c, new TreeNode());
            }
            node = node.get(c);
        }
        node.setEnd();
    }

    private TreeNode searchPrefix(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.containsKey(c)) {
                node = node.get(c);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TreeNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TreeNode node = searchPrefix(prefix);
        return node != null;
    }
    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();

        tree.insertInterval(1, 3);
        tree.insertInterval(2, 6);
        tree.insertInterval(8, 10);
        tree.insertInterval(15, 18);

        System.out.println("Overlapping intervals with [4, 5]:");
        List<Interval> overlappingIntervals = tree.findOverlappingIntervals(4, 5);
        for (Interval interval : overlappingIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }

        System.out.println("\nOverlapping intervals with [2, 4]:");
        overlappingIntervals = tree.findOverlappingIntervals(2, 4);
        for (Interval interval : overlappingIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }

        System.out.println("\nDeleting interval [2, 6]...");
        tree.deleteInterval(2, 6);

        System.out.println("\nOverlapping intervals with [4, 5]:");
        overlappingIntervals = tree.findOverlappingIntervals(4, 5);
        for (Interval interval : overlappingIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }

}
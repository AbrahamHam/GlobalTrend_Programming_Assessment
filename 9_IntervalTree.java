import java.util.ArrayList;
import java.util.List;


class IntervalTree {
    private Node root;

    private class Node {
        Interval interval;
        int maxEnd;
        Node left;
        Node right;
        boolean isRed;

        Node(Interval interval) {
            this.interval = interval;
            this.maxEnd = interval.end;
            this.isRed = true;
        }
    }

    public void insertInterval(int start, int end) {
        root = insertInterval(root, new Interval(start, end));
        root.isRed = false;
    }

    private Node insertInterval(Node node, Interval interval) {
        if (node == null) {
            return new Node(interval);
        }

        if (interval.start < node.interval.start) {
            node.left = insertInterval(node.left, interval);
        } else {
            node.right = insertInterval(node.right, interval);
        }

        if (node.right != null && node.right.isRed && (node.left == null || !node.left.isRed)) {
            node = rotateLeft(node);
        }
        if (node.left != null && node.left.isRed && node.left.left != null && node.left.left.isRed) {
            node = rotateRight(node);
        }
        if (node.left != null && node.left.isRed && node.right != null && node.right.isRed) {
            flipColors(node);
        }

        node.maxEnd = Math.max(node.interval.end, Math.max(node.left != null ? node.left.maxEnd : 0, node.right != null ? node.right.maxEnd : 0));
        return node;
    }

    public void deleteInterval(int start, int end) {
        root = deleteInterval(root, new Interval(start, end));
    }

    private Node deleteInterval(Node node, Interval interval) {
        if (node == null) {
            return null;
        }

        if (interval.start < node.interval.start) {
            node.left = deleteInterval(node.left, interval);
        } else if (interval.start > node.interval.start) {
            node.right = deleteInterval(node.right, interval);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                node.interval = findMin(node.right).interval;
                node.right = deleteMin(node.right);
            }
        }

        if (node.right != null && node.right.isRed) {
            node = rotateLeft(node);
        }
        if (node.left != null && node.left.isRed && node.left.left != null && node.left.left.isRed) {
            node = rotateRight(node);
        }
        if (node.left != null && node.left.isRed && node.right != null && node.right.isRed) {
            flipColors(node);
        }

        node.maxEnd = Math.max(node.interval.end, Math.max(node.left != null ? node.left.maxEnd : 0, node.right != null ? node.right.maxEnd : 0));
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.maxEnd = Math.max(node.interval.end, Math.max(node.left != null ? node.left.maxEnd : 0, node.right != null ? node.right.maxEnd : 0));
        return node;
    }

    public List<Interval> findOverlappingIntervals(int start, int end) {
        List<Interval> result = new ArrayList<>();
        findOverlappingIntervals(root, new Interval(start, end), result);
        return result;
    }

    private void findOverlappingIntervals(Node node, Interval interval, List<Interval> result) {
        if (node == null) {
            return;
        }

        if (interval.end >= node.interval.start && interval.start <= node.interval.end) {
            result.add(node.interval);
        }

        if (node.left != null && interval.start <= node.left.maxEnd) {
            findOverlappingIntervals(node.left, interval, result);
        }

        if (node.right != null && interval.start <= node.right.maxEnd) {
            findOverlappingIntervals(node.right, interval, result);
        }
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.isRed = node.isRed;
        node.isRed = true;
        node.maxEnd = Math.max(node.interval.end, Math.max(node.left != null ? node.left.maxEnd : 0, node.right != null ? node.right.maxEnd : 0));
        x.maxEnd = Math.max(x.interval.end, Math.max(x.left != null ? x.left.maxEnd : 0, x.right != null ? x.right.maxEnd : 0));
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.isRed = node.isRed;
        node.isRed = true;
        node.maxEnd = Math.max(node.interval.end, Math.max(node.left != null ? node.left.maxEnd : 0, node.right != null ? node.right.maxEnd : 0));
        x.maxEnd = Math.max(x.interval.end, Math.max(x.left != null ? x.left.maxEnd : 0, x.right != null ? x.right.maxEnd : 0));
        return x;
    }

    private void flipColors(Node node) {
        node.isRed = !node.isRed;
        if (node.left != null) {
            node.left.isRed = !node.left.isRed;
        }
        if (node.right != null) {
            node.right.isRed = !node.right.isRed;
        }
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

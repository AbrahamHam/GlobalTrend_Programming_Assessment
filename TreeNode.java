class TreeNode {
    TreeNode[] links;
    final int R = 26;
    boolean isEnd;

    TreeNode() {
        links = new TreeNode[R];
    }

    boolean containsKey(char c) {
        return links[c - 'a'] != null;
    }

    TreeNode get(char c) {
        return links[c - 'a'];
    }

    void put(char c, TreeNode node) {
        links[c - 'a'] = node;
    }

    void setEnd() {
        isEnd = true;
    }

    boolean isEnd() {
        return isEnd;
    }
}
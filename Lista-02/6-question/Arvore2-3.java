import java.util.NoSuchElementException;

public class RedBlackBST<Key extends comparable<Key>, Value>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node{
        private Key K;
        private Value V;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key K, Value V, boolean color, int size){
            this.K = K;
            this.V = V;
            this.color = color;
            this.size = size;
        }
    }

    private boolean isRed(Node x){
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x){
        if (x == null) return 0;
        return x.size;
    }

    public int size(){
        return size(root);
    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);

        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x
    }

    private void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void put(Key K, Value V){
        root = put(root, K, V);
        root.color = BLACK;
    }

    private void put(Node h, Key K, Value V){
        if (h == null) return new Node(K, V, RED, 1);

        int cmp = K.compareTo(h.K);
        
        if (cmp < 0) return h.left = put(h.left, K, V);
        else if (cmp > 0) return h.right = put(h.right, K, V);
        else h.V = V;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.right) && isRed(h.left)) flipColors(h);

        h.size = 1 + size(h.left) + size(h.right);

        return h;
    }
}
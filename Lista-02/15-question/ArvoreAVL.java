import java.util.NoSuchElementException;

public class ArvoreAVL<Key extends Comparable<Key>, Value>{
    private Node root; // Topo da árvore

    private class Node{
        private final Key K; //  Chave do nó
        private Value V; // Valor do nó
        private Node left, right; // Ponteiros para esquerda e direita do no h

        private int height; // Altura da árvore
        private int size; // Tamanho da árvore

        // Construtor da classe Node
        public Node(Key K, Value V, int height, int size){
            this.K = K;
            this.V = V;
            this.height = height;
            this.size = size;
        }
    }

    // Metodo pra verificar se a arvore é vazia
    public boolean isEmpty(){
        return root == null;
    }

    // Metodo para saber o tamanho da árvore
    public int size(){
        return size(root);
    }

    // Metodo privado do size retornando apenas o tamanho do nó
    private int size(Node x){
        if (x == null) return 0;
        return x.size;
    }

    // Metodo privado da altura da árvore
    private int height(Node x){
        if (x == null) return -1;
        return x.height;
    }

    // Metodo para calcular o balanceamento conforme a propriedade da árvore AVL
    private int balanceFactor(Node x){
        if (x == null) return 0;
        return height(x.left) - height(x.right); // Ramo esquerdo menos o direito
    }

    // Metodo para recalcular a altura e tamanho da arvore
    private void update(Node x){
        x.size = 1 + size(x.left) + size(x.right); // Calculando o tamanho

        x.height = 1 + Math.max(height(x.left)) + Math.max(height(x.right)); // A altura vai ser 1 + a altura mais alta dos filhos
    }

    // Metodo para fazer a rotacao a direita de um nó
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        update(x);
        update(h);

        return x
    }

    // Metodo para rotacionar um nó a esquerda
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        update(x);
        update(h);

        return x
    }

    // Metodo para balancear uma subarvore
    private Node balance(Node x){
        if (x == null) return null; // Se for nulo quer dizer q nao existe o nó

        // Se o metodo de  verificar se um lado ta maior q o outro retorna a diferenca maior q 1, ent o ramo maior esta a esquerda
        if (balanceFactor(x) > 1){
            if (balanceFactor(x.left) < 0){ // Verificamos se a direia do ramo é maior q a esquerda, se sim ent precisamos reotacionar 
                x.left = balanceFactor(x.left);
            }

            rotateRight(x); // Fora da condicao é somente rotacionar pra direita para balancear

        } else if (balanceFactor(x) < -1){ // Se o metodo que verifica a altura dos ramos retornar algo menor que -1 é pq o maior ramo esta a direita
            if (balanceFactor(x.right) > 0){ // Verificamos se o ramo da esquerda é maior q o da direita
                x.right = rotateRight(x.right);
            }
            rotateLeft(x); // Rotaciona a esquerda pra balancear
        }

        update(x);
        return x;
    }

    // Metodo publico para inserir um nó na arvore AVL
    public void put(Key K, Value V){
        if (V == null) delete(K) return

        return root = put(root, K, V); // Transforma o nó raiz em preto sempre
    }

    // Metodo privado que insere o nó
    private Node put(Node x, Key K, Value V){
        if (x == null) return new Node(K, V, 0, 1); // Se o nó que estamos procurando for nulo é pq nao existe, ent inserimos!

        int cmp = K.compareTo(x.K); // Instanciando compareTo

        if (cmp < 0) x.left = put(x.left, K, V); // Se o resultado do compareTo for menor que 0 é pq a chave que estamos procurando é menor que a atual
        else if (cmp > 0) x.right = put(x.right, K, V);// Se o resultado for maior que 0, ent a chave que estamos procurando é maior
        else x.V = V;

        return balance(x);
    }

}
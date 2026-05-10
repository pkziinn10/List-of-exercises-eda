public class BSTsemCount<Key extends Comparable<Key>, Value>{
    private Node root; // Cria o nó raiz da arvore

    // Criando a estrutura do nosso nó
    private class Node{
        private Key K;
        private Value V;
        private Node right, left;
    }

    // Construtor de cada nó sem receber o count
    public Node(Key K, Value V){
        this.K = K;
        this.V = V;
    }

    // Metodo size que vai retorna o tamanho da árvore
    public int size(){
        return size(root); // Antes retornava o count, agr ta sendo usad chamda recursiva
    }

    private int size(Node x){
        if (x == null) return 0; // Caso o nó não exista na árvore

        // Aqui chama de forma recursiva se o nó existir
        // Soma 1 com ele msm, mais oq tem a esquerda e a direita  
        return 1 + size(x.left) + size(x.right);
    }

    // Metodo que serve para verificar se a árvore é vazia
    public boolean isEmpty(){
        return size() == 0; // Calcula de forma dinamica se o valor é 0
    }

    // Metodo para procura a chave na árvore
    public Value get(Key K){
        Node x == root; // Cria o nó raiz

        // while para caçar a arvore quando o nó raiz não é nulo
        while (x != null){
            int cmp = K.compareTo(x.K); // Comparando cm o compareTo

            if (cmp < 0) x = x.left; // Se o valor da comparação for menor, é pq a chave ta na esquerda
            else if (cmp > 0) x = x.right; // Se for maior, é pq ta na direita
            else return x.V; // Senão cmp é 0, ent achamos a chave
        }

        return null; // Caso x seja nulo, ent a chave nao está na arvore
    }

    // Metod para inserir um nó na arvore ou ent atualizar o valor
    public void put(Key K, Value V){
        if (V == null){ //Se o valor que formos inserir for nulo, ent excluimos a chave
            delete(K);
            return
        }

        // Chama de frma recursiva o metodo privado
        root = put(root, K, V);
    }

    // Metodo privado que faz a recursão
    private void put(Node x, Key K, Value V){
        if (x == null) return null; // Se a razi for nula, ent não existe arvore

        int cmp = K.compareTo(x.K);

        if (cmp < 0) return x.left = put(x.left, K, V); // Se o valor for menor, ent ta na esquerda
        else if (cmp > 0) return x.right = put(x.right, K, V); // Se for maior, ent ta na direita
        else x.V = V; // Senão é pq achamos a chave, ent atualiza o valor dela

        return x;
    }

    // Metodo para retornar o nó minimo
    private Node min(Node x){
        if (x.left == null) return x; // Se não tem nó a esquerda ent o atual é o nó minimo
        return min(x.left); // Senão, continua descendo a esquerda
    }

    // Metodo para deletar o nó minimo de uma arvore
    public void deleteMin(){
        if (isEmpty()) return;
        root = deleteMin(root);
    }

    // Metodo que deleta o nó mínimo da árvore
    private Node deleteMin(Node x){
        // Se não tem ngm a esquerda ent o nó atual e minimo
        // Retornamos o da direta para tirar o atual
        if (x.left == null) return x.right;

        x.left = deleteMin(x.left); // Continua descendo para a esquerda

        return x; // Só retornamos, ao inves de atualizar o count
    }

    public void delete(Key K){
        root = delete(root, K);
    }

    private Node delete(Node x, Key K){
        if (x == null) return null;

        int cmp = K.compareTo(x.K);

        if (cmp < 0) x.left = delete(x.left, K);
        else if (cmp > 0) x.right = delete(x.right, K);
        else {
            // Caso 1: Quando o nó só tem um filho
            if(x.right == null) return x.left;
            if(x.left == null) return x.right;

            // Caso 2: Quando o nó tem dois filhos
            Node t = x;

            x = min(t.right); // Buscamos o sucessor, que é o menor nó na subarvore a direita

            x.right = deleteMin(t.right); // Agora a subarvore a direta será a msm da estrutura antiga sem o nó minimo

            x.left = t.left; // Subarvore a esquerda continua do msm jeito
        }

        return x;
    }
}
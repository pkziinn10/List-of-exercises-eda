import java.util.HashSet;

public boolean isBinarySearchTree(Node r) {
    // 1. Verifica a integridade estrutural (sem ciclos)
    HashSet<Node> visitados = new HashSet<>();
    if (!checkTree(r, visitados)) {
        return false; // Não é uma árvore binária válida
    }

    // 2. Verifica a propriedade de busca passando limites nulos inicialmente (sem limites)
    return checkBST(r, null, null);
}

// Método da Questão 19 para verificar ciclos
private boolean checkTree(Node x, HashSet<Node> visitados) {
    if (x == null) return true;
    if (visitados.contains(x)) return false; // Ciclo detectado!
    
    visitados.add(x);
    return checkTree(x.left, visitados) && checkTree(x.right, visitados);
}

// Método novo para verificar as propriedades de BST
private boolean checkBST(Node x, Key min, Key max) {
    // Caso base: se chegou a um nó nulo, este caminho é válido
    if (x == null) return true;

    // Se o limite MÍNIMO existir, a chave atual TEM que ser MAIOR que ele
    // Caso contrário (se for menor ou igual), a regra da BST foi violada
    if (min != null && x.key.compareTo(min) <= 0) {
        return false;
    }

    // Se o limite MÁXIMO existir, a chave atual TEM que ser MENOR que ele
    // Caso contrário (se for maior ou igual), a regra da BST foi violada
    if (max != null && x.key.compareTo(max) >= 0) {
        return false;
    }

    // Chamadas recursivas:
    // Para a ESQUERDA: O limite mínimo continua o mesmo, mas o limite MÁXIMO passa a ser a chave atual.
    // Para a DIREITA: O limite MÍNIMO passa a ser a chave atual, mas o limite máximo continua o mesmo.
    return checkBST(x.left, min, x.key) && checkBST(x.right, x.key, max);
}

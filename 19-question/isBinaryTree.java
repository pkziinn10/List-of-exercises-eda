import java.util.HashSet;

public boolean isBinaryTree(Node r){
    // Cria um conjunto vazio para registrar os nós visitados
    HashSet<Node> visitados = new HashSet<>();

    // Chama o metodo checkTree para validação
    return checkTree(r, visitados);
}

private boolean checkTree(Node x, HashSet<Node> visitados){
    // Chegar ao nó nulo quer dizer que chegou no final de um ramo
    if (x == null) return true

    // Nesse caso chegamos ao msm nó ja visitado por um caminho diferente, ent existe ciclos
    if (visitados.conatins(x)) return false;

    visitados.add(x); // Marca o nó como visitado

    // Chama recursivamente, pois o nó so é valido se as subarvores a esquerda e a direita for valida
    return checkTree(x.left, visitados) && checkTree(x.right, visitados);
}
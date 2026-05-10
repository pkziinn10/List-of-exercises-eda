public boolean isBalanced(Node x){
    if (x == null) return true; 

    // Calcula a diferença entre os ramos da arvore
    int diferenca = Math.abs(height(x.left) - height(x.right));

    // Se a diferença for mair que 1 ent esta desbalanceada
    if (diferenca > 1) return false;

    // Fica chamando recursivamente para os demais nós
    return isBalancead(x.left) && isBalancead(x.right);
}
import java.util.LinkedList;
import java.util.Queue;

public class LinkedList<Key extends Comparable<Key>, Value>{
     private Node first;
     private int n;

    // Classe que define a estrutura do nó
     private class Node{
        private Key K;
        private Value V;
        private Node next;
        
        // Construtor do nó, inciando as chaves, valores e o proximo nó
        public Node(Key K, Value V, Node next){
            this.K = K;
            this.V = V;
            this.next = next;
        }
    }
    // Metodo que retorna a quantidade total de elementos na lista
    public int size(){
        return n;
    }

    // Metodo que verifica se a lista é vazia
    public boolean isEmpty(){
        return n == 0;
    }

    // Metodo para encontrar uma chave na lista
    public Value get(Key K){

        // Verificacao para saber se a chave é nula
        if (K == null) return null;

        // Percorremos toda a lista encadeada ate encontrar a chave
        for (Node x = first; x != null; x = x.next){

            // Aqui comparamos a chave do nó com a chave que quero encontrar
            int cmp = K.compareTo(x.K);

            // Se o resultado da comparacao for 0 é pq achamos o nó que contem a chave, ent retornamos o valor dela
            if (cmp == 0) return x.V;

            // Se a comparacao retorna algo menor, é pq esta na esquerda
            if (cmp < 0) return null;
        }

        return null;
    }

    // Metodo para inserir uma chave na lista
    public void put(Key K, Value V){

        // Se a chave for nula, retornamos null
        if (K == null) return null;

        // Se o valor for nulo a gente exclui a chave, pois nao pode existir
        if (V == null){
            delete(K);
            return;
        }

        Node anterior = null; // Guarda o nó anterior
        Node atual = first; // Guarda o nó atual

        // Aqui estamos fazendo a procura da chave que esta no nosso nó atual com a chave que queremos
        // Se essa comparacao for maior que 0, atualizamos o nó atual para o proximo e o anterior para para tual
        while (atual != null && K.compareTo(atual.K) > 0){
            anterior = atual;
            atual = atual.next;
        }

        // Se a comparacao for igual a 0 é pq achamos a chave, ent atualizamos o valor dela
        if (atual != null && K.compareTo(atual.K) == 0){
            atual.V = V;
            return;
        }

        Node novo = new Node(K, V, atual);

        // Se o nó anterior for igual a nulo, é pq nao andamos na lista
        if (anterior == null){
            first = novo; // Ent o nó que criamos vai ser o primeiro
        } else{
            anterior.next = novo; // Caso contrário, adicionamos ao proximo nó do anterior o novo que criamos
        }

        n++;
    }

    
}

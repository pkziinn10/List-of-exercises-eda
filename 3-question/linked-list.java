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

    public void delete(Key K){
        // Tratando o caso para se a chave for nula
        if (K == null) return null;

        Node anterior = null; // Criando o nó anterior
        Node atual = first; // Criando o nó atual

        // Verificando se a chave atual é maior que a chave removida
        while (atual != null && K.compareTo(atual.K) > 0){
            anterior = atual; // Se for, a gente so atualiza o valor do anterior com o nó atual
            atual = atual.next; // E o proximo nó vai ser o atual
        }

        // Se encontrarmos a chave
        if (atual != null && K.compareTo(atual.K) == 0){

            // Verificamos se o anterior é nulo
            if (anterior == null){
                first = atual.next; // Se for, ent a chave excluida vai ser a primeira, recebendo ent o proximo nó
            }else{
                anterior.next = atual.next; // Se não for nulo, ent a gente salva como o proximo nó do anterior sera o proximo do nó atual
            }
            n--; // E decrementa a quantidade dos elementos
        }
    }

    // Metodo para procura a menor chave
    public Key min(){

        // Verifica se a lista é vazia
        if (isEmpty()) return null;

        // Como a lista está ordenada, ent a chave min vai ser a primeira
        return first.key;
    }

    // Metodo para procurar a maior chave
    public Key max(){

        // Verifica se a lista é vazia
        if (isEmpty()) return null;

        Node anterior = null; // Cria o nó anterior
        Node atual = first; // Cria o nó atual

        // Enquanto o valor do atual for diferente do nulo, a gente so vai passando
        while (atual != null){
            anterior = atual; // Atualiza o valor do anterior
            atual = atual.next; // Atualiza o valor do atual
        }
        // Como saiu do loop, ent o atual é nulo, logo o anterior é o maior nó
        return anterior.key;
    }

    public Key floor(Key K){
        if (K == null || isEmpty()) return null;

        Node anterior = null;
        Node atual = first;

        while (atual != null){
            int cmp = K.compareTo(atual.K);

            if (cmp == 0) return atual.K;

            if (cmp < 0) break;

            anterior = atual;
            atual = atual.next;
        }

        return (anterior == null) ? null : anterior.K;
    }
}

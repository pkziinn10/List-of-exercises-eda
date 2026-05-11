package br.ufc.crateus.eda.st;

import java.util.LinkedList;
import java.util.Queue;

// A classe precisa que a Chave (K) seja Comparável para manter a ordem crescente
public class BinarySearchST<K extends Comparable<K>, V> implements ST<K, V> {
    private K[] keys;      // Vetor que armazenará as chaves em ordem crescente
    private V[] values;    // Vetor que armazenará os valores correspondentes
    private int n = 0;     // Contador de elementos presentes na tabela

    // Construtor: inicializa os vetores com o tamanho inicial solicitado
    public BinarySearchST(int length) {
        keys = (K[]) new Comparable[length]; // Inicializa vetor de chaves (ordenadas)
        values = (V[]) new Object[length];   // Inicializa vetor de valores
    }

    // Método Rank: O coração da busca binária. 
    // Ele diz quantas chaves no vetor são estritamente menores que a chave procurada.
    public int rank(K key) {
        int lo = 0, hi = n - 1;              // Define os limites inferior e superior da busca
        while (lo <= hi) {                   // Enquanto houver um intervalo válido
            int mid = lo + (hi - lo) / 2;    // Calcula o ponto médio (evita overflow)
            int cmp = key.compareTo(keys[mid]); // Compara a chave buscada com a do meio
            if (cmp < 0) hi = mid - 1;       // Se for menor, descarta a metade direita
            else if (cmp > 0) lo = mid + 1;  // Se for maior, descarta a metade esquerda
            else return mid;                 // Se for igual, retorna a posição exata
        }
        return lo;                           // Se não achar, retorna a posição onde a chave deveria ser inserida
    }

    @Override
    public void put(K key, V value) {
        if (key == null) return;             // Ignora chaves nulas
        int i = rank(key);                   // Usa busca binária para achar a posição da chave

        // Se a chave já existe (i < n e o que está lá é igual à chave)
        if (i < n && keys[i].compareTo(key) == 0) {
            values[i] = value;               // Apenas atualiza o valor
            return;
        }

        // Se o vetor estiver cheio, duplica o tamanho de ambos
        if (n == keys.length) resize(2 * keys.length);

        // ABRE ESPAÇO: Move todos os elementos maiores que a nova chave para a direita
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];           // Desloca a chave
            values[j] = values[j - 1];       // Desloca o valor
        }

        keys[i] = key;                       // Insere a nova chave na posição correta (mantendo a ordem)
        values[i] = value;                   // Insere o novo valor na posição correspondente
        n++;                                 // Incrementa o total de elementos
    }

    @Override
    public V get(K key) {
        if (isEmpty()) return null;          // Se a tabela estiver vazia, retorna nulo
        int i = rank(key);                   // Procura o índice da chave via Busca Binária
        // Se o índice for válido e a chave bater com a pesquisada
        if (i < n && keys[i].compareTo(key) == 0) return values[i];
        return null;                         // Caso contrário, chave não existe
    }

    @Override
    public void delete(K key) {
        if (isEmpty()) return;               // Tabela vazia, nada a fazer
        int i = rank(key);                   // Localiza a posição da chave
        if (i < n && keys[i].compareTo(key) == 0) { // Se a chave for encontrada:
            // FECHA O BURACO: Move todos os elementos à direita para uma posição à esquerda
            for (int j = i; j < n - 1; j++) {
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }
            n--;                             // Diminui o contador
            keys[n] = null;                  // Remove referência para ajudar o Garbage Collector
            values[n] = null;                // Remove referência do valor
        }
    }

    // Dobra o tamanho dos vetores mantendo os dados
    private void resize(int capacity) {
        K[] tempK = (K[]) new Comparable[capacity];
        V[] tempV = (V[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempK[i] = keys[i];
            tempV[i] = values[i];
        }
        keys = tempK;
        values = tempV;
    }

    @Override
    public boolean contains(K key) { return get(key) != null; }

    @Override
    public int size() { return n; }

    @Override
    public boolean isEmpty() { return n == 0; }

    @Override
    public Iterable<K> keys() {
        Queue<K> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) queue.add(keys[i]);
        return queue;
    }
}
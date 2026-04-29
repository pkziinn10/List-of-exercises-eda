import java.util.LinkedList;
import java.util.Queue;

public class ArrayST<K, V> implements ST<K, V> {
    private K[] keys;          // Vetor para armazenar as chaves
    private V[] values;        // Vetor para armazenar os valores correspondentes
    private int n = 0;         // Contador de pares chave-valor inseridos

    // Construtor: inicializa os vetores com o tamanho inicial (length)
    public ArrayST(int length) {
        keys = (K[]) new Object[length];     // Inicializa vetor de chaves
        values = (V[]) new Object[length];   // Inicializa vetor de valores
    }

    // Método Privado Resize: Duplica o tamanho dos vetores quando necessário
    private void resize(int capacity) {
        K[] tempK = (K[]) new Object[capacity];   // Novo vetor temporário para chaves
        V[] tempV = (V[]) new Object[capacity];   // Novo vetor temporário para valores
        for (int i = 0; i < n; i++) {             // Percorre os elementos atuais
            tempK[i] = keys[i];                   // Copia chave para o novo vetor
            tempV[i] = values[i];                 // Copia valor para o novo vetor
        }
        keys = tempK;                             // Substitui o vetor antigo pelo novo
        values = tempV;                           // Substitui o vetor antigo pelo novo
    }

    @Override
    public void put(K key, V value) {
        if (key == null) return;                  // Chaves nulas não são permitidas
        if (value == null) {                      // Se o valor for nulo, a regra da ST...
            delete(key);                          // ...é remover a chave da tabela
            return;
        }

        for (int i = 0; i < n; i++) {             // Percorre as chaves existentes (Busca Sequencial)
            if (keys[i].equals(key)) {            // Se a chave já existir na tabela
                values[i] = value;                // Atualiza o valor existente
                return;                           // Finaliza o método
            }
        }

        if (n == keys.length) resize(2 * keys.length); // Se o vetor encher, dobra o tamanho

        keys[n] = key;                            // Adiciona a nova chave no final
        values[n] = value;                        // Adiciona o novo valor no final
        n++;                                      // Incrementa o número de elementos
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < n; i++) {             // Percorre o vetor de chaves
            if (keys[i].equals(key))              // Se encontrar a chave
                return values[i];                 // Retorna o valor na mesma posição
        }
        return null;                              // Se não encontrar, retorna nulo
    }

    @Override
    public void delete(K key) {
        for (int i = 0; i < n; i++) {             // Busca a posição da chave
            if (keys[i].equals(key)) {            // Se encontrar:
                keys[i] = keys[n-1];              // Coloca a última chave no lugar da deletada
                values[i] = values[n-1];          // Coloca o último valor no lugar do deletado
                keys[n-1] = null;                 // Limpa a última posição (evita loitering)
                values[n-1] = null;               // Limpa a última posição do valor
                n--;                              // Diminui o contador
                return;
            }
        }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;                  // Verifica se o get retorna algo diferente de null
    }

    @Override
    public int size() {
        return n;                                 // Retorna a quantidade de elementos
    }

    @Override
    public boolean isEmpty() {
        return n == 0;                            // Retorna verdadeiro se n for zero
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> queue = new LinkedList<>();      // Cria uma fila para armazenar as chaves
        for (int i = 0; i < n; i++) queue.add(keys[i]); // Adiciona todas as chaves na fila
        return queue;                             // Retorna a fila como um iterável
    }
}
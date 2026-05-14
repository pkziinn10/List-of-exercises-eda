public class Page<Key extends Comparable<Key>, Value> {
    
    // ATRIBUTOS DA PÁGINA
    private int M;                 // Ordem da Árvore. Define o tamanho limite dos arrays.
    private int n;                 // Contador exato de quantas chaves estão guardadas AGORA nesta página.
    private boolean isExternal;    // Funciona como um "crachá": true = Sou uma Folha; false = Sou um Nó Interno (tenho filhos).
    
    private Key[] keys;            // O armário ordenado onde guardamos as chaves.
    private Value[] vals;          // O armário de gavetas correspondentes aos valores (usado SÓ se for Folha).
    private Page[] children;       // Os "fios" que ligam esta página às páginas de baixo (usado SÓ se for Nó Interno).

    public Page(boolean isExternal, int M) {
    }

    private int rank(Key K) {
        if (K == null) return 0;

        int lo = 0, hi = n - 1;

        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = K.compareTo(keys[mid]);

            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }

        return lo;
    }

    
    // Puxa um valor guardado (Só funciona nas folhas)
    public Value get(Key K) {
        if ( K == null ) return null;

        int i = rank(K);

        if ( i < n && keys[i].compareTo(K) == 0){
            return vals[i];
        }

        return null;
    }
    public Page next(Key key) {
        
    }
    
    // Insere dados novos numa página Folha
    public void put(Key key, Value val) {
    }

    // Insere um NOVO FILHO numa página Interna (Isso acontece quando uma página de baixo quebra/split)
    public void put(Key key, Page childPage) {
    }
    
    // Métodos de utilidade rápidos
    public boolean isExternal() { return isExternal; }
    public boolean isEmpty()    { return n == 0; }
    public boolean hasOverflow(){ return n == M; } // Se bater no M, a página transbordou e precisa do split.

    // "Rasga" a página no meio e devolve a metade nova que foi criada
    public Page split() {

    }
}
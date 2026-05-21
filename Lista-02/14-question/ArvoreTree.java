public class ArvoreTree<Key extends comparable<Key>, Value>{
    private int M;
    private int N;
    private boolean isExternal;

    private Key[] keys;
    private Value[] vals;
    private Page[] children;
}
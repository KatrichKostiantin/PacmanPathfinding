public interface SearchPath {
    Couple step();
    Iterable<Integer> pathTo(int v);
}

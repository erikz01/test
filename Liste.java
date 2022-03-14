interface Liste <T> extends Iterable<T> { //B1
    int stoerrelse ();
    void leggTil (T x);
    T hent ();
    T fjern ();
}
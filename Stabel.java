public class Stabel <T> extends Lenkeliste <T> {
    @Override
    // putter noder forst i listen
    public void leggTil(T x) {
        Node nyNode = new Node(x);

        antNoder++;

        // hvis det er ingen noder i listen
        if (forste == null) {
            forste = nyNode;
            return;
        }
        // // annen maate aa gjore det paa med to pekere
        // Node nyPeker = forste;
        // forste = nyNode;
        // forste.neste = nyPeker;
        nyNode.neste = forste;
        forste = nyNode;
    }
}

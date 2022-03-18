public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override
    // putter noden i listen slik at det blir i stigende rekkefolge fra minst til hoyst
    public void leggTil(T x) {
        Node nyNode = new Node(x);
        antNoder++;
        
        // hvis listen er tom
        if (forste == null) {
            forste = nyNode;
            return;
        }

        Node tmp = forste;

        // hvis dataen man putter inn er minst
        // hvis dataen er helt likt i forste node
        if (forste.data.compareTo(nyNode.data) > 0 || forste.data.compareTo(nyNode.data) == 0) {
            nyNode.neste = forste;
            forste = nyNode;
            return;
        }

        // kjorer hvis dataen > forste
        while (tmp.neste != null) {
            // gaar inn hvis tmp.data < nyNode.data
            if (tmp.data.compareTo(nyNode.data) < 0) {
                
                //gaar inn hvis tmp.neste.data == nyNode.data
                if (tmp.neste.data.compareTo(nyNode.data) == 0) {
                    nyNode = tmp.neste;
                    tmp.neste = nyNode;
                    return;
                }

                //gaar inn hvis tmp.neste.data > nyNode.data
                if (tmp.neste.data.compareTo(nyNode.data) > 0) {
                    nyNode = tmp.neste;
                    tmp.neste = nyNode;
                    return;
                }
                tmp = tmp.neste;
            }
        }
        tmp.neste = nyNode;
    }

    @Override
    public T hent() {
        return super.hent();
    }
    
    @Override
    public T fjern() {
        return super.fjern();
    }
}

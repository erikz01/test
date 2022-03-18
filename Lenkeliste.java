import java.util.Iterator;

public abstract class Lenkeliste <T> implements Liste <T> {

    Node forste = null;
    int antNoder = 0;

    public class Node {
        Node neste = null;
        T data;

        // konstruktor
        public Node(T data) {
            this.data = data;
        }
    }
    @Override
    // returnere antall noder i Lenkelisten
    public int stoerrelse() {
        return antNoder;
    }
    @Override
    // legger node sist i listen
    public void leggTil(T x) {
        // oppretter ny node
        Node nyNode = new Node(x);

        // plusser paa tellern
        antNoder ++;

        // hvis forste peker paa null, sett forste til aa peke paa noden
        if (forste == null) {
            forste = nyNode;
            return;
        }

        // nyPeker peker forst paa forste noden
        Node nyPeker = forste;
        while (nyPeker.neste != null) {
            
            // flytter paa nyPeker til aa peke paa Nypeker sin neste
            nyPeker = nyPeker.neste;
        }
        // hvis nyPeker.neste peker paa null, saa pek paa den ny noden
        // nyPeker.neste peker paa null, hvis nyPeker peker paa siste node i listen
        nyPeker.neste = nyNode;
    }
    @Override
    // henter data i forste node
    public T hent() {
        return forste.data;
    }
    @Override
    // fjerner forste element i listen
    public T fjern() {

        // hvis listen er tom kjor
        if (forste == null) {
            throw new UgyldigListeindeks(-1);
        }

        antNoder --;
        // hvis det er ett element og man fjerner den
        if (forste.neste == null) {
            T res = forste.data;
            forste = null;
            // antNoder --;
            return res;
        }
        
        // hvis det er flere enn en node i listen
        Node nyPeker = forste;  
        T res = nyPeker.data; 
        nyPeker = forste.neste;
        forste = nyPeker;
        return res;
    }
    @Override

    // printer ut dataen i alle nodene i listen
    public String toString() {
        String svarStreng = " ";
        Node nyPeker = forste;
        if (forste == null) {
            return "Listen er tom; ";
        }
        else {
            while (nyPeker != null) {
                svarStreng += nyPeker.data + ", ";
                nyPeker = nyPeker.neste;
            }
            return svarStreng;
        }
    }
    // lager iterator for aa kunne bruke en for-each loop
    @Override
    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }
    
    public class LenkelisteIterator implements Iterator<T> {
        T verdi;
        Node node = forste;
        
        @Override
        public boolean hasNext() {
            return (node != null);
        }
        @Override
        public T next() {
            verdi = node.data;
            node = node.neste;
            return verdi;
        }
    }
}

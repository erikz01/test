public class IndeksertListe <T> extends Lenkeliste <T> {
    int teller;

    // legger til node i en posisjon
    public void leggTil (int pos, T x) {
        // hvis pos er ugyldig
        if (pos < 0 || pos > stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        // lager ny node og plusser paa antNoder telleren
        Node nyNode = new Node(x);
        antNoder++;
        
        // kjorer hvis listen er tom
        if (forste == null) {
            forste = nyNode;
            return;
        }

        // legger node paa slutten av listen
        if (pos == stoerrelse()) {
            Node sluttNode = forste;
            
            while (sluttNode.neste != null) {
                sluttNode = sluttNode.neste;
            }
            sluttNode.neste = nyNode;
            return;
        }
        
        // putter noden paa starten av lenkelisten
        if (pos == 0) {
            nyNode.neste = forste;
            forste = nyNode;
            return;
        }
        
        //kjorer hvis noden skal settes i midten av listen
        Node nyPeker = forste;
        while (teller + 1 < pos) {
            nyPeker = nyPeker.neste;
            teller++;
        }
        nyNode.neste = nyPeker.neste;
        nyPeker.neste = nyNode;
        teller = 0;
    }
    
    public void sett (int pos, T x) {

        // hvis pos er ugyldig
        if (pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }

        // hvis pos er 0
        if (pos == 0) {
            forste.data = x;
        }
        Node nyPeker = forste;

        // kjorer hvis pos > 0
        while (teller < pos) {
            nyPeker = nyPeker.neste;
            teller++;
        }
        nyPeker.data = x;
        teller = 0;
    }

    public T hent (int pos) {

        // hvis pos er ugyldig
        if (pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }

        // hvis vi skal hente data i forste noden
        if (pos == 0) {
            return forste.data;
        }
        Node nyPeker = forste;

        // kjorer hvis pos > 0
        while (teller < pos) {
            nyPeker = nyPeker.neste;
            teller++;
        }
        teller = 0;
        return nyPeker.data;
    }

    public T fjern (int pos) {

        // hvis det ikke er noder i listen
        if (forste == null) {
            throw new UgyldigListeindeks(-1);
        }

        // hvis pos er ugyldig (utenfor det som er tillat)
        if (pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }

        Node nyPeker = forste;

        // kjorer hvis man fjerner forste element
        if (pos == 0) {
            T res = forste.data;
            nyPeker = nyPeker.neste; 
            forste = forste.neste;
            return res;
        }

        // kjorer hvis pos > 0
        while (teller + 1 < pos) {
            nyPeker = nyPeker.neste;
            teller++;
        }
        T res = nyPeker.neste.data;
        Node tmp = nyPeker.neste.neste;

        nyPeker.neste = tmp;
        forste = nyPeker;
        antNoder--;
        return res;
    }
}

import java.util.Iterator;

//Bekalger for mye kommentarer i denne obligen, men siden vi skal bruke denne obligen i senere obliger har jeg valgt å kommentere mer for å hjelpe meg i fremtiden. I tillegg var denne obligen litt brainfuck
public abstract class LenkeListe<T> implements Liste<T> {

    //Gjenbrukte kode fra forelesningen
    public class Node {  
        Node neste_node = null;
        T data;
        Node (T x) { this.data = x;}
    }
//Tips fra oppgaven om å bruke protected istenfor private 
    protected int stoerrelse = 0;
    protected Node første_node = null;
    protected Node siste_node = null;

//B2
    class LenkeListeIterator implements Iterator<T> {
        Node peker = første_node;

        @Override
        public boolean hasNext() {
            if (er_listen_tom()) {
                return false;
            }
            else {  
                return true;
            }
        }

        @Override
        public T next() {
            T data = peker.data;
            peker = peker.neste_node;
            return data;
        }
    }

//B3
    public Iterator<T> iterator() {
        return new LenkeListeIterator();
    }


//Metoden stoerrelse() returner størrelsen av listen
    public int stoerrelse() { 
        return this.stoerrelse;
    }



//Metoden leggTil(T x) skal legge inn et nytt element; det skal legges sist i listen.
    @Override
    public void leggTil(T x) {
        Node nyNode = new Node(x);

        //Hvis listen er tom 
        if(er_listen_tom()) {
            første_node = nyNode;
            
        }

        //Gjenbrukte kode fra gruppetime
        else {
            Node test_node = første_node;
            //Denne måten finner man siste node fordi hver node er linket til hverandre unntatt den siste som er null: så lenge en node ikke er null vil test_node gå til neste node
            //test_node er en test node som har oppgaven å finne den siste noden 
            while (test_node.neste_node != siste_node) {
            test_node = test_node.neste_node;
            }
            //når while finner at test_node er null vil den siste noden sin null bli erstattet med en kobling til Nynode. Da er ikke siste node null lengre og blir den nest siste noden. Den siste noden nå er nyNode som nå er null. Dette vil skje hver gang leggTil(T x) blir brukt 
            test_node.neste_node = nyNode;                

            }
            //adderer med 1 siden nå har størrelsen blitt økt med 1
            stoerrelse +=1;
        }



//Metoden hent() skal returnere det FØRSTE elementet i listen. HUSK det skal ikke fjernes fra listen.
    @Override
    public T hent() {
        //sjekker om den er tom
        if (er_listen_tom()) {
            System.out.println("Den er tom");
        }
        //hvorfor funker det ikke med x??
        //return første_node.x;
        return første_node.data;
    }



//Sjekker om først_node sin verdier null. Dersom den er det betyr det at det ikke er noen elementer i første_node noe som igjen betyr at den er tom.
    public boolean er_listen_tom() {
        if (første_node == null) {
            return true;
        }
        else {  
            return false;
        }
    }



// Metoden fjern() skal fjerne det første elementet i listen og returnere det.
    @Override
    public T fjern(){
        if (er_listen_tom()){
            throw new UgyldigListeindeks(0);
        }
        T fjernet_element = første_node.data;

        // Derosom første_node sin neste_node er null betyr det at første_node ikke har noen neste_node, som igjen betyr at listen bare innehodler et element. Det betyr at dette elementet er både første_node og siste_node. Skriver at begge er null fordi de ikke referer til noe 
        if(første_node.neste_node == null){
            siste_node = null;
            første_node = null;
        }

        else if (er_listen_tom()==false){ 
            første_node = første_node.neste_node;

        }
        stoerrelse-=1;
        return fjernet_element;
    }



    @Override
    public String toString(){
        if (er_listen_tom()){
            return ("Listen er tom");
        }
        String str = " ";        
        Node node = første_node;
        str += node.data;
        while(node.neste_node != siste_node){
            str +=" "+ node.neste_node.data;
            node = node.neste_node;
        }
        return str;
    }

}


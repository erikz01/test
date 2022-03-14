public class Koe<T> extends LenkeListe<T> {

    //Som jeg oppfatter det fungerer et kø slik at en ny person skal stå sist og at personen først i køen blir blir fjernet først.
    //Skriver at metoden for leggTil() legger elementet bakerst og fjern() fjerner første element. 


    @Override
    //fjerne det første elementet
    public T fjern(){
        if (er_listen_tom()){
            throw new UgyldigListeindeks(0);
        }

        T fjernet_element = første_node.data;

        if(første_node.neste_node == null){
            siste_node = null;
            første_node = null;
        }

        else if (!er_listen_tom()){
            første_node = første_node.neste_node;
        }
        stoerrelse-=1;
        return fjernet_element;
    }



    @Override
    //Metoden leggTil(T x) skal legge inn et nytt element; det skal legges sist i listen.
    public void leggTil(T x) {
    Node nynode = new Node(x);
        if(første_node == null) {
            første_node = nynode;
        }

        else {
        Node n = første_node;
            while(n.neste_node != null) {
            n = n.neste_node;
            }
        n.neste_node = nynode;
        }
        stoerrelse +=1;
    }
}

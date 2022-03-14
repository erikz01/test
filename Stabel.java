public class Stabel<T> extends LenkeListe<T> { 
    @Override
    public void leggTil(T x) {
        Node nyNode = new Node(x);

        //Nynode sin null blir erstattet med referansen til førstenode som nå blir den nest første noden
        nyNode.neste_node = første_node;
        //Videre vil Nynode bli til første_node, den er ikke null fordi den referer til den nest første noden
        første_node = nyNode; 

        stoerrelse+=1;
    }

}
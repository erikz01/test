public class Prioritetskoe<T extends Comparable<T>> extends LenkeListe<T> {

    public void leggTilbestemt(int indeks,T x)	{
            Node peker = new Node(x);
            
            if(indeks==0){
                Node nyNode = new Node(x);
                nyNode.neste_node = første_node;
                første_node = nyNode;
            }
            else{
            Node nåværende_node = første_node;
                for(int i=1;i<indeks;i+=1){
                    nåværende_node = nåværende_node.neste_node;
                }
            peker.neste_node = nåværende_node.neste_node;
            nåværende_node.neste_node = peker;
            }
        }

    @Override
    public void leggTil(T x) {
        Node peker = første_node;
        //Gjenbrukte kode fra forelesningen
        if (er_listen_tom()) {
            super.leggTil(x);
            return;
        }
        for (int i = 0; i < stoerrelse; i++) {
            if (peker.data.compareTo(x)>=0) {
                //trenger en metode som kan legge til en element i en bestemt indeks
                leggTilbestemt(i, x);
                stoerrelse+=1;
                return;
            }
        }
        super.leggTil(x);
        //det funket:D
    } 

}

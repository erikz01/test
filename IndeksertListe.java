public class IndeksertListe <T> extends LenkeListe<T> {

    //Metoden leggTil(int pos,T x) skal sette inn x i listen i posisjon pos
    public void leggTil(int pos,T x)	{

    if(pos < 0 || pos > stoerrelse()){
        throw new UgyldigListeindeks(pos);
    }
        Node ny_første_node = new Node(x);	
		Node nyNode = new Node(x);
	
        //hvis brukeren har lyst å legge til et element i indeks 0
		if(pos==0)
		{
            ny_første_node.neste_node = første_node;
            første_node = ny_første_node;
		}

		else{
		Node nåværende_node = første_node;
        //skriver int i = 1 dersom else kjører vet vi allerede at vi ikke skal erstatte den første noden som er indeks 0 
		for(int i=1;i<pos;i+=1){
            nåværende_node = nåværende_node.neste_node;
		}
        //nåværende_node sin referanse blir erstattet med referansen til nyNode. Altså nåværende_node sin neste node før nyNode kom inn
		nyNode.neste_node = nåværende_node.neste_node;
        //nyNode vil nå ligge etter nåværende_node
		nåværende_node.neste_node = nyNode;
		}
        stoerrelse += 1;
        //det fungerer!!!
	}


//Metoden sett(int pos,T x) skal erstatte elementet i posisjon pos med x
    public void sett(int pos, T x){
        if(pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }

        Node peker = første_node;
        for(int i = 0; i < pos; i+=1){
            peker = peker.neste_node;
        }
        peker.data = x ;   
    }


//Metoden hent(int pos) skal hente elementet i gitt posisjon der
    public T hent(int pos){
        if(pos < 0 || pos >= stoerrelse()){
        throw new UgyldigListeindeks(pos);
        } else {

        Node peker = første_node;
        for(int i = 0; i < pos; i+=1){
            peker = peker.neste_node;
        }
        return peker.data;
        }
    }


//Metoden fjern(int pos) skal fjerne elementet i posisjon pos 
    public T fjern(int pos) {
        if(pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }

        Node peker = første_node;
        T res = null;

        if (pos == 0) {
            første_node = første_node.neste_node;
        }

        //Gjenbrukte kode fra forelesningen
        else { 
            for (int i = 0; i < pos-1; i+=1){
                peker = peker.neste_node;
            }    
            res = peker.neste_node.data;
            peker.neste_node = peker.neste_node.neste_node;
        }

        stoerrelse-=1;
        return res;
    }
}

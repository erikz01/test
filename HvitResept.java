public class HvitResept extends Resept {
    
    public HvitResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }


    @Override
    public String farge() {
        return "hvit";
    }

//returnerer legemiddel.hentPris for å gjøre det enklere for meg å huske prisen jeg setter på legemiddlene 
    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }


    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + " " + "Farge: hvit";
    }
}   

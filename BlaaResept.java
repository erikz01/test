public class BlaaResept extends Resept {
    
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return "blaa";
    }

    @Override
    public int prisAaBetale() {
        // Betaler bare 25 prosent av prisen, faar altsaa 75 prosent rabatt
        return (int)(legemiddel.hentPris()*0.25);
    }

    @Override
    public String toString() {
        return ("-------BlaaResept-------" + '\n' +
        legemiddel.toString() + '\n' + 
        "Pris med resept: " + prisAaBetale() + " kr" + '\n' +
        "ReseptId: " + reseptId + '\n' + 
        hentLege() + '\n' +
        "Pasient: " + pasient + '\n' +
        "antReit:" + reit + '\n');
    }
}

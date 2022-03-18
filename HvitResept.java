public class HvitResept extends Resept {
    
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return "hvit";
    }
    
    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }

    @Override
    public String toString() {
        return ("-------HvitResept-------" + '\n' + 
        legemiddel.toString() + '\n' + 
        "ReseptId: " + reseptId + '\n' + 
        hentLege() + '\n' +
        "Pasient: " + pasient + '\n' +
        "antReit:" + reit + '\n');
    }
}

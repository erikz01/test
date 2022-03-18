public class MilResept extends HvitResept {
    
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3);
        
    }
    @Override
    public int prisAaBetale() {
        // MilResept er gratis
        return 0;
    }

    @Override
    public String toString() {
        return ("-------MilResept-------" + '\n' +
        legemiddel.toString() + '\n' + 
        "Pris med resept: " + prisAaBetale() + " kr" + '\n' +
        "ReseptId: " + reseptId + '\n' + 
        hentLege() + '\n' +
        "Pasient: " + pasient + '\n' +
        "antReit:" + reit + '\n');
    }
}

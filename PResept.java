public class PResept extends HvitResept {

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale() {
        // brukeren kan aldri betale mindre enn 0 kroner
        if (legemiddel.hentPris()-108 < 0) {
            return 0;
        }
        return legemiddel.hentPris()-108;
    }

    @Override
    public String toString() {
        return ("-------P_resept-------" + '\n' +
        legemiddel.toString() + '\n' + 
        "Pris med resept: " + prisAaBetale() + " kr" + '\n' +
        "ReseptId: " + reseptId + '\n' + 
        hentLege() + '\n' +
        "Pasient: " + pasient + '\n' +
        "antReit:" + reit + '\n');
    }
}
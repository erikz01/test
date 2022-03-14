public class BlaaResept extends Resept {

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }


    @Override
    public int prisAaBetale() {
        //før 
        //int rabatt = (int) (legemiddel.hentPris() * 0.75);
        int rabatt = (int)Math.round(legemiddel.hentPris() * 0.75);
        int endelig_pris = legemiddel.hentPris() - rabatt;
        //Hvorfor spør oppgaven oss om å runde av til nærmeste krone hvis prisen allerede er et heltall?
        //før return endelig_pris;
        return endelig_pris;
    }


    @Override
    public String farge() {
        return "blå";
    }


    @Override
    public String toString() {
        return "Legemiddel: " + legemiddel.toString() + " " + "Farge: blå";
    }
}

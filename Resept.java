public abstract class Resept {
    protected static int reseptId;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;
    
    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        reseptId++;
    }
    public int hentReseptId() {
        return reseptId;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public Pasient pasient() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        // hvis reit er under 1 sa returneres false
        if (reit > 0) {
            reit--;
            return true;
        }
        return false;
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    // @Override
    public String toString() {
        return (legemiddel.toString() + '\n' + 
        "ReseptId: " + reseptId + '\n' + 
        hentLege() + '\n' +
        "Pasient: " + pasient + '\n' +
        "antReit:" + reit + '\n');
    }
}

public abstract class Legemiddel {
    
    protected String navn;
    protected int pris;
    protected double virkestoff;
    protected static int Id;
    
    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        Id++ ;
    }

    public int hentId() {
        return Id;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(int nyPris) {
        pris = nyPris;
    }

    @Override
    public String toString() {
        return "Legemiddel: " + navn + '\n' + "pris: " + pris + " kr" + '\n' + "virkestoff: " + virkestoff + " mg" + '\n';
    }
}
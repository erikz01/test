public class Narkotisk extends Legemiddel {

    private int narkotisk_styrke;

    public Narkotisk(String navn, int pris, double virkestoff, int narkotisk_styrke) {
        super(navn, pris, virkestoff);
        this.narkotisk_styrke = narkotisk_styrke;
    }


    //Metode som returnerer narkotisk styrke
    public int hentNarkotiskStyrke() {
        return narkotisk_styrke;
    }

    @Override
    public String toString() {
        return "navn: " + navn + " pris: " + pris + " virkestoff: " + virkestoff + " narkotisk styrke " + narkotisk_styrke;
    }

}

public class Vanedannende extends Legemiddel { 

    private int vanedannende_styrke;

    public Vanedannende(String navn, int pris, double virkestoff, int vanedannende_styrke) {
        super(navn, pris, virkestoff);
        this.vanedannende_styrke = vanedannende_styrke;
    }


    //Metode som returnerer vanedannende styrke 
    public int hentVanedannendeStyrke() {
        return this.vanedannende_styrke;
    }

    @Override
    public String toString() {
        return "navn: " + navn + " pris: " + pris + " virkestoff: " + virkestoff + " Vanedannede med styrke: " + vanedannende_styrke;
    }



}
public abstract class Legemiddel { 

    protected static int id_klasse = 1;
    protected int id;
    protected String navn;
    protected int pris;
    // husk det skal være i mg
    protected double virkestoff;


    public Legemiddel(String navn, int pris, double virkestoff) {
        this.id = id_klasse;
        //adderer id_klasse med 1 for hver gang jeg lager en subklasse av Legemiddel
        id_klasse ++;
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
    }


    //Metode som returnerer id
    public int hentId() {
        return this.id;
    }


    //Metode som returnerer navn
    public String hentNavn() {
        return this.navn;
    }


    //Metode som returnerer pris
    public int hentPris() {
        return this.pris;
    }    


    //Metode som returnerer virkestoff i mg
    public double hentVirkestoff() {
        return this.virkestoff;
    }


    //Metode som endrer verdi på pris
    public void settNyPris(int ny_pris) {
        this.pris = ny_pris;
    }
}

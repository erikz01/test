public class Pasient {
    
    private String navn;
    private String fodselnr;
    private int id;
    private static int id_klassen = 1;
    //liste med resepter
    IndeksertListe<Resept> liste;//er Stabel den beste måten?
    int antallNarkotiskeResepter = 0;

    //veldig lik oppgave som klassen resept i oblig 2
    public Pasient(String navn, String fodselnr) {
        this.navn = navn;
        this.fodselnr = fodselnr;
        this.id = id_klassen;
        id_klassen +=1;
    }

    public IndeksertListe<Resept> hent_reseptliste(){
        return liste;
    }

    public String hent_navn() { 
        return navn;
    }

    public String hent_fodselnr() { 
        return fodselnr;
    }

    public int hent_id() { 
        return id;
    }

    @Override
    public String toString() {

        return (navn + ", " + fodselnr);

    }


    //brukes i legemiddel for a telle narkotisk legemiddel for pasient
    public int hentAntallNarkotisk() {
        return antallNarkotiskeResepter;
    }

    public void oekAntallNarkotisk() {
        antallNarkotiskeResepter++;
    }

    public void leggTilResept(Resept resept) {
        liste.leggTil(resept);
    }
}

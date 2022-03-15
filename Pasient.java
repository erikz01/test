public class Pasient {
    
    private String navn;
    private String fodselnr;
    private int id;
    private static int id_klassen = 1;
    Stabel<Resept> liste;//er Stabel den beste måten?

    //veldig lik oppgave som klassen resept i oblig 2
    public Pasient(String navn, String fodselnr) {
        this.navn = navn;
        this.fodselnr = fodselnr;
        this.id = id_klassen;
        id_klassen +=1;
    }

    public Stabel<Resept> hent_reseptliste(){
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
}

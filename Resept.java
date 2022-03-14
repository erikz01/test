public abstract class Resept {
    
    protected int id;
    protected static int id_klasse_respet = 1;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected Pasient pasient;
    //reit er antall ganger igjen på respeten. husk å gjøre den ugyldig når den er 0
    protected int reit;


    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
    id = id_klasse_respet;
    id_klasse_respet +=1;

    this.legemiddel = legemiddel;
    this.lege = utskrivendeLege;
    this.pasient = pasient;
    this.reit = reit;
    }


    public int hentId(){
        return id;  
    }


    public Legemiddel hentLegemiddel(){
        return legemiddel;  
    }


    public Lege hentLege(){
        return lege;  
    }


    public Pasient hentPasient(){
        return pasient;  
    }


    public int hentReit(){
        return reit;  
    }


    //Forsøker å bruke resepten én gang. Returner false om resepten alt er oppbrukt, ellers returnerer den true. I tillegg fjerner den en dersom det ikke er tomt.
    public boolean bruk() {
        if (reit > 0) {
            reit -=1;
            return true;
        }
    return false;
    }


    //abstract public String farge: Returnerer reseptens farge. Enten “hvit” eller "blaa".
    abstract public String farge();


    //abstract public int prisAaBetale: Returnerer prisen pasienten må betale.
    abstract public int prisAaBetale();

    @Override
    public String toString() {
        return super.toString();
    }


}

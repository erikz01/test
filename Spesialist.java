public class Spesialist extends Lege implements Godkjenningsfritak {

    private String kontrollID;

    public Spesialist(String navn, String kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }


    @Override
    public String hent_KontrollID() {
        return kontrollID;
    }


    @Override
    public String toString() {
        return super.toString() + "\n" + "kontrollID: " + kontrollID;
    }
}

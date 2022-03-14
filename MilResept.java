public class MilResept extends HvitResept {

    public MilResept(Legemiddel legemiddel, Lege lege, Pasient pasient) {
        super(legemiddel, lege, pasient, 3 );
    }

    //returner 0 siden det 100% rabatt som er gratis
    @Override
    public int prisAaBetale() {
        return 0;
    }

    @Override
    public String toString() {
        return "Militær resept med 100% rabatt " + super.toString();
    }
}

public class TestlesFil {
    public static void main(String[] args) throws UlovligUtskrift {
        Legesystem2 legesystem2 = new Legesystem2();
        // tester lesFil metode
        legesystem2.lesFil("legedata.txt");
        //legesystem2.skriv_info();2

        // tester legg til i kommandoløkken
        legesystem2.kommandoLokke();
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Legesystem2 {
    private IndeksertListe<Pasient> pasientliste = new IndeksertListe<>();
    private IndeksertListe<Legemiddel> legemiddelliste = new IndeksertListe<>();
    private Prioritetskoe<Lege> legeliste = new Prioritetskoe<>(); //vett ikje koffor det ikje fungerer 
    private IndeksertListe<Resept> reseptliste = new IndeksertListe<>();

    public void lesFil(String filnavn) {
        
        //Scanner scan = null;

        //try & catch for a sjekke om det blir tatt inn fil
        try {

            //lager et filobjekt som sendes til Scanner
            File fil = new File(filnavn);
            Scanner scanner = new Scanner(fil);


        //lesing av fil
        while (scanner.hasNextLine()) {

            int indeks = 0;

            String linje = scanner.nextLine();
            String[] biter = linje.split(",");
            //System.out.println(linje);

            //hvis det starter med 
            if(linje.contains("#")) {
                indeks ++;
                //return;
            }

            //pasient
            if (indeks == 1) {
                String fullnavn = biter[0];
                String fnr = biter[1];
                Pasient pasientinfo = new Pasient(fullnavn, fnr);
                pasientliste.leggTil(pasientinfo);
            }

            //Legemidler
            else if (indeks == 2) {
                
                String navn = biter[0];
                double mengdeVirkestoff = Double.parseDouble(biter[3]);
                //Type er biter[1]
                int pris = Integer.parseInt(biter[2]);
                //double mengdeVirkestoff = Double.parseDouble(biter[3]);

                //hvis det er mindre enn 4 biter, så tilhører den Vanlig legemiddel
                if (biter.length < 4) {
                    
                    Vanlig vanlig = new Vanlig(navn, pris, mengdeVirkestoff);
                    legemiddelliste.leggTil(vanlig);
                    return;

                }

                //Hvis det er narkotisk eller vanedannende
                //USIKKER HVORDAN MAN VISER TIL AT LEGEMIDDELET ENTEN TILHØRER NARKOTISK ELLER VANEDANNENDE?
    

                Narkotisk narkotisk = new Narkotisk(navn, pris, mengdeVirkestoff, Integer.parseInt(biter[3]));
                Vanedannende vanedannende = new Vanedannende(navn, pris, mengdeVirkestoff, Integer.parseInt(biter[3]));
                
                if(biter[1].equals(narkotisk)) {
                    legemiddelliste.leggTil(narkotisk);
                }
                
                else if (biter[1].equals(vanedannende)) {
                    legemiddelliste.leggTil(vanedannende);
                }

                // if (narkotisk instanceof Narkotisk){

                //     legemiddelliste.leggTil(narkotisk);

                // } else if (vanedannende instanceof Vanedannende) {

                //     legemiddelliste.leggTil(vanedannende);

                // }
        
            }

            //Lege
            else if (indeks == 3) {

                //Fjerner dr. fra alle doktornavnene
                // https://www.codegrepper.com/code-examples/java/java+remove+first+3+characters+from+string
                String midlNavn = biter[0];
                String lege = midlNavn.substring(4);

                String id = biter[1];

                //hvis biter[1] (id) == 0, så er det vanlig lege
                if (id.equals(0)) {
                    Lege legeinfo = new Lege(lege);
                    legeliste.leggTil(legeinfo);
                }
                
                else if (id.equals(0)) {
                    Spesialist spesialistInfo = new Spesialist(lege, id);
                    legeliste.leggTil(spesialistInfo, id);
                }
                
                //Jeg vet ikke hvordan prioritetskoen din ser ut? Så vet ikke hvordan jeg skal gaa frem videre?   

            }

            //Resept
            else if (indeks == 4) {

                //bruker Integer.parseInt(); fra oblig 1
                int legemiddelNummer = Integer.parseInt(biter[0]);
                Legemiddel valgtLegemid = null;
                for (Legemiddel legemdl : legemiddelliste){
                    if (legemdl.hentId() == legemiddelNummer){
                        valgtLegemid = legemdl;
                    }
                }

                //skal hente objektene fr a
                String legenavn = biter[1];
                Lege navnettillege = null;
                for (Lege navn  : legeliste){ //fungerer ikke på grunn av linje 8
                    if (navn.hentNavn().equals(legenavn)){
                        navnettillege = navn;
                    }
                }

                int pasientID = Integer.parseInt(biter[2]);
                Pasient idpasientene = null;
                for (Pasient id  : pasientliste){ 
                    if (id.hent_id() == pasientID){
                        idpasientene = id;
                    }
                }
                
                String type = biter[ 3 ];

                //SKAL KALLE PÅ SKRIVRESEPTMETODE FRA LEGE!
                //hvitResept
                if (type.contains("hvit")) {  
                    int reit = Integer.parseInt(biter[ 4 ]);
                    Resept hvit = new HvitResept(valgtLegemid,navnettillege,idpasientene,reit);
                    reseptliste.leggTil(hvit);
                }

                //blaaresept
                if (type.contains("blaa")) {  
                    int reit = Integer.parseInt(biter[ 4 ]);
                    Resept blaa = new BlaaResept(valgtLegemid,navnettillege,idpasientene,reit);
                    reseptliste.leggTil(blaa);
                }

                //prevensjonresept
                if (type.contains("p")) {  
                    int reit = Integer.parseInt(biter[ 4 ]);
                    Resept prresept = new PResept(valgtLegemid,navnettillege,idpasientene,reit);
                    reseptliste.leggTil(prresept);
                }

                //militær
                //Den enesete som ikke bruker reit er militær, kanskje fordi de får 3 uannsett som da sa i oblig 2??
                if (type.contains("militaer")) {  
                    Resept mResept = new MilResept(valgtLegemid,navnettillege,idpasientene);
                    reseptliste.leggTil(mResept);
                }

            }

        }
    }

        //forteller java hva feilen er
        catch(FileNotFoundException e) {
            System.out.println("Finner ikke filen" + filnavn);
        }
    }

        //E2
        // Skal kalle på de andre metodene fra E4, E5, E6 osv.
        public void kommandoLokke() {

            boolean skalKjore = true;
            

            while (skalKjore = true) {
                    Scanner input = new Scanner(System.in);
                System.out.println(
                    "Velkommen til legesystem! Her er dine valg:" +
                    "\nTast [1] Oversikt over pasienter, leger, legemidler og resepter." +
                    "\nTast [2] For å opprette og legge til nye elementer i systemet." +
                    "\nTast [3] For å bruke en gitt resept fra listen til en pasient." +
                    "\nTast [4] For å skrive ut forskjellige former for statistikk." +
                    "\nTast [5] For å skrive alle data til fil."+
                    "\nTast [6] For å avslutte programmet.");

                String svar = input.nextLine(); //leser bruker input
                if (svar.equals(1)) {

                }
                else if (svar.equals(2)) {

                }
                else if (svar.equals(3)) {
                    
                }
                else if (svar.equals(4)) {
                    
                }
                else if (svar.equals(5)) {
                    
                }
                else if (svar.equals(6)) {
                    System.out.println("Avslutter programmet..");
                    skalKjore = false;
                }
                
                // // Lag en toString metode i pasient for fin utskrift
                // for (Pasient pasientInfo : pasientliste) {
                //     System.out.println(pasientInfo);
                // }
    
                // System.out.println("Valgt pasient er: " + pasientInput);

            }
    
        }



        //E3
        public void skriv_info() {

            System.out.println("Liste av alle pasienter:");
            for (Pasient pasient : pasientliste) {
                System.out.println(pasient);
            }

            System.out.println("Liste av alle legemiddelene:");
            for (Legemiddel legemiddel : legemiddelliste) {
                System.out.println(legemiddel);
            }

            System.out.println("\nReseptene som er registrert i systemet:");
            for (Resept resept : reseptliste) {
                System.out.println("-> " + resept);
            }

            System.out.println("Liste over alle leger:");
            for (Resept lege : legeliste) { //fungerer ikke på grunn av linje 8
                System.out.println(lege);
            }
        }


        
        //E4
        public void leggtil() {
            //Hvordan man lager input i java:
            //https://www.w3schools.com/java/showjava.asp?filename=demo_api_scanner
            Scanner input_objekt = new Scanner(System.in);

            System.out.println("Hva vil du legg til?");
            System.out.println("For å velge Lege skriv: lege");
            System.out.println("For å velge Pasient skriv: pasient");
            System.out.println("For å velge Resept skriv: resept");
            System.out.println("For å velge Legemiddel skriv: middel");




            String svar = input_objekt.nextLine();

            if (svar.equals("lege")) {  
                //1.bruker skrive navn
                System.out.println("Du har valgt å legge til en lege:");
                System.out.println("Hva er navnet til legen din?");
                String legenavn = input_objekt.nextLine();

                //2.Spør om legen er en Spesialist. Eller la koden sjekker det selv med en å ta navnet inn i en forlokke av alle speislister?
                //må sporre om kontrollID

                System.out.println("Er " + legenavn + " en spesialist?");
                System.out.println("Skriv enten \'ja\' eller \'nei\':");
                String spesialist = input_objekt.nextLine();

                if (spesialist.toLowerCase().equals("ja")) {
                    System.out.println("Hva er kontrollID'en til " + legenavn + "?");  
                    String id = input_objekt.nextLine();
                    Spesialist nyspesialist = new Spesialist(legenavn,id);
                    legeliste.leggTil(nyspesialist);//feilmedling pga linje 8
                    System.out.println(legenavn + " med kontrollID: " + id + " er nå lagt inn i systemet vårt som spesialist.");  
                }

                else if (spesialist.toLowerCase().equals("nei")) { 
                    //legger til som vanlig lege:
                    Lege nylege = new Lege(legenavn);
                    legeliste.leggTil(legenavn);//feilmedling pga linje 8
                    System.out.println(legenavn + " er ikke en spesialist og er lagt som en vanlig lege i systemtet vårt.");  
                }

                else if (!(spesialist.toLowerCase().contains("nei") || spesialist.toLowerCase().contains("ja"))) { 
                    System.out.println("Ugyldig input! Skriv enten\'ja\' eller \'nei\'");  
                }

                //Legge til pasient 
                //Denne kan Inge ta. Bare følg oppsettet mitt i Lege
                else if (svar.equals("pasient")) {  
                }



                //My kan gjøre denne. Den kan være litt vanskelig. det er derfor du får denne :D
                //Inge du kan også prøve på denne
                else if (svar.equals("resept")) {  
                }



                else if (svar.equals("legemiddel")) {  
                    System.out.println("Du har valgt å legge til et nytt legemiddel.");  
                    System.out.println("For å velge Narkotisk skriv: \'narkotisk\'");  
                    System.out.println("For å velge Vanedannede skriv: \'vane\'");  
                    System.out.println("For å velge Vanlig skriv: \'vanlig\'");

                    String valg = input_objekt.nextLine();

                    if (valg.toLowerCase().equals("narkotisk")) {
                        //oppsett  public Narkotisk(String navn, int pris, double virkestoff, int narkotisk_styrke) {
                        System.out.println("Skriv inn navnet til det narkotiske legemiddelet:");
                        String narko_navn = input_objekt.nextLine();

                        System.out.println("Skriv inn prisen til det narkotiske legemiddelet:");
                        //int narko_pris = Integer.parseInt(input_objekt.nextLine());
                        int narko_pris = Integer.parseInt(input_objekt.nextLine());

                        System.out.println("Skriv inn virkestoff til det narkotiske legemiddelet:");
                        double narko_virkestoff = Double.parseDouble(input_objekt.nextLine());

                        System.out.println("Skriv inn styrken til det narkotiske legemiddelet:");
                        int narko_styrke = Integer.parseInt(input_objekt.nextLine());

                        Narkotisk nynarko = new Narkotisk(narko_navn, narko_pris, narko_virkestoff, narko_styrke);

                        legemiddelliste.leggTil(nynarko);
                    }


                    if (valg.toLowerCase().equals("vane")) {
                        //public Vanedannende(String navn, int pris, double virkestoff, int vanedannende_styrke) {
                        System.out.println("Skriv inn navnet til det Vanedannende legemiddelet:");
                        String vane_navn = input_objekt.nextLine();

                        System.out.println("Skriv inn prisen til det Vanedannende legemiddelet:");
                        //int narko_pris = Integer.parseInt(input_objekt.nextLine());
                        int vane_pris = Integer.parseInt(input_objekt.nextLine());

                        System.out.println("Skriv inn virkestoff til det Vanedannende legemiddelet:");
                        double vane_virkestoff = Double.parseDouble(input_objekt.nextLine());

                        System.out.println("Skriv inn styrken til det Vanedannende legemiddelet:");
                        int vane_styrke = Integer.parseInt(input_objekt.nextLine());

                        Narkotisk nyvane = new Narkotisk(vane_navn, vane_pris, vane_virkestoff, vane_styrke);

                        legemiddelliste.leggTil(nyvane);
                    }



                    //Inge kan prøve på denne og legge til en if-sjekk for å sjekke om brukeren skrev riktig. 
                    if (valg.toLowerCase().equals("vanlig")) {
                    }



                }
            }
        }
}

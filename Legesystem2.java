import java.io.File;
import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.io.PrintWriter;
import java.util.Scanner;

public class Legesystem2 {
    private IndeksertListe<Pasient> pasientliste = new IndeksertListe<>();
    private IndeksertListe<Legemiddel> legemiddelliste = new IndeksertListe<>();
    // private IndeksertListe<Lege> legeliste = new IndeksertListe<>();
    //Prioritetskoe funker ikke helt
    private Prioritetskoe<Lege> legeliste = new Prioritetskoe<>(); //vett ikje koffor det ikje fungerer 
    private IndeksertListe<Resept> reseptliste = new IndeksertListe<>();

    public void lesFil(String filnavn) throws UlovligUtskrift {
        
        //Scanner scan = null;

        //try & catch for a sjekke om det blir tatt inn fil
        try {

            //lager et filobjekt som sendes til Scanner
            File fil = new File(filnavn);
            Scanner scanner = new Scanner(fil);
            int indeks = 0;

            //lesing av fil
            while (scanner.hasNextLine()) {

                String linje = scanner.nextLine();
                String[] biter = linje.trim().split(",");
                // System.out.println(linje);

                //hvis det starter med 
                if(linje.contains("#")) {
                    // System.out.println("øker indeks fra " + indeks + " til " + (indeks + 1));
                    indeks ++;
                    //return;
                } 
                
                else {
                    //pasient
                    if (indeks == 1) {
                        String fullnavn = biter[0];
                        String fnr = biter[1];
                        Pasient pasientinfo = new Pasient(fullnavn, fnr);
                        // System.out.println(fullnavn + "blir lagt til i pasientliste");
                        pasientliste.leggTil(pasientinfo);
                    }

                    //Legemidler
                    else if (indeks == 2) {
                        
                        String navn = biter[0];
                        double mengdeVirkestoff = Double.parseDouble(biter[3]);
                        //Type er biter[1]
                        int pris = Integer.parseInt(biter[2]);
                        
                        if(biter[1].equals("narkotisk")) {
                            Narkotisk narkotisk = new Narkotisk(navn, pris, mengdeVirkestoff, Integer.parseInt(biter[3]));
                            legemiddelliste.leggTil(narkotisk);
                        }

                        else if (biter[1].equals("vanlig")) {
                            Vanlig vanlig = new Vanlig(navn, pris, mengdeVirkestoff);
                            legemiddelliste.leggTil(vanlig);
                        }
                        
                        else if (biter[1].equals("vanedannende")) {
                            Vanedannende vanedannende = new Vanedannende(navn, pris, mengdeVirkestoff, Integer.parseInt(biter[3]));
                            legemiddelliste.leggTil(vanedannende);
                            
                        }
                
                    }

                    //Lege
                    else if (indeks == 3) {

                        //Fjerner dr. fra alle doktornavnene
                        // https://www.codegrepper.com/code-examples/java/java+remove+first+3+characters+from+string
                        String lege = biter[0];

                        String id = biter[1];

                        //hvis biter[1] (id) == 0, så er det vanlig lege
                        if (id.equals("0")) {
                            Lege legeinfo = new Lege(lege);
                            legeliste.leggTil(legeinfo);
                        }
                        
                        else {
                            Spesialist spesialistInfo = new Spesialist(lege, id);
                            legeliste.leggTil(spesialistInfo);
                        }
                        
                        //Jeg vet ikke hvordan prioritetskoen din ser ut? Så vet ikke hvordan jeg skal gaa frem videre?   

                    }


                    else if (indeks == 4) {

                        try {
                            int legemiddelNummer = Integer.parseInt(biter[0]);
                            String legenavn = biter[1];
                            int pasientID = Integer.parseInt(biter[2]);
                            String type = biter[3];
                        
                            Legemiddel valgtLegemid = null;
                            Lege valgtlege = null;
                            Pasient idpasientene = null;
        
                            //bruker Integer.parseInt(); fra oblig 1
                            for (Legemiddel legemdl : legemiddelliste){
                                if (legemdl.hentId() == legemiddelNummer){
                                    valgtLegemid = legemdl;
                                    break;
                                }
        
                            }
        
                            //skal hente objektene fr a
                            for (Lege lege : legeliste){ 
                                if (lege.hentNavn().equals(legenavn)){
                                    valgtlege = lege;
                                    break;
                                }
                            }
        
                            for (Pasient iD  : pasientliste){ 
                                if (iD.hent_id() == pasientID){
                                    idpasientene = iD;
                                }
        
                            }
        
                            // if (valgtLegemid == null || valgtlege == null || idpasientene == null) {
                            //     //denne skal egentlig ikke kjøre??
        
                            // } else {
        
                            //SKAL KALLE PÅ SKRIVRESEPTMETODE FRA LEGE!
                            //hvitResept
                            if (type.contains("hvit")) {  
                                int reit = Integer.parseInt(biter[ 4 ]);
                                
                                reseptliste.leggTil(valgtlege.skrivHvitResept(valgtLegemid, idpasientene, reit));
                                //Resept hvit = new HvitResept(valgtLegemid,valgtlege,idpasientene,reit);
                                //reseptliste.leggTil(hvit);
                            }
        
                            //blaaresept
                            else if (type.contains("blaa")) {  
                                int reit = Integer.parseInt(biter[ 4 ]);
                                Resept blaa = new BlaaResept(valgtLegemid,valgtlege,idpasientene,reit);
                                reseptliste.leggTil(blaa);
                            }
        
                            //prevensjonresept
                            else if (type.contains("p")) {  
                                int reit = Integer.parseInt(biter[ 4 ]);
                                Resept prresept = new PResept(valgtLegemid,valgtlege,idpasientene,reit);
                                reseptliste.leggTil(prresept);
                            }
        
                            //militær
                            //Den enesete som ikke bruker reit er militær, kanskje fordi de får 3 uannsett som da sa i oblig 2??
                            else if (type.contains("militaer")) {  
                                Resept mResept = new MilResept(valgtLegemid,valgtlege,idpasientene);
                                reseptliste.leggTil(mResept);
                            }
                            
                        } 
                        catch (Exception e) {
                            System.out.println("Det skjedde et problem");
                        }

                        // }
                    }

                }

            }
       
        }

        //forteller java hva feilen er
        catch(FileNotFoundException e) {
            System.out.println("Finner ikke filen" + filnavn);
        }
    }

        // E2
        // Skal kalle på de andre metodene fra E4, E5, E6 osv.
        public void kommandoLokke() throws UlovligUtskrift {

            boolean skalKjore = true;

            while (skalKjore) {
                
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
                if (svar.equals("1")) {
                    skriv_info();
                }
                else if (svar.equals("2")) {
                    leggtil();
                }
                // else if (svar.equals("3")) {
                //     Brukresept();
                // }
                // else if (svar.equals("4")) {
                //     statistikk();
                // }
                // else if (svar.equals("5")) {
                //     SkrivTilFil();
                // }
                // else if (svar.equals("6")) {
                //     System.out.println("Avslutter programmet..");
                //     skalKjore = false;
                // }
                // else {
                //     System.out.println("Det skjedde en feil");
                // }
                
                //input.close();
            }
    
        }



        //E3
        public void skriv_info() {

            System.out.println("Liste av alle pasienter:");
            for (Pasient pasient : pasientliste) {
                System.out.println(pasient);
            }

            System.out.println("\nListe av alle legemiddelene:");
            for (Legemiddel legemiddel : legemiddelliste) {
                System.out.println(legemiddel);
            }

            System.out.println("Reseptene som er registrert i systemet:");
            for (Resept resept : reseptliste) {
                System.out.println(resept);
            }

            System.out.println("Liste over alle leger:");

            for (Lege lege : legeliste) { 
                System.out.println(lege);
            }
            // int ganger = legeliste.stoerrelse();

            // for (int i = 0; i < ganger; i++) {
            //     System.out.println(legeliste.fjern());
            // }

        }

        //E4
        public void leggtil() throws UlovligUtskrift {
            //Hvordan man lager input i java:
            //https://www.w3schools.com/java/showjava.asp?filename=demo_api_scanner
            Scanner input_objekt = new Scanner(System.in);

            System.out.println("Hva vil du legg til?");
            System.out.println("For a velge Lege skriv: lege");
            System.out.println("For a velge Pasient skriv: pasient");
            System.out.println("For a velge Resept skriv: resept");
            System.out.println("For a velge Legemiddel skriv: middel");


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
                    legeliste.leggTil(nylege);//feilmedling pga linje 8
                    System.out.println(legenavn + " er ikke en spesialist og er lagt som en vanlig lege i systemtet vårt.");
                }

                else if (!(spesialist.toLowerCase().contains("nei") || spesialist.toLowerCase().contains("ja"))) { 
                    System.out.println("Ugyldig input! Skriv enten\'ja\' eller \'nei\'");
                }

                //Legge til pasient 
                //Denne kan Inge ta. Bare følg oppsettet mitt i Lege
            }

            else if (svar.equals("pasient")) {  
                System.out.println("Du har valgt til a legge til en pasient");

                //Sporre om navn
                System.out.println("Hva er navnet ditt?: ");
                String pasientNavn = input_objekt.nextLine();

                //sporre om fodselsnr¨
                System.out.println("Hva er fodselsnummeret ditt?: ");
                String fodselsNr = input_objekt.nextLine();

                //Lager ny pasient
                Pasient nyPasient = new Pasient(pasientNavn, fodselsNr);
                pasientliste.leggTil(nyPasient);
                System.out.println("Ny pasient:" + pasientNavn + ", " + fodselsNr + " er lagt til i systemet.");
            }

            else if (svar.equals("resept")) {  
                System.out.println("Du har valgt a legge til en ny resept. ");
                System.out.println("For a velge HvitResept skriv: \'hvit\'");
                System.out.println("For a velge BlaaResept skriv: \'blaa\'");
                System.out.println("For a velge MilResept skriv: \'mil\'");
                System.out.println("For a velge PResept skriv: \'pr\'");

                String valg = input_objekt.nextLine();

                if (valg.toLowerCase().equals("hvit")) {
                    // public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit)
                    System.out.println("Du har valgt hvit resept");
                    System.out.println("===============================LEGEMIDLER============================");
                    Legemiddel hvitFunnetLmd  = null;
                    for (Legemiddel legemiddel : legemiddelliste) {
                        System.out.println(legemiddel.navn);
                    }
                    while (hvitFunnetLmd == null) {
                        System.out.println("\nSkriv inn navnet til legemiddelet: ");
                        String hvitLmdNavn = input_objekt.nextLine();
                        
                        for (Legemiddel legemiddel : legemiddelliste) {
                            if (legemiddel.hentNavn().equals(hvitLmdNavn)) {
                                hvitFunnetLmd = legemiddel;
                            }
                        }
                    }
                    System.out.println("==============================LEGER==================================");
                    Lege hvitFunnetLege = null;
                    for (Lege lege : legeliste) {
                        System.out.println(lege.hentNavn());
                    }
                    while (hvitFunnetLege == null) {
                        System.out.println("\nSkriv inn navnet til legen som skriver ut resepten: ");
                        String hvitUskrivendeLege = input_objekt.nextLine();

                        for (Lege lege : legeliste) {
                            if (lege.hentNavn().equals(hvitUskrivendeLege)) {
                                hvitFunnetLege = lege;
                            }
                        }
                    }
                    System.out.println("==============================PASIENTER==================================");
                    Pasient hvitFunnetPasient = null;
                    for (Pasient pasient : pasientliste) {
                        System.out.println(pasient.hent_navn());
                    }
                    while (hvitFunnetPasient == null) {
                        System.out.println("\nSkriv inn navnet til pasienten som skal faa resepten: ");
                        String hvitPasientNavn = input_objekt.nextLine();

                        for (Pasient pasient : pasientliste) {
                            if (pasient.hent_navn().equals(hvitPasientNavn)) {
                                hvitFunnetPasient = pasient;
                            }
                        }
                    }
                    int hvitAntReit = -1;
                    while (hvitAntReit < 0) {
                        try {
                            System.out.println("Skriv inn antall reit til resepten");
                            hvitAntReit = Integer.parseInt(input_objekt.nextLine());
                        } catch (Exception e) {
                            System.out.println("reit kan ikke vare under 0");
                        }
                    }
                    reseptliste.leggTil(hvitFunnetLege.skrivHvitResept(hvitFunnetLmd, hvitFunnetPasient, hvitAntReit));
                }

                else if (valg.toLowerCase().equals("blaa")) {
                    // public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit)
                    System.out.println("Du har valgt blaa resept");
                    System.out.println("===============================LEGEMIDLER============================");
                    Legemiddel funnetLmd = null;
                    for (Legemiddel legemiddel : legemiddelliste) {
                        System.out.println(legemiddel.hentNavn());
                    }
                    while (funnetLmd == null) {
                        System.out.println("\nSkriv inn navnet til legemiddelet: ");
                        String blaaLmdNavn = input_objekt.nextLine();
                        
                        for (Legemiddel legemiddel : legemiddelliste) {
                            if (legemiddel.hentNavn().equals(blaaLmdNavn)) {
                                funnetLmd = legemiddel;
                            }
                        }
                    }
                    System.out.println("==============================LEGER==================================");
                    Lege funnetLege = null;
                    for (Lege lege : legeliste) {
                        System.out.println(lege.hentNavn());
                    }
                    while (funnetLege == null) {
                        System.out.println("\nSkriv inn navnet til legen som skriver ut resepten: ");
                        String blaaUtskrivendeLege = input_objekt.nextLine();

                        for (Lege lege : legeliste) {
                            if (lege.hentNavn().equals(blaaUtskrivendeLege)) {
                                funnetLege = lege;
                            }
                        }
                    }
                    System.out.println("==============================PASIENTER==================================");
                    Pasient funnetPasient = null;
                    for (Pasient pasient : pasientliste) {
                        System.out.println(pasient.hent_navn());
                    }
                    while (funnetPasient == null) {
                        System.out.println("\nSkriv inn navnet til pasienten som skal faa resepten: ");
                        String blaaPasientNavn = input_objekt.nextLine();

                        for (Pasient pasient : pasientliste) {
                            if (pasient.hent_navn().equals(blaaPasientNavn)) {
                                funnetPasient = pasient;
                            }
                        }
                    }
                    int blaaAntReit = -1;
                    while (blaaAntReit < 0) {
                        try {
                            System.out.println("Skriv inn antall reit til resepten");
                            blaaAntReit = Integer.parseInt(input_objekt.nextLine());
                        } catch (Exception e) {
                            System.out.println("reit kan ikke vare under 0");
                        }
                    }
                    reseptliste.leggTil(funnetLege.skrivBlaaResept(funnetLmd, funnetPasient, blaaAntReit));
                }

                else if (valg.toLowerCase().equals("mil")) {
                    // public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient)
                    System.out.println("Du har valgt mil resept");
                    System.out.println("===============================LEGEMIDLER============================");
                    Legemiddel funnetLmd  = null;
                    for (Legemiddel legemiddel : legemiddelliste) {
                        System.out.println(legemiddel.hentNavn());
                    }
                    while (funnetLmd == null) {
                        System.out.println("\nSkriv inn navnet til legemiddelet: ");
                        String milLmdNavn = input_objekt.nextLine();
                        
                        for (Legemiddel legemiddel : legemiddelliste) {
                            if (legemiddel.hentNavn().equals(milLmdNavn)) {
                                funnetLmd = legemiddel;
                            }
                        }
                    }
                    System.out.println("==============================LEGER==================================");
                    Lege funnetLege = null;
                    for (Lege lege : legeliste) {
                        System.out.println(lege.hentNavn());
                    }
                    while (funnetLege == null) {
                        System.out.println("\nSkriv inn navnet til legen som skriver ut resepten: ");
                        String milUtskrivendeLege = input_objekt.nextLine();

                        for (Lege lege : legeliste) {
                            if (lege.hentNavn().equals(milUtskrivendeLege)) {
                                funnetLege = lege;
                            }
                        }
                    }
                    System.out.println("==============================PASIENTER==================================");
                    Pasient funnetPasient = null;
                    for (Pasient pasient : pasientliste) {
                        System.out.println(pasient.hent_navn());
                    }
                    while (funnetPasient == null) {
                        System.out.println("\nSkriv inn navnet til pasienten som skal faa resepten: ");
                        String milPasientNavn = input_objekt.nextLine();

                        for (Pasient pasient : pasientliste) {
                            if (pasient.hent_navn().equals(milPasientNavn)) {
                                funnetPasient = pasient;
                            }
                        }
                    }
                    reseptliste.leggTil(funnetLege.skrivMilResept(funnetLmd, funnetPasient));

                }

                else if (valg.toLowerCase().equals("pr")) {
                    // public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient)
                    System.out.println("Du har valgt pr resept");
                    System.out.println("===============================LEGEMIDLER============================");
                    Legemiddel funnetLmd  = null;
                    for (Legemiddel legemiddel : legemiddelliste) {
                        System.out.println(legemiddel.hentNavn());
                    }
                    while (funnetLmd == null) {
                        System.out.println("\nSkriv inn navnet til legemiddelet: ");
                        String prLmdNavn = input_objekt.nextLine();
                        
                        for (Legemiddel legemiddel : legemiddelliste) {
                            if (legemiddel.hentNavn().equals(prLmdNavn)) {
                                funnetLmd = legemiddel;
                            }
                        }
                    }
                    System.out.println("==============================LEGER==================================");
                    Lege funnetLege = null;
                    for (Lege lege : legeliste) {
                        System.out.println(lege.hentNavn());
                    }
                    while (funnetLege == null) {
                        System.out.println("\nSkriv inn navnet til legen som skriver ut resepten: ");
                        String prUtskrivendeLege = input_objekt.nextLine();

                        for (Lege lege : legeliste) {
                            if (lege.hentNavn().equals(prUtskrivendeLege)) {
                                funnetLege = lege;
                            }
                        }
                    }
                    System.out.println("==============================PASIENTER==================================");
                    Pasient funnetPasient = null;
                    for (Pasient pasient : pasientliste) {
                        System.out.println(pasient.hent_navn());
                    }
                    while (funnetPasient == null) {
                        System.out.println("\nSkriv inn navnet til pasienten som skal faa resepten: ");
                        String prPasientNavn = input_objekt.nextLine();

                        for (Pasient pasient : pasientliste) {
                            if (pasient.hent_navn().equals(prPasientNavn)) {
                                funnetPasient = pasient;
                            }
                        }
                    }
                    int prAntReit = -1;
                    while (prAntReit < 0) {
                        try {
                            System.out.println("Skriv inn antall reit til resepten");
                            prAntReit = Integer.parseInt(input_objekt.nextLine());
                        } catch (Exception e) {
                            System.out.println("reit kan ikke vare under 0");
                        }
                    }

                    reseptliste.leggTil(funnetLege.skrivPResept(funnetLmd, funnetPasient, prAntReit));
                }
            }

            else if (svar.equals("middel")) {  
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

                    System.out.println("Skriv inn navnet til det vanlige legemiddelet: ");
                    String vanligNavn = input_objekt.nextLine();

                    System.out.println("Skriv inn prisen til det vanlige legemiddelet:");
                    int vanligPris = Integer.parseInt(input_objekt.nextLine());

                    System.out.println("Skriv inn virkestoff til det vanlige legemiddelet: ");
                    Double vanligVirkestoff = Double.parseDouble(input_objekt.nextLine());

                    Vanlig nyVanlig = new Vanlig(vanligNavn, vanligPris, vanligVirkestoff);
                    legemiddelliste.leggTil(nyVanlig);

                }
            }
                //input_objekt.close();
            }
/*
        //E5
        public void Brukresept() {  
            System.out.println("Hvilken pasient vil du se resepter for?");
            //Jeg antar at navnene er tatt fra pasientliste i linje 6 og er fra leggtil metoden i E4. Kan lage en for-loop som sjekker inputen til brukeren og returer true eller fasle

            Scanner input_sjekk = new Scanner(System.in);
            //Pasient p = null;
            //Resept resept = null;

            int antallpasienter = 0;
            for (Pasient pasient : pasientliste) { 
                //pasient = p;

                //0: Anne (fnr 12121212121) prøver å gjøre samme utskriftformat
                System.out.println(antallpasienter + ": " + pasient.hent_navn() + " (fnr " + pasient.hent_fodselnr() + ")");
                antallpasienter +=1;
            }

            int valg_av_pasientnavn = input_sjekk.nextInt();
            Pasient p = pasientliste.hent(valg_av_pasientnavn);
            System.out.println("Valgt Pasient: " + p.hent_navn() + " (fnr " + p.hent_fodselnr() + ")" );
            System.out.println("Hvilken resept vil du bruke?");

            int resept_teller = 0;
            for(Resept element : p.hent_reseptliste()) {
                //resept = element;
                System.out.println(resept_teller + ": " + element.hentLegemiddel() + "("+ element.hentReit() +" reit)");
                resept_teller+=1;
                }

            int velge_resept = input_sjekk.nextInt();
            Resept resept = reseptliste.hent(velge_resept);
            resept.bruk();
            System.out.println("Brukte resept paa " + resept.hentLegemiddel() + "." + " Antall reit igjen er: " + resept.hentReit());
            input_sjekk.close();
            }

            //E6
            public void statistikk() {

            //henter totalt antall resepter med vanedannede legemiddel
                int antall_v = 0;
                for (Resept resept : reseptliste) {
                    if (resept.hentLegemiddel() instanceof Vanedannende) {
                        antall_v+= 1;
                    }
                System.out.println("Totalt Vanedannende resepter:" + antall_v);
                }

            //henter totalt antall resepter med narkotisk legemiddel
                int antall_n = 0;
                for (Resept resept : reseptliste) {
                    if (resept.hentLegemiddel() instanceof Narkotisk) {
                        antall_n+= 1;
                    }
                    System.out.println("Totalt Vanedannende resepter:" + antall_n);
                }

                //Statistikk om mulig misbruk av narkotika

                //leger i alfabetisk rekkefolge som har skrevet
                //ut minst en resept pa narkokotiske legemidler,
                //og antallet slike resepter per lege



                //MY SKRIV HER:

                //List opp alle navnene pa alle pasienter
                //som har minst en gyldig resept pa 
                //narkotiske legemidler og, for disse, skriv ut
                //antallet per pasient

                int antReseptMedNarko = 0;
                int godkjentReseptTilLege = 0;
                for (Lege lege : legeliste) {
                    for (Resept resept : lege.hentUtskrevneResepter()) {
                        if (resept.hentLegemiddel() instanceof Narkotisk) {
                            antReseptMedNarko ++;
                            System.out.println("Lege: " + lege.hentNavn() + " Antall resepter med narko: " + antReseptMedNarko);

                        }
                    }
                }

                int totalNarkotiskResept = 0;
                String navnMedNarkotisk = "Alle pasienter med gyldig resept pa narkotisk legemidler";
                
                //looper gjennom alle pasienter i pasientliste
                for (Pasient pasient : pasientliste) {
                    //looper gjennom alle resepter for alle pasienter
                    for (Resept resepter : pasient.hent_reseptliste()) {
                        if (resepter.hentLegemiddel() instanceof Narkotisk) {

                            totalNarkotiskResept ++;
                            pasient.oekAntallNarkotisk();
                            navnMedNarkotisk = "\nNavn: " + pasient + " , antall narkotiske resepter: " + pasient.hentAntallNarkotisk(); ;
                            System.out.println();
                        }

                    }

                }
                System.out.println(navnMedNarkotisk + "\nAntall narkotiske resepter: " + totalNarkotiskResept);
            }

            
            public void SkrivTilFil(){

                try {
        
                    PrintWriter skriv = new PrintWriter("nyfil.txt");
    
                    //Skriver inn pasienter
                    skriv.append("# Pasienter (navn, fnr)\n");
                    for (Pasient pasient : pasientliste) {
                        skriv.append(pasient + "\n");
                    }
        
                        //for legemidler
                    skriv.append("# Legemidler (navn, type, pris, virkestoff, [styrke])\n");
                    for (Legemiddel legemiddel: legemiddelliste) {
                        skriv.append(legemiddel + "\n");
                    }
        
                        //Leger
                    skriv.append("# Leger (navn, kontrollid / 0 hvis vanlig lege)\n");
                    for (Lege lege : legeliste) {
                        skriv.append(lege + "\n");
                    }
        
                    //resept
                    skriv.append("# Resepter (legemiddelNummer, legenavn, pasientID, type, [reit]\n");
                    for (Resept resept : reseptliste) {
                        skriv.append(resept + "\n");
                    }
                    skriv.close();
                }

                catch(IOException e) {
                    e.printStackTrace();
                    System.out.println("Det skjedde en feil");
        
                }
                

            }
            */
}

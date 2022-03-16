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



                //My kan gjøre denne. Den kan være litt vanskelig. det er derfor du får denne 😃
                //Inge du kan også prøve på denne
                else if (svar.equals("resept")) {  
                    System.out.println("Du har valgt å legge til en ny resept. ");
                    System.out.println("For å velge HvitResept skriv: \'hvit\'");
                    System.out.println("For å velge BlaaResept skriv: \'blaa\'");
                    System.out.println("For å velge MilResept skriv: \'mil\'");
                    System.out.println("For å velge PResept skriv: \'pr\'");

                    String valg = input_objekt.nextLine();

                    if (valg.toLowerCase().equals("hvit")) {
                        // public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit)
                        System.out.println("Du har valgt hvit resept");
                        System.out.println("Skriv inn navnet til legemiddelet: ");
                        String hvitLmdNavn = input_objekt.nextLine();

                        // sjekker om legemiddelet er i systemet
                        // burde kanskje bruke try and catch
                        for (Legemiddel legemiddel : legemiddelliste) {
                            if (legemiddel.hentNavn() == hvitLmdNavn) {
                                Legemiddel funnetLmd = legemiddel; 
                                break;
                            }
                            System.out.println("Legemiddelet " + hvitLmdNavn + " fantes ikke i systemet");
                            // burde ha en throw new feilmelding
                        }

                        System.out.println("Skriv inn navnet til legen som skriver ut resepten: ");
                        String hvitUskrivendeLege = input_objekt.nextLine();

                        for (Lege lege : legeliste) {
                            if (lege.hentNavn() == hvitUskrivendeLege) {
                                Lege funnetLege = lege;
                                break;
                            }
                            System.out.println("Legen " + hvitUskrivendeLege + " fantes ikke i systemet");
                        }

                        System.out.println("Skriv inn navnet til pasienten til resepten: ");
                        String hvitPasientNavn = input_objekt.nextLine();

                        for (Pasient pasient : pasientliste) {
                            if (pasient.hent_navn() == hvitPasientNavn) {
                                Pasient funnetPasient = pasient;
                                break;
                            }
                            System.out.println("Pasienten " + hvitPasientNavn + " fantes ikke i systemet");
                        }

                        System.out.println("Skriv inn antall reit til resepten");
                        int hvitAntReit = Integer.parseInt(input_objekt.nextLine());

                        funnetLege.skrivHvitResept(funnetLmd, funnetPasient, hvitAntReit);
                    }
                    if (valg.toLowerCase().equals("blaa")) {
                        // public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit)
                        System.out.println("Du har valgt blaa resept");
                        System.out.println("Skriv inn navnet til legemiddelet: ");
                        String blaaLmdNavn = input_objekt.nextLine();

                        for (Legemiddel legemiddel : legemiddelliste) {
                            if (legemiddel.hentNavn() == blaaLmdNavn) {
                                Legemiddel funnetLmd = legemiddel; 
                                break;
                            }
                            System.out.println("Legemiddelet " + blaaLmdNavn + " fantes ikke i systemet");
                            // burde ha en throw new feilmelding
                        }

                        System.out.println("Skriv inn navnet til legen som skriver ut resepten: ");
                        String blaaUskrivendeLege = input_objekt.nextLine();

                        for (Lege lege : legeliste) {
                            if (lege.hentNavn() == blaaUskrivendeLege) {
                                Lege funnetLege = lege; 
                                break;
                            }
                            System.out.println("Legen " + blaaUskrivendeLege + " fantes ikke i systemet");
                        }

                        System.out.println("Skriv inn navnet til pasienten til resepten: ");
                        String blaaPasientNavn = input_objekt.nextLine();

                        for (Pasient pasient : pasientliste) {
                            if (pasient.hent_navn() == blaaPasientNavn) {
                                Pasient funnetPasient = pasient;
                                break;
                            }
                            System.out.println("Pasienten " + blaaPasientNavn + " fantes ikke i systemet");
                        }

                        System.out.println("Skriv inn antall reit til resepten");
                        int blaaAntReit = Integer.parseInt(input_objekt.nextLine());

                        funnetLege.skrivBlaaResept(funnetLmd, funnetPasient, hvitAntReit);
                    }
                    if (valg.toLowerCase().equals("mil")) {
                        // public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient)
                        System.out.println("Du har valgt mil resept");
                        System.out.println("Skriv inn navnet til legemiddelet: ");
                        String milLmdNavn = input_objekt.nextLine();

                        System.out.println("Skriv inn navnet til legen som skriver ut resepten: ");
                        String milUskrivendeLege = input_objekt.nextLine();

                        for (Lege lege : legeliste) {
                            if (lege.hentNavn().contains(milUskrivendeLege)) {
                                break;
                            }
                            System.out.println("Legen " + milUskrivendeLege + " fantes ikke i systemet");
                        }

                        System.out.println("Skriv inn navnet til pasienten til resepten: ");
                        String milPasientNavn = input_objekt.nextLine();
                    }
                    if (valg.toLowerCase().equals("pr")) {
                        // public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient)
                        System.out.println("Du har valgt mil resept");
                        System.out.println("Skriv inn navnet til legemiddelet: ");
                        String prLmdNavn = input_objekt.nextLine();

                        System.out.println("Skriv inn navnet til legen som skriver ut resepten: ");
                        String prUskrivendeLege = input_objekt.nextLine();

                        // må sjekke om legen finnes i systemet
                        for (Lege lege : legeliste) {
                            if (lege.hentNavn().contains(prUskrivendeLege)) {
                                break;
                            }
                            System.out.println("Legen " + prUskrivendeLege + " fantes ikke i systemet");
                        }

                        System.out.println("Skriv inn navnet til pasienten til resepten: ");
                        String prPasientNavn = input_objekt.nextLine();

                        System.out.println("Skriv inn antall reit til resepten");
                        int prAntReit = Integer.parseInt(input_objekt.nextLine());
                    }
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

                        System.out.println("Skriv inn navnet til det vanlige legemiddelet: ");
                        String vanligNavn = input_objekt.nextLine();

                        System.out.println("Skriv inn prisen til det vanlige legemiddelet:");
                        int vanligPris = Integer.parseInt(input_objekt.nextLine());

                        System.out.println("Skriv inn virkestoff til det vanlige legemiddelet: ");
                        int vanligVirkestoff = Integer.parseInt(input_objekt.nextLine());

                        Vanlig nyVanlig = new Vanlig(vanligNavn, vanligPris, vanligVirkestoff);
                        legemiddelliste.leggTil(nyVanlig);

                    }



                }
            }
        }


        //E5
        public void Brukresept() {  
            System.out.println("Hvilken pasient vil du se resepter for?");
            //Jeg antar at navnene er tatt fra pasientliste i linje 6 og er fra leggtil metoden i E4. Kan lage en for-loop som sjekker inputen til brukeren og returer true eller fasle

            Scanner input_sjekk = new Scanner(System.in);
            //Pasient p = null;
            //Resept resept = null;

            int antallpasienter =0;
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
            }


            //E6
            public void statestikk() {

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
            }

}

public class Lege implements Comparable<Lege>{

    IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();

    private String navn;

    //fungerer denne måten 
    private BlaaResept blaa_resept;

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }


    //D1
    //https://stackoverflow.com/questions/6203411/comparing-strings-by-their-alphabetical-order
    @Override
    public int compareTo(Lege lege) {
        return (this.navn.compareTo(lege.hentNavn()));
    }



    public IndeksertListe<Resept> hentUtskrevneResepter(){
        return utskrevneResepter;
    }



    //D3
    HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {  
    //Om en vanlig lege prøver å skrive ut et narkotisk legemiddel, kastes unntaket UlovligUtskrift:
    if ((legemiddel instanceof Narkotisk) == true) {  
        throw new UlovligUtskrift(this,legemiddel);
    }
    HvitResept hvit_resept = new HvitResept(legemiddel, this, pasient,reit);
    utskrevneResepter.leggTil(hvit_resept);
    return hvit_resept;
    }



    BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {  
        //Om en vanlig lege prøver å skrive ut et narkotisk legemiddel, kastes unntaket UlovligUtskrift:
        if ((legemiddel instanceof Narkotisk) == true) {  
            throw new UlovligUtskrift(this,legemiddel);
        }

        //Spesialister kan alltid skrive ut Narkotiske legemidler men bare på blå resept.
        else if ((this instanceof Spesialist)== true) {  
            BlaaResept blaa_resept = new BlaaResept(legemiddel, this, pasient,reit);
            utskrevneResepter.leggTil(blaa_resept);
        }

    return blaa_resept;
    }



    //gjenta det med de andre reseptene
    MilResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if ((legemiddel instanceof Narkotisk) == true) {
            throw new UlovligUtskrift(this, legemiddel);
            
        }
        MilResept milresept = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(milresept);
        
        return milresept;
    }



    PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if ((legemiddel instanceof Narkotisk) == true) {
            throw new UlovligUtskrift (this, legemiddel);
        }
        PResept presept = new PResept (legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(presept);
        return presept;
    }



    @Override
    public String toString() {
        return "Legens sitt navn: " + navn;
    }
}

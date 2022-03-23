
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
    public int compareTo(Lege Lege) {
        // returnere integer
        return (this.navn.compareTo(Lege.hentNavn()));
    }



    public IndeksertListe<Resept> hentUtskrevneResepter(){
        return utskrevneResepter;
    }


    //D3
    HvitResept skrivHvitResept (Legemiddel Legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    //Om en vanlig Lege prøver å skrive ut et narkotisk Legemiddel, kastes unntaket UlovligUtskrift:
        if ((Legemiddel instanceof Narkotisk) == true) {  
            throw new UlovligUtskrift(this,Legemiddel);
        }
        HvitResept hvit_resept = new HvitResept(Legemiddel, this, pasient,reit);
        utskrevneResepter.leggTil(hvit_resept);
        return hvit_resept;
    }



    BlaaResept skrivBlaaResept (Legemiddel Legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {  
        //Om en vanlig Lege prøver å skrive ut et narkotisk Legemiddel, kastes unntaket UlovligUtskrift:
        if ((Legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) == true) {  
            throw new UlovligUtskrift(this,Legemiddel);
        }

        // //Spesialister kan alltid skrive ut Narkotiske Legemidler men bare på blå resept.
        // else if ((this instanceof Spesialist)== true) {  
        //     BlaaResept blaa_resept = new BlaaResept(Legemiddel, this, pasient,reit);
        //     utskrevneResepter.leggTil(blaa_resept);
        // }
        BlaaResept blaa_resept = new BlaaResept(Legemiddel, this, pasient,reit);
        utskrevneResepter.leggTil(blaa_resept);
        return blaa_resept;
    }



    //gjenta det med de andre reseptene
    MilResept skrivMilResept (Legemiddel Legemiddel, Pasient pasient) throws UlovligUtskrift {
        if ((Legemiddel instanceof Narkotisk) == true) {
            throw new UlovligUtskrift(this, Legemiddel);
            
        }
        MilResept milresept = new MilResept(Legemiddel, this, pasient);
        utskrevneResepter.leggTil(milresept);
        
        return milresept;
    }



    PResept skrivPResept (Legemiddel Legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if ((Legemiddel instanceof Narkotisk) == true) {
            throw new UlovligUtskrift (this, Legemiddel);
        }
        PResept presept = new PResept (Legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(presept);
        return presept;
    }



    @Override
    public String toString() {
        return "Legens sitt navn: " + navn;
    }

}
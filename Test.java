import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {

    public void lesFil(String filnavn) {
        
        Scanner scan = null;

        //try & catch for a sjekke om det blir tatt inn fil
        try {

            //lager et filobjekt som sendes til Scanner
            File fil = new File(filnavn);
            scan = new Scanner(fil);

        }
        
        //forteller java hva feilen er
        catch(FileNotFoundException e) {

            System.out.println("Finner ikke filen" + filnavn);

        }

        //lesing av fil
        while (scan.hasNextLine()) {
            String linje = scan.nextLine();

            if (!linje.startsWith("#")) {
                System.out.println(linje);
                // String[] biter = linje.split(",");
                // String pasient = biter[0];
                // // String fodselsnummer = biter [1];
                // System.out.println(pasient);
                // // System.out.println(fodselsnummer);
            }
            
            //lager en array
            // String[] biter = linje.split("#"); 
            // System.out.println(biter[0]);
            // linje.split(",");
            // System.out.println(pasient);
            
            
            
            
            // System.out.println(biter[1]);
            // System.out.println(biter[3]);
            
        }

    }
}
// import java.util.Scanner;
// import java.io.File;
// import java.io.FileNotFoundException;


// class Legesystem {



//     private Prioritetskoe<Lege> prioritetskoeLege = new Prioritetskoe<>();



//     //holde styr på flere lister med informasjon om legemidler, resepter, leger og pasienter

//     //Skal benytte seg av liste, som du selv bestemmer

//     //les fra fil metode, vedlegg 2 legedata.txt
//     public void lesFraFil(String filnavn) {

//         Scanner scan = null;

//         try {

//             //lager et filobjekt som sendes til Scanner
//             File fil = new File(filnavn);
//             scan = new Scanner(fil);

//         }
//         catch(FileNotFoundException e) {

//             System.out.println("Finner ikke filen" + filnavn);

//         }

//         while (scan.hasNextLine()) {
//             //String linje = scan.nextLine();
//             //String[] biter = linje.split("");
//             String[] linje = scan.nextLine().split(" ");

//         //sjekker for pasient 
//         if (linje[0].contains("#")) {

//         }

//         //sjekker for lege 
//         if (linje[5].contains("#")) {

//         }




//     }


//     }
// }
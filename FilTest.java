
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.PrintWriter;

// public class FilTest {
//     public static void main(String[] args) {

//             try {
        
//                 PrintWriter skriv = new PrintWriter(new FileWriter("nyfil.txt"));
//                 skriv.append("# Pasienter (navn, fnr)\n");

//                 //Skriver inn pasienter
//                 for (Pasient pasient : pasientliste) {
//                     skriv.append(pasient + "\n");
//                 }

//                 //for legemidler
//                 for (Legemiddel legemiddel: legemiddelliste) {
//                     skriv.append(legemiddel + "\n");
//                 }

//                 //Leger
//                 for (Lege lege : legeliste) {
//                     skriv.append(lege + "\n");
//                 }

//                 for (Resept resept : reseptliste) {
//                     skriv.append(resept + "\n");
//                 }
//                 skriv.close();
//             }
//             catch(IOException e) {
//                 e.printStackTrace();
//                 System.out.println("Det skjedde en feil");

//             }



            
//     }
// }

// public class TesterUt {
    
//     else if (indeks == 4) {



//         // try {
//         //     for ()
//         // }
//         // catch(Exception e) {
//         //     System.out.println("Det skjedde en feil med innlesing");
//         // }

                  
//         //bruker Integer.parseInt(); fra oblig 1
//         int legemiddelNummer = Integer.parseInt(biter[0]);
//         Legemiddel valgtLegemid = null;
//         for (Legemiddel legemdl : legemiddelliste){
//             if (legemdl.hentId() == legemiddelNummer){
//                 valgtLegemid = legemdl;
//             }
//         }



//         //skal hente objektene fr a
//         String legenavn = biter[1];
//         Lege navnettillege = null;
//         for (Lege navn : legeliste){ //fungerer ikke på grunn av linje 8
//             if (navn.hentNavn().equals(legenavn)){
//                 navnettillege = navn;
//             }
//         }

//         int pasientID = Integer.parseInt(biter[2]);
//         Pasient idpasientene = null;
//         for (Pasient iD  : pasientliste){ 
//             if (iD.hent_id() == pasientID){
//                 idpasientene = iD;
//             }
//         }
        
//         String type = biter[ 3 ];

//         //SKAL KALLE PÅ SKRIVRESEPTMETODE FRA LEGE!
//         //hvitResept
//         if (type.contains("hvit")) {  
//             int reit = Integer.parseInt(biter[ 4 ]);
            
//             reseptliste.leggTil(navnettillege.skrivHvitResept(valgtLegemid, idpasientene, reit));
//             //Resept hvit = new HvitResept(valgtLegemid,navnettillege,idpasientene,reit);
//             //reseptliste.leggTil(hvit);
//         }

//         //blaaresept
//         else if (type.contains("blaa")) {  
//             int reit = Integer.parseInt(biter[ 4 ]);
//             Resept blaa = new BlaaResept(valgtLegemid,navnettillege,idpasientene,reit);
//             reseptliste.leggTil(blaa);
//         }

//         //prevensjonresept
//         else if (type.contains("p")) {  
//             int reit = Integer.parseInt(biter[ 4 ]);
//             Resept prresept = new PResept(valgtLegemid,navnettillege,idpasientene,reit);
//             reseptliste.leggTil(prresept);
//         }

//         //militær
//         //Den enesete som ikke bruker reit er militær, kanskje fordi de får 3 uannsett som da sa i oblig 2??
//         else if (type.contains("militaer")) {  
//             Resept mResept = new MilResept(valgtLegemid,navnettillege,idpasientene);
//             reseptliste.leggTil(mResept);
//         }

//     }

// }
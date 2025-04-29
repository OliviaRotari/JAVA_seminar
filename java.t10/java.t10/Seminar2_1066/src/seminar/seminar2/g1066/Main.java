package seminar.seminar2.g1066;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//    Să se scrie o aplicație pentru gestiunea fondului de carte al unei biblioteci. Vor fi definite
//    următoarele clase, interfețe, enumerări:
//    Domeniu - enumerare pentru domenii. Constante: ISTORIE(1), MATEMATICA(2), IT(3),
//    GEOGRAFIE(4) ... . Fiecărui domeniu i se va asocia un cod de domeniu: int codDomeniu. Câmpul
//    codDomeniu va avea metode de acces.
//    Sala - enumerare a sălilor de lectură cu locațiile acestora. Constante:
//    VICTOR_SLAVESCU("Bibloteca Centrala"), GRIGORE_MOISIL("Facultatea de Matematica"),
//            ... . Fiecărei săli i se va asocia unitatea bibliotecii de care aparține: String unitate. Câmpul unitate
//    va avea metode de acces.
//            Publicatie - clasă abstractă pentru modelarea conceptului de publicație. Câmpuri: String titlu;
//    String cota, String editura; int anAparitie; double valoareInventar, Domeniu[] domenii. Clasa va
//    avea constructor fără parametri și constructor de inițializare pentru câmpurile menționate (în
//                                                                                                        ordinea în care au fost menționate!), metode de acces pentru câmpurile private, suprascriere pentru
//    toString(), implementare egalitate după cota și comparator după anAparitie.
//    Constructorul de inițializare și metoda get pentru câmpul anAparitie vor arunca excepții pentru
//    valori mai mari decât 2025.
//    Operatiuni - Interfață pentru implementarea operațiunilor de împrumut. Metode abstracte:
//    void imprumut(long nrZile); // Va fi stabilită data returnării ca data curentă + nrZile
//    void rezervareSala(Sala numeSala); // Se specifică sala la care este rezervată cartea
//    void returnare(); //Sunt puse pe null campurile cu data returnării sau sala la care a fost rezervată
//    Carte - clasă utilizată pentru definire cărți. Este extensie de Publicatie și va implementa
//    Operatiuni. Câmpuri: String[] autori, int nrPagini, Sala salaRezervare, Date dataReturnare.
//    Câmpurile salaRezervare și dataReturnare sunt utilizate în procesul de rezervare la sală și
//    împrumut. Acestea vor fi modificate prin implementarea interfeței Operațiuni. Clasa va avea
//    constructor fără parametri și constructor de inițializare pentru câmpurile menționate (în ordinea în
//            care au fost menționate!), metode de acces pentru câmpurile private, suprascriere pentru toString()
//    și clonare profundă.
//            Revista - clasă utilizată pentru definire de reviste. Este extensie a clasei Publicatie. Câmpuri:
//    String colectia; int aparitiiAn. Clasa va avea constructor fără parametri și constructor de
//    inițializare pentru câmpurile menționate, metode de acces pentru câmpurile private, suprascriere
//    pentru toString().
//            1. Să se definească clasele, enumerările și interfețele menționate
//2. Să se citească din fișierul carti.csv obiecte Carte și să se memoreze într-un vector de tip Carte[].
//    Citirea se va face utilizând clasa Scanner prin redirectarea intrării standard.
//3. Să se printeze într-un fișier text cărțile din vectorul creat la punctul 2, care nu sunt împrumutate
//    sau rezervate la sală. Vor fi salvate cota, titlul și autorii pentru fiecare carte.
//4. Să se creeze listă de cărți de tip List<Carte> cu elementele din vectorul creat la punctul 2.
//            5. Să se salveze lista de cărți în fișierul carti.dat (Serializarea).
//            6. Să se restaureze lista de cărți din fișierul carti.dat (Deserializarea).
//            7. Să se efectueze operațiuni de căutare, selecție și sortare pe lista de cărți folosind metode List și
//    Collections.
//8. Să se creeze o bibliotecă de tip jar cu conținutul proiectului.


public class Main {
    public static SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");
    private static Carte[] carti;
    private static List<Carte> listaCarti;



    public static void main(String[] args) {
        try {
            // --- Cod testare manuala Carte
//            Carte carte = new Carte(
//                    "Istoria declinului si prabusirii Imperiului Roman",
//                    "II234167",
//                    "Polirom",
//                    2002,
//                    100,
//                    new Domeniu[]{Domeniu.ISTORIE},
//                    new String[]{"Edward Gibbon"},
//                    500
//            );
//            System.out.println(carte);
//            Carte carte1=carte;
//            Carte carte2 = new Carte();
//            System.out.println(carte.equals(carte2));
//            Carte carte3 = new Carte("II234167");
//            System.out.println(carte.equals(carte3));
//            System.out.println(carte.compareTo(carte2));
////Test clonare
//            Carte clona =(Carte) carte.clone();
//            carte.getAutori()[0]="Theodor Mommsen";
//            System.out.println("Obiect original:\n"+carte);
//            System.out.println("Clona:\n"+clona);

////Test extindere autori
//            Carte carte4 = new Carte();
//            carte4.addAutori("Pop Adrian");
//            carte4.addAutori("Pop Flavius");
//            System.out.println(carte4);

////            Test operatiuni
//            carte.imprumut(20);
//            System.out.println("Carte imprumutata:\n"+carte);
//            carte.setDataReturnare(formatData.parse("01.03.2025"));
//            carte.returnare();
//            carte.rezervareSala(Sala.GRIGORE_MOISIL);
//            System.out.println("Carte la sala:\n"+carte);

            // --- Cerinta 2: Citire obiecte Carte din fisierul carti.csv ---
            // Să se citească din fișierul carti.csv obiecte Carte și să se memoreze într-un vector de tip Carte[].
            citireCarti();
            System.out.println("Carti citite din fisier:");
            for (Carte c : carti) {
                System.out.println(c);
            }

            // --- Cerinta 3: Printare carti disponibile (neimprumutate si nerezervate) intr-un fisier text ---
//            Să se printeze într-un fișier text cărțile din vectorul creat la punctul 2, care nu sunt împrumutate
//            sau rezervate la sală. Vor fi salvate cota, titlul și autorii pentru fiecare carte.
                    System.out.println("Carti disponibile: <CartiD.csv>");
            print("CartiD.csv");

            //--- Cerinta 4: Creare lista de carti de tip List<Carte> ---
//            Să se creeze listă de cărți de tip List<Carte> cu elementele din vectorul creat la punctul 2.

            creareLista();
            System.out.println("Lista carti:");
            for (Carte carte : listaCarti) {
                System.out.println(carte);
            }
            // --- Cerinta 5: Salvare lista de carti in fisier binar (Serializare) ---

//          salvare("carti.dat");

            // --- Cerinta 6: Restaurare lista de carti din fisier binar (Deserializare) ---
            restaurare("carti.dat");
            for (Carte carte : listaCarti) {
                System.out.println(carte);
            }

            // --- Cerinta 7:  Să se efectueze operațiuni de căutare, selecție și sortare pe lista
            // de cărți folosind metode List și Collections.

//            Cautare dupa Cota
            System.out.println("Cota:");
            String cota = new BufferedReader(new InputStreamReader(System.in)).readLine();
            Carte carteCautata = new Carte(cota);
            int k = listaCarti.indexOf(carteCautata);
            if (k==-1){
                System.out.println("Nu am gasit cartea cu cota "+cota);
            } else {
                System.out.println("Cartea cautata:");
                System.out.println(listaCarti.get(k));
            }

            //sortare dupa titlu
            Comparator<Carte> comparatorTitlu = new Comparator<Carte>() {
                @Override
                public int compare(Carte o1, Carte o2) {
                    return o1.titlu.compareTo(o2.titlu);
                }
            };
            Collections.sort(listaCarti,comparatorTitlu);
            System.out.println("Lista sortata dupa titlu:");
            for (Carte carte : listaCarti) {
                System.out.println(carte);
            }

            //cautare binara dupa titlu
            System.out.println("Titlu:");
            String titlu = new BufferedReader(new InputStreamReader(System.in)).readLine();
            carteCautata.setTitlu(titlu);
            k = Collections.binarySearch(listaCarti,carteCautata,comparatorTitlu);
            if (k<0){
                System.out.println("Nu am gasit cartea cu titlul:"+titlu);
                System.out.println("Pozitie de inserare: "+(-k-1));
            } else {
                System.out.println("Cartea cautata:\n"+listaCarti.get(k));
            }

            // --- Cerinta 8: Creare biblioteca .jar cu continutul proiectului ---
            // (Aceasta cerinta se realizeaza la final, din IDE sau folosind comanda de build)
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    // --- Cerinta 5: Salvare lista de carti in fisier binar folosind ObjectOutputStream (Serializare) ---
    private static void salvare(String numeFisier) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(numeFisier))) {
            for (Carte carte : listaCarti) {
                out.writeObject(carte);
            }
            System.out.println("Salvare in " + numeFisier);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.toString());
        }
    }

    // --- Cerinta 6: Restaurare lista de carti din fisier binar folosind ObjectInputStream (Deserializare) ---
    private static void restaurare(String numeFisier) {
        try (FileInputStream in1 = new FileInputStream(numeFisier);
             ObjectInputStream in = new ObjectInputStream(in1)) {
            listaCarti.clear();
            while (in1.available()!=0){
                listaCarti.add((Carte) in.readObject());
            }
            System.out.println("Lista restaurata din fisierul "+numeFisier);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.toString());
        }
    }

    // --- Cerinta 3: Salvare in fisier text a cartilor disponibile (neimprumutate, nerezervate) ---
    private static void print(String numeFisier) {
        try (PrintWriter out = new PrintWriter(numeFisier)) {
            for (Carte carte : carti) {
                if (carte.getDataReturnare() == null && carte.getSalaRezervare() == null) {
                    out.println(carte.cota + "," + carte.titlu + "," +
                            String.join("/", carte.getAutori()));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.toString());
        }
    }

    //ceinta4
    // --- Cerinta 4: Creare lista de carti pe baza vectorului carti[] ---
    public static List<Carte> getListaCarti() {
        return listaCarti;
    }

    public static void creareLista() {
        listaCarti = new ArrayList<>();
        for (Carte carte : carti) {
            listaCarti.add(carte);
        }
//        Collections.addAll(listaCarti, carti);// alternativa
    }

    // --- Cerinta 2: Citire carti din fisier folosind BufferedReader ---
    public static void citireCarti() {
        carti = new Carte[0];
        try (BufferedReader in = new BufferedReader(new FileReader("carti.csv"))) {
            String linie;
            while ((linie = in.readLine()) != null) {
                String[] t = linie.trim().split(",");
                Carte carte = new Carte();
                carte.setTitlu(t[0].trim());
                carte.setCota(t[1].trim());
                carte.setEditura(t[2].trim());
                carte.setAnAparitie(Integer.parseInt(t[3].trim()));
                carte.setValoareInventar(Double.parseDouble(t[4].trim()));
                String[] domeniiS = t[5].trim().split("/");
                Domeniu[] domenii = new Domeniu[domeniiS.length];
                for (int i = 0; i < domenii.length; i++) {
                    domenii[i] = Domeniu.valueOf(domeniiS[i].toUpperCase());
                }
                carte.setDomenii(domenii);
                carte.setAutori(t[6].trim().split("/"));
                carte.setNrPagini(Integer.parseInt(t[7].trim()));
                carti = Arrays.copyOf(carti, carti.length + 1);
                carti[carti.length - 1] = carte;
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

package seminar.seminar1.g1066;

import java.util.Arrays;


//1. Să se definească un masiv bidimensional cu un număr diferit de elemente pe fiecare linie.
//Dimensiunile liniilor și valorile folosite în inițializarea liniilor sunt furnizate prin linie de comandă
//        (parametri metodei statice main), astfel:
//numar_elemente0,valoare0,..., numar_elementen-1,valoaren-1
//unde numar_elemente reprezintă numărul de elemente de pe o linie iar valoare reprezintă valoarea
//elementelor de pe linie.
//        2. Să se definească următoarele metode pentru lucrul cu masivul creat la punctul 1:
//metodă prin care să se insereze o linie într-o anumită poziție (inclusiv pe ultima poziție);
//metodă prin care să se elimine o linie cu o poziție indicată;
//metodă prin care să se schimbe toate valorile unei linii;
//metodă prin care să fie adăugate elemente la o linie.
//
//        3. Să se definească un masiv tridimensional cu un număr diferit de elemente pe fiecare dimensiune.
//Dimensiunile vor fi citite dintr-un fișier text input.txt prin redictarea inputului standard. Va fi
//folosită clasa Scanner. Pe prima linie în fișierul input se află prima dimensiune. Pe următoarele
//linii se află dimensiunile 2 și 3.
//Matricele masivului tridimensional vor fi inițializate cu valori 0, 1, ....
//Exemplu
//Pentru fișierul input:
//        3
//        5,7,3,6
//        4,3,1
//        2,2
//outputul va fi:
//        [
//        [0, 0, 0, 0, 0]
//        [0, 0, 0, 0, 0, 0, 0]
//        [0, 0, 0]
//        [0, 0, 0, 0, 0, 0]
//        ]
//        [
//        [1, 1, 1, 1]
//        [1, 1, 1]
//        [1]
//        ]
//        [
//        [2, 2]
//        [2, 2

public class Main {
    public static void main(String[] args) {
//        System.out.println("Seminar 1 1066.");
//        for (String e:args){
//            System.out.println(e);
//        }
        // CERINTA 1: Definire masiv bidimensional cu lungimi și valori primite ca parametri în linia de comandă
        int n = args.length/2;
        double[][] x = new double[n][];
        for (int i = 0; i < n; i++) {
            x[i] = new double[Integer.parseInt(args[2*i])];
            Arrays.fill(x[i], Double.parseDouble(args[2 * i + 1]));
        }
        afisare(x,"Matricea initiala:");

//        // Cod adăugat pentru testare funcționalități suplimentare
        try{
            // CERINTA 2 - metoda de inserare linie la o anumita pozitie
            x = inserare(x,2,5,Math.PI);
            afisare(x,"Matricea dupa inserare:");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // Metoda de afișare a masivului bidimensional
    private static void afisare(double[][] x,String mesaj){
        System.out.println(mesaj);
        for (double[] linie:x){
            System.out.println(Arrays.toString(linie));
        }
    }

    // CERINTA 2: Metoda de inserare a unei linii într-o anumită poziție
    private static double[][] inserare(double[][] x,int k,int n,double v) throws Exception{
        if (k<0 || k>x.length){
            throw new Exception("pozitie de inserare incorecta!");
        }
        double[][] y = new double[x.length+1][];
//        Adaugat in afara seminarului
//        Copiere linii de la 0 la k-1 inclusiv, din x in y
        System.arraycopy(x,0,y,0,k);
        // Creare linie k și umplere cu valoarea v
        y[k] = new double[n];

        Arrays.fill(y[k],v);
//        Copiere linii de la k-1 la sfarsit, din x in y
        System.arraycopy(x,k,y,k+1,x.length-k);
        return y;

    }

}

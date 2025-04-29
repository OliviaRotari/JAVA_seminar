package seminar.seminar6.g1060;

import seminar.seminar2.g1060.Depozit;
import seminar.seminar2.g1060.Moneda;

import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        seminar.seminar2.g1060.Main.citireTitulari();
        seminar.seminar2.g1060.Main.citireDepozite();
        List<Depozit> lista = seminar.seminar2.g1060.Main.getDepozite();
        System.out.println("Lista depozite:");
//        for (Depozit depozit:lista){
//            System.out.println(depozit);
//        }
////        Afisare prin clasa anonima
//        lista.forEach(new Consumer<Depozit>() {
//            @Override
//            public void accept(Depozit depozit) {
//                System.out.println(depozit);
//            }
//        });
////  Afisare prin functie lambda
//        lista.forEach( depozit -> System.out.println(depozit) );
// Afisare prin referinta la metoda
        lista.forEach(System.out::println);

//        Tema 3
//        1
//        Selectie dupa cnp
        List<Depozit> cerinta1 = lista.stream()
                .filter(depozit -> depozit.getTitular().getCnp()==2701212038976L)
                .toList();
        System.out.println("Cerinta 1:");
        cerinta1.forEach(System.out::println);
        //    2
        List<Depozit> cerinta2 = lista.stream()
                .filter(depozit -> depozit.getMoneda()== Moneda.EURO)
                .toList();
        System.out.println("Cerinta 2:");
        cerinta2.forEach(System.out::println);

    }

}

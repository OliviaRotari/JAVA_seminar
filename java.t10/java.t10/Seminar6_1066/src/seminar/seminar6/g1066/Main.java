package seminar.seminar6.g1066;

import seminar.seminar2.g1066.Carte;

import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        seminar.seminar2.g1066.Main.citireCarti();
        seminar.seminar2.g1066.Main.creareLista();
        List<Carte> lista = seminar.seminar2.g1066.Main.getListaCarti();
        System.out.println("Lista carti:");
//        for (Carte carte:lista){
//            System.out.println(carte);
//        }
////        Afisare lista prin consumator instantiat cu ajutorul unei clase anonime
//        lista.forEach(new Consumer<Carte>() {
//            @Override
//            public void accept(Carte carte) {
//                System.out.println(carte);
//            }
//        });
////        Afisare lista prin functie lambda
//        lista.forEach( carte -> System.out.println(carte) );
//        Afisare folosind referinte la metode
        lista.forEach(System.out::println);

//        Cerinta 1
        List<Carte> cerinta1 = lista.stream()
                .filter(carte -> carte.getValoareInventar() > 50 && carte.getValoareInventar() < 100)
                .toList();
        System.out.println("--> Cerinta 1");
        cerinta1.forEach(System.out::println);

    }
}

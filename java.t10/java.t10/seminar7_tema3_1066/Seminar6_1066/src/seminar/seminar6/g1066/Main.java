package seminar.seminar6.g1066;

import seminar.seminar2.g1066.Carte;
import seminar.seminar2.g1066.Publicatie;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

//Folosind biblioteca jar creată pentru tema 2.1, să se efectueze următoarele operațiuni asupra unei
//liste de cărți folosind facilitățile de prelucrare funcțională a colecțiilor:
//        1. Selecția cărților având valoarea de inventar între anumite limite
//2. Sortarea cărților după editură
//3. Selecția cărților unui anumit autor
//4. Sortarea cărților după anul apariției
//5. Selecția tuturor editurilor într-o colecție de tip Set<String>
//6. Gruparea cărților pe cote și autori într-o structură de tip Map<String, String[]> unde cheie este
//cota iar valoare sunt autorii
//7. Gruparea cărților pe edituri într-o structură de tip Map<String, List<Carte>>
//8. Gruparea titlurilor pe edituri într-o structură de tip Map<String,List<String>>
//9. Calcul valoare medie de inventar a cărților pe edituri într-o structură Map<String,Double>
//10. Gruparea titlurilor și anilor de apariție pe cote, într-o structură de tip Map<String,?> unde cheie
//este cota iar valoarea este de formată de titlu și an apariție
//11. Selecția tuturor autorilor într-o colecție de tip Set<String> prin implementarea unui colector
//        particularizat

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

//        Cerinta 1 Selecția cărților având valoarea de inventar între anumite limite
        List<Carte> cerinta1 = lista.stream()
                .filter(carte -> carte.getValoareInventar() > 50 && carte.getValoareInventar() < 100)
                .toList();
        System.out.println("--> Cerinta 1");
        cerinta1.forEach(System.out::println);

//        Cerinta 2 Sortarea cărților după editură
        List<Carte> cerinta2 = lista.stream()
                .sorted((carte1, carte2) -> carte1.getEditura().compareTo(carte2.getEditura()))
                .toList();
        System.out.println("Lista sortata dupa editura:");
        cerinta2.forEach(System.out::println);

//        Cerintele 3 Selecția cărților unui anumit autor
        String numeAutor = "Georgescu";
        List<Carte> cerinta3 = lista.stream()
                .filter(carte -> {
                    for (String autor : carte.getAutori()) {
                        if (autor.contains(numeAutor)) {
                            return true;
                        }
                    }
                    return false;
                })
                .toList();
        System.out.println("--> Cerinta 3");
        cerinta3.forEach(System.out::println);

        //Sortarea cărților după anul apariției
        List<Carte> cerinta4 = lista.stream()
                .sorted((carte1, carte2) -> Integer.compare(carte1.getAnAparitie(), carte2.getAnAparitie()))
                .toList();
        System.out.println("--> Cerinta 4");
        cerinta4.forEach(System.out::println);



//        Cerinta 5 . Selecția tuturor editurilor într-o colecție de tip Set<String>

        Set<String> edituri = lista.stream()
                .map(Carte::getEditura)
                .collect(Collectors.toSet());
        System.out.println("Edituri:");
        edituri.forEach(System.out::println);

//        Cerinta 6

               // 6. Gruparea cărților pe cote și autori într-o structură de tip Map<String, String[]> unde cheie este
        // cota iar valoare sunt autorii
        Map<String, String[]> cerinta6 = lista.stream()
                .collect(Collectors.toMap(
                        Carte::getCota,
                        Carte::getAutori));
        System.out.println("Cerinta 6. Afisare autori pe cote:");
        cerinta6.forEach((cheie, valoare) -> System.out.println(cheie + ":" + Arrays.toString(valoare)));

//        Cerinta 7
//7. Gruparea cărților pe edituri într-o structură de tip Map<String, List<Carte>>

        Map<String, List<Carte>> grupareCarti = lista.stream()
                .collect(Collectors.groupingBy(Carte::getEditura));
        System.out.println("Cerinta 7. Grupare carti pe edituri:");
        grupareCarti.forEach((cheie, valoare) -> System.out.println(cheie + ":" + valoare));

//        Cerinta 8
               // 8. Gruparea titlurilor pe edituri într-o structură de tip Map<String,List<String>>
        Map<String, List<String>> grupareTitluri = lista.stream()
                .collect(Collectors.groupingBy(
                        Carte::getEditura,
                        Collectors.mapping(
                                Carte::getTitlu,
                                Collectors.toList())));
        System.out.println("Cerinta 8. Grupare titluri pe edituri:");
        grupareTitluri.forEach((cheie, valoare) -> {
            System.out.println(cheie + ":");
            valoare.forEach(System.out::println);
        });

//        Cerinta 9
                //9. Calcul valoare medie de inventar a cărților pe edituri într-o structură Map<String,Double>
        Map<String, Double> valoriMedii = lista.stream()
                .collect(Collectors.groupingBy(
                        Carte::getEditura,
                        Collectors.averagingDouble(Carte::getValoareInventar)
                ));
        System.out.println("Cerinta 9. Valori medii pe edituri:");
        valoriMedii.forEach((cheie, valoare) -> System.out.println(cheie + ":" + valoare));

//        Cerinta 10
                //10. Gruparea titlurilor și anilor de apariție pe cote, într-o structură de tip Map<String,?> unde cheie
        //este cota iar valoarea este de formată de titlu și an apariție
        Map<String, ?> cerinta10 = lista.stream()
                .collect(Collectors.toMap(Carte::getCota, carte -> new Object() {
                    final Object camp1 = carte.getTitlu();
                    final Object camp2 = carte.getAnAparitie();

                    @Override
                    public String toString() {
                        return camp1 + "," + camp2;
                    }
                }));
        System.out.println("Cerinta 10:");
        cerinta10.forEach((cheie, valoare) -> System.out.println(cheie + ":" + valoare));

        //    11. Selecția tuturor autorilor într-o colecție de tip Set<String>
        //    prin implementarea unui colector particularizat
        // Cerinta 11
        Set<String> autori = lista.stream()
                .flatMap(carte -> Arrays.stream(carte.getAutori()))
                .collect(Collectors.toSet());
        System.out.println("--> Cerinta 11");
        autori.forEach(System.out::println);

    }
}

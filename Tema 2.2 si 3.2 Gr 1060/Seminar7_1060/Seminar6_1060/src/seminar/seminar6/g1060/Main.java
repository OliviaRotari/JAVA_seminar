package seminar.seminar6.g1060;

import seminar.seminar2.g1060.Cont;
import seminar.seminar2.g1060.Depozit;
import seminar.seminar2.g1060.Moneda;
import seminar.seminar2.g1060.Titular;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
//  1. Selecția depozitelor unui client
        List<Depozit> cerinta1 = lista.stream()
                .filter(depozit -> depozit.getTitular().getCnp()==2701212038976L)
                .toList();
        System.out.println("Cerinta 1:");
        cerinta1.forEach(System.out::println);

//    2 Selecția depozitelor cu o anumită monedă
        List<Depozit> cerinta2 = lista.stream()
                .filter(depozit -> depozit.getMoneda()== Moneda.EURO)
                .toList();
        System.out.println("Cerinta 2:");
        cerinta2.forEach(System.out::println);

//        3 Selecția depozitelor deschise într-o anumită perioadă
        try {
            Date data1 = seminar.seminar2.g1060.Main.formatData.parse("01.02.2023"),
                    data2 = seminar.seminar2.g1060.Main.formatData.parse("28.02.2023");
            List<Depozit> cerinta3 = lista.stream()
                    .filter( depozit -> depozit.getDataDeschidere().after(data1) && depozit.getDataDeschidere().before(data2) )
                    .toList();
            System.out.println("Cerinta 3");
            System.out.println("Depozite intre "+
                    seminar.seminar2.g1060.Main.formatData.format(data1)+" si "+
                    seminar.seminar2.g1060.Main.formatData.format(data2));
            cerinta3.forEach(System.out::println);
        }
        catch (Exception ex){
            System.err.println(ex);
        }

//        4 Sortarea depozitelor după data deschiderii
        List<Depozit> cerinta4 = lista.stream()
                .sorted().toList();
        System.out.println("Depozite sortate dupa data deschiderii:");
        cerinta4.forEach(System.out::println);
//        4 bis. Sortare dupa nume titular
        List<Depozit> cerinta4Bis = lista.stream()
                .sorted( (depozit1,depozit2) -> depozit1.getTitular().getNume().compareTo(depozit2.getTitular().getNume()) )
                .toList();
        System.out.println("Depozite sortate dupa numele titularului:");
        cerinta4Bis.forEach(System.out::println);

//        Cerinta 5 Selecția tuturor titularilor într-o colecție de tip Set<Titular>
        Set<Titular> titulari = lista.stream()
                .map(Cont::getTitular)
                .collect(Collectors.toSet());
        System.out.println("Cerinta 5. Titulari:");
        titulari.forEach(System.out::println);

//        Cerinta 6 Regruparea depozitelor după cod contract și titular
//        într-o structură de tip Map<Integer,Titular>
//unde cheie este codul de contract iar valoare este titularul
        Map<Integer,Titular> cerinta6 = lista.stream()
                .collect(Collectors.toMap(
                        Depozit::getCodContract,
                        Depozit::getTitular));
        System.out.println("Cerinta 6");
        cerinta6.forEach( (cheie,valoare) -> System.out.println(cheie+":"+valoare));


//        7 Gruparea depozitelor după CNP titular
//        într-o structură de tip Map<Long,List<Depozit>>
        Map<Long,List<Depozit>> cerinta7 = lista.stream()
                .collect( Collectors.groupingBy(depozit -> depozit.getTitular().getCnp()) );
        System.out.println("Cerinta 7:");
        cerinta7.forEach((cheie,valoare)->{
            System.out.println(cheie);
            valoare.forEach(System.out::println);
        });

//        8Calcul valoare medie depozite pe fiecare monedă
//        într-o structură de tip Map<Moneda,Double>
        Map<Moneda,Double> valoriMedii = lista.stream()
                .collect( Collectors.groupingBy(
                        Depozit::getMoneda,
                        Collectors.averagingDouble(Depozit::getValoare)) );
        System.out.println("Cerinta 8. Valori medii:");
        valoriMedii.forEach((cheie,valoare)-> System.out.println(cheie+":"+valoare));

//    9 Gruparea CNP-urilor pe sucursale într-o structură de tip Map<String,List<Long>>
        Map<String,List<Long>> cerinta9 = lista.stream()
                .collect( Collectors.groupingBy(
                        Depozit::getSucursala,
                        Collectors.mapping(
                                depozit -> depozit.getTitular().getCnp(),
                                Collectors.toList()) ) );
        System.out.println("Cerinta 9:");
        cerinta9.forEach((cheie,valoare)->{
            System.out.println(cheie);
            valoare.forEach(System.out::println);
        });

//        Cerinta 10
        //Regruparea depozitelor pe cod contract, nume titular
        // și dată deschidere într-o structură de tip
        //Map<Integer,?> unde cheie este codul de contract iar
        // valoarea este de formată de numele
        //titularului și dată deschidere
        Map<Integer,?> cerinta10 = lista.stream()
                .collect(Collectors.toMap( Depozit::getCodContract, depozit -> new Object(){
                    private final Object camp1 = depozit.getTitular().getNume();
                    private final Object camp2 = depozit.getDataDeschidere();
                    @Override
                    public String toString() {
                        return camp1 + "," + camp2;
                    }
                }  ));
        System.out.println("Cerinta 10:");
        cerinta10.forEach((cheie,valoare)-> System.out.println(cheie+":"+valoare));
    }


}

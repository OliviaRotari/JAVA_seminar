import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Proiect> proiecte = new ArrayList<>();

        // 1. Citire proiecte din fisier
        System.out.println("Citire proiecte din fisierul proiecte.csv...");
        try (BufferedReader br = Files.newBufferedReader(Paths.get("proiecte.csv"))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] t = linie.split(",");
                int cod = Integer.parseInt(t[0].trim());
                String acronim = t[1].trim();
                String sef = t[2].trim();
                String departament = t[3].trim();
                double buget = Double.parseDouble(t[4].trim());
                int nrMembri = Integer.parseInt(t[5].trim());

                proiecte.add(new Proiect(cod, acronim, sef, departament, buget, nrMembri));
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului proiecte.csv: " + e.getMessage());
        }

        // 2. Afisare buget total ASE
        System.out.println("\n--- Bugetul total la nivel de ASE ---");
        double bugetTotal = proiecte.stream()
                .mapToDouble(Proiect::getBuget)
                .sum();
        System.out.println("Buget Total: " + bugetTotal);

        // 3. Afisare acronime pentru fiecare departament
        System.out.println("\n--- Acronimele proiectelor grupate pe departamente ---");
        Map<String, List<String>> acronimePeDepartament = proiecte.stream()
                .collect(Collectors.groupingBy(
                        Proiect::getDepartament,
                        Collectors.mapping(Proiect::getAcronim, Collectors.toList())
                ));

        acronimePeDepartament.forEach((departament, acronime) ->
                System.out.println(departament + ": " + String.join("!", acronime))
        );

        // 4. Salvare proiecte sortate descrescator dupa numar membri
        System.out.println("\n--- Salvare proiecte sortate descrescator dupa numar membri in proiecte_sortate.csv ---");
        proiecte.sort((p1, p2) -> Integer.compare(p2.getNrMembri(), p1.getNrMembri()));

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("proiecte_sortate.csv"))) {
            for (Proiect p : proiecte) {
                bw.write(p.getCod() + "," + p.getAcronim() + "," + p.getBuget() + "," + p.getNrMembri());
                bw.newLine();
            }
            System.out.println("Fisier proiecte_sortate.csv creat cu succes!");
        } catch (IOException e) {
            System.err.println("Eroare la salvarea proiecte_sortate.csv: " + e.getMessage());
        }

        // 5. Salvare in fisier binar si creare Map
        System.out.println("\n--- Salvare proiecte in fisier binar proiecte.dat ---");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("proiecte.dat"))) {
            oos.writeObject(proiecte);
            System.out.println("Fisier proiecte.dat creat cu succes!");
        } catch (IOException e) {
            System.err.println("Eroare la salvarea proiecte.dat: " + e.getMessage());
        }

        System.out.println("\n--- Citire din proiecte.dat si creare Map (cod proiect -> acronim si buget) ---");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("proiecte.dat"))) {
            List<Proiect> proiecteDinBinar = (List<Proiect>) ois.readObject();

            Map<Integer, String> proiecteMap = proiecteDinBinar.stream()
                    .collect(Collectors.toMap(
                            Proiect::getCod,
                            p -> p.getAcronim() + " - " + p.getBuget()
                    ));

            proiecteMap.forEach((cod, detalii) ->
                    System.out.println("Cod " + cod + ": " + detalii)
            );

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Eroare la citirea proiecte.dat: " + e.getMessage());
        }
    }
}

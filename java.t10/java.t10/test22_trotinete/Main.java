import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Map<String, Trotineta> trotinete = new HashMap<>();

        // 2) Citirea fișierului trotinete.txt
        try (BufferedReader br = new BufferedReader(new FileReader("trotinete.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");
                String id = tokens[0];
                float distanta = Float.parseFloat(tokens[1]);
                float vitezaMedie = Float.parseFloat(tokens[2]);
                float vitezaMaxima = Float.parseFloat(tokens[3]);
                Trotineta t = new Trotineta(id, distanta, vitezaMedie, vitezaMaxima);
                trotinete.put(id, t);
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
        }

        // Afișare trotinete cu viteza maxima > 50 km/h
        System.out.println("Trotinete cu viteza maxima > 50 km/h:");
        for (Trotineta t : trotinete.values()) {
            if (t.getVitezaMaxima() > 50) {
                System.out.println(t);
            }
        }

        // 3) Gruparea după viteza medie
        System.out.println("\nStatistici dupa viteza medie:");

        trotinete.values().stream()
                .collect(Collectors.groupingBy(
                        Trotineta::getVitezaMedie,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    int count = list.size();
                                    float sumaDistante = 0;
                                    for (Trotineta t : list) {
                                        sumaDistante += t.getDistantaTotala();
                                    }
                                    return new AbstractMap.SimpleEntry<>(count, sumaDistante);
                                }
                        )
                ))
                .forEach((vitezaMedie, entry) -> {
                    System.out.println("viteza medie " + vitezaMedie + " km/h -> " +
                            entry.getKey() + " trotinete, suma distanțelor parcurse " + entry.getValue() + " km");
                });

        // 4) Scriere în fișier binar Rapide.dat
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Rapide.dat"))) {
            for (Trotineta t : trotinete.values()) {
                if (t.getVitezaMedie() > 14 || t.getVitezaMaxima() > 50) {
                    oos.writeObject(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la scrierea in fisier binar: " + e.getMessage());
        }

        // 5) Citire și afișare din Rapide.dat
        System.out.println("\nTrotinete din Rapide.dat:");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Rapide.dat"))) {
            while (true) {
                Trotineta t = (Trotineta) ois.readObject();
                System.out.println(t);
            }
        } catch (EOFException e) {
            // sfarsitul fisierului - normal
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Eroare la citirea din fisier binar: " + e.getMessage());
        }
    }
}

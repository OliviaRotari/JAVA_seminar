import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    public static SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");
    private static Achizitie[] achizitii;
    private static List<Achizitie> listaAchizitii;

    public static void main(String[] args) {
        try {
            citireAchizitii();
            System.out.println("Achizitii citite din fisier:");
            for (Achizitie a : achizitii) {
                System.out.println(a);
            }

            // 2) Afisare achizitii din prima jumatate a lunii si cantitate > 100
            System.out.println("\nAchizitii din prima jumatate a lunii si cantitate > 100:");
            for (Achizitie a : achizitii) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(a.getDataAchizitie());
                int zi = calendar.get(Calendar.DAY_OF_MONTH);
                if (zi <= 15 && a.getCantitate() > 100) {
                    System.out.println(a);
                }
            }

            // 3) Grupare dupa cod produs
            creareLista();
            Map<String, List<Achizitie>> grupare = listaAchizitii.stream()
                    .collect(Collectors.groupingBy(Achizitie::getCodProdus));

            Map<String, Double> valoriTotale = new TreeMap<>();

            for (String cod : grupare.keySet()) {
                int nrAchizitii = grupare.get(cod).size();
                double valoareTotala = grupare.get(cod).stream().mapToDouble(Achizitie::valoare).sum();
                valoriTotale.put(cod, valoareTotala);
            }

            System.out.println("\nProduse grupate descrescator dupa valoare totala:");
            valoriTotale.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .forEach(entry -> {
                        String cod = entry.getKey();
                        int nrAchizitii = grupare.get(cod).size();
                        double valoare = entry.getValue();
                        System.out.printf("Produs %s -> %d achizitii, valoare totala %.2f Lei\n",
                                cod, nrAchizitii, valoare);
                    });

            // 4) Salvare produse frecvente (>3 achizitii) in Frecvente.dat
            salvareProduseFrecvente("Frecvente.dat", grupare);

            // 5) Citire produse din Frecvente.dat
            citireProduseFrecvente("Frecvente.dat");

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void citireAchizitii() {
        achizitii = new Achizitie[0];
        try (BufferedReader in = new BufferedReader(new FileReader("achizitii.txt"))) {
            String linie;
            while ((linie = in.readLine()) != null) {
                String[] t = linie.trim().split(",");
                String cod = t[0].trim();
                int an = Integer.parseInt(t[1].trim());
                int luna = Integer.parseInt(t[2].trim());
                int zi = Integer.parseInt(t[3].trim());
                int cantitate = Integer.parseInt(t[4].trim());
                float pret = Float.parseFloat(t[5].trim());

                Calendar c = Calendar.getInstance();
                c.set(an, luna - 1, zi); // luna incepe de la 0
                Date data = c.getTime();

                Achizitie achizitie = new Achizitie(cod, data, cantitate, pret);
                achizitii = Arrays.copyOf(achizitii, achizitii.length + 1);
                achizitii[achizitii.length - 1] = achizitie;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void creareLista() {
        listaAchizitii = new ArrayList<>();
        Collections.addAll(listaAchizitii, achizitii);
    }

    public static void salvareProduseFrecvente(String numeFisier, Map<String, List<Achizitie>> grupare) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(numeFisier))) {
            for (String cod : grupare.keySet()) {
                if (grupare.get(cod).size() > 3) {
                    out.writeObject(cod);
                }
            }
            System.out.println("\nProduse frecvente salvate in " + numeFisier);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, e.toString());
        }
    }

    public static void citireProduseFrecvente(String numeFisier) {
        try (FileInputStream in1 = new FileInputStream(numeFisier);
             ObjectInputStream in = new ObjectInputStream(in1)) {

            System.out.println("\nProduse frecvente citite din " + numeFisier + ":");
            while (in1.available() != 0) {
                String cod = (String) in.readObject();
                System.out.println(cod);
            }
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, e.toString());
        }
    }
}

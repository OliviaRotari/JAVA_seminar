import java.io.Serializable;
import java.util.Objects;

public class Trotineta implements Comparable<Trotineta>, Serializable {
    private String id;
    private float distantaTotala;
    private float vitezaMedie;
    private float vitezaMaxima;

    // Constructor cu parametri
    public Trotineta(String id, float distantaTotala, float vitezaMedie, float vitezaMaxima) {
        this.id = id;
        this.distantaTotala = distantaTotala;
        this.vitezaMedie = vitezaMedie;
        this.vitezaMaxima = vitezaMaxima;
    }

    // Constructor default
    public Trotineta() {
        this.id = "";
        this.distantaTotala = 0;
        this.vitezaMedie = 0;
        this.vitezaMaxima = 0;
    }

    // Getteri
    public String getId() { return id; }
    public float getDistantaTotala() { return distantaTotala; }
    public float getVitezaMedie() { return vitezaMedie; }
    public float getVitezaMaxima() { return vitezaMaxima; }

    @Override
    public String toString() {
        return id + " " + distantaTotala + " km, viteza medie: " + vitezaMedie + " km/h, viteza maxima: " + vitezaMaxima + " km/h";
    }

    // Egalitate: dacă diferența dintre distanțe e mai mică de 10 km
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trotineta)) return false;
        Trotineta t = (Trotineta) o;
        return Math.abs(this.distantaTotala - t.distantaTotala) < 10;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Comparare dupa distanta totala
    @Override
    public int compareTo(Trotineta other) {
        if (this.equals(other)) {
            return 0;
        }
        return Float.compare(this.distantaTotala, other.distantaTotala);
    }
}

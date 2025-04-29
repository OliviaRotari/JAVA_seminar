import java.io.Serializable;
import java.util.Objects;

public class Proiect implements Comparable<Proiect>, Serializable {
    private int cod;
    private String acronim;
    private String sefProiect;
    private String departament;
    private double buget;
    private int nrMembri;

    public Proiect() {}

    public Proiect(int cod, String acronim, String sefProiect, String departament, double buget, int nrMembri) {
        this.cod = cod;
        this.acronim = acronim;
        this.sefProiect = sefProiect;
        this.departament = departament;
        this.buget = buget;
        this.nrMembri = nrMembri;
    }

    public int getCod() { return cod; }
    public String getAcronim() { return acronim; }
    public String getSefProiect() { return sefProiect; }
    public String getDepartament() { return departament; }
    public double getBuget() { return buget; }
    public int getNrMembri() { return nrMembri; }

    @Override
    public String toString() {
        return cod + ", " + acronim + ", " + sefProiect + ", " + departament + ", " + buget + ", " + nrMembri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proiect)) return false;
        Proiect proiect = (Proiect) o;
        return cod == proiect.cod;
    }

    @Override
    public int compareTo(Proiect o) {
        return Double.compare(this.buget, o.buget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod);
    }
}


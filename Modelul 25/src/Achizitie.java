import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Achizitie implements Comparable<Achizitie>, Serializable {
    protected String codProdus;
    protected Date dataAchizitie;
    protected int cantitate;
    protected float pretUnitar;

    public Achizitie() {
    }

    public Achizitie(String codProdus, Date dataAchizitie, int cantitate, float pretUnitar) {
        this.codProdus = codProdus;
        this.dataAchizitie = dataAchizitie;
        this.cantitate = cantitate;
        this.pretUnitar = pretUnitar;
    }

    public String getCodProdus() {
        return codProdus;
    }

    public void setCodProdus(String codProdus) {
        this.codProdus = codProdus;
    }

    public Date getDataAchizitie() {
        return dataAchizitie;
    }

    public void setDataAchizitie(Date dataAchizitie) {
        this.dataAchizitie = dataAchizitie;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public float getPretUnitar() {
        return pretUnitar;
    }

    public void setPretUnitar(float pretUnitar) {
        this.pretUnitar = pretUnitar;
    }

    public double valoare() {
        return cantitate * pretUnitar;
    }

    @Override
    public String toString() {
        return "Achizitie{" +
                codProdus +
                "," + Main.formatData.format(dataAchizitie) +
                "," + cantitate +
                "," + pretUnitar +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achizitie achizitie = (Achizitie) o;
        return Objects.equals(codProdus, achizitie.codProdus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codProdus);
    }

    @Override
    public int compareTo(Achizitie o) {
        return Double.compare(this.valoare(), o.valoare());
    }
}


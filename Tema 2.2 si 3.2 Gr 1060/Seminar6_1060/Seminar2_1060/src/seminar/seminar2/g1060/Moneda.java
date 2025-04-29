package seminar.seminar2.g1060;
//Moneda - enumerare pentru valută. Constante: LEU(1), EURO(2), DOLAR(3),
// .... Fiecărei valute i se va
//asocia un cod: int codValuta. Câmpul codValuta va avea metode de acces.
public enum Moneda {
    LEU(1), EURO(2), DOLAR(3);
    private int codValuta;

    Moneda(int codValuta) {
        this.codValuta = codValuta;
    }

    public int getCodValuta() {
        return codValuta;
    }

    public void setCodValuta(int codValuta) {
        this.codValuta = codValuta;
    }
}

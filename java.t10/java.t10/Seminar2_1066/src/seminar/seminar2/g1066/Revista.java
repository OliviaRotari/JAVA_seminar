package seminar.seminar2.g1066;

//Revista - clasă utilizată pentru definire de reviste. Este extensie a clasei Publicatie. Câmpuri:
//String colectia; int aparitiiAn. Clasa va avea constructor fără parametri și constructor de
//inițializare pentru câmpurile menționate, metode de acces pentru câmpurile private, suprascriere
//pentru toString().

public class Revista extends Publicatie {
    private String colectia;
    private int aparitiiAn;

    public Revista() {
    }

    public Revista(String titlu, String cota, String editura, int anAparitie, double valoareInventar,
                   Domeniu[] domenii, String colectia, int aparitiiAn) throws Exception {
        super(titlu, cota, editura, anAparitie, valoareInventar, domenii);
        this.colectia = colectia;
        this.aparitiiAn = aparitiiAn;
    }

    public Revista(String cota) {
        super(cota);
    }

    // metode de acces
    public String getColectia() {
        return colectia;
    }

    public void setColectia(String colectia) {
        this.colectia = colectia;
    }

    public int getAparitiiAn() {
        return aparitiiAn;
    }

    public void setAparitiiAn(int aparitiiAn) {
        this.aparitiiAn = aparitiiAn;
    }

    @Override
    public String toString() {
        return super.toString()+"\nRevista{" +
                colectia +
                "," + aparitiiAn +
                "}";
    }
}

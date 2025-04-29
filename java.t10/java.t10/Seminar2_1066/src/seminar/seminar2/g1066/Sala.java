package seminar.seminar2.g1066;

// Cerinta: Definirea enumerarii Sala
//Sala - enumerare a sălilor de lectură cu locațiile acestora. Constante:
//VICTOR_SLAVESCU("Bibloteca Centrala"), GRIGORE_MOISIL("Facultatea de Matematica"),
//        ... . Fiecărei săli i se va asocia unitatea bibliotecii de care aparține: String unitate. Câmpul unitate
//va avea metode de acces.

public enum Sala {
    VICTOR_SLAVESCU("Bibloteca Centrala"),
    GRIGORE_MOISIL("Facultatea de Matematica");
    private String unitate;

    Sala(String unitate) {
        this.unitate = unitate;
    }

   // Metode de acces pentru unitate

    public String getUnitate() {
        return unitate;
    }

    public void setUnitate(String unitate) {
        this.unitate = unitate;
    }
}

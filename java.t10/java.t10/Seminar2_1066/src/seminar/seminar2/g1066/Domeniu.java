package seminar.seminar2.g1066;

// Cerinta: Definirea enumerarii Domeniu
//Domeniu - enumerare pentru domenii. Constante: ISTORIE(1), MATEMATICA(2), IT(3),
//GEOGRAFIE(4) ... . Fiecărui domeniu i se va asocia un cod de domeniu: int codDomeniu. Câmpul
//codDomeniu va avea metode de acces.

public enum Domeniu {
    ISTORIE(1), MATEMATICA(2),
    IT(3), GEOGRAFIE(4),STATISTICA(5);
    private int codDomeniu;

    Domeniu(int codDomeniu) {
        this.codDomeniu = codDomeniu;
    }

    // Metode de acces pentru codDomeniu

    public int getCodDomeniu() {
        return codDomeniu;
    }

    public void setCodDomeniu(int codDomeniu) {
        this.codDomeniu = codDomeniu;
    }
}

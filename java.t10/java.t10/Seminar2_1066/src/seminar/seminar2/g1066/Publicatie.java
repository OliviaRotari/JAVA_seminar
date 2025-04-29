package seminar.seminar2.g1066;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

// Cerinta: Definirea clasei abstracte Publicatie
//Publicatie - clasă abstractă pentru modelarea conceptului de publicație. Câmpuri: String titlu;
//        String cota, String editura; int anAparitie; double valoareInventar, Domeniu[] domenii. Clasa va
//avea constructor fără parametri și constructor de inițializare pentru câmpurile menționate (în
//                                                                                                    ordinea în care au fost menționate!), metode de acces pentru câmpurile private, suprascriere pentru
//toString(), implementare egalitate după cota și comparator după anAparitie.
//Constructorul de inițializare și metoda get pentru câmpul anAparitie vor arunca excepții pentru
//valori mai mari decât 2025.

public abstract class Publicatie implements Comparable<Publicatie>, Serializable {
    protected String titlu,cota,editura;
    protected int anAparitie;
    protected double valoareInventar;
    protected Domeniu[] domenii;

    public Publicatie() {
    }

    // Cerinta: Constructor de inițializare cu verificare anAparitie
    public Publicatie(String titlu, String cota, String editura,
                      int anAparitie, double valoareInventar, Domeniu[] domenii) throws Exception {
        // Cerinta: Verificare anAparitie < 2025
        if (anAparitie>2025){
            throw new Exception("An invalid!");
        }
        // Sfarsit cerinta: Verificare anAparitie < 2025
        this.titlu = titlu;
        this.cota = cota;
        this.editura = editura;
        this.anAparitie = anAparitie;
        this.valoareInventar = valoareInventar;
        this.domenii = domenii;
    }

    public Publicatie(String cota) {
        this.cota = cota;
    }

    // metode de acces (getter/setter)
    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getCota() {
        return cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public int getAnAparitie() {
        return anAparitie;
    }
    // Cerinta: Verificare anAparitie < 2025 la setare
    public void setAnAparitie(int anAparitie) throws Exception{
        if (anAparitie>2025){
            throw new Exception("An invalid!");
        }
        // Sfarsit cerinta: Verificare anAparitie < 2025 la setare
        this.anAparitie = anAparitie;
    }

    public double getValoareInventar() {
        return valoareInventar;
    }

    public void setValoareInventar(double valoareInventar) {
        this.valoareInventar = valoareInventar;
    }

    public Domeniu[] getDomenii() {
        return domenii;
    }

    public void setDomenii(Domeniu[] domenii) {
        this.domenii = domenii;
    }

    // suprascriere toString
    @Override
    public String toString() {
        return "Publicatie{" +
                titlu +
                "," + cota +
                "," + editura +
                "," + anAparitie +
                "," + valoareInventar +
                "," + Arrays.toString(domenii) +
                '}';
    }

    // suprascriere egalitate dupa cota
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publicatie that = (Publicatie) o;
        return cota.equals(that.cota);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cota);
    }

    // comparator dupa anAparitie
    @Override
    public int compareTo(Publicatie o) {
        return Integer.compare(anAparitie,o.anAparitie);
    }
}

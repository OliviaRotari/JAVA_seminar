package seminar.seminar2.g1066;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Cerinta: Definirea clasei Carte
//Carte - clasă utilizată pentru definire cărți. Este extensie de Publicatie și va implementa
//Operatiuni. Câmpuri: String[] autori, int nrPagini, Sala salaRezervare, Date dataReturnare.
//Câmpurile salaRezervare și dataReturnare sunt utilizate în procesul de rezervare la sală și
//împrumut. Acestea vor fi modificate prin implementarea interfeței Operațiuni. Clasa va avea
//constructor fără parametri și constructor de inițializare pentru câmpurile menționate (în ordinea în
//care au fost menționate!), metode de acces pentru câmpurile private, suprascriere pentru toString()
//și clonare profundă.

public class Carte extends Publicatie implements Cloneable,Operatiuni{
    private String[] autori;
    private int nrPagini;
    private Sala salaRezervare;
    private Date dataReturnare;

//    private int campNou;

    private static final long serialVersionUID=1L;

    public Carte() {
    }

    public Carte(String titlu, String cota, String editura,
                 int anAparitie, double valoareInventar,
                 Domeniu[] domenii, String[] autori, int nrPagini) throws Exception {
        super(titlu, cota, editura, anAparitie, valoareInventar, domenii);
        this.autori = autori;
        this.nrPagini = nrPagini;
    }

    public Carte(String cota) {
        super(cota);
    }

    public String[] getAutori() {
        return autori;
    }

    public void setAutori(String[] autori) {
        this.autori = autori;
    }

    public int getNrPagini() {
        return nrPagini;
    }

    public void setNrPagini(int nrPagini) {
        this.nrPagini = nrPagini;
    }

    public Sala getSalaRezervare() {
        return salaRezervare;
    }

    public void setSalaRezervare(Sala salaRezervare) {
        this.salaRezervare = salaRezervare;
    }

    public Date getDataReturnare() {
        return dataReturnare;
    }

    public void setDataReturnare(Date dataReturnare) {
        this.dataReturnare = dataReturnare;
    }

    @Override
    public String toString() {
        return super.toString()+"\nCarte{" +
                Arrays.toString(autori) +
                "," + nrPagini +
                "," + (salaRezervare==null?"":salaRezervare) +
                "," + (dataReturnare==null?"":Main.formatData.format(dataReturnare)) +
                "}";
    }

    // Cerinta: Clonare carte
    @Override
    public Object clone() throws CloneNotSupportedException {
        Carte clona=(Carte) super.clone();
        if (autori!=null) {
            clona.setAutori(autori.clone());
        }
        if (clona!=null) {
            clona.setDomenii(domenii.clone());
        }
        if (dataReturnare!=null) {
            clona.setDataReturnare((Date) dataReturnare.clone());
        }
        return clona;
    }

    // extindere autori
    public void addAutori(String autor){
        if (autori==null){
            autori = new String[0];
        }
        String[] autori = new String[this.autori.length+1];
        System.arraycopy(this.autori,0,autori,0,this.autori.length);
        autori[autori.length-1]=autor;
        this.autori=autori;
    }

    // implementare Operatiuni
    // Cerinta: Imprumut carte
    @Override
    public void imprumut(long nrZile) {
        if (dataReturnare==null && salaRezervare==null){
            Date dataCurenta = new Date();
            dataReturnare = new Date(dataCurenta.getTime()+ TimeUnit.DAYS.toMillis(nrZile));
        } else {
            System.out.println("Carte distribuita la sala sau imprumutata!");
        }

    }

    // Cerinta: Rezervare sala
    @Override
    public void rezervareSala(Sala numeSala) {
        if (dataReturnare==null && salaRezervare==null){
            salaRezervare = numeSala;
        } else {
            System.out.println("Carte distribuita la sala sau imprumutata!");
        }
    }

    // Cerinta: Returnare carte
    @Override
    public void returnare() {
        if (dataReturnare!=null){
            Date dataCurenta = new Date();
            if (dataReturnare.before(dataCurenta)){
                long intarziere = TimeUnit.MILLISECONDS.toDays
                        (dataCurenta.getTime()-dataReturnare.getTime());
                System.out.println("Intarziere "+intarziere+" zile!");
            }
            dataReturnare=null;
        } else {
            salaRezervare=null;
        }
    }
}

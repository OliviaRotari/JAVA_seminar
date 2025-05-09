package seminar.seminar2.g1066;

// Cerinta: Definirea interfetei Operatiuni
//Operatiuni - Interfață pentru implementarea operațiunilor de împrumut. Metode abstracte:
//void imprumut(long nrZile); // Va fi stabilită data returnării ca data curentă + nrZile
//void rezervareSala(Sala numeSala); // Se specifică sala la care este rezervată cartea
//void returnare(); //Sunt puse pe null campurile cu data returnării sau sala la care a fost rezervată

public interface Operatiuni {
    void imprumut(long nrZile); // Va fi stabilită data returnării ca data curentă + nrZile
    void rezervareSala(Sala numeSala); // Se specifică sala la care este rezervată cartea
    void returnare(); //Sunt puse pe null campurile cu data returnării sau sala la care a fost rezervată
}

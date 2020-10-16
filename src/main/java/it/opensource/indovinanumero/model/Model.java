package it.opensource.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

    private final int NMAX = 100;

    private final int TMAX = 8;

    private int numeroSegreto;

    // TODO in effetti il numero di tentivi effettuati si può dal numero di elementi in tentativiEffettuati
    private int numeroTentativiEffettuati;

    private boolean inGioco = false;

    private Set<Integer> tentativiEffettuati = new HashSet<>();

    public Model() {

        this.numeroSegreto = (int) (Math.random() * NMAX) + 1;
    }

    public int getTMAX() {

        return TMAX;
    }

    public int getNumeroSegreto() {

        return numeroSegreto;
    }

    public boolean isInGioco() {

        return inGioco;
    }

    public void setInGioco(boolean inGioco) {

        this.inGioco = inGioco;
    }

    public int getNumeroTentativiEffettuati() {

        return numeroTentativiEffettuati;
    }

    public void setNumeroTentativiEffettuati(int numeroTentativiEffettuati) {

        this.numeroTentativiEffettuati = numeroTentativiEffettuati;
    }

    public Set<Integer> getTentativiEffettuati() {

        return tentativiEffettuati;
    }

    /**
     * Inizializza nuova partita
     */
    public void init() {

        numeroSegreto = (int) (Math.random() * NMAX) + 1;
        numeroTentativiEffettuati = 0;
        inGioco = true;
        tentativiEffettuati.clear();
    }

    /**
     * Verifiva se il valore intero tentato e' valido
     *
     * @return boolean validita' del valore usato nel tentativo.
     */
    public int verificaTentativo(int valoreTentativo) {

        // verifica che la partita sia in corso
        if (!inGioco) {
            throw new IllegalStateException("La partita è già terminata\n");
        }

        // verifica che il valore dell'intero usato nel tentivo rientri nell'intervallo valido
        if (!tentativoValido(valoreTentativo)) {
            throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e " + NMAX + "\n");
        }

        // il valore del tentativo e' valido
        numeroTentativiEffettuati++;
        tentativiEffettuati.add(valoreTentativo);

        // se si e' raggiunto il numero massimo di tentativi la partita comunque termina
        if (numeroTentativiEffettuati == TMAX) {
            inGioco = false;
        }

        // se si e' indovinato il numero
        if (valoreTentativo == numeroSegreto) {
            inGioco = false;
            return 0;
        }

        if (valoreTentativo < numeroSegreto) {
            return -1; // valore inserito basso
        } else {
            return 1; // valore inserito alto
        }
    }

    /**
     * Verifica che il numero intero inserito sia valido
     *
     * @param valoreTentativo
     * @return boolean
     */
    private boolean tentativoValido(int valoreTentativo) {

        if (valoreTentativo < 1 || valoreTentativo > NMAX) {
            return false;
        } else {
            if (this.tentativiEffettuati.contains(valoreTentativo)) {
                return false;
            }
            return true;
        }
    }
}

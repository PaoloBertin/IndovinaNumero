package it.opensource.indovinanumero.model;

import org.apache.log4j.Logger;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Business logic dell'applicazione
 */
public class Model {

    private static final Logger log = Logger.getLogger(Model.class.getName());

    // minimo intervallo valori interi da indovinare
    private static final int NMIN = 1;

    /**
     * massimo intervallo dei valori interi da indovinare
     */
    private static final int NMAX = 100;

    /**
     * numero massimo tentativi
     */
    private static final int TMAX = 8;

    /**
     * Collezione tentativi gia' effettuati
     */
    private final Set<Integer> tentativiGiaEffettuati = new HashSet<>();

    /**
     * Numero segreto da indovinare
     */
    private int numeroSegreto;

    /**
     * E' attiva una partita
     */
    private boolean inGioco = false;

    /**
     * Inizializza una nuova partita
     */
    public Model() {

        // init();
    }

    public int getNMIN() {

        return NMIN;
    }

    public int getNMAX() {

        return NMAX;
    }

    public int getTMAX() {

        return TMAX;
    }

    public Set<Integer> getTentativiGiaEffettuati() {

        return tentativiGiaEffettuati;
    }

    public int getNumeroSegreto() {

        return numeroSegreto;
    }

    public void setNumeroSegreto(int numeroSegreto) {

        this.numeroSegreto = numeroSegreto;
    }

    public int getNumeroTentativiEffettuati() {

        return tentativiGiaEffettuati.size();
    }

    public boolean isInGioco() {

        return inGioco;
    }

    public void setInGioco(boolean inGioco) {

        this.inGioco = inGioco;
    }

    public int getNumeroTentiviRimasti() {

        return TMAX - tentativiGiaEffettuati.size();
    }

    /**
     * Inizializza lo stato del gioco
     */
    public void init() {

        Random random = new Random();
        this.numeroSegreto = random.nextInt(NMAX) + NMIN;
        this.inGioco = true;
        this.tentativiGiaEffettuati.clear();

        log.info("inizializzato modello per nuova partita");
        log.debug("             numero segreto = " + numeroSegreto);
        log.debug("                  in giooco = " + inGioco);

    }

    /**
     * Verifica se il valore inserito corrisponde al valore da indovinare
     *
     * @param valoreTentativo valore inserito dal giocatore
     * @return esito confronto fra valore valore del tentativo e valore da indovinare
     */
    public int verificaTentativo(int valoreTentativo) {

        tentativiGiaEffettuati.add(valoreTentativo);

        // TODO non e' compito di questo metodo
        // verifica che la partita sia in corso
        if (!isInGioco()) {
            throw new IllegalStateException("La partita è già terminata\n");
        }

        // il valore del tentativo e' aggiungiunto ai tentativi gia' effettuati
        tentativiGiaEffettuati.add(valoreTentativo);

        // TODO non e' compito di questo metodo
        // se si e' raggiunto il numero massimo di tentativi la partita comunque termina
        if (tentativiGiaEffettuati.size() == TMAX) {
            //inGioco = false;
            setInGioco(false);
        }

        // se si e' indovinato il numero
        if (valoreTentativo == numeroSegreto) {
            log.debug("il valore " + valoreTentativo + " e' il numero segreto");
            inGioco = false;
            return 0;
        }

        log.debug("il valore di tentativo " + valoreTentativo + " non e' il valore da indovinare");

        if (valoreTentativo < numeroSegreto) {
            return -1; // valore inserito basso
        } else {
            return 1; // valore inserito alto
        }
    }

    /**
     * Verifica che il numero intero inserito sia valido
     * <p>
     * Il valore inserito n e' valido se:
     * a) NMIN &lt; n &lt; NMAX
     * b) n non e' stato utilizzato (non appartiene a tentiviEffettuati)
     *
     * @param valoreTentativo tentativo soluzione
     * @return boolean il tentativo e' valido
     */
    public boolean tentativoValido(int valoreTentativo) {

        if ((valoreTentativo < 1) || (valoreTentativo > getNMAX())) {
            log.debug("il valore di tentativo " + valoreTentativo + " non e' valido");
            return false;
        } else {
            if (tentativiGiaEffettuati.contains(valoreTentativo)) {
                return false;
            }
            return true;
        }
    }
}

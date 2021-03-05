package it.opensource.indovinanumero.controller;

import it.opensource.indovinanumero.model.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.log4j.Logger;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    private static final Logger log = Logger.getLogger(FXMLController.class.getName());

    private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtMessaggio;

    @FXML
    private Label lblNumeroTentativiRimasti;

    @FXML
    private Button btnProva;

    @FXML
    private TextField txtTentativo;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private ProgressBar pgbTentativi;

    @FXML
    void doNuovaPartita(ActionEvent event) {

        // model.init();
        // inizializza l'interfaccia
        // txtMessaggio.clear();
        // lblNumeroTentativiRimasti.setText(Integer.toString(model.getNumeroTentiviRimasti()));
        // txtTentativo.clear();
        inizializzaNuovaPartita();
    }

    // TODO il metodo fa due compiti
    // verifica l'input del giocatore
    // verifica se si e' indovinato il valore correttto
    @FXML
    void doTentativo(ActionEvent event) {

        //lettura tentativo utente
        int valoreTentativo;
        try {
            valoreTentativo = Integer.parseInt(txtTentativo.getText());
            // verifica che il valore inserito sia valido (NMIN < n < NMAX e n diverso dai valori precedenti)
            if (!model.tentativoValido(valoreTentativo)) {
                txtMessaggio.appendText("Inserire un numero intero tra " + model.getNMIN() + " e " + model.getNMAX() + "\n");
                return;
            }
        } catch (NumberFormatException e) {
            txtMessaggio.appendText("Inserire un numero intero tra " + model.getNMIN() + " e " + model.getNMAX() + "\n");
            log.debug("tentativo del giocatore = " + txtTentativo.getText());
            return;
        }


        int esitoTentativo = model.verificaTentativo(valoreTentativo);
        if (esitoTentativo == 0) {
            txtMessaggio.appendText("Hai vinto! Hai effettuato " + model.getNumeroTentativiEffettuati() + " tentativi");
            btnProva.setDisable(true);
        } else if (esitoTentativo == -1) {
            txtMessaggio.appendText("valore " + valoreTentativo + " troppo BASSO\n");
        } else if (esitoTentativo == 1) {
            txtMessaggio.appendText("valore " + valoreTentativo + " troppo ALTO\n");
        }
        var percentuale = model.getPercentualeTentativiEffettuati();
        pgbTentativi.setProgress(percentuale);
        lblNumeroTentativiRimasti.setText(Integer.toString(model.getNumeroTentiviRimasti()));
    }

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        assert txtMessaggio != null : "fx:id=\"txtMessagio\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblNumeroTentativiRimasti != null : "fx:id=\"lblNumeroTentativiRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert pgbTentativi != null : "fx:id=\"pgbTentativi\" was not injected: check your FXML file 'Scene.fxml'.";

        log.debug("inizializzato controller");
    }

    public void setModel(Model model) {

        this.model = model;
        inizializzaNuovaPartita();
    }

    private void inizializzaNuovaPartita() {

        // inizializza modello
        model.init();

        // inizializza l'interfaccia
        btnProva.setDisable(false);
        txtMessaggio.clear();
        lblNumeroTentativiRimasti.setText(Integer.toString(model.getNumeroTentiviRimasti()));
        txtTentativo.clear();
        pgbTentativi.setProgress(0);
    }
}


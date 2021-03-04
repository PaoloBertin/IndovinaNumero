package it.opensource.indovinanumero.controller;

import it.opensource.indovinanumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    /**
     * @param event nuova partita
     */
    @FXML
    void doNuova(ActionEvent event) {

        model.init();

        //gestione dell'interfaccia
        layoutTentativo.setDisable(false);
        txtRisultato.clear();
        txtRimasti.setText(Integer.toString(model.getTMAX()));
        txtTentativi.clear();
    }

    @FXML
    void doTentativo(ActionEvent event) {
        //leggere l'input dell'utente
        String ts = txtTentativi.getText();
        int valoreTentativo;
        try {
            valoreTentativo = Integer.parseInt(ts);
        } catch (NumberFormatException e) {
            txtRisultato.appendText("Devi inserire un numero!\n");
            return;
        }

        // esitoTentativo = 0   ->  valore tentativo corretto
        // esitoTentativo = -1  ->  valore tentativo troppo basso
        // esitoTentativo = 1   ->  valore tentativo troppo alto
        int esitoTentativo = -1;

        try {
            esitoTentativo = model.verificaTentativo(valoreTentativo);
        } catch (IllegalStateException se) {
            txtRisultato.appendText(se.getMessage());
            return;
        } catch (InvalidParameterException parameterException) {
            txtRisultato.appendText(parameterException.getMessage());
            return;
        }

        if (esitoTentativo == 0) {
            txtRisultato.appendText("Hai vinto! Hai effettuato " + model.getNumeroTentativiEffettuati() + " tentativi");
        }else if(esitoTentativo == -1) {
            txtRisultato.appendText("valore " + valoreTentativo +  " troppo BASSO\n");
        }else if (esitoTentativo == 1) {
            txtRisultato.appendText("valore " + valoreTentativo +  " troppo ALTO\n");
        }

        txtRimasti.setText(Integer.toString(model.getTMAX() - model.getNumeroTentativiEffettuati()));
    }

    public void setModel(Model model) {

        this.model = model;
    }

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

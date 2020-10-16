package it.opensource.indovinanumero;

import it.opensource.indovinanumero.controller.FXMLController;
import it.opensource.indovinanumero.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // carica la vista e la assegna al nodo radice
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = loader.load();

        // crea la scena
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/styles.css");

        // costruisce il controller associato alla vista
        FXMLController controller;
        controller = loader.getController();

        // definisce il modello associato alla vista // TODO NOOOOOOO !!! accoppia tecnologia UI con dominio applicazione
        Model model = new Model();
        controller.setModel(model); // TODO spostare l'inizializzzazione del modello in FXMLController

        // definisce la finestra
        stage.setTitle("Indovina Numero");
        stage.setScene(scene);
        stage.show();
    }
}

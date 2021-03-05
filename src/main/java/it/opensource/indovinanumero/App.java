package it.opensource.indovinanumero;

import it.opensource.indovinanumero.controller.FXMLController;
import it.opensource.indovinanumero.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class App extends Application {

    private static final Logger log = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        log.trace("main applicazione");

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        log.trace("start applicazione");

        // carica la vista e la assegna al nodo radice
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = loader.load();

        // crea la scena
        Scene scene = new Scene(root);
        // carica il foglio di stile associato alla vista
        scene.getStylesheets()
             .add("/styles/styles.css");

        // costruisce il controller associato alla vista
        FXMLController fxmlController;
        fxmlController = loader.getController();

        // crea il modello e lo rende disponibile al controller della vista
        // il modello e' creato qui in modo sia visibile, in generale, da piu' controller
        Model model = new Model();
        fxmlController.setModel(model);
        log.trace("iniettato model in fxmlController");

        // definisce la finestra
        stage.setTitle("Indovina Numero");
        stage.setScene(scene);
        stage.show();
    }

}

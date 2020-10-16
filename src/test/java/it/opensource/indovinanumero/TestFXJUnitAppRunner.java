package it.opensource.indovinanumero;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeoutException;

public class TestFXJUnitAppRunner extends ApplicationTest {

    private Button button;

    @BeforeEach
    public void setUp() throws Exception {

        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(App::new);
        FxToolkit.showStage();
        WaitForAsyncUtils.waitForFxEvents(100);
    }

    @AfterEach
    public void tearDown() throws TimeoutException {

        FxToolkit.cleanupStages();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.toFront();
    }

}
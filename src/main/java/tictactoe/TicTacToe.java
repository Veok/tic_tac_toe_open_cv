package tictactoe;

import tictactoe.controller.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.opencv.core.Core;


public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/TicTacToeFX.fxml"));
        FXMLLoader fxmlLoader1 = new FXMLLoader(TicTacToe.class.getClassLoader().getResource("fxml/TicTacToeFX.fxml"));
        primaryStage.setTitle("tictactoe");
        primaryStage.setScene(new Scene(fxmlLoader1.load(), 960, 625));
        primaryStage.setResizable(false);
        primaryStage.show();

        ViewController viewController = fxmlLoader1.getController();

        Button button = viewController.resetButton;
        button.setOnAction(event -> viewController.reset());

        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            if (viewController.getCameraController().isRunning()) {
                viewController.getCameraController().turnOff();
                viewController.getCameraController().initializeCamera(viewController);
            }
            System.exit(0);
        });

    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }
}

package TicTacToe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


public class ViewController {


    @FXML
    private ImageView frame;

    private CameraController cameraController = new CameraController();

    @FXML
    public Button resetButton;


    public Button reset(){
        resetButton.setOnAction(event -> cameraController.getGameController().restartGame() );
        return resetButton;
    }

    public ViewController() {
        cameraController.initializeCamera(this);
    }

    public CameraController getCameraController() {
        return cameraController;
    }

    ImageView getFrame() {
        return frame;
    }


}

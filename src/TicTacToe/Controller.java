package TicTacToe;

import TicTacToe.service.CameraService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

//TODO add reset button functionality + points view
public class Controller {

    public Button resetButton;
    @FXML
    private ImageView frame;

    private CameraService cameraService = new CameraService();

    public Controller() {
        cameraService.initializeCamera(this);
    }

    public CameraService getCameraService() {
        return cameraService;
    }

    public ImageView getFrame() {
        return frame;
    }

    public Button getResetButton() {
        return resetButton;
    }
}

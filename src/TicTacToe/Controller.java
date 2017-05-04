package TicTacToe;

import TicTacToe.service.CameraService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


public class Controller {



    @FXML
    private ImageView frame;

    private CameraService cameraService = new CameraService();

    @FXML
    public Button resetButton;

    public Controller() {
        cameraService.initializeCamera(this);
    }

    public CameraService getCameraService() {
        return cameraService;
    }

    public ImageView getFrame() {
        return frame;
    }


}

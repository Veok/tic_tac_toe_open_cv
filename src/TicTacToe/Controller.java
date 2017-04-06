package TicTacToe;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.opencv.core.*;

import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static TicTacToe.VideoHandler.toFxImage;

public class Controller {

    @FXML
    private ImageView frame;

    private VideoCapture videoCapture = new VideoCapture();
    private ScheduledExecutorService timer;
    private Mat mat = new Mat();
    private boolean isRunning;

    public Controller() {
        this.isRunning = true;
        initializeCamera(this);
    }

    void initializeCamera(Controller controller) {
        videoCapture.open(0);
        if (isRunning) {
            Runnable runnable = () -> {
                Mat image = getMat();
                toFxImage(image);
                VideoHandler.onFXThread(controller.getFrame().imageProperty(), toFxImage(image));

            };
            timer = Executors.newSingleThreadScheduledExecutor();
            timer.scheduleAtFixedRate(runnable, 0, 33, TimeUnit.MILLISECONDS);
        } else {
            timer.shutdown();
            videoCapture.release();
        }
    }

    private Mat getMat() {
        videoCapture.read(mat);
        Core.flip(mat, mat, 1);
        return mat;
    }
    
    private ImageView getFrame() {
        return frame;
    }

    boolean isRunning() {
        return isRunning;
    }

    void turnOff() {
        isRunning = false;
    }
}

package TicTacToe;

import TicTacToe.service.video.CameraService;
import TicTacToe.service.video.VideoService;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.opencv.core.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static TicTacToe.service.video.CameraService.toFxImage;


public class Controller {

    @FXML
    private ImageView frame;

    private VideoService videoService = new VideoService();
    private ScheduledExecutorService timer;
    private boolean isRunning;


    public Controller() {
        this.isRunning = true;
        initializeCamera(this);
    }

    void initializeCamera(Controller controller) {
        videoService.getVideoCapture().open(0);
        videoService.getVideoCapture().set(VideoService.CAMERA_WIDTH_ID, VideoService.CAMERA_WIDTH);
        videoService.getVideoCapture().set(VideoService.CAMERA_HEIGHT_ID, VideoService.CAMERA_HEIGHT);

        if (isRunning) {
            Runnable runnable = () -> {
                Mat image = videoService.getMat();
                CameraService.toFxImage(image);
                //detectCircle();
                videoService.drawGameBoard();
                CameraService.onFXThread(controller.getFrame().imageProperty(), toFxImage(image));
            };
            timer = Executors.newSingleThreadScheduledExecutor();
            timer.scheduleAtFixedRate(runnable, 0, 33, TimeUnit.MILLISECONDS);
        } else {
            timer.shutdown();
            videoService.getVideoCapture().release();
        }
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

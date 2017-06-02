package tictactoe.controller;

import tictactoe.service.camera.CameraService;
import tictactoe.service.video.VideoService;
import org.opencv.core.Mat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Lelental on 06.04.2017.
 */
public class CameraController extends CameraService {

    private final int CAMERA_ID = 0;
    private final static int CAMERA_WIDTH_ID = 3;
    private static int CAMERA_HEIGHT_ID = 4;

    private GameController game;
    private VideoController video;
    private ScheduledExecutorService timer;
    private boolean isRunning;

    CameraController() {
        this.video = new VideoController();
        this.game = new GameController(video);
        this.isRunning = true;
    }

    public void initializeCamera(ViewController viewController) {

        video.capture().open(CAMERA_ID);
        video.capture().set(CAMERA_WIDTH_ID, VideoService.CAMERA_WIDTH);
        video.capture().set(CAMERA_HEIGHT_ID, VideoService.CAMERA_HEIGHT);

        if (isRunning) {
            Runnable runnable = () -> {
                Mat image = video.getCapturedFrame();
                toFxImage(image);
                game.startGame();
                onFXThread(viewController.getFrame().imageProperty(), toFxImage(image));
            };
            timer = Executors.newSingleThreadScheduledExecutor();
            timer.scheduleAtFixedRate(runnable, 0, 33, TimeUnit.MILLISECONDS);

        } else {
            timer.shutdown();
            video.capture().release();
        }
    }


    public boolean isRunning() {
        return isRunning;
    }

    public void turnOff() {
        isRunning = false;
    }

    GameController getGame() {
        return game;
    }
}

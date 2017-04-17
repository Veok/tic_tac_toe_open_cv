package TicTacToe.service;


import TicTacToe.Controller;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.opencv.core.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Lelental on 06.04.2017.
 */
public class CameraService {

    private final int CAMERA_ID = 0;

    private VideoService videoService;
    private ScheduledExecutorService timer;
    private boolean isRunning;

    public CameraService() {
        this.videoService = new VideoService();
        this.isRunning = true;
    }


    public void initializeCamera(Controller controller) {

        videoService.getVideoCapture().open(CAMERA_ID);
        videoService.getVideoCapture().set(VideoService.CAMERA_WIDTH_ID, VideoService.CAMERA_WIDTH);
        videoService.getVideoCapture().set(VideoService.CAMERA_HEIGHT_ID, VideoService.CAMERA_HEIGHT);

        if (isRunning) {
            Runnable runnable = () -> {
                Mat image = videoService.getMat();
                CameraService.toFxImage(image);
                videoService.paintGameBoard();
                videoService.detectCircle();
                videoService.paintNought();
                CameraService.onFXThread(controller.getFrame().imageProperty(), toFxImage(image));
            };
            timer = Executors.newSingleThreadScheduledExecutor();
            timer.scheduleAtFixedRate(runnable, 0, 33, TimeUnit.MILLISECONDS);
        } else {
            timer.shutdown();
            videoService.getVideoCapture().release();
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void turnOff() {
        isRunning = false;
    }

    private static BufferedImage matToBufferedImage(Mat original) {
        BufferedImage image;
        int width = original.width(), height = original.height(), channels = original.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        original.get(0, 0, sourcePixels);

        if (original.channels() > 1) {
            image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        } else {
            image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        }
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);

        return image;
    }

    private static Image toFxImage(Mat mat) {
        return SwingFXUtils.toFXImage(matToBufferedImage(mat), null);
    }

    private static <T> void onFXThread(final ObjectProperty<T> property, final T value) {
        Platform.runLater(() -> property.set(value));
    }


}

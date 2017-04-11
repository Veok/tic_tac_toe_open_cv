package TicTacToe;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;

import org.opencv.imgproc.Imgproc;
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
                findBoard();
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

    private void findBoard() {
        Size sizeOfBoard = new Size(4, 4);
        MatOfPoint2f points = new MatOfPoint2f();

        final boolean chessboardCorners = Calib3d.findChessboardCorners(getMat(), sizeOfBoard, points);

        if (chessboardCorners) {
            Mat gray = new Mat();
            Imgproc.cvtColor(getMat(),gray, Imgproc.COLOR_BGR2GRAY);
            Imgproc.cornerSubPix(gray, points, new Size(11, 11), new Size(-1, 1),
                    new TermCriteria(TermCriteria.EPS + TermCriteria.MAX_ITER, 30, 0.1));
            Calib3d.drawChessboardCorners(getMat(), sizeOfBoard, points, true);
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

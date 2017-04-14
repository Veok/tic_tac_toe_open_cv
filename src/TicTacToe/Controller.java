package TicTacToe;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
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
    private Mat gray = new Mat();
    private boolean isRunning;

    private final int CAMERA_WIDTH_ID = 3;
    private final int CAMERA_HEIGHT_ID = 4;
    private final int CAMERA_WIDTH = 960;
    private final int CAMERA_HEIGHT = 540;


    public Controller() {
        this.isRunning = true;
        initializeCamera(this);
    }

    void initializeCamera(Controller controller) {
        videoCapture.open(0);
        videoCapture.set(CAMERA_WIDTH_ID, CAMERA_WIDTH);
        videoCapture.set(CAMERA_HEIGHT_ID, CAMERA_HEIGHT);
        if (isRunning) {
            Runnable runnable = () -> {
                Mat image = getMat();
                toFxImage(image);
                //detectCircle();
                drawGameBoard();
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


    private void detectCircle() {
        Imgproc.cvtColor(getMat(), gray, Imgproc.COLOR_RGBA2GRAY);
        Imgproc.GaussianBlur(gray, gray, new Size(9, 9), 2, 2);
        Mat circles = new Mat();
        Imgproc.HoughCircles(gray, circles, Imgproc.CV_HOUGH_GRADIENT, 2, gray.rows() / 8, 100, 100, 20, 400);
        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            Point pt = new Point(Math.round(circle[0]), Math.round(circle[1]));
            int radius = (int) Math.round(circle[2]);
            Imgproc.circle(mat, pt, radius, new Scalar(0, 255, 0), 1);
            Imgproc.circle(mat, pt, radius, new Scalar(0, 0, 255), 2);

        }
    }

    private void drawGameBoard() {

        Scalar scalar = new Scalar(54, 69, 79);
        int thickness = 11;

        Imgproc.line(mat, new Point(CAMERA_WIDTH / 3, 0),
                new Point(CAMERA_WIDTH / 3, CAMERA_HEIGHT), scalar, thickness);

        Imgproc.line(mat, new Point(CAMERA_WIDTH / 3 * 2, 0),
                new Point(CAMERA_WIDTH / 3 * 2, CAMERA_HEIGHT), scalar, thickness);

        Imgproc.line(mat, new Point(0, CAMERA_HEIGHT / 3),
                new Point(CAMERA_WIDTH, CAMERA_HEIGHT / 3), scalar, thickness);

        Imgproc.line(mat, new Point(0, CAMERA_HEIGHT / 3 * 2),
                new Point(CAMERA_WIDTH, CAMERA_HEIGHT / 3 * 2), scalar, thickness);

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

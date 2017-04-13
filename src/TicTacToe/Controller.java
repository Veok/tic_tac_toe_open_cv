package TicTacToe;


import com.sun.javafx.geom.Vec3f;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static TicTacToe.VideoHandler.toFxImage;
import static java.lang.Math.*;
import static org.opencv.imgproc.Imgproc.*;
import static org.opencv.imgproc.Imgproc.HoughLines;
import static org.opencv.imgproc.Imgproc.HoughLinesP;

public class Controller {

    @FXML
    private ImageView frame;

    private VideoCapture videoCapture = new VideoCapture();
    private ScheduledExecutorService timer;
    private Mat mat = new Mat();
    private Mat gray = new Mat();
    private boolean isRunning;
    private Size sizeOfBoard = new Size(4, 4);
    private MatOfPoint2f points = new MatOfPoint2f();


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
                //findBoard();
                detectCircle();
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
        boolean boardCorners = Calib3d.findChessboardCorners(getMat(), sizeOfBoard, points);

        if (boardCorners) {
            cvtColor(getMat(), gray, COLOR_RGBA2GRAY);
            cornerSubPix(gray, points, new Size(11, 11), new Size(-1, 1),
                    new TermCriteria(TermCriteria.EPS + TermCriteria.MAX_ITER, 30, 0.1));
            Calib3d.drawChessboardCorners(getMat(), sizeOfBoard, points, true);
        }

    }

    private void detectCircle() {
        cvtColor(getMat(), gray, COLOR_RGBA2GRAY);
        GaussianBlur(gray, gray, new Size(9, 9), 2, 2);
        Mat circles = new Mat();
        HoughCircles(gray, circles, CV_HOUGH_GRADIENT, 2, gray.rows() / 8, 100, 100, 20, 400);
        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            Point pt = new Point(Math.round(circle[0]), Math.round(circle[1]));
            int radius = (int) Math.round(circle[2]);
            Imgproc.circle(getMat(), pt, radius, new Scalar(0, 255, 0), 1);
            Imgproc.circle(getMat(), pt, radius, new Scalar(0, 0, 255), 2);

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

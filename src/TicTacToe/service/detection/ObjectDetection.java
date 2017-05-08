package TicTacToe.service.detection;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

/**
 * @author Lelental on 05.05.2017.
 */
public class ObjectDetection implements ICanBeDetected {

    private Mat gray;
    private Mat hsv;
    private Mat mat;
    private Point detectedPoint;
    private Mat circles = new Mat();

    ObjectDetection(Mat mat) {
        this.gray = new Mat();
        this.hsv = new Mat();
        this.mat = mat;

    }

    @Override
    public void detect() {

        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGBA2GRAY);
        Imgproc.cvtColor(mat, hsv, Imgproc.COLOR_RGB2HSV);
        Imgproc.GaussianBlur(gray, gray, new Size(9, 9), 2, 2);

        Core.inRange(hsv, new Scalar(0, 100, 100), new Scalar(10, 255, 255), circles);
        Imgproc.HoughCircles(gray, circles, Imgproc.CV_HOUGH_GRADIENT,
                2, gray.rows() / 8, 90, 100, 20, 60);

        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            detectedPoint = new Point(Math.round(circle[0]), Math.round(circle[1]));
        }
    }


    public Point getDetectedPoint() {
        return detectedPoint;
    }


    void resetDetection() {
        this.detectedPoint = null;
    }
}

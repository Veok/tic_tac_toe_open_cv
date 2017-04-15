package TicTacToe.service.video;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

/**
 * @author Lelental on 14.04.2017.
 */
public class VideoService {

    final static int CAMERA_WIDTH_ID = 3;
    final static int CAMERA_HEIGHT_ID = 4;
    final static int CAMERA_WIDTH = 960;
    final static int CAMERA_HEIGHT = 540;

    private Mat mat = new Mat();
    private Mat gray = new Mat();
    private Mat hsv = new Mat();

    private VideoCapture videoCapture = new VideoCapture();

    public VideoService() {
    }

    public void detectCircle() {

        Mat circles = new Mat();

        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGBA2GRAY);
        Imgproc.cvtColor(mat, hsv, Imgproc.COLOR_RGB2HSV);
        Imgproc.GaussianBlur(gray, gray, new Size(9, 9), 2, 2);
//TODO find bgr codes for blue
        Core.inRange(hsv, new Scalar(160, 100, 100), new Scalar(179, 255, 255), circles);

        Imgproc.HoughCircles(gray, circles, Imgproc.CV_HOUGH_GRADIENT, 2, gray.rows() / 8, 100, 100, 20, 80);

        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            Point pt = new Point(Math.round(circle[0]), Math.round(circle[1]));
            int radius = (int) Math.round(circle[2]);
            Imgproc.circle(mat, pt, radius, new Scalar(0, 255, 0), 1);
            Imgproc.circle(mat, pt, radius, new Scalar(0, 0, 255), 2);

        }
    }

    public void drawGameBoard() {

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

    public Mat getMat() {
        videoCapture.read(mat);
        Core.flip(mat, mat, 1);
        return mat;
    }

    public VideoCapture getVideoCapture() {
        return videoCapture;
    }
}

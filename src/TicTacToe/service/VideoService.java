package TicTacToe.service;

import TicTacToe.model.Cell;
import org.opencv.core.*;
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

    private Mat mat;
    private Mat gray;
    private Mat hsv;
    private VideoCapture videoCapture;
    private Point pointOfCircle;
    private CellService cellService;

    public VideoService() {
        this.mat = new Mat();
        this.gray = new Mat();
        this.hsv = new Mat();
        this.videoCapture = new VideoCapture();
        this.cellService = new CellService();
    }

    public void detectCircle() {

        Mat circles = new Mat();

        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGBA2GRAY);
        Imgproc.cvtColor(mat, hsv, Imgproc.COLOR_RGB2HSV);
        Imgproc.GaussianBlur(gray, gray, new Size(9, 9), 2, 2);

        Core.inRange(hsv, new Scalar(252, 179, 82), new Scalar(227, 141, 31), circles);
        Imgproc.HoughCircles(gray, circles, Imgproc.CV_HOUGH_GRADIENT, 2, gray.rows() / 8, 90, 100, 20, 60);

        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            Point pt = new Point(Math.round(circle[0]), Math.round(circle[1]));
            setPointOfCircle(pt);
        }
    }

    public void paintGameBoard() {

        Scalar scalar = new Scalar(54, 69, 79);
        int lineThickness = 11;

        Imgproc.line(mat, new Point(CAMERA_WIDTH / 3, 0),
                new Point(CAMERA_WIDTH / 3, CAMERA_HEIGHT), scalar, lineThickness);

        Imgproc.line(mat, new Point(CAMERA_WIDTH / 3 * 2, 0),
                new Point(CAMERA_WIDTH / 3 * 2, CAMERA_HEIGHT), scalar, lineThickness);

        Imgproc.line(mat, new Point(0, CAMERA_HEIGHT / 3),
                new Point(CAMERA_WIDTH, CAMERA_HEIGHT / 3), scalar, lineThickness);

        Imgproc.line(mat, new Point(0, CAMERA_HEIGHT / 3 * 2),
                new Point(CAMERA_WIDTH, CAMERA_HEIGHT / 3 * 2), scalar, lineThickness);

    }

    public void paintNought() {

        if (getPointOfCircle() != null) {

            int noughtThickness = 4;
            double x = getPointOfCircle().x;
            double y = getPointOfCircle().y;

            for (Cell s : cellService.getListOfCells()) {
                if (s.isPainted()) {
                    Imgproc.circle(mat, s.getCenterPoint(), 20, new Scalar(222, 1, 34), noughtThickness);
                }
                if (x > s.getMinX() && x < s.getMaxX() && y > s.getMinY() && y < s.getMaxY()) {
                    s.setPainted();
                }
            }
        }
    }

    public void drawCross() {
        //Imgproc.line(mat,new Point(s.getCenterPoint().x-40,s.getCenterPoint().y+45),new Point(s.getCenterPoint().x+40,s.getCenterPoint().y-45), new Scalar(231,31,3),3);
        //Imgproc.line(mat,new Point(s.getCenterPoint().x-40,s.getCenterPoint().y-45),new Point(s.getCenterPoint().x+40,s.getCenterPoint().y+45), new Scalar(231,31,3),3);
    }

    public Mat getMat() {
        videoCapture.read(mat);
        Core.flip(mat, mat, 1);
        return mat;
    }

    public Point getPointOfCircle() {
        return pointOfCircle;
    }

    public void setPointOfCircle(Point pointOfCircle) {
        this.pointOfCircle = pointOfCircle;
    }

    public VideoCapture getVideoCapture() {
        return videoCapture;
    }
}

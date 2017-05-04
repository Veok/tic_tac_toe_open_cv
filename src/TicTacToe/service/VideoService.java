package TicTacToe.service;

import TicTacToe.model.Cell;
import TicTacToe.model.Mark;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.List;

/**
 * @author Lelental on 14.04.2017.
 */
//TODO simplify
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
    private static Point startPoint;
    private static Point endPoint;

    public VideoService() {
        this.mat = new Mat();
        this.gray = new Mat();
        this.hsv = new Mat();
        this.videoCapture = new VideoCapture();
        this.cellService = new CellService();
    }

    //TODO refactor to DetectionService
    public void detectCircle() {

        Mat circles = new Mat();

        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGBA2GRAY);
        Imgproc.cvtColor(mat, hsv, Imgproc.COLOR_RGB2HSV);
        Imgproc.GaussianBlur(gray, gray, new Size(9, 9), 2, 2);

        Core.inRange(hsv, new Scalar(0, 100, 100), new Scalar(10, 255, 255), circles);
        Imgproc.HoughCircles(gray, circles, Imgproc.CV_HOUGH_GRADIENT,
                2, gray.rows() / 8, 90, 100, 20, 60);

        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            Point pt = new Point(Math.round(circle[0]), Math.round(circle[1]));
            setPointOfCircle(pt);
        }
    }

    //TODO refactor to PaintService
    public void paintGameBoard() {

        int lineThickness = 11;
        Scalar boardColor = new Scalar(54, 69, 79);

        Imgproc.line(mat, new Point(CAMERA_WIDTH / 3, 0),
                new Point(CAMERA_WIDTH / 3, CAMERA_HEIGHT), boardColor, lineThickness);

        Imgproc.line(mat, new Point(CAMERA_WIDTH / 3 * 2, 0),
                new Point(CAMERA_WIDTH / 3 * 2, CAMERA_HEIGHT), boardColor, lineThickness);

        Imgproc.line(mat, new Point(0, CAMERA_HEIGHT / 3),
                new Point(CAMERA_WIDTH, CAMERA_HEIGHT / 3), boardColor, lineThickness);

        Imgproc.line(mat, new Point(0, CAMERA_HEIGHT / 3 * 2),
                new Point(CAMERA_WIDTH, CAMERA_HEIGHT / 3 * 2), boardColor, lineThickness);

    }

    //TODO simplify conditions
    public void paintNought() {

        if (getPointOfCircle() != null) {

            int noughtThickness = 4;
            double x = getPointOfCircle().x;
            double y = getPointOfCircle().y;
            Scalar noughtColor = new Scalar(222, 1, 34);

            for (Cell cell : cellService.getListOfCells()) {

                if (cell.isPainted() && cell.getMark() != Mark.Cross) {
                    Imgproc.circle(mat, cell.getCenterPoint(), 20, noughtColor, noughtThickness);
                }

                if (x > cell.getMinX() && x < cell.getMaxX() && y > cell.getMinY() && y < cell.getMaxY()
                        && cell.getMark() != Mark.Cross && cell.getMark() != Mark.Nought) {

                    cell.setPainted();
                    cell.setMark(Mark.Nought);
                    cellService.cellArray(cell.getRow(), cell.getColumn(), cell.getMark());
                    AIService.turn++;
                }
            }
        }
    }

    //TODO simplify conditions
    public void paintCross() {

        int crossThickness = 11;
        Scalar crossColor = new Scalar(231, 31, 3);

        if (AIService.turn > 0) {

            AIService aiService = new AIService(cellService.getListOfCells());
            aiService.makeMove();
            Cell cell2 = cellService.getListOfCells().get(aiService.getCellId());

            for (Cell cell : cellService.getListOfCells()) {

                if (cell.isPainted() && cell.getMark() != Mark.Nought) {

                    Imgproc.line(mat, new Point(cell.getCenterPoint().x - 40, cell.getCenterPoint().y + 45),
                            new Point(cell.getCenterPoint().x + 40, cell.getCenterPoint().y - 45),
                            crossColor, crossThickness);

                    Imgproc.line(mat, new Point(cell.getCenterPoint().x - 40, cell.getCenterPoint().y - 45),
                            new Point(cell.getCenterPoint().x + 40, cell.getCenterPoint().y + 45),
                            crossColor, crossThickness);
                }

                if (cell.getMark() != Mark.Nought) {
                    cell = cell2;
                    cell.setPainted();
                    cell.setMark(Mark.Cross);
                    cellService.cellArray(cell.getRow(), cell.getColumn(), cell.getMark());
                }
            }
        }
    }

    //TODO refactor spaghetti
    public void paintLine() {
        List<Cell> list = cellService.getListOfCells();

        if (!CellService.isGameOver()) {

            if (CellService.winPositions(0, 0, list.get(0).getMark())) {
                if (list.get(1).getMark() == list.get(0).getMark() && list.get(0).getMark() == list.get(2).getMark()) {
                    startPoint = list.get(0).getCenterPoint();
                    endPoint = list.get(2).getCenterPoint();
                }
                if (list.get(0).getMark() == list.get(3).getMark() && list.get(0).getMark() == list.get(6).getMark()) {
                    startPoint = list.get(0).getCenterPoint();
                    endPoint = list.get(6).getCenterPoint();
                }
            }
            if (CellService.winPositions(0, 1, list.get(1).getMark())) {
                startPoint = list.get(1).getCenterPoint();
                endPoint = list.get(7).getCenterPoint();

            }
            if (CellService.winPositions(0, 2, list.get(2).getMark())) {
                startPoint = list.get(2).getCenterPoint();
                endPoint = list.get(8).getCenterPoint();
            }
            if (CellService.winPositions(1, 0, list.get(3).getMark())) {
                startPoint = list.get(3).getCenterPoint();
                endPoint = list.get(5).getCenterPoint();
            }
            if (CellService.winPositions(2, 0, list.get(6).getMark())) {
                startPoint = list.get(6).getCenterPoint();
                endPoint = list.get(9).getCenterPoint();
            }
            if (CellService.winPositions(1, 1, list.get(6).getMark()) && list.get(6).getMark() == list.get(2).getMark()) {
                startPoint = list.get(6).getCenterPoint();
                endPoint = list.get(2).getCenterPoint();
            }
            if (CellService.winPositions(1, 1, list.get(8).getMark()) && list.get(8).getMark() == list.get(0).getMark()) {
                startPoint = list.get(8).getCenterPoint();
                endPoint = list.get(0).getCenterPoint();

            }
        } else {
            paintEndGameLine();
        }

    }

    private void paintEndGameLine() {
        Imgproc.line(mat, startPoint, endPoint, new Scalar(255, 255, 255), 11);
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

    public CellService getCellService() {
        return cellService;
    }


}

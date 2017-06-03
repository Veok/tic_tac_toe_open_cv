package tictactoe.service.paint;

import tictactoe.model.Cell;
import tictactoe.service.GameService;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.List;

import static tictactoe.service.coordinates.WinLineCoordinate.winPositionsToPaint;

/**
 * @author Lelental on 05.05.2017.
 */
public class WinLine implements ICanBePainted {

    private static Point startPoint;
    private static Point endPoint;
    private List<Cell> list;
    private Mat mat;
    private boolean canPaint = false;

    WinLine(List<Cell> list, Mat mat) {
        this.list = list;
        this.mat = mat;
    }

    @Override
    public void paint() {

        if (!GameService.isGameOver() && !canPaint) {

            if (winPositionsToPaint(0, 0, list.get(0).getMark())) {

                if (list.get(1).getMark() == list.get(0).getMark()
                        && list.get(0).getMark() == list.get(2).getMark()) {
                    indicatePoints(0, 2);
                }

                if (list.get(0).getMark() == list.get(3).getMark()
                        && list.get(0).getMark() == list.get(6).getMark()) {
                    indicatePoints(0, 6);
                }

            } else if (winPositionsToPaint(0, 1, list.get(1).getMark())) {
                indicatePoints(1, 7);

            } else if (winPositionsToPaint(0, 2, list.get(2).getMark())) {
                indicatePoints(2, 8);

            } else if (winPositionsToPaint(1, 0, list.get(3).getMark())) {
                indicatePoints(3, 5);

            } else if (winPositionsToPaint(2, 0, list.get(6).getMark())) {
                indicatePoints(6, 8);

            } else if (winPositionsToPaint(1, 1, list.get(6).getMark())
                    && list.get(2).getMark() == list.get(6).getMark()) {
                indicatePoints(6, 2);

            } else if (winPositionsToPaint(1, 1, list.get(8).getMark())
                    && list.get(8).getMark() == list.get(0).getMark()) {
                indicatePoints(8, 0);
            }
        } else {
            paintEndGameLine();
        }
    }

    private void paintEndGameLine() {
        Imgproc.line(mat, startPoint, endPoint,
                new Scalar(150,255,186), 8);
    }

    private void indicatePoints(int beginCell, int endCell) {
        startPoint = list.get(beginCell).getCenterPoint();
        endPoint = list.get(endCell).getCenterPoint();
        canPaint = true;
    }

    void erase() {
        startPoint = null;
        endPoint = null;
        canPaint = false;
    }

}
